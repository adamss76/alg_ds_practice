package divide_and_conquer;

import java.util.Random;
import java.util.stream.IntStream;

public class KthLargest {
	/**
	 * This solution first sorts the array then returns the value in the kth largest position (nums.length - k)
	 * @param nums
	 * @param k
	 * @return
	 */
	 public int findKthLargestSlow(int[] nums, int k) {
		 int[] sorted = IntStream.of(nums).sorted().toArray();
	        
	     return sorted[sorted.length - k];
	 }
	 
	 public int findKthLargest(int[] nums, int k) {
		 int kth_largest = findKthLargestFast( nums, nums.length - k, 0, nums.length );
		 
		 return nums[kth_largest];
	 }
	 
	 /**
	  * uses a quicksort selection algorithm to recursively find the kth largest element in linear time
	  * @param a
	  * @param k
	  * @param start_index inclusive
	  * @param end_index exclusive
	  * @return index of kth largest element
	  */
	 private int findKthLargestFast(int[] a, int k, int start_index, int end_index) {
		 //base case
		 if ( start_index >= end_index ) {
			 return start_index;
		 }
		 
		 //1. partition around a random pivot and return the pivot's index in a sorted array (using quicksort partition algorithm)
		 int pivot_index = partition(a, start_index, end_index);
		 
		 //2a. if pivot index equals k: return pivot element
		 if ( pivot_index == k) {
			 return pivot_index;
		 }
		 //2b. if pivot index greater than k, recurse on the portion of array less than pivot index
		 else if ( pivot_index > k ) {
			 return findKthLargestFast(a, k, start_index, pivot_index - 1);
		 } 
		 //2c. if pivot index less than k, recurse on portion of array greater than pivot index
		 else {
			 
			 return findKthLargestFast(a, k, pivot_index + 1, end_index);
		 }		 
	 }
	 
	 /**
	  * 
	  * @param a
	  * @param start_index inclusive
	  * @param end_index exclusive
	  * @return the correct position in a of the pivot element
	  */
	 private int partition(int[] a, int start_index, int end_index) {
		 Random rand = new Random();
		 
		 int pivot_index = rand.nextInt(end_index - start_index) + start_index;
		 int pivot_element = a[ pivot_index ];
		 
		 int first_element = a[ start_index ];
		 a[ start_index ] = pivot_element;
		 a[ pivot_index ] = first_element;
		 
		 int partition = start_index;
		 for ( int sorted_index = start_index + 1; sorted_index < end_index; sorted_index++) {
			 if ( a[ sorted_index ] < pivot_element ) {
				 partition++;
				 int element_less_than_pivot = a[ sorted_index ];
				 int element_greater_than_pivot = a[ partition];
				 a[ partition ] = element_less_than_pivot;
				 a[ sorted_index ] = element_greater_than_pivot;
			 }
		 }
		 //partion points to the end of the portion of the array that contains values strictly less than the pivot element
		 //swap pivot element with partition element
		 int swap = a [ partition ];
		 a[ partition ] = pivot_element;
		 a[ start_index ] = swap;
		 
		 return partition;
	 }
}
