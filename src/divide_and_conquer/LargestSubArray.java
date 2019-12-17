package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * mistakes: 
 * 1. not advancing temp pointer after initialization (in naive solution)
 * 2. using array type instead of Collections type. arrays are the wrong choice when values need to change dynamically.
 * 3. need to make sure left and right sub arrays are contiguous in divide and conquer solution (added a boolean flag to check)
 * 4. Array.asList(original_array) creates a fixed sized ArrayList that is backed by the original array: adding and removing elements is not allowed. It is better to create a new array list: List<Integer> list = new ArrayList<Integer>(Arrays.asList(original_array))
 * 
 * @author adamss
 *
 */
public class LargestSubArray {

	public static Integer[] largest_sub_array_naive(Integer[] a) {
		Integer[] largest_sub_array = null;
		Integer global_max = null;
		
		for (int i = 0; i < a.length; i++) {
			Integer[] temp = new Integer[a.length];
			int current_max = a[i];
			
			//initialize temp with the first value in the list of contiguous values we are testing
			temp[0] = a[i];
			int temp_pointer = 1;
			
			if (global_max == null) {
				global_max = a[i];
				largest_sub_array = Arrays.copyOf(temp, 1);
			}
			
			for (int j = i+1; j < a.length; j++) {
				if ( current_max + a[j] > current_max) {
					current_max = current_max + a[j];
					temp[temp_pointer] = a[j];
					temp_pointer++;
				} else {
					// this value does not increase the sub array sum
					break;
				}
			}
			
			//did we find a new global max?
			if (current_max > global_max) {
				global_max = current_max;
				largest_sub_array = Arrays.copyOf(temp, temp.length);
			}
			
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (Integer value : largest_sub_array) {
			if (value != null) result.add(value);
		}
		
		return result.toArray(new Integer[result.size()]);
	}
	
	public static Integer[] largest_sub_array(Integer[] a) {		
		//base case
		if (a.length == 2) {
			
			List<Integer> largest = new ArrayList<Integer>();
			int sum = a[0] + a[1];
			
			if (sum > a[0] && sum > a[1]) {
				largest.add(a[0]);
				largest.add(a[1]);
			} else if (a[0] > a[1]) {
				largest.add(a[0]);
			} else {
				largest.add(a[1]);
			}
			return largest.toArray(new Integer[largest.size()]);
		}
		
		//base case
		if (a.length == 1 ) return a;
		
		int mid = a.length / 2;
		
		Integer[] left_largest = largest_sub_array(Arrays.copyOfRange(a, 0, mid));
		Integer[] right_largest = largest_sub_array(Arrays.copyOfRange(a, mid, a.length));
		
		Integer[] largest_split_array = largest_split_array(left_largest, right_largest, a, mid);
		
		if ( largest_split_array != null) {
			return largest_split_array;
		} else if ( calculate_total(left_largest) > calculate_total(right_largest) ) {
			return left_largest;
		} else {
			return right_largest;
		}
		
	}
	
	private static Integer[] largest_split_array(Integer[] left, Integer[] right, Integer[] original, int mid) {
		
		int left_total = calculate_total( left );
		int right_total = calculate_total( right );
		int max = Math.max(left_total, right_total);
		
		Integer[] max_split_sub_array = null;
		
		//look for the largest sub array that crosses mid (ie some combination of the left and right sub arrays)
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(Arrays.asList(left));
		int current_total = Integer.MIN_VALUE;
		for ( int i = mid; i < original.length; i++ ) {
			Integer value = original[i];
			temp.add(value);
			current_total = (current_total == Integer.MIN_VALUE) ? (left_total + value) : current_total + value;
			if ( current_total > max ) {
				max_split_sub_array = temp.toArray(new Integer[temp.size()]);
				max = current_total;
			}
		}
		
		return max_split_sub_array;
	}
	
	private static int calculate_total(Integer[] a) {
		Integer total = null;
		for (Integer value : a) {
			if (total == null ) total = value;
			else total = total + value;
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		Scanner in =  new Scanner(System.in);
		int size = in.nextInt();
		
		Integer[] input = new Integer[size];
		int i = 0;
		while (in.hasNext()) {
			input[i] = in.nextInt();
			i++;
		}
		
		Integer[] largest_sub_array_naive = largest_sub_array_naive(input);
		Integer[] largest_sub_array = largest_sub_array( input );
		
		System.out.println(Arrays.toString(largest_sub_array_naive));
		
		System.out.println(Arrays.toString(largest_sub_array));
	}
}
