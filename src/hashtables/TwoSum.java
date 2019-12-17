package hashtables;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TwoSum {

	public static Integer find_target_sum(HashMap<BigInteger,BigInteger> input, int start_target, int end_target ) {
		Integer target_count = 0;
		
		for (int target = start_target; target <= end_target; target++) {
			System.out.println("target = " + target );
			for (BigInteger value : input.keySet() ) {
				//System.out.println("value = " + value);
				BigInteger sum_complement = new BigInteger(Integer.toString(target)).subtract(value);
				//System.out.println("searching for " + sum_complement);
				if ( input.get(sum_complement) != null ) {
					//System.out.println("found " + sum_complement);
					target_count++;
					break;
				}
			}
		}
		
		return target_count;
	}
	
	public static Integer find_target_sum_fast( HashMap<BigInteger, BigInteger> input, int start_target, int end_target) {
		Integer two_sum_count = 0;
		
		List<BigInteger> keys = new ArrayList<BigInteger>( input.keySet() );
		Collections.sort(keys);
		//System.out.println("finished sorting");
		for (int i = start_target; i <= end_target; i++ ) {
			BigInteger current_target = new BigInteger(Integer.toString(i));
			int left = 0;
			int right = keys.size() - 1;
			System.out.println("current target sum" + current_target);
			for (BigInteger n : keys) {
				System.out.println("checking input value " + n );
				BigInteger sum_complement = current_target.subtract(n);
				
				if (keys.get(left).compareTo(sum_complement) < 0 || keys.get(right).compareTo( sum_complement ) > 0) {
					//two sum does not exist for this target and input value
					System.out.println("two sum does not exist for target" + current_target + " and input value" + n);
					continue;
				}
				
				while ( left >= 0 && right < keys.size() && left != right ) {
					//found two sum
					if (keys.get(left).compareTo(sum_complement) == 0 || keys.get(right).compareTo(sum_complement) == 0 ) {
						System.out.println("found two sum for target " + current_target + " and input value " + n);
						two_sum_count ++;
						break;
					} else if (keys.get( left ).compareTo(sum_complement) > 0 ) {
						System.out.println("incrementing left");
						left++;
					} else {
						System.out.println("decrementing right");
						right --;
					}
				}
			}
		}
		
		return two_sum_count;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner( new File("/Users/adamss/git_projects/practice/src/hashtables/programming_prob-2sum.txt") );
		HashMap<BigInteger, BigInteger> input = new HashMap<BigInteger, BigInteger>(1000000, Float.parseFloat(".5"));
		
		while ( in.hasNext() ) {
			BigInteger next = new BigInteger( in.next() );
			input.put(next, next);
		}
		
		System.out.println(find_target_sum(input, -10000, 10000) );
	}
}
