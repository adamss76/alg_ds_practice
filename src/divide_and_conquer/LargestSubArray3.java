package divide_and_conquer;

import java.util.Arrays;

import divide_and_conquer.util.SubArray;

public class LargestSubArray3 {
	public int maxSubArray(int[] nums) {
		SubArray result = new SubArray(nums);
		return largest_sub_array(result).total();
	}
	
	private SubArray largest_sub_array(SubArray sub_array) {
		int[] a = sub_array.subArray();
		
		//base case
		if ( a.length == 1) return sub_array;
		if ( a.length == 2 ) {
			if (a[0] + a[1] > a[0] && a[0] + a[1] > a[1]) {
				return sub_array;
			} else {
				if ( a[0] > a[1] ) {
					return new SubArray(a, Arrays.copyOf(a, 1), 0);
				} else {
					return new SubArray(a, Arrays.copyOfRange(a, 1, 1), 1);
				}
			}
		}
		
		int mid = a.length / 2;
		
		SubArray left = new SubArray(a, Arrays.copyOfRange(a, 0, mid), index)
	}
}
