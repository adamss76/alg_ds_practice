package graphs.directed.all_pairs_shortest_path;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Edge> incoming;
	private List<Edge> outgoing;
	private Integer id;
	private Double	shortest_path_length;
	private Double bellman_adjustment;
	private Edge selected_edge;
	
	public Node(Integer id) {
		this.id = id;
		incoming = new ArrayList<Edge>();
		outgoing = new ArrayList<Edge>();
		shortest_path_length = Double.POSITIVE_INFINITY;
		bellman_adjustment = null;
		selected_edge = null;
	}

	public void set_shortest_path_length(Double value) {
		this.shortest_path_length = value;
	}
	
	public void set_bellman_adjustment(Double p) {
		this.bellman_adjustment = p;
	}
	
	public void add_outgoing_edge(Edge e) {
		this.outgoing.add(e);
	}
	
	public void add_incoming_edge(Edge e) {
		this.incoming.add(e);
	}
	
	public void set_selected_edge (Edge e) {
		selected_edge = e;
	}
	
	public Integer id() {
		return this.id;
	}
	
	public Double shortest_path_length() {
		return this.shortest_path_length;
	}
	
	public Double bellman_adjustment() {
		return this.bellman_adjustment;
	}
	
	public List<Edge> outgoing_edges() {
		return this.outgoing;
	}
	
	public List<Edge> incoming() {
		return this.incoming;
	}
	
	public void reset_shortest_path_length() {
		this.shortest_path_length = Double.POSITIVE_INFINITY;
	}
	
	public Edge selected_edge() {
		return this.selected_edge;
	}
}
