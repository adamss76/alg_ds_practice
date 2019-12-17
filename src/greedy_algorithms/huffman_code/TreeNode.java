package greedy_algorithms.huffman_code;

public class TreeNode {
	private TreeNode parent;
	private TreeNode left;
	private TreeNode right;
	private Integer weight;
	private String encoding;
	String label;
	
	public TreeNode(TreeNode parent, TreeNode left_child, TreeNode right_child, Integer weight) {
		this.parent = parent;
		this.left = left_child;
		this.right = right_child;
		this.weight = weight;
		this.encoding = "";
		this.label = weight.toString();
	}
	
	public TreeNode left() {
		return this.left;
	}
	
	public TreeNode right() {
		return this.right;
	}
	
	public Integer weight() {
		return this.weight;
	}
	
	public String encoding() {
		return this.encoding;
	}
	
	public String label() {
		return this.label;
	}
	
	public void set_encoding(String s) {
		this.encoding = s;
	}
	
	public void set_parent(TreeNode new_parent) {
		this.parent = new_parent;
	}
}
