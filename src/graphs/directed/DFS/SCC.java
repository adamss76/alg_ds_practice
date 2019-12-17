package graphs.directed.DFS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SCC {
	
	private static Integer current_finishing_time;
	private static Node current_leader;
	
	public static int[] find_top_five_strongly_connected_components(Map<Integer, Node> graph) {
		current_finishing_time = 0;
		current_leader = null;
		Map<Integer, Node> graph_by_finishing_time = new TreeMap<Integer, Node>();
		Map<Integer, List<Node>> strongly_connected_components = new HashMap<Integer, List<Node>>();
		//1. apply DFS on graph with nodes arranged in ascending order by node id
		for ( Integer node_id : graph.keySet() ) {
			Node node = graph.get(node_id);
			if ( !node.explored() ) {
				depth_first_search_compute_finishing_times(node, graph_by_finishing_time);
			}
		}
		
		// clear each node's explored field before computing leaders
		for ( Integer finishing_time : graph_by_finishing_time.keySet() ) {
			Node node = graph_by_finishing_time.get( finishing_time );
			node.setUnexplored();
		}
		
		//2. apply DFS to each node in graph in descending order of finishing time
		Set<Integer> finishing_times_asc = graph_by_finishing_time.keySet();
		for (int finishing_time = finishing_times_asc.size(); finishing_time > 0; finishing_time-- ) {
			Node node = graph_by_finishing_time.get( finishing_time );
			if ( !node.explored() ) {
				current_leader = node;
				node.setLeader(current_leader);
				List<Node> temp = new ArrayList<Node>();
				temp.add(node);
				strongly_connected_components.put(current_leader.node_id(), temp);
				depth_first_search_compute_leaders(node, strongly_connected_components);
			}
		}
		
		//compute the five largest strongly connected components
		List<List<Node>> largest_five_sccs = largest_five_sccs( strongly_connected_components );
		int[] size_of_five_largest_sccs = new int[5];
		int i = 0;
		for ( List<Node> scc : largest_five_sccs) {
			System.out.println("scc " + i + " size = " + scc.size() );
			size_of_five_largest_sccs[i] = scc.size();
			i++;
		}
		return size_of_five_largest_sccs;
	}
	
	public static Map< Integer, List< Node > > find_strongly_connected_componenets_components( Map< Integer, Node > graph ) {
		current_finishing_time = 0;
		current_leader = null;
		Map<Integer, Node> graph_by_finishing_time = new TreeMap<Integer, Node>();
		Map<Integer, List<Node>> strongly_connected_components = new HashMap<Integer, List<Node>>();
		//1. apply DFS on graph with nodes arranged in ascending order by node id
		for ( Integer node_id : graph.keySet() ) {
			Node node = graph.get(node_id);
			if ( !node.explored() ) {
				depth_first_search_compute_finishing_times(node, graph_by_finishing_time);
			}
		}
		
		// clear each node's explored field before computing leaders
		for ( Integer finishing_time : graph_by_finishing_time.keySet() ) {
			Node node = graph_by_finishing_time.get( finishing_time );
			node.setUnexplored();
		}
		
		//2. apply DFS to each node in graph in descending order of finishing time
		Set<Integer> finishing_times_asc = graph_by_finishing_time.keySet();
		for (int finishing_time = finishing_times_asc.size(); finishing_time > 0; finishing_time-- ) {
			Node node = graph_by_finishing_time.get( finishing_time );
			if ( !node.explored() ) {
				current_leader = node;
				node.setLeader(current_leader);
				List<Node> temp = new ArrayList<Node>();
				temp.add(node);
				strongly_connected_components.put(current_leader.node_id(), temp);
				depth_first_search_compute_leaders(node, strongly_connected_components);
			}
		}
		
		return strongly_connected_components;
	}
	
	private static void depth_first_search_compute_finishing_times(Node i, Map<Integer, Node> graph_by_finishing_time) {
		i.setExplored();
		for (Node edge : i.get_reverse_edges() ) {
			if ( !edge.explored() ) {
				depth_first_search_compute_finishing_times( edge, graph_by_finishing_time );
			}		
		}
		 current_finishing_time++;
		 i.setFinishingTime( current_finishing_time );
		 //System.out.println("node " + i.node_id() + " finishing time: " + i.finishing_time() );
		 graph_by_finishing_time.put(current_finishing_time, i);
	}
	
	private static void depth_first_search_compute_leaders(Node i, Map<Integer, List<Node>> strongly_connected_components) {
		i.setExplored();
		for (Node edge :i.get_forward_edges() ) {
			if ( !edge.explored() ) {
				edge.setLeader(current_leader);
				List<Node> this_nodes_sccs = strongly_connected_components.containsKey( i.leader().node_id() ) ? strongly_connected_components.get( i.leader().node_id() ) : new ArrayList<Node>();
				this_nodes_sccs.add(edge);
				strongly_connected_components.put(i.leader().node_id(), this_nodes_sccs);
				
				depth_first_search_compute_leaders( edge, strongly_connected_components );
			}			
		}
	}
	
	private static List<List<Node>> largest_five_sccs(Map<Integer, List<Node>> strongly_connected_components ) {
		List<List<Node>> sccs_desc = new ArrayList<List<Node>>();
		
		Set<List<Node>> sccs_ordered_by_size = new TreeSet<List<Node>>(new Comparator<List<Node>>() {
			public int compare(List<Node> o1, List<Node> o2) {
				if ( o1.size() > o2.size() ) {
					return -1;
				} else  {
					return 1;
				}
			}
		});
		
		for ( Integer scc_id : strongly_connected_components.keySet() ) {
			List<Node> scc = strongly_connected_components.get(scc_id);
			sccs_ordered_by_size.add( scc );
		}
		
		sccs_desc.addAll( sccs_ordered_by_size );
		sccs_desc.subList(0, sccs_desc.size() >= 5 ? 5 : sccs_desc.size() );
		
		return sccs_desc.subList(0, sccs_desc.size() >= 5 ? 5 : sccs_desc.size() );
	}
}
