package greedy_algorithms.minimum_spanning_tree.kruskal;

public class UFNode {
	private Integer id;
	private UFCluster cluster;
	private UFNode root;
	
	public UFNode( Integer v, UFCluster cluster ) {
		this.id = v;
		this.cluster = cluster;
		this.root = this;
	}
	
	public Integer id() {
		return this.id;
	}
	
	public UFNode root() {
		return this.root;
	}
	
	public UFCluster cluster() {
		return this.cluster;
	}
	
	public void set_root(UFNode new_root) {
		this.root = new_root;
	}
	
	public void set_cluster(UFCluster new_cluster) {
		this.cluster = new_cluster;
	}
}
