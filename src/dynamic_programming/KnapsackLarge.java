package dynamic_programming;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KnapsackLarge {
	
	public static int calculate_max_value(int item, int x, int capacity, int number_of_items, Map<String, Integer> subproblems, List<Integer> values, List<Integer> weights) {
		String key = construct_key(item, x);
		
		//if this problem has already been solved, return the solution
		if (subproblems.containsKey(key)) {
			return subproblems.get(key);
		}
		
		//base case
		if ( item == 1 ) {
			
			if ( x - weights.get( item ) < 0 ) {
				String temp_key = construct_key(item - 1, x);
				if (subproblems.containsKey(temp_key) ) {
					return subproblems.get( temp_key );
				} else {
					int max_value = subproblems.get(construct_key(item - 1, x));
					subproblems.put(construct_key(item, x), max_value);
					return max_value;
				}
			} else {
				
				int value_with_i = subproblems.get(construct_key(item - 1, x - weights.get( item ))) + values.get( item );
				int value_without_i = subproblems.get( construct_key( item - 1, x ));
				int max_value = Math.max(value_with_i, value_without_i);
				subproblems.put(key, max_value);
				return max_value;
			}
		}
		
		if  ( x - weights.get( item ) < 0) {
			return calculate_max_value(item - 1, x, capacity, number_of_items, subproblems, values, weights);
		} else {
			int value_with_item = calculate_max_value(item - 1, x - weights.get( item ), capacity, number_of_items, subproblems, values, weights) + values.get( item );
			int value_without_item = calculate_max_value(item - 1, x, capacity, number_of_items, subproblems, values, weights);
			int max_value = Math.max(value_with_item, value_without_item);
			subproblems.put(key, max_value);
			return subproblems.get(key);
		}

	}
	
	private static String construct_key(int item, int weight) {
		return item + "_" + weight;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/dynamic_programming/knapsack_big.txt"));
		String[] line = in.nextLine().split(" ");
		int capacity = Integer.parseInt( line[0] );
		int number_of_items = Integer.parseInt( line[1] );
		Map<String, Integer> subproblem_solutions = new HashMap<String, Integer>();
		List<Integer> weights = new ArrayList<Integer>();
		List<Integer> values = new ArrayList<Integer>();
		
		//these lists start at index 1 (for item 1) not zero
		weights.add(0, 0);
		values.add(0, 0);
		
		while (in.hasNext()) {
			String[] new_line = in.nextLine().split(" " );
			int value = Integer.parseInt(new_line[0]);
			int weight = Integer.parseInt(new_line[1]);
			
			weights.add(weight);
			values.add(value);
		}
		//initialze sub problem solutions when item = 0
		for (int i = 0; i <= capacity; i++ ) {
			String key = construct_key(0, i);
			subproblem_solutions.put(key, 0);
		}
		//initialize
		System.out.println(calculate_max_value(number_of_items, capacity, capacity, number_of_items, subproblem_solutions, values, weights));
	}
}
