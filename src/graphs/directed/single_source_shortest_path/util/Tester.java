package graphs.directed.single_source_shortest_path.util;

import java.io.File;
import java.util.Map;

import graphs.directed.single_source_shortest_path.Edge;
import graphs.directed.single_source_shortest_path.Node;
import graphs.directed.single_source_shortest_path.ShortestPathLength;

public class Tester {

	public static void main( String[] args ) throws Exception {
		Map< Integer, Node > graph = WeightedGraphBuilder.build_weighted_graph_from_file(new File("/Users/adamss/git_projects/practice/src/graphs/directed/single_source_shortest_path/dijkstraData.txt"));
		
		Map<Integer, Integer> shortest_path_lengths = ShortestPathLength.find_shortest_path_from_node(1, graph);
		/*for (Integer node_id : graph.keySet() ) {
			Node node = graph.get(node_id);
			System.out.println("node " + node_id + " has the following edges:");
			for (Edge edge : node.edges() ) {
				System.out.println( edge.head().node_id() );
			}
		}*/
		for ( Integer node_id : shortest_path_lengths.keySet() ) {
			System.out.println("shortest path length for node " + node_id + " = " + shortest_path_lengths.get(node_id));
		}
	}
}
