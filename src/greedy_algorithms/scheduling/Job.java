package greedy_algorithms.scheduling;

public class Job {
	private Integer weight;
	private Integer length;
	private Double score;
	private Integer completion_time;
	
	public Job(Integer weight, Integer length, Double score) {
		this.weight = weight;
		this.length = length;
		this.score = score;
		this.completion_time = null;
	}

	public Integer weight() {
		return this.weight;
	}
	
	public Integer length() {
		return this.length;
	}
	
	public Double score() {
		return this.score;
	}
	
	public void set_completion_time( Integer new_completion_time ) {
		this.completion_time = new_completion_time;
	}
	
	public Integer completion_time() {
		return this.completion_time;
	}
}
