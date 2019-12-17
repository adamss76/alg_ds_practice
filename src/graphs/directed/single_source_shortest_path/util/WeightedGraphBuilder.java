package graphs.directed.single_source_shortest_path.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import graphs.directed.single_source_shortest_path.Edge;
import graphs.directed.single_source_shortest_path.Node;

public class WeightedGraphBuilder {
	
	public static Map< Integer, Node > build_weighted_graph_from_file( File filename ) throws FileNotFoundException {
		Map< Integer, Node > graph = new HashMap< Integer, Node >();
		
		Scanner in = new Scanner( filename  );
		
		while( in.hasNextLine() ) {
			String[] line = in.nextLine().split("\t");
			//System.out.println("node = " + line[0] + " edges:");
			Node node = new Node( Integer.parseInt( line[0] ) );
			for (int i = 1; i < line.length; i++) {
				String[] edge_and_weight = line[i].split(",");
				Integer edge_node_id = Integer.parseInt( edge_and_weight[0]);
				Node head;
				
				if ( graph.containsKey( edge_node_id ) ) {
					head = graph.get( edge_node_id );
				} else {
					head = new Node( edge_node_id );
					graph.put(edge_node_id, head);
				}
				
				Integer edge_length = Integer.parseInt( edge_and_weight[1] );
				Edge new_edge = new Edge( head.node_id(), edge_length );
				node.add_edge(new_edge);
				//System.out.println("edge id = " + new_edge.head().node_id() + " length = " + new_edge.length());	
			}
			//System.out.println();
			graph.put(node.node_id(), node);
		}
		
		return graph;
	}

}
