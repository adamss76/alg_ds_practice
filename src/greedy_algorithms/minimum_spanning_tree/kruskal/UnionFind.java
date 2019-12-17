package greedy_algorithms.minimum_spanning_tree.kruskal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
	private Map<Integer, UFCluster> clusters;
	private Map<Integer, UFNode> all_vertices;
	
	public UnionFind( List<Integer> vertices ) {
		clusters = new HashMap<Integer, UFCluster>();
		all_vertices = new HashMap<Integer, UFNode>();
		for ( Integer v : vertices ) {
			UFCluster cluster = new UFCluster( v );
			UFNode node = new UFNode( v, cluster );
			all_vertices.put(node.id(), node);
			cluster.add_element(node);
			clusters.put(cluster.id(), cluster);
		}
	}
	
	public Integer number_of_clusters() {
		return clusters.size();
	}
	
	public Integer find(Integer v) {
		UFNode x = all_vertices.get(v);
		return x.root().id();
	}
	
	public void union(Integer v1, Integer v2) {
		UFNode x = all_vertices.get(v1);
		UFNode y = all_vertices.get(v2);
		
		UFCluster x_cluster = x.cluster();
		UFCluster y_cluster = y.cluster();
	
		if ( x.cluster().size() >= y.cluster().size() ) {
			//merge y into x
			for (UFNode node : y_cluster.elements().values() ) {
				node.set_root( x.root() );
				node.set_cluster( x_cluster );
				x_cluster.add_element( node );				
			}
			
			clusters.remove( y_cluster.id() );
		} else {
			//merge x into y
			for (UFNode node : x_cluster.elements().values() ) {
				node.set_root( y.root() );
				node.set_cluster( y_cluster );
				y_cluster.add_element( node );
			}
			
			clusters.remove( x_cluster.id() );
		}
	}
}
