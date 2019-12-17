package randomized;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MinCut {
	
	public static Integer find_min_cut(Graph g) {
		
		while( g.number_of_vertices() > 2 ) {
			Edge e = find_edge_to_contract( g.edges() );
			Node v1 = e.head();
			Node v2 = e.tail();
			//1. create new contracted node
			Node v1_v2 = new Node( v1.id() + "_" + v2.id() );
			
			//2. delete e from v1 and v2
			v1.remove_edge(e);
			v2.remove_edge(e);
			
			//3. contract v1 and v2 
			//a. merge v1's and v2's edges
			//b. delete any resulting self loops
			List<Integer> edge_ids = new ArrayList<Integer>();
			edge_ids.addAll(v1.edges().keySet());
			for (Integer id : edge_ids ) {
				
				Edge edge_to_merge = v1.edges().get(id);
				
				//check for self loop
				if ( !edge_to_merge.head().equals( v2 ) && !edge_to_merge.tail().equals( v2 ) ) {
					
					//replace node v1 with new contracted node v1_v2
					if ( edge_to_merge.head().equals( v1 ) ) {
						edge_to_merge.set_head( v1_v2 );
						//add this edge to v1_v2 adjacency list
						v1_v2.add_edge(edge_to_merge);
					} else {
						edge_to_merge.set_tail(v1_v2);
						//add this edge to v1_v2 adjacency list
						v1_v2.add_edge(edge_to_merge);
					}
					
				} else {
					//this edge is a self loop and should be deleted
					v1.remove_edge(edge_to_merge);
					v2.remove_edge(edge_to_merge);
					g.remove_edge(edge_to_merge);
				}
			}
			
			for (Integer id: v2.edges().keySet() ) {
				//already deleted self loops so just need to merge this node's edges
				Edge edge_to_merge = v2.edges().get(id);
				
				if( edge_to_merge.head().equals(v2) ) {
					edge_to_merge.set_head( v1_v2 );
					v1_v2.add_edge(edge_to_merge);
				} else {
					edge_to_merge.set_tail(v1_v2);
					v1_v2.add_edge(edge_to_merge);
				}
			}
			
			//5. delete e from graph
			g.remove_edge(e);
			
			//6. delete v1 from graph
			g.remove_vertex(v1);
			
			//7. delete v2 from graph
			g.remove_vertex(v2);
			
			//8 add new super node to graph
			g.add_vertex(v1_v2);
		}
		
		return g.number_of_edges();
	}

	private static Edge find_edge_to_contract( Map<Integer, Edge> edges ) {
		List<Integer> edge_ids = new ArrayList<Integer>();
		edge_ids.addAll(edges.keySet());
		
		Random r = new Random();
		int index = r.nextInt( edge_ids.size() );
		Integer edge_id = edge_ids.get(index);
		
		return edges.get(edge_id);
	}
	
	public static Graph initialize_graph() throws Exception {
		Scanner in = new Scanner( new File("/Users/adamss/git_projects/practice/src/randomized/kargerMinCut.txt"));
		
		Map<String, Map<String, Node>> node_edge_map = new HashMap<String, Map<String, Node>>();
		Graph g = new Graph();
		int edge_id = 0;
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split("	");
			String node_id = line[0];
			
			//check if this node has already been created
			Node v = g.nodes().get(node_id);
			if ( v == null ) {
				v = new Node( node_id);
				Map<String, Node> adjacency_list = new HashMap<String, Node>();
				node_edge_map.put(node_id, adjacency_list);
				
				g.add_vertex(v);
			}
			
			//add nodes to adjacency list
			for (int i = 1; i < line.length; i++) {
				Node adj = g.nodes().get( line[i] );
				
				if  (adj == null ) {
					adj = new Node( line[i] );
					g.add_vertex(adj);
					
					//since this node is new, update node_edge_map
					Map<String, Node> outgoing_edges = new HashMap<String, Node>();
					node_edge_map.put(adj.id(), outgoing_edges);
				}
				
				//check if this edge has already been created
				if ( !node_edge_map.get(node_id).containsKey(adj.id()) ) {
					//1. create new edge
					//2. add edge to both node's adjacency lists
					//3. add edge to graph
					
					Edge e = new Edge(edge_id, v, adj);
					v.add_edge(e);
					adj.add_edge(e);
					g.add_edge(e);
					
					//housekeeping: increment counter and update node_edge map
					Map<String, Node> adjacency_list_1 = node_edge_map.get(v.id());
					Map<String, Node> adjacency_list_2 = node_edge_map.get(adj.id());
					adjacency_list_1.put(adj.id(), adj);
					adjacency_list_2.put(v.id(), v);
					
					edge_id ++;
				}
			}
		}
		
		return g;
	}
	
	public static void main(String[] args) throws Exception {
		
		Double min_cut = Double.POSITIVE_INFINITY;
		for (int i = 0; i < 10000; i ++) {
			Graph g = initialize_graph();
			Double this_cut = new Double ( find_min_cut( g ) );
			if ( min_cut.compareTo(this_cut) > 0 ) {
				min_cut = this_cut;
			}
		}
		System.out.println( min_cut.intValue() );
	}
}
