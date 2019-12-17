package randomized;

import java.util.HashMap;
import java.util.Map;

public class Node {
	
	private String id;
	private Map<Integer, Edge> edges;
	
	Node( String id ) {
		this.id = id;
		edges = new HashMap<Integer, Edge>();
	}
	
	public String id() {
		return this.id;
	}
	
	public Map<Integer, Edge> edges() {
		return this.edges;
	}
	
	public void add_edge(Edge e) {
		this.edges.put(e.id(), e);
	}
	
	public void remove_edge(Edge e) {
		this.edges.remove( e.id() );
	}
	
}
