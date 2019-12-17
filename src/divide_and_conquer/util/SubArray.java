package divide_and_conquer.util;

public class SubArray {
	
	private int index_in_source_array;
	private int total;
	private int[] sub_array;
	private int[] source;
	
	public SubArray(int[] original_array, int[] sub, int index){
		this.sub_array = sub;
		this.source = original_array;
		this.total = calculate_total(this.sub_array);
		this.index_in_source_array = index;
	}
	
	public SubArray(int[] original) {
		this.source = original;
	}
	
	private int calculate_total(int[] a) {
		
		int total = Integer.MIN_VALUE;
		
		for (int value : a) {
			total = total == Integer.MIN_VALUE ? value : total + value;
		}
		
		return total;
	}
	
	public int[] subArray() {
		return sub_array;
	}
	
	public int[] originalArray() {
		return source;
	}
	
	public int total() {
		return this.total;
	}
	
	public int index() {
		return index_in_source_array;
	}
	
	public void setIndex(int index){
		this.index_in_source_array = index;
	}
}
