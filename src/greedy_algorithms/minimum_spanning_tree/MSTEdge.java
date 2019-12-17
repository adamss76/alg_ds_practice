package greedy_algorithms.minimum_spanning_tree;

import java.util.ArrayList;
import java.util.List;

public class MSTEdge {
	private Integer id;
	private Integer length;
	private MSTNode v_1;
	private MSTNode v_2;
	
	public MSTEdge(Integer id, Integer length, MSTNode v_1, MSTNode v2) {
		this.id = id;
		this.length = length;
		this.v_1 = v_1;
		this.v_2 = v2;
	}
	
	public Integer id() {
		return this.id;
	}
	
	public Integer length() {
		return this.length;
	}
	
	public MSTNode v1() {
		return this.v_1;
	}
	
	public MSTNode v2() {
		return this.v_2;
	}
	
	public List<MSTNode> vertices() {
		List<MSTNode> vertices = new ArrayList<MSTNode>();
		vertices.add(v_1);
		vertices.add(v_2);
		
		return vertices;
	}
	
	public MSTNode get_other_vertex( MSTNode head ) {
		if ( head.id().equals(v_1.id()) ) {
			return v_2;
		} else {
			return v_1;
		}
	}
}
