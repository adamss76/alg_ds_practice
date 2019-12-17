package graphs.directed.single_source_shortest_path;

public class Edge {

	private final Integer head;
	private final Integer length;
	
	public Edge(Integer head, Integer length ) {
		this.head = head;
		this.length = length;
		
	}
	
	public Integer head_id() {
		return this.head;
	}
	
	public Integer length() {
		return this.length;
	}
}
