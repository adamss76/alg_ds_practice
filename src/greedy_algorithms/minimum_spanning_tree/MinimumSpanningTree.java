package greedy_algorithms.minimum_spanning_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTree {
	private Integer weight;
	private List<MSTEdge> edges;
	private List<MSTNode> vertices;
	
	public MinimumSpanningTree() {
		weight = null;
		edges = new ArrayList<MSTEdge>();
		vertices = new ArrayList<MSTNode>();
	}
	
	public void add_edge(MSTEdge e) {
		edges.add(e);
		
		weight = (weight == null) ? e.length() : weight + e.length();
	}
	
	public void add_vertex(MSTNode node) {
		vertices.add( node );
	}
	
	public List<MSTNode> nodes_in_mst() {
		ArrayList<MSTNode> copy = new ArrayList<MSTNode>();
		Collections.copy(copy, vertices);
		
		return copy;
	}
	
	public Integer mst_weight() {
		return new Integer(weight);
	}
}
