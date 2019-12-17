package greedy_algorithms.minimum_spanning_tree.kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HammingDistance {

	/*public static Integer find_hamming_distance_clusters(List<Integer> input) {
		Set<Integer> input_without_duplicates = new HashSet( input );
		Set<Integer> clusters = new HashSet<Integer>();
		Set<Integer> black_list = new HashSet<Integer>();
		
		for (Integer n : input_without_duplicates ) {
			List<Integer> one_away = find_one_away( n );
			List<Integer> two_away = find_two_away( n );
			black_list.addAll(one_away );
			black_list.addAll( two_away );
			
			if (!black_list.contains( n ) ) {
				clusters.add( n );
			}
		}
		
		return clusters.size();
	}*/
	
	/*public static Integer find_hamming_distance_2(List<Integer> input) {
		HashSet<Integer> input_no_duplicates = new HashSet<Integer>( input );
		HashMap<Integer, List<Integer>> one_away = new HashMap<Integer, List<Integer>>();
		HashMap<Integer, List<Integer>> two_away = new HashMap<Integer, List<Integer>>();
		HashSet<Integer> clusters = new HashSet<Integer>();
		
		for (Integer n : input_no_duplicates ) {
			List<Integer> one = find_one_away( n );
			List<Integer> two = find_two_away( n );
			one_away.put(n, one);
			two_away.put(n, two);
		}
		
		for (Integer n : input_no_duplicates) {
			boolean new_cluster = true;
			
			if ( !clusters.contains( n) ) {
				for (Integer one : one_away.get( n ) ) {
					if ( clusters.contains(one) ) {
						new_cluster = false;
					}
				}
				
				for (Integer two : two_away.get( n ) ) {
					if ( clusters.contains( two )) {
						new_cluster = false;
					}
				}
				
				if (new_cluster == true) {
					clusters.add( n );
				}
			}
		}
		
		return clusters.size();
	}*/
	
	public static Integer find_minimum_hamming_distance_clusters( List<Integer> i ) {
		Set<Integer> input = new HashSet<Integer>(i);
		UnionFind uf = new UnionFind(new ArrayList<Integer>(input));
		
		for (Integer n : input ) {
			Set<Integer> cluster_candidates = new HashSet<Integer>();
			List<Integer> one_away = find_one_away( n );
			List<Integer> two_away = find_two_away( n );
			
			cluster_candidates.addAll( one_away );
			cluster_candidates.addAll( two_away );
			
			for ( Integer candidate : cluster_candidates ) {
				if (input.contains(candidate) ){
					if (uf.find( n ) != uf.find( candidate ) )  {
						uf.union(n, candidate);
					}
				}				
			}
		}
		
		return uf.number_of_clusters();
	}
	
	private static List<Integer> find_one_away(Integer n) {
		List<Integer> one_away = new ArrayList<Integer>();
		for (int i = 1; i < 25; i++) {
			Integer temp = 1 << i -1;
			Integer candidate = n ^ temp;
			
			if ( candidate <= 33554431 ) {
				one_away.add( candidate );
			}
			
		}
		
		return one_away;
	}
	
	private static List<Integer> find_two_away(Integer n) {
		List<Integer> two_away = new ArrayList<Integer>();
		for (int i = 1; i < 25; i++) {
			Integer temp = 1 << i - 1;
			Integer one_away = n ^ temp;
			
			if ( one_away <= 33554431 ) {
				for (int j = i + 1; j < 25; j++ ) {
					Integer temp2 = 1 << j - 1;
					Integer two_away_value = one_away ^ temp2;
					if (two_away_value <= 33554431 ) {
						two_away.add( two_away_value );
					}
				}
			}
			
		}
		
		return two_away;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/greedy_algorithms/minimum_spanning_tree/kruskal/clustering_big.txt"));
		List<Integer> input = new ArrayList<Integer>();
		in.nextLine();
		
		while ( in.hasNext() ) {
			String line = in.nextLine().replace(" ", "");
			Integer n = Integer.parseInt(line, 2);
			input.add(n);
		}
		//System.out.println( find_hamming_distance_2(input) );
		//System.out.println(find_hamming_distance_clusters(input) );
		System.out.println(find_minimum_hamming_distance_clusters(input));
	}

}
