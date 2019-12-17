package heaps.median_maintenance;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaintainMedian {
	
	//max heap containing the smallest half of all numbers added so far
	private static PriorityQueue<Integer> smallest_half;
	
	//min heap containing largest half of all numbers added so far
	private static PriorityQueue<Integer> largest_half;
	
	private static Integer size;
	
	private MaintainMedian() {
		size = 0;
		largest_half = new PriorityQueue<Integer>();
		smallest_half = new PriorityQueue<Integer>( new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -1 * o1.compareTo(o2);
			}
		});
	}
	
	public static void initialize() {
		new MaintainMedian();
	}
	
	public static Integer find_new_median( Integer new_value ) {
		Integer left = smallest_half.peek();
		Integer right = largest_half.peek();
		
		//add new value
		if (left == null) {
			smallest_half.add(new_value);
			size++;
		}
		else if ( new_value.compareTo( left ) <= 0 ) {
			smallest_half.add( new_value );
			size++;
		} else {
			largest_half.add( new_value );
			size++;
		} 
		
		//re-balance heaps
		Integer heap_size_diff = smallest_half.size() - largest_half.size();
		if ( Math.abs( heap_size_diff ) > 1 ) {
			if ( smallest_half.size() > largest_half.size() ) {
				Integer max_left_value = smallest_half.remove();
				largest_half.add( max_left_value );
			} else {
				Integer min_right_value = largest_half.remove();
				smallest_half.add( min_right_value );
			}
		}
		
		//calculate new median
		int median_index = (size % 2 == 0) ? size / 2 : ( size + 1) / 2;
		if ( smallest_half.size() >= median_index ) {
			return smallest_half.peek();
		} else {
			return largest_half.peek();
		}
	}
	
}
