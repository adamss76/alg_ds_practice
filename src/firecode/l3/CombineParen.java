package firecode.l3;

import java.util.ArrayList;

public class CombineParen {

	public static void main(String[] args) {
		combParenthesis(2);
	}
	
	public static void combParenthesis(int pairs) {
		String[] result = new String[  2 * pairs  ];
		//StringBuilder result = new StringBuilder();
		parens(pairs, result, 0, 0, 0);
		
		//System.out.println(result.toString());
	}
	
	public static void parens(int pairs, String[] result,  int open, int close, int pos) {
		
		if (close == pairs ) {
			for (int i = 0; i < result.length; i++ ) {
				System.out.print(result[i]);
			}
			System.out.println();
		}
		else {			
						
			if (open > close ) {
				result[pos] = ")";
				
				parens(pairs, result, open, close + 1, pos + 1);
			}
			if (open < pairs) {
				result[pos] = "(";
				
				parens( pairs, result, open + 1, close, pos + 1);
			}			
		}
		
	}
	
	

}
