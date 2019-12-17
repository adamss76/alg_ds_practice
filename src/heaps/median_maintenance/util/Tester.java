package heaps.median_maintenance.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import heaps.median_maintenance.MaintainMedian;

public class Tester {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/heaps/median_maintenance/util/median_test.txt") );
		MaintainMedian.initialize();
		ArrayList<Integer> medians = new ArrayList<Integer>();
		Integer sum = 0;
		
		while( in.hasNext() ) {
			Integer new_value = in.nextInt();
			Integer new_median = MaintainMedian.find_new_median(new_value);
			medians.add(new_median);
			sum = sum + new_median;
		}
		
		in.close();
		
		System.out.println(sum % 10000);
	}
}
