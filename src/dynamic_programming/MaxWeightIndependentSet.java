package dynamic_programming;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MaxWeightIndependentSet {
	
	public static Map<Integer, Integer> mwis(List<Integer> weights) {
		List<Integer> sub_problems = new ArrayList<Integer>();
		Map<Integer, Integer> is = new HashMap<Integer, Integer>();
		//initialize
		sub_problems.add(0, 0);
		sub_problems.add(1, weights.get(1));
		for ( int i = 2; i < weights.size() - 1; i++ ) {
			Integer value_with_i = sub_problems.get(i - 2) + weights.get(i);
			Integer value_without_i	= sub_problems.get(i - 1);
		
			Integer max_weight = Math.max(value_without_i, value_with_i );
			sub_problems.add(i, max_weight);
		}
		
		//reconstruct the minimum weight independent set
		int i = sub_problems.size() - 1;
		while (i > 1 ) {
			if (sub_problems.get( i-1 ) >= sub_problems.get( i -2 ) + weights.get( i )) {
				i --;
			} else {
				is.put(i, weights.get(i));
				i = i -2;
			}
		}
		if (i == 1) {
			is.put(i, weights.get( i ));
		}
		/*for ( int i = sub_problems.size() - 1; i > 0; i-- ) {
			if (i == 1) {
				if ( sub_problems.get( i ) == sub_problems.get(i + 1) ) {
					//System.out.println(i);
					is.put(i, weights.get(i));
				}
			}
			else if ( sub_problems.get(i) == (sub_problems.get( i - 2 ) + weights.get( i )) ) {
				//System.out.println(i);
				is.put(i, weights.get( i ));
			}
		}*/
		
		return is;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/dynamic_programming/mwis.txt"));
		List<Integer> input = new ArrayList<Integer>();
		//need 1 index, not zero index list
		input.add(0, 0);
		int index = 1;
		
		in.nextLine();
		
		while (in.hasNext()) {
			input.add(index, in.nextInt());
			index++;
		}
		String answer = "";
		Map<Integer, Integer> set = mwis(input);
		
		if (set.containsKey(1)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(2)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(3)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(4)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(17)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(117)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(517)) answer = answer + "1";
		else answer = answer + 0;
		
		if (set.containsKey(997)) answer = answer + "1";
		else answer = answer + 0;
		//1, 2, 3, 4, 17, 117, 517, 997
		System.out.println(answer);
		
	}

}
