package greedy_algorithms.minimum_spanning_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MSTNode {
	
	private Integer id;
	private List<MSTEdge> edges;
	private MSTEdge selected_edge;
	private Integer key;
	
	public MSTNode(Integer id) {
		this.id = id;
		this.edges = new ArrayList<MSTEdge>();
		selected_edge = null;
		key = Integer.MAX_VALUE;
	}
	
	public Integer id() {
		return this.id;
	}
	public void add_edge(MSTEdge e) {
		edges.add(e);
	}
	
	public List<MSTEdge> edges() {
		return new ArrayList<MSTEdge>( this.edges );
	}
	
	public Integer min_edge_length() {
		return this.key;
	}
	
	public void set_min_edge_length(Integer new_key) {
		this.key = new_key;
	}
	
	public void set_selected_edge(MSTEdge e) {
		selected_edge = e;
	}
	
	public MSTEdge selected_edge() {
		return this.selected_edge;
	}
}
