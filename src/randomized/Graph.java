package randomized;

import java.util.HashMap;
import java.util.Map;

public class Graph {
	
	private Map<Integer, Edge> edges;
	private Map<String, Node> vertices;
	
	Graph() {
		edges = new HashMap<Integer, Edge>();
		vertices = new HashMap<String, Node>();
	}
	
	public void add_edge(Edge e) {
		edges.put(e.id(), e);
	}
	
	public void add_vertex(Node v) {
		vertices.put(v.id(), v);
	}

	public void remove_edge(Edge e) {
		edges.remove( e.id() );
	}
	
	public void remove_vertex(Node v) {
		vertices.remove( v.id() );
	}
	
	public Integer number_of_edges() {
		return edges.keySet().size();
	}
	
	public Integer number_of_vertices() {
		return vertices.keySet().size();
	}
	
	public Map<Integer, Edge> edges() {
		return this.edges;
	}
	
	public Map<String, Node> nodes() {
		return this.vertices;
	}
}
