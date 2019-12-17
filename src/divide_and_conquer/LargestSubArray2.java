package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This implementation has a n log(n) asymptotic runtime
 * @author adamss
 *
 */
public class LargestSubArray2 {
	
	public int maxSubArray(int[] nums) {
		return IntStream.of( largest_sub_array(nums) ).parallel().sum();
	}
	
	private int[] largest_sub_array(int[] a) {
		
		//base case
		if (a.length == 1) return a;
		if (a.length == 2) {
			int total = a[0] + a[1];
			if ( total > a[0] && total > a[1] ) {
				return a;
			} else if (a[0] > a[1]) {
				int[] temp = new int[1];
				temp[0] = a[0];
				return temp;
			} else {
				int[] temp = new int[1];
				temp[0] = a[1];
				return temp;
			}
		}
		
		int mid = a.length / 2;
		
		int[] left = largest_sub_array(Arrays.copyOfRange(a, 0, mid));
		int[] right = largest_sub_array(Arrays.copyOfRange(a, mid, a.length));
		int[] split = largest_split_array(left, right, a, mid);
		
		if ( split != null ) return split;
		else if (IntStream.of(left).parallel().sum() > IntStream.of(right).parallel().sum() ) {
			return left;
		} else {
			return right;
		}
	}
	
	private int[] largest_split_array(int[] left, int[] right, int[] original, int mid) {
		
		// 1. find largest sub array in left half that includes mid-1
		// 2. find largest sub array in right half that includes mid
		// 3. combine results from 1. and 2.
				
		int[] largest_split = null;
		
		int left_total = IntStream.of(left).parallel().sum();
		int right_total = IntStream.of(right).parallel().sum();		
		int max = Math.max(left_total, right_total);		
		
		//find max left subarray that includes midpoint
		int current_left_split_total = Integer.MIN_VALUE;
		int max_left_split_total = Integer.MIN_VALUE;
		List<Integer> max_left_split_subarray = new ArrayList<Integer>();
		List<Integer> current_left_split_subarray = new ArrayList<Integer>();
		
		for (int i = mid - 1; i >= 0; i --) {
			int value = original[i];
			current_left_split_total = (current_left_split_total == Integer.MIN_VALUE) ? value : current_left_split_total + value;
			current_left_split_subarray.add(0, value);
			if (current_left_split_total > max_left_split_total) {
				max_left_split_total = current_left_split_total;
				max_left_split_subarray = new ArrayList<Integer>(current_left_split_subarray);				
			}
			
		}
		
		//find max right subarray that includes midpoint
		int current_right_split_total = Integer.MIN_VALUE;
		int max_right_split_total = Integer.MIN_VALUE;
		List<Integer> max_right_split_subarray = new ArrayList<Integer>();
		List<Integer> current_right_split_subarray = new ArrayList<Integer>();
		
		for (int i = mid; i < original.length; i++) {
			int value = original[i];
			current_right_split_total = (current_right_split_total == Integer.MIN_VALUE) ? value : current_right_split_total + value;
			current_right_split_subarray.add(value);
			if ( current_right_split_total > max_right_split_total ) {
				max_right_split_total = current_right_split_total;
				max_right_split_subarray = new ArrayList<Integer>(current_right_split_subarray);
			}
		}
		
		if (  (max_left_split_subarray.stream().mapToInt(Integer :: intValue).sum() + max_right_split_subarray.stream().mapToInt(Integer :: intValue).sum() ) > max ) {
			List<Integer> split_array_list = new ArrayList<Integer>();
			split_array_list.addAll(max_left_split_subarray);
			split_array_list.addAll(max_right_split_subarray);
			largest_split = split_array_list.stream().parallel().mapToInt(Integer :: intValue).toArray();
		}
		
		return largest_split;
	}

}
