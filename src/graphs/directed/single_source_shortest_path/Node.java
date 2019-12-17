package graphs.directed.single_source_shortest_path;

import java.util.ArrayList;
import java.util.List;

public final class Node {
	private final Integer node_id;
	private List<Edge> edges;
	private Integer shortest_path_length;
	
	public Node(Integer node_id) {
		this.node_id = node_id;
		edges = new ArrayList<Edge>();
		shortest_path_length = 100000;
	}
	
	public void add_edge(Edge m) {
		edges.add(m);
	}
	
	public List<Edge> edges() {
		return new ArrayList<Edge>(this.edges);
	}
	
	public Integer node_id() {
		return this.node_id;
	}
	
	public void setShortestPathLength(Integer length) {
		this.shortest_path_length = length;
	}
	
	public Integer shortest_path_length() {
		return this.shortest_path_length;
	}
	
	@Override
	public boolean equals(Object obj) {
		Node the_other_node = (Node) obj;
		if (this.node_id() == the_other_node.node_id() ) {
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		
		return this.node_id().hashCode();
	} 
}
