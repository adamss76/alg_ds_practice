package divide_and_conquer;

import java.util.Arrays;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		test_kth_largest();		
	}
	
	public void test_largest_sub_array() {
		Scanner in =  new Scanner(System.in);
		int size = in.nextInt();
		
		int[] input = new int[size];
		int i = 0;
		while (in.hasNext()) {
			input[i] = in.nextInt();
			i++;
		}
		LargestSubArray2 largest_sub_arrray_test = new LargestSubArray2();
		MajorityElement majority_element_test = new MajorityElement();
		//System.out.println(tester.maxSubArray(input));
		
		System.out.println(majority_element_test.majorityElement(input));
	}
	
	public static void test_kth_largest() {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int k = in.nextInt();
		
		int[] input = new int[ size ];
		int i = 0;
		while ( in.hasNext() ) {
			input[ i ] = in.nextInt();
			i++;
		}
		
		KthLargest test = new KthLargest();
		/*for (int j = 0; j <1000; j++ ) {
			int answer = test.findKthLargest(input, k);
			System.out.println("answer = " + answer);
		}*/
		
		System.out.println("answer = " + test.findKthLargest(input, k));
		
	}

}
