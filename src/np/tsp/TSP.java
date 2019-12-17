package np.tsp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class TSP {
	
	public static Double find_min_cost_tsp_route( List<City> cities ) {
		City starting_city = cities.get(0);
		starting_city.set_start();
		
		Map<BitSet, Map<Integer, Double>> subproblems = new HashMap<BitSet, Map<Integer, Double>>();
		Map<Integer, List<BitSet>> size_map = new HashMap<Integer, List<BitSet>>();
		
		initialize_subproblems(cities, starting_city, subproblems, size_map);
		
		//Base Case
		List<BitSet> one_city_paths = size_map.get(1);
		
		for (BitSet one_city_path : one_city_paths ) {
			//base case starting city = destination city: The one node path length from the starting city to itself is 0, 
			//base case starting city != destination city: for all other destinations it's impossible to get from the starting city to another city visiting only one vertex so the distance is positive infinity
			Map<Integer, Double> cost_map = new HashMap<Integer, Double>();
			if (one_city_path.get( (starting_city.id()) ) ) {
				cost_map.put(starting_city.id(), 0.0);
			} else {
				cost_map.put(one_city_path.nextSetBit(0), Double.POSITIVE_INFINITY);
			}
			subproblems.put(one_city_path, cost_map);
		}
		
		//calculate subproblem values
		for (int m = 2; m <= cities.size(); m++ ) {
			//m refers to the subproblem size (number of cities in this path)
			
			List<BitSet> all_m_size_routes = size_map.get( m );
			
			/* memory optimization */
			List<BitSet> old_subproblems = size_map.get( m - 2 );
			if ( old_subproblems != null ) {
				for ( BitSet old_subproblem : old_subproblems ) {
					subproblems.remove( old_subproblem );
				}
			}
			
			for (BitSet an_m_size_route : all_m_size_routes ) {				
				
				for (City destination : cities ) {
					//for each destination in this route calculate min cost route
					if ( !destination.id().equals( starting_city.id() ) && an_m_size_route.get( destination.id() ) ) {
						
						//find min cost path to this destination by calculating A[{S - j][k] + Ckj for all k in {S - j}
						
						//1. find set {S-j}
						BitSet route_without_destination = an_m_size_route.get(0, cities.size() );
						route_without_destination.clear( destination.id() );
						
						//2. for each potential destination in set {S-j} retrieve it's min cost (this subproblem has already been solved)
						//3. calculate the this set {S} with destination j's cost using the previous subproblem cost calculated in step 2 and adding in the cost from k to j
						Double prev_cost = Double.POSITIVE_INFINITY;
						for (City k : cities ) {
							if ( !k.id().equals( destination.id() ) && an_m_size_route.get( k.id() ) ) {
								Double subproblem_cost = ( subproblems.get( route_without_destination ).get( k.id() ) == null ) ? Double.POSITIVE_INFINITY : subproblems.get( route_without_destination ).get( k.id() );
									Double new_cost = (subproblem_cost.equals(Double.POSITIVE_INFINITY ) ) ? Double.POSITIVE_INFINITY : subproblem_cost + find_cost(k, destination);
									
									if ( Double.compare(new_cost, prev_cost) < 0 ) {
										subproblems.get( an_m_size_route ).put( destination.id(), new_cost );
										prev_cost = new_cost;
									}
							}
						}
					}
				}
			}
		}
		
		//calculate the final cost of the final step in the route from the end destination back to the starting city
		BitSet all_cities = new BitSet( cities.size() );
		all_cities.set(0, cities.size());
		Map<Integer, Double> final_subproblem = subproblems.get( all_cities );
		Double min_cost_tsp = Double.POSITIVE_INFINITY;
		for (Integer j : final_subproblem.keySet() ) {
			//TODO potential bug, I'm assuming the the list of cities is index by city id. I should switch to storing the cities in a Map
			Double subproblem_cost = final_subproblem.get( j ) == null ? Double.POSITIVE_INFINITY : final_subproblem.get( j );
			Double new_cost = subproblem_cost.equals( Double.POSITIVE_INFINITY ) ? Double.POSITIVE_INFINITY : subproblem_cost + find_cost(cities.get( j ), starting_city );
			if ( Double.compare(new_cost, min_cost_tsp) < 0 ) {
				min_cost_tsp = new_cost;
			}
		}
		
		return min_cost_tsp;
	}
	
	private static List<BitSet> n_choose_k (List<City> cities, int k, City starting_city ) {
		
		List< BitSet > k_routes = new ArrayList<BitSet>();
		
		Iterator<int[]> itr = CombinatoricsUtils.combinationsIterator(cities.size(), k);
		while (itr.hasNext()) {
			int[] combination = itr.next();
			BitSet route = new BitSet( cities.size() );
			for (int i = 0; i < combination.length; i++ ) {
				route.set( combination[i] );
			}
			
			if ( k == 1) {
				k_routes.add( route );
			} else if ( route.get( starting_city.id() ) ) {
				k_routes.add( route );
			}
		}
		
		return k_routes;
	}
	
	private static void add_to_size_map( int size_of_path, BitSet path, Map<Integer, List<BitSet>> size_map ) {
		
		List<BitSet> paths_of_this_size = ( size_map.get( size_of_path ) == null ) ? new ArrayList<BitSet>() : size_map.remove( size_of_path );
		
		paths_of_this_size.add( path );
		
		size_map.put( size_of_path , paths_of_this_size );
		
	}
	
	private static Double find_cost( City c1, City c2 ) {
		
		Double x_diff = c2.x() - c1.x();
		Double y_diff = c2.y() - c1.y();
		
		Double x_diff_squared = x_diff * x_diff;
		Double y_diff_squared = y_diff * y_diff;
		
		return Math.sqrt( x_diff_squared + y_diff_squared );
	}
	
	private static void initialize_subproblems( List<City> cities, City start, Map< BitSet, Map< Integer, Double >> subproblems, Map<Integer, List<BitSet>> size_map ) {
		
		for (int k = 1; k <= cities.size(); k++ ) {
			List<BitSet> k_routes = n_choose_k( cities, k, start);
			
			for (BitSet a_route : k_routes ) {
				
				add_to_size_map(a_route.cardinality(), a_route, size_map);
				
				//initialize the cost for all destinations on this route as null since they haven't been calculated yet
				Map<Integer, Double> destination_cost_map = new HashMap<Integer, Double>();
				for (City destination : cities ) {
					if ( a_route.get( destination.id() ) ) {
						destination_cost_map.put(destination.id(), null);
					}
				}				
				subproblems.put(a_route, destination_cost_map);				
			}
			
		}
	}
	
	public static void main( String[] args ) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/np/tsp/cities_test_twelve.txt"));
		Integer number_of_cities = Integer.parseInt(in.nextLine());
		List<City> cities = new ArrayList<City>();
		Integer city_id = 0;
		
		while( in.hasNextLine() ) {
			String[] coordinates = in.nextLine().split(" " );
			Double x = new Double( coordinates[0] );
			Double y = new Double( coordinates[1] );
			City c = new City(city_id, x, y);
			cities.add( c );
			city_id++;			
		}
		
		System.out.println( TSP.find_min_cost_tsp_route(cities) );
	}
}
