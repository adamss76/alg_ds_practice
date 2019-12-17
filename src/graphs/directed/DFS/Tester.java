package graphs.directed.DFS;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import graphs.directed.DFS.util.Builder;

public class Tester {

	public static void main(String[] args) throws Exception {
		Map<Integer, Node> graph = Builder.construct_graph_from_file( new File("/Users/adamss/git_projects/practice/src/graphs/directed/DFS/scc_test5.txt") );
		Iterator<Integer >itr = graph.keySet().iterator();
		/*while ( itr.hasNext() ) {
			Integer node_id = itr.next();
			Node node = graph.get( node_id );
			System.out.println("node id: " + node_id );
			System.out.println("forward edges: " );
			for (Node forward_edge :node.get_forward_edges() ) {
				System.out.println( forward_edge.node_id() );
			}
			System.out.println("reverse edges: ");
			for (Node reverse_edge : node.get_reverse_edges() ) {
				System.out.println( reverse_edge.node_id());
			}
			System.out.println();
			System.out.println();
		}*/
		int[] strongly_connected_components = new SCC().find_top_five_strongly_connected_components(graph);
		//System.out.println();
		System.out.println( strongly_connected_components[ 0 ] );
		System.out.println( strongly_connected_components[ 1 ] );
		System.out.println( strongly_connected_components[ 2 ] );
		System.out.println( strongly_connected_components[ 3 ] );
		System.out.println( strongly_connected_components[ 4 ] );
	}

}
