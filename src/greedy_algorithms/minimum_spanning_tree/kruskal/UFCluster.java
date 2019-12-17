package greedy_algorithms.minimum_spanning_tree.kruskal;

import java.util.HashMap;
import java.util.Map;

public class UFCluster {
	private Integer id;
	private Map<Integer, UFNode> elements;
	
	public UFCluster(Integer id) {
		this.id = id;
		elements = new HashMap<Integer, UFNode>();
	}
	
	public Map<Integer, UFNode> elements() {
		return this.elements;
	}
	
	public void add_element(UFNode node) {
		elements.put(node.id(), node );
	}
	
	public Integer size() {
		return elements.size();
	}
	
	public Integer id() {
		return this.id;
	}
}
