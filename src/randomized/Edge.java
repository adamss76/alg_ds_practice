package randomized;

public class Edge {
		private Integer id;
		private Node head;
		private Node tail;
		
		Edge(Integer id, Node v1, Node v2 ) {
			this.id = id;
			this.head = v1;
			this.tail = v2;
		}
		
		public Integer id() {
			return this.id;
		}
		
		public Node head() {
			return this.head;
		}
		
		public Node tail() {
			return this.tail;
		}
		
		public void set_head(Node new_v1) {
			this.head = new_v1;
		}
		
		public void set_tail(Node new_v2) {
			this.tail = new_v2;
		}

}
