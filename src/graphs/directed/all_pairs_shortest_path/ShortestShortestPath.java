package graphs.directed.all_pairs_shortest_path;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ShortestShortestPath {
	
	private static Double global_shortest_path = Double.POSITIVE_INFINITY;
	
	public static Double find_shortest_shortest_path(File input_graph) throws FileNotFoundException {
		Graph g = create_graph_from_input_file( input_graph );
		Graph g_transform = create_johnsons_transformed_graph( g );
		Double[] p_values = calculate_weighted_costs( g_transform );
		//debug
		System.out.println("bellman adjustment values:");
		int index = 0;
		for (Double value : p_values ) {
			System.out.println("node id: " + index);
			System.out.println("p value: " + value );
			System.out.println();
			index++;
		}
		//end debug
		
		//negative cycle detected
		if (p_values == null) {
			return null;
		}
		
		for ( Edge e : g.edges() ) {
			Double p_head = p_values[ e.head().id() ];
			Double p_tail = p_values[ e.tail().id() ];
			
			e.head().set_bellman_adjustment( p_head );
			e.tail().set_bellman_adjustment( p_tail );
			
			Double weighted_cost = e.cost() + p_head - p_tail;			
			e.set_adjusted_cost(weighted_cost);
			
			//debug
			System.out.println("edge head id: " + e.head().id() + " tail id: " + e.tail().id() );
			System.out.println("unweighted edge cost = " + e.cost() );
			System.out.println("weighted cost equations: " + e.cost() + " + " + e.head().bellman_adjustment() + " - " + e.tail().bellman_adjustment() );
			System.out.println("weighted cost: " + e.adjusted_cost() );
			System.out.println();
			
		}
		for (Node node : g.nodes().values() ) {
			find_shortest_path( node, g );
			reset_shortest_path_values(g);
		}
		
		return global_shortest_path;
	}
	
	private static void reset_shortest_path_values( Graph g ) {
		for (Node v : g.nodes().values() ) {
			v.reset_shortest_path_length();
			v.set_selected_edge(null);
		}
	}
	
	/**
	 * Uses Dijkstra's single source shortest path to calculate the shortest path values from the source node to every other node in the graph.
	 * @param source
	 * @param g
	 */
	private static void find_shortest_path(Node source, Graph g ) {
		PriorityQueue<Node> h = new PriorityQueue<Node>(Comparator.comparing(Node :: shortest_path_length));
		source.set_shortest_path_length(0.0);
		
		//initialize heap
		for (Node v : g.nodes().values() ) {
			if ( v != source ) {
				h.add( v );
			}
		}
		
		//recalculate shortest path for source vertex's incident edge nodes
		for (Edge e : source.outgoing_edges() ) {
			Node tail = e.tail();
			h.remove(tail);
			Double new_cost = e.head().shortest_path_length() + e.adjusted_cost();
			tail.set_shortest_path_length( new_cost );
			tail.set_selected_edge( e );
			h.add( tail );
		}
		
		while ( !h.isEmpty() ) {
			Node v = h.remove();
			Double unweighted_length = v.shortest_path_length() + v.selected_edge().tail().bellman_adjustment() - v.selected_edge().head().bellman_adjustment();
			if ( unweighted_length < global_shortest_path ) {
				//debug
				System.out.println("found a new min");
				System.out.println("node id: " + v.id() );
				System.out.println("new min: " + unweighted_length);
				System.out.println("weighted length: " + v.shortest_path_length() );
				System.out.println("bellman adjustemtnt:");
				System.out.println("tail id: " + v.selected_edge().tail().id() );
				System.out.println("head id: " + v.selected_edge().head().id() );
				System.out.println("+ " + v.selected_edge().tail().bellman_adjustment() );
				System.out.println(" - " + v.selected_edge().head().bellman_adjustment());
				System.out.println();
				//end debug
				global_shortest_path = unweighted_length;
			}
			
			//recalutate path length for v's outgoing edges
			for (Edge e : v.outgoing_edges() ) {
				Node tail = e.tail();
				if ( h.contains( tail ) ) {
					h.remove( tail );
					Double new_cost = v.shortest_path_length() + e.adjusted_cost();					
					tail.set_shortest_path_length( Math.min(tail.shortest_path_length(), new_cost) );
					tail.set_selected_edge( e );
					h.add( tail );
				}
				
			}
			//v.reset_shortest_path_length();
		}
		//source.reset_shortest_path_length();
	}
	
	private static Graph create_graph_from_input_file(File input_graph) throws FileNotFoundException {
		Scanner in = new Scanner(input_graph);
		in.nextLine(); //skip first line of file
		
		Map<Integer, Node> nodes = new HashMap<Integer, Node>();
		List<Edge> edges = new ArrayList<Edge>();
		
		while( in.hasNextLine() ) {
			String[] line = in.nextLine().split(" " );
			Integer head_id = Integer.parseInt( line[0] );
			Integer tail_id = Integer.parseInt( line[1] );
			Double cost = Double.parseDouble( line[2] );
			
			Node head = nodes.containsKey( head_id ) ? nodes.get( head_id ) : new Node(head_id);
			Node tail = nodes.containsKey( tail_id ) ? nodes.get( tail_id ) : new Node(tail_id);
			Edge e = new Edge(head, tail, cost);
			
			head.add_outgoing_edge(e);
			tail.add_incoming_edge(e);
			
			edges.add(e);
			
			if ( !nodes.containsKey( head_id ) ) {
				nodes.put(head_id, head);
			}
			
			if ( !nodes.containsKey( tail_id ) ) {
				nodes.put(tail_id, tail);
			}
			
		}
		
		return new Graph(nodes, edges);
	}
	
	private static Graph create_johnsons_transformed_graph(Graph original ) {
		Node source = new Node(0);
		List<Edge> transformed_edges = new ArrayList<Edge>(original.edges());
		Map<Integer, Node> transformed_vertices = new HashMap<Integer, Node>(original.nodes());
		
		for (Integer node_id : original.nodes().keySet() ) {
			Node tail = transformed_vertices.get(node_id);
			Edge zero_edge = new Edge(source, tail, 0.0);
			
			source.add_outgoing_edge(zero_edge);
			tail.add_incoming_edge(zero_edge);
			
			transformed_edges.add(zero_edge);
		}
		
		transformed_vertices.put(source.id(), source);
		
		Graph johnsons_transformed_graph = new Graph(transformed_vertices, transformed_edges);
		
		return johnsons_transformed_graph;
	}
	
	/**
	 * Bellman Ford algorithm to calculate weight adjustments in Johnson's all pairs shortest path algorithm
	 * @param g_prime
	 * @return mapping of all vertice's bellman for min path length value with a starting node s.id() = 0
	 * if a negative cycle is encountered this algorithm returns a null value
	 */
	private static Double[] calculate_weighted_costs(Graph g_prime) {
		Integer number_of_vertices = g_prime.nodes().keySet().size();
		Integer n_minus_1 = number_of_vertices - 2;
		
		Double[][] a = new Double[ number_of_vertices ][ number_of_vertices ];
		
		//base case: shortest path from s to s with zero edges
		a[0][0] = 0.0;
		
		//base case: shortest path from s to all other vertices with zero edges
		for (int i = 1; i < number_of_vertices; i++ ) {
			a[0][i] = Double.POSITIVE_INFINITY;
		}
		
		//main loop
		for (int number_of_edges = 1; number_of_edges < number_of_vertices; number_of_edges ++ ) {
			for (Integer node_id : g_prime.nodes().keySet() ) {
				
				Double old_min_length = a[ number_of_edges - 1 ][ node_id ];
				Double min_incoming_edge_path_length = Double.POSITIVE_INFINITY;
				List<Edge> incoming_edges = g_prime.nodes().get( node_id ).incoming();
				
				for (Edge e : incoming_edges ) {
					if ( !a[number_of_edges - 1][ e.head().id() ].equals( Double.POSITIVE_INFINITY ) ) {
						Double new_path_length = a[number_of_edges - 1][ e.head().id() ] + e.cost();
						//select minimum path length using incoming edges
						if ( new_path_length < min_incoming_edge_path_length ) {
							min_incoming_edge_path_length = new_path_length;
						}
					}
										
				}
				
				a[ number_of_edges ][ node_id ] = Math.min(old_min_length, min_incoming_edge_path_length);
				
				//check for negative cycles. return null if negative cycle is found
				if ( number_of_edges == number_of_vertices - 1 ) {
					if ( !a[ n_minus_1][ node_id ].equals( a[ number_of_edges ][ node_id ] ) ) {
						//debug
						System.out.println("node_id = " + node_id);
						System.out.println("a[ n_minus_1 ][ node_id ] = " + a[ n_minus_1 ][ node_id ]);
						System.out.println("a[ number_of_edges ][ node_id ] = " + a[ number_of_edges ][ node_id ]);
						//end debug
						//negative cost cycle found. return null
						return null;
					}
				}
				
			}
		}
		
		return a[ n_minus_1 ];
	}
	
	public static void main(String[] args) throws Exception {
		find_shortest_shortest_path(new File ("/Users/adamss/git_projects/practice/src/graphs/directed/all_pairs_shortest_path/g3.txt") );
		
		System.out.println(global_shortest_path);
	}
}
