package nearest_neighor_approximation;

import java.util.Comparator;
import java.util.PriorityQueue;

public class City {
	
	private Boolean visited;
	private Double x;
	private Double y;
	private Integer id;
	
	City(Integer id, Double x, Double y, Integer n) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.visited = Boolean.FALSE;
	}
	
	public Integer id() {
		return this.id;
	}
	
	public void visit() {
		this.visited = Boolean.TRUE;
	}
	
	public Boolean has_been_visited() {
		return this.visited;
	}
	
	public Double x() {
		return this.x;
	}
	
	public Double y()  {
		return this.y;
	}
	
	public Double find_distance( City c ) {
		Double x_diff = c.x() - this.x;
		Double y_diff = c.y() - this.y;
		
		Double x_squared = x_diff * x_diff;
		Double y_squared = y_diff * y_diff;
		
		Double distance = Math.sqrt( x_squared + y_squared );
		
		return distance;
	}
}
