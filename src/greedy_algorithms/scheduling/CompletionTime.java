package greedy_algorithms.scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CompletionTime {
	
	public static BigInteger calculate_total_weighted_completion_time( List<Job> all_jobs ) {
		BigInteger total_weighted_completion_time = BigInteger.ZERO;
		Integer completion_time = 0;
		PriorityQueue<Job> h = new PriorityQueue<Job>( all_jobs.size(), Comparator.comparing( Job::score ).thenComparing(Job::weight).reversed() );
		
		//inititalze
		//TODO: need to review the reference implementation of addall. the javadoc does not specify runtime performance better than nlogn but depending on the implementation it might actually run in linear time
		h.addAll( all_jobs );
		
		//select job with maximum score until all jobs have been selected
		while ( !h.isEmpty() ) {
			Job selected = h.remove();
			completion_time = completion_time + selected.length();
			Integer weighted_completion_time = completion_time * selected.weight();
			total_weighted_completion_time = total_weighted_completion_time.add(new BigInteger( weighted_completion_time.toString() ));
		}
		
		return total_weighted_completion_time;
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/greedy_algorithms/scheduling/jobs.txt"));
		Integer size = in.nextInt();
		List<Job> jobs1 = new ArrayList<Job>( size );
		List<Job> jobs2	= new ArrayList<Job>( size );
		while ( in.hasNext() ) {
			//String[] line = in.nextLine().split(" " );
			Double weight = in.nextDouble();
			Double length = in.nextDouble();
			Double score_1 = weight - length;
			Double score_2 = weight/length;
			jobs1.add( new Job(weight.intValue(), length.intValue(), score_1) );
			jobs2.add( new Job(weight.intValue(), length.intValue(), score_2 ) );
		}
		System.out.println(calculate_total_weighted_completion_time( jobs1 ) );
		System.out.println(calculate_total_weighted_completion_time( jobs2 ) );
		in.close();
	}
}
