package greedy_algorithms.minimum_spanning_tree.kruskal;

import java.util.ArrayList;
import java.util.List;

public class Edge {
	private Integer v1;
	private Integer v2;
	private Integer length;
	
	public Edge (Integer v1, Integer v2, Integer length) {
		this.v1 = v1;
		this.v2 = v2;
		this.length = length;
	}
	
	
	public List<Integer> endpoints() {
		List<Integer> vertices = new ArrayList<Integer>();
		vertices.add(v1);
		vertices.add(v2);
		
		return vertices;
	}
	
	public Integer length() {
		return this.length;
	}
}
