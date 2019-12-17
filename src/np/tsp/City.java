package np.tsp;

public class City {
	private Integer id;
	private Boolean is_start;
	private Double x;
	private Double y;
	
	City(Integer id, Double x, Double y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.is_start = Boolean.FALSE;
	}
	
	public void set_start() {
		this.is_start = Boolean.TRUE;
	}
	
	public Double x() {
		return this.x;
	}
	
	public Double y() {
		return this.y;
	}
	
	public Boolean is_start() {
		return this.is_start;
	}
	
	public Integer id() {
		return this.id;
	}
}
