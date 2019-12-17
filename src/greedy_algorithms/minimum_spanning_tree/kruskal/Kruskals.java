package greedy_algorithms.minimum_spanning_tree.kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kruskals {
	
	public static Integer max_spacing(Integer number_of_clusters, List<Integer> vertices, List<Edge> edges	) {
		Integer max_spacing = Integer.MAX_VALUE;
		Collections.sort(edges, Comparator.comparing(Edge :: length).reversed());
		UnionFind uf = new UnionFind( vertices );
		int edge_counter = edges.size() - 1;
		
		while (uf.number_of_clusters() > number_of_clusters && edge_counter >= 0 ) {
			Edge e = edges.get(edge_counter);
			List<Integer> endpoints = e.endpoints();
			Integer v1 = endpoints.get(0);
			Integer v2 = endpoints.get(1);
			
			if ( uf.find(v1) != uf.find(v2) ) {
				uf.union(v1, v2);
				edges.remove(edge_counter);
			}
			edge_counter --;
		}
		
		//find the minumum edge between clusters
		for (int i = edges.size() - 1; i >= 0 ; i--) {
			List<Integer> endpoints = edges.get(i).endpoints();
			Integer v1 = endpoints.get(0);
			Integer v2 = endpoints.get(1);
			if (uf.find(v1) != uf.find(v2) ) {
				max_spacing = edges.get(i).length();
				break;
			}
		}
		
		return max_spacing;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("/Users/adamss/git_projects/practice/src/greedy_algorithms/minimum_spanning_tree/kruskal/clustering1.txt"));
		Integer size = Integer.parseInt( in.nextLine() );
		List<Edge> edges = new ArrayList<Edge>();
		List<Integer> vertices = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList());
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			Integer v1 = Integer.parseInt(line[0]);
			Integer v2 = Integer.parseInt(line[1]);
			Integer length = Integer.parseInt(line[2]);
			
			Edge e = new Edge(v1, v2, length);
			edges.add(e);
		}
		System.out.println(max_spacing(4, vertices, edges));
		in.close();
	}
}
