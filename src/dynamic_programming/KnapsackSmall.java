package dynamic_programming;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackSmall {

	public static Integer max_knapsack_value(List<Integer> weights, List<Integer> values, Integer capacity, Integer number_of_items) {
		//item by weight
		int[][] sub_problems = new int[number_of_items + 1][capacity + 1];
		//since subproblems array is already initialized with zeros, I can skip initializing the values for zero items sub_problems[0][x] = 0
		
		//compute max value for each sub_problem
		for (int item = 1; item <= number_of_items; item ++ ) {
			for (int x = 1; x <= capacity; x++ ) {
				if ( x - weights.get( item ) < 0 ) {
					sub_problems[item][x] = sub_problems[item - 1][x];
				} else {
					int value_with_item = sub_problems[item - 1][x - weights.get( item )] + values.get( item );
					int value_without_item = sub_problems[item - 1][x];
					sub_problems[item][x] = Math.max(value_with_item, value_without_item);
				}
			}
		}
		return sub_problems[ number_of_items ][ capacity ];
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/dynamic_programming/knapsack1.txt"));
		String[] line = in.nextLine().split(" ");
		int capacity = Integer.parseInt( line[0] );
		int number_of_items = Integer.parseInt( line[1] );
		
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
		
		System.out.println(max_knapsack_value(weights, values, capacity, number_of_items));
	}
}
