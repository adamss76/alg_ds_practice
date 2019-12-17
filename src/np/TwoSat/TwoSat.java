package np.TwoSat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import graphs.directed.DFS.Node;
import graphs.directed.DFS.SCC;

public class TwoSat {
	
	public static boolean is_satisfiable( File constraints ) throws FileNotFoundException {
		
		Map< Integer, Node > nodes = new HashMap< Integer, Node >();
		
		Scanner in = new Scanner( constraints );
		Integer number_of_variables = Integer.parseInt( in.nextLine() );
		
		//initialize nodes for each variable
		for ( int i = 1; i <= number_of_variables; i++ ) {
			Node n = new Node( i );
			Node n_not = new Node ( i * -1 );
			
			nodes.put( n.node_id() , n );
			nodes.put( n_not.node_id(), n_not );
		}
		
		//construct implication graph
		while ( in.hasNextLine() ) {
			String[] clause = in.nextLine().split(" " );
			Integer m1_id = Integer.parseInt( clause[ 0 ] );
			Integer m2_id = Integer.parseInt( clause[ 1 ] );
			
			//edge from not_m1 to m2
			Node not_m1 = nodes.get( m1_id * -1 );
			Node m2 = nodes.get( m2_id );
			
			not_m1.add_forward_edge( m2 );
			m2.add_reverse_edge( not_m1 );
			
			//edge from not_m2 to m1
			Node not_m2 = nodes.get( m2_id * -1 );
			Node m1 = nodes.get( m1_id );
			
			not_m2.add_forward_edge( m1 );
			m1.add_reverse_edge( not_m2 );
			
		}
		
		in.close();
		
		Map< Integer, List< Node >> strongly_connected_components = SCC.find_strongly_connected_componenets_components( nodes );
		
		//decide whether or not these constraints can be satisfied
		for ( Integer leader_id : strongly_connected_components.keySet() ) {
			List< Node > component = strongly_connected_components.get( leader_id );
			
			for ( Node n : component ) {
				Node n_not = nodes.get( n.node_id() * -1 );
				if ( component.contains( n_not ) ) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static void main( String[] args ) throws Exception {
		
		File constraints1 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat1.txt" );
		File constraints2 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat2.txt" );
		File constraints3 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat3.txt" );
		File constraints4 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat4.txt" );
		File constraints5 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat5.txt" );
		File constraints6 = new File( "/Users/adamss/git_projects/practice/src/np/TwoSat/2sat6.txt" );
		
		System.out.println( "1 = " + is_satisfiable( constraints1 ) );
		System.out.println( "2 = " + is_satisfiable( constraints2 ) );
		System.out.println( "3 = " + is_satisfiable( constraints3 ) );
		System.out.println( "4 = " + is_satisfiable( constraints4 ) );
		System.out.println( "5 = " + is_satisfiable( constraints5 ) );
		System.out.println( "6 = " + is_satisfiable( constraints6 ) );
	}
}
