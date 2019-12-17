package divide_and_conquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
	
	public static int find_majority_element_naive(int[] nums) {
		Integer majority_element = null;
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		for (int number : nums) {
			Integer count = counts.get(number);
			
			if ( count != null ) {
				count ++;
				counts.put(number, count);				
				if ( count > ( nums.length / 2 ) ) {
					majority_element = number;
					return majority_element;
				}
			} else {
				counts.put(number, 1);
				if (nums.length < 3) {
					majority_element = number;
					return majority_element;
				}
			}
		}
		
		return majority_element;
	}
	
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> element_count = new HashMap<Integer, Integer>();
		return majority_element(nums, element_count);
	}

	private Integer majority_element(int[] a, Map<Integer, Integer> element_counts) {
		//base case
		if (a.length == 1) {
			Integer count = element_counts.get(a[0]) == null ? 1 : element_counts.get(a[0]) + 1;
			element_counts.put(a[0], count);
			return a[0];
		}
		
		int mid = a.length / 2;
		int majority = a.length / 2;
		
		int majority_left = majority_element(Arrays.copyOfRange(a, 0, mid), element_counts);		
		int majority_right = majority_element( Arrays.copyOfRange(a, mid, a.length), element_counts );
		
		return element_counts.get(majority_left) > element_counts.get(majority_right) ? majority_left : majority_right;
	}
}
