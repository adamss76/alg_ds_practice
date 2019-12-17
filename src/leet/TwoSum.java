package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
	
	public static int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> sums = new HashMap<Integer, List<Integer>>();
        int[] answer = new int[2];
        
        for (int i = 0; i < nums.length; i++ ) {
            if (sums.containsKey(nums[i])) {
            	List<Integer> indecies = sums.get(nums[i]);
            	indecies.add(i);
            	sums.put(nums[i], indecies);
            } else {
            	List<Integer> indecies = new ArrayList<Integer>();
            	indecies.add( i);
            	sums.put(nums[i], indecies);
            }
        }
        //find two sum
        for (int i = 0; i < nums.length; i++ ) {
            if (sums.containsKey(target - nums[i]) && 
            		( sums.get(target - nums[i]).size() > 1 ||  sums.get(target - nums[i]).get(0) != i ) ) {
            	List<Integer> two_sum_matches = sums.get(target - nums[i]);
            	int index_of_two_sum = -1;
            	for (Integer index : two_sum_matches) {
            		if (index != i) {
            			index_of_two_sum = index;
            			break;
            		}
            	}
            	if (index_of_two_sum > i ) {
            		answer[0] = i;
            		answer[1] = index_of_two_sum;
            	} else {
            		answer[0] = index_of_two_sum;
            		answer[1] = i;
            	}
            	break;
            }
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] test = new int[3];
		test[0] = 3;
		test[1] = 2;
		test[2] = 4;
		int target = 6;
		
		System.out.println(twoSum(test, target));
	}
	
}
