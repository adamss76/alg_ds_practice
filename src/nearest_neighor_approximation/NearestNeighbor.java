package nearest_neighor_approximation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NearestNeighbor {
	
	public static double nearest_neigbor_tsp(List<City> cities_to_visit) {
		
		//list of cities left visit sorted by id
		
		Double tour_length = 0.0;		
		
		//start at the first city
		City starting_city = cities_to_visit.remove(0);
		City last_visited = starting_city;
		
		//main loop
		while ( !cities_to_visit.isEmpty() ) {
			
			City nearest_neighbor = find_nearest_neighbor(cities_to_visit, last_visited );
			tour_length = tour_length + last_visited.find_distance( nearest_neighbor );
			last_visited = nearest_neighbor;
			cities_to_visit.remove( nearest_neighbor );	
		}
		
		//complete tour
		tour_length = tour_length + last_visited.find_distance( starting_city );
		
		//return tour_length;
		return Math.floor( tour_length );
	}
	
	private static City find_nearest_neighbor( List<City> cities, City c1) {
		Double min_distance = Double.POSITIVE_INFINITY;
		City nearest_neighbor = null;
		for (City c2 : cities) {
			if ( !c2.id().equals(c1.id()) ) {
				Double distance = c1.find_distance( c2 );
				//check if this city is the nearest neighbor so far
				if ( distance.compareTo( min_distance ) < 0 ) {
					nearest_neighbor = c2;
					min_distance = distance;
				}
			}
		}
		return nearest_neighbor;
	}
	
	public static void main(String[] args ) throws Exception {
		Scanner in = new Scanner( new File("/Users/adamss/git_projects/practice/src/nearest_neighor_approximation/nn.txt") );
		Integer number_of_cities = Integer.parseInt(in.nextLine());
		List<City> cities = new ArrayList<City>();
		Integer city_id = 1;
		City starting_city = null;
		
		while( in.hasNextLine() ) {
			String[] coordinates = in.nextLine().split(" " );
			Double x = new Double( coordinates[1] );
			Double y = new Double( coordinates[2] );
			
			City c = new City(city_id, x, y, number_of_cities);
			
			if( starting_city == null ) {
				starting_city = c;
			}
			
			cities.add( city_id - 1, c);
			city_id++;			
		}
		
		System.out.println( nearest_neigbor_tsp(cities) );
	}

}
