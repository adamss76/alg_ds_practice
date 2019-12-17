package graphs.directed.DFS;

import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
	
	private Integer node_id;
	private Set<Node> forward_edges;
	private Set<Node> reverse_edges;
	private Integer finishing_time;
	private boolean explored;
	private Node leader;
	
	public Node(Integer nodeId) {
		forward_edges = new HashSet<Node>();
		reverse_edges = new HashSet<Node>();
		this.node_id = nodeId;
		this.finishing_time = 0;
		this.explored = false;
		this.leader = null;
	}
	
	public void add_forward_edge(Node forward_edge ) {
		forward_edges.add(forward_edge);
	}
	
	public void add_reverse_edge(Node reverse_edge) {
		this.reverse_edges.add(reverse_edge);
	}
	
	public Set<Node> get_forward_edges() {
		return forward_edges;
	}
	
	public Set<Node> get_reverse_edges() {
		return reverse_edges;
	}

	@Override
	public int compareTo(Node o) {	
		return this.node_id.compareTo(o.node_id);
	}

	public Integer node_id() {
		return this.node_id;
	}
	
	public Integer finishing_time() {
		return this.finishing_time;
	}
	
	public void setFinishingTime( Integer new_finishing_time ) {
		this.finishing_time = new_finishing_time;
	}
	
	public boolean explored() {
		return this.explored;
	}
	
	public void setExplored() {
		this.explored = true;
	}
	
	public void setUnexplored() {
		this.explored = false;
	}
	
	public Node leader() {
		return this.leader;
	}
	
	public void setLeader( Node new_leader ) {
		this.leader = new_leader;
	}
}
