package graphs.directed.all_pairs_shortest_path;

public class Edge {
	private Node head;
	private Node tail;
	private Double cost;
	private Double adjusted_cost;
	
	public Edge(Node head, Node tail, Double cost) {
		this.head = head;
		this.tail = tail;
		this.cost = cost;
	}
	
	public void set_adjusted_cost(Double p) {
		this.adjusted_cost = p;
	}
	
	public Node tail() {
		return tail;
	}
	
	public Node head() {
		return head;
	}
	
	public Double cost() {
		return cost;
	}
	
	public Double adjusted_cost() {
		return adjusted_cost;
	}
}
