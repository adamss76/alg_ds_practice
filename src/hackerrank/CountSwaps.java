package hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class CountSwaps {
	
	static int minimumSwaps(int[] arr) {
		
		boolean[] visited = new boolean[ arr.length ];
		Map<Integer, Integer> original_index_map = new HashMap<Integer, Integer>();
		Map<Integer, Integer> sorted_index_map = new HashMap<Integer, Integer>();
		List<List<Integer>> cycles = new ArrayList<List<Integer>>();
		
		//initialize original indexes in unsorted array
		for (int i = 0; i < arr.length; i++ ) {
			original_index_map.put( arr[i], i );
		}
		
		//sort input array to find each entry's correct final position
		Arrays.sort( arr );
		
		//initialize array of sorted indexes in order to speed up lookups
		for (int i = 0; i < arr.length; i++ ) {
			sorted_index_map.put( arr[ i ], i );
		}
		
		//compute cycles
		for (int i = 1; i <= arr.length; i++ ) {
			if ( ! visited[ i - 1 ] ) {
				cycles.add( visit( i, visited,original_index_map,sorted_index_map, arr ) );
			}
		}
        
        return arr.length - cycles.size();
    }
    
    private static List<Integer> visit(int i , boolean[] has_been_visited, Map<Integer, Integer> original_indexes, Map<Integer, Integer> sorted_indexes, int[] sorted_array ) {
    	List<Integer> this_cycle = new ArrayList<Integer>();
    	Stack<Integer> temp = new Stack<Integer>();
    	
    	temp.push( i );
    	
    	while ( !temp.isEmpty() ) {
    		int val = temp.pop();
    		int original_index = original_indexes.get( val );
    		int final_index = sorted_indexes.get( val );
    		
    		has_been_visited[ final_index] = Boolean.TRUE;
    		this_cycle.add( val );
    		
    		if ( original_index != final_index && !has_been_visited[ sorted_indexes.get( original_index + 1) ] ) {
    			temp.push( sorted_array[ original_index ] );
    		}
    	}
    	
    	return this_cycle;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }
        //for(int i = 0; i < 10; i++) {
        	int res = minimumSwaps(arr);
        	System.out.println(res);
        //}

        //bufferedWriter.write(String.valueOf(res));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
                
    }

}
