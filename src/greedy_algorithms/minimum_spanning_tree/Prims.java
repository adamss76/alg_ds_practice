package greedy_algorithms.minimum_spanning_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prims {
	
	public static MinimumSpanningTree find_mst(Map<Integer, MSTNode> vertices) {
		MinimumSpanningTree mst = new MinimumSpanningTree();
		Map<Integer, MSTNode> v_not_in_mst = new HashMap<Integer, MSTNode>(vertices);
		PriorityQueue<MSTNode> h = new PriorityQueue<MSTNode>(Comparator.comparing(MSTNode :: min_edge_length));
		
		//initialize heap with all veritices
		h.addAll( v_not_in_mst.values()	);
		
		while ( !h.isEmpty() ) {
			MSTNode node = h.remove();
			
			v_not_in_mst.remove( node.id() );
			mst.add_vertex(node);
			if ( node.selected_edge() != null )	{
				mst.add_edge( node.selected_edge() );
			}
			
			//recalculate keys
			for (MSTEdge e : node.edges() ) {
				MSTNode w = e.get_other_vertex( node );
				if ( v_not_in_mst.containsKey( w.id() )) {
					if (w.min_edge_length() > e.length() ) {
						h.remove( w );
						w.set_min_edge_length( e.length() );
						w.set_selected_edge( e );
						h.add( w );
					}
				}
			}
		} 
		
		return mst;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/greedy_algorithms/minimum_spanning_tree/graph.txt"));
		Map<Integer, MSTNode> nodes = new HashMap<Integer, MSTNode>();
		Map<Integer, MSTEdge> edges = new HashMap<Integer, MSTEdge>();
		in.nextLine();
		Integer edge_counter = 1;
		while ( in.hasNextLine() ) {
			String[] line = in.nextLine().split(" ");
			Integer node1_id = Integer.parseInt( line[0] );
			Integer node2_id = Integer.parseInt( line[1] );
			Integer edge_length = Integer.parseInt( line[2] );
			
			MSTNode node_1 = (nodes.containsKey( node1_id )) ? nodes.remove(node1_id) : new MSTNode(node1_id);
			MSTNode node_2 = (nodes.containsKey( node2_id )) ? nodes.remove(node2_id) : new MSTNode(node2_id);
			
			MSTEdge edge = new MSTEdge(edge_counter, edge_length, node_1, node_2);
			edge_counter++;
			
			node_1.add_edge(edge);
			node_2.add_edge(edge);
			
			nodes.put(node_1.id(), node_1);
			nodes.put(node2_id, node_2);
			edges.put(edge.id(), edge);
		}
		
		MinimumSpanningTree mst = find_mst(nodes);
		System.out.println("total = " + mst.mst_weight());
	}
}
