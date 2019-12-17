package leet;

import java.util.Scanner;

public class ReverseInt {
	
	public static int reverse(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) return 0;
       
        String forward = Integer.toString(x);
        String reverse = "";
        boolean negative = forward.substring(0, 1).equals("-");
        
        if (negative ) {
        	forward = forward.substring(1, forward.length());
        }
        
        for (int i = forward.length() - 1; i >= 0; i-- ) {
            String c = forward.substring(i, i + 1);
            
            reverse = reverse + c;
        }
        
        if (negative) {
            reverse = "-".concat(reverse);
        }
        
        return Integer.parseInt(reverse);
    }
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		try {
			int x = Integer.parseInt(line);
			System.out.println(reverse(x));
			in.close();
		} catch ( NumberFormatException e) {
			in.close();
			System.out.println(0);
		}
		
	}
	
}
