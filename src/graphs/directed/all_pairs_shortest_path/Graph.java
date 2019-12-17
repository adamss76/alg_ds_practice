package graphs.directed.all_pairs_shortest_path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private Map<Integer, Node> nodes;
	private List<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<Integer, Node>();
		edges = new ArrayList<Edge>();
	}
	
	public Graph(Map<Integer, Node> nodes, List<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public void add_node(Node v) {
		nodes.put(v.id(), v);
	}
	
	public void add_edge(Edge e) {
		edges.add(e);
	}
	
	public Map<Integer, Node> nodes() {
		return nodes;
	}
	
	public List<Edge> edges() {
		return edges;
	}
}
