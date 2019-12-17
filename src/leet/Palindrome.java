package leet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Palindrome {
	
	public static boolean isPalindrome(int x) {
		
        //negative number can't be a palindrome		
        if ( x < 0 ) {
        	return false;
        }
        //only one digit = palindrome
        if ( x < 10 ) return true;
        
        BigInteger power = BigInteger.ONE;
        int shift_ten = 0;
        int index = 0;
        List<Integer> digits = new ArrayList<Integer>();
        int reverse = 0;
        
        while ( shift_ten != x ) {
        	power = power.multiply(new BigInteger(Integer.toString(10)));
        	shift_ten = new BigInteger(Integer.toString(x)).mod(power).intValue();
        	int digit = shift_ten / ( power.divide(new BigInteger(Integer.toString( 10 )) ).intValue() );
        	digits.add(index, digit);
        	index ++;
        }
        for ( int i = 0; i < digits.size(); i++ ) {
        	reverse = reverse + digits.get(i) * ten_raised(digits.size() - i - 1);
        }
        	
        return (reverse == x);
    }
	
	private static int ten_raised(int exponent) {
		int ten_power = 1;
		
		for (int i = exponent; i > 0; i--) {
			ten_power = ten_power * 10;
		}
		
		return ten_power;
	}
	
	public static boolean isPalindromeString(int x) {
		
		String original = Integer.toString(x);
		String reverse = "";
		for (int i = 0; i < original.length(); i++ ) {
			reverse = reverse + original.charAt(i);
		}
		
		return original.equals(reverse);
	}
		
	
	public static void main(String[] args) {
		System.out.println("");
		System.out.println(isPalindromeString(1000000001));
	}
}
