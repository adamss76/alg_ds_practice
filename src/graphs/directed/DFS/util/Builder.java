package graphs.directed.DFS.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import graphs.directed.DFS.Node;

public class Builder {

	public static Map<Integer, Node> construct_graph_from_file(File filename) throws FileNotFoundException {
		Scanner in = new Scanner(filename);
		Map<Integer, Node > graph = new TreeMap<Integer, Node>();
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			Integer node_id = Integer.valueOf( line[0] );
			Integer outgoing_edge = Integer.valueOf( line[1] );
			
			Node node;
			Node forward_edge;
			
			if ( graph.containsKey( node_id ) ) {
				node = graph.get( node_id );
			} else {
				node = new Node( node_id );
				graph.put( node_id, node );
			}
			
			if ( graph.containsKey( outgoing_edge ) ) {
				forward_edge = graph.get( outgoing_edge );
			} else {
				forward_edge = new Node( outgoing_edge );
				graph.put( outgoing_edge , forward_edge );
			}
			
			node.add_forward_edge( forward_edge );
			
			//add_reverse_edge
			forward_edge.add_reverse_edge( node );
		}
		
		in.close();
		
		return graph;
	}
	
}
