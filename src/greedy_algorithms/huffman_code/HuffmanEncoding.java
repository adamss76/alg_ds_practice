package greedy_algorithms.huffman_code;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncoding {
	private static String max = "";
	private static String min = "";
	
	public static void encode_alphabet(List<TreeNode> alphabet) {
		PriorityQueue<TreeNode> h = new PriorityQueue<TreeNode>( Comparator.comparing(TreeNode :: weight) );
		
		h.addAll(alphabet);
		
		while ( h.size() > 1 ) {
			TreeNode t1 = h.remove();
			TreeNode t2 = h.remove();
			TreeNode merged = merge_tree(t1, t2);
			h.add( merged );
		}
		
		TreeNode root = h.remove();
		
		print_codes(root, "");
	}
	
	public static TreeNode merge_tree(TreeNode t1, TreeNode t2) {
		TreeNode internal_node = new TreeNode(null, t1, t2, t1.weight() + t2.weight() );
		t1.set_parent(internal_node);
		t2.set_parent(internal_node);
		
		return internal_node;
	}
	
	public static void print_codes(TreeNode t, String encoding) {
		
		if (t.left() == null && t.right() == null ) {
			t.set_encoding(encoding);
			System.out.println(t.label + ": " + t.encoding() );
			if (encoding.length() > max.length() ) {
				max = encoding;
			}
			if ( encoding.length() < min.length() || min.length() == 0 ) {
				min = encoding;
			}
			return;
		}
		
		print_codes(t.left(), encoding + "0");
		
		print_codes(t.right(), encoding + "1");
	}
	
	public static void main( String[] args ) throws Exception {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/greedy_algorithms/huffman_code/huffman.txt"));
		List<TreeNode> alphabet = new ArrayList<TreeNode>();
	
		in.nextLine();
		
		while ( in.hasNext() ) {
			Integer weight = in.nextInt();
			TreeNode leaf = new TreeNode(null, null, null, weight);
			alphabet.add( leaf );
		}
		
		encode_alphabet(alphabet);
		System.out.println("max encoding = " + max.length());
		System.out.println("min encoding = " + min.length());
	}
	
}
