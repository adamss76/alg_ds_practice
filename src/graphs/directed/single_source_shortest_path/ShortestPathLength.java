package graphs.directed.single_source_shortest_path;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPathLength {
	
	/**
	 * 
	 * @param starting_node_id
	 * @return
	 */
	public static Map<Integer, Integer> find_shortest_path_from_node(Integer starting_node_id, Map<Integer, Node> weighted_graph ) {
		Map<Integer, Integer> shortest_path_lengths = new HashMap<Integer, Integer>();
		
		PriorityQueue<Node> Q = new PriorityQueue<Node>(weighted_graph.keySet().size(), new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if ( o1.shortest_path_length().equals(o2.shortest_path_length()) ) {
					return 0;
				} else if ( o1.shortest_path_length() > o2.shortest_path_length() ) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		//1. Initialization
		//1a. initialize heap with all nodes in graph
		for ( Integer node_id : weighted_graph.keySet() ) {
			Node node = weighted_graph.get( node_id );
			Q.add(weighted_graph.get( node_id ));
		}
		
		//1b. initialize starting node
		weighted_graph.get(starting_node_id).setShortestPathLength(0);
		shortest_path_lengths.put(starting_node_id, weighted_graph.get(starting_node_id).shortest_path_length());
		
		//1c. compute new dijkstra's greedy score for starting node's edges
		for (Edge edge : weighted_graph.get(starting_node_id).edges()) {
			Node edge_node = weighted_graph.get(edge.head_id());
			if ( Q.contains( edge_node )) {
				Q.remove( edge_node );
				//compute new score
				Integer old_score = edge_node.shortest_path_length();
				Integer new_score = weighted_graph.get( starting_node_id ).shortest_path_length() + edge.length();
				Integer best_score = Math.min(old_score, new_score);
				//add node to queue with new shortest path length
				edge_node.setShortestPathLength( best_score );				
				Q.add( edge_node );
			}
		}
		
		while ( !Q.isEmpty() ) {
			//2. select node with shortest path length and remove from queue
			Node selected = Q.poll();
			shortest_path_lengths.put(selected.node_id(), selected.shortest_path_length() );
			
			//3. recalculate dijkstra score for edges of the node that was just removed
			for ( Edge edge : selected.edges() ) {
				Node edge_head = weighted_graph.get(edge.head_id());
				if (Q.contains( edge_head )) {
					Q.remove( edge_head );
					//compute new score
					Integer old_score = edge_head.shortest_path_length();
					Integer new_score = selected.shortest_path_length() + edge.length();
					Integer best_score = Math.min(old_score, new_score);
					
					edge_head.setShortestPathLength( best_score );
					Q.add( edge_head );
				}
			}
		}
		
		return shortest_path_lengths;
	}

}
