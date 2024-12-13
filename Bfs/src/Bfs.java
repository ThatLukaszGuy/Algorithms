import java.util.Arrays;
import java.util.ArrayList;

public class Bfs {
	
	// Simple Bfs implementation to learn - Lecture 11 - notes 
	// tried sticking to naming conventions from codeexpert 
	// n = vertices amount, m = edge count, E = adjacency list etc etc 
	
	//***************************************************************************//
	// Boilerplate Graph creation for int nodes like 0 .. n 
	//***************************************************************************//
	
	public static ArrayList<ArrayList<Integer>> createGraph(int n) {
		// adjacency List E - set of edges
		ArrayList<ArrayList<Integer>> E = new ArrayList<>(n);
		
		// fill dimensions with other Arraylists so its actuall an n* adjacency list for every node 0 - n - 1 to have their list
		// of neighbors theyre poinitng to 
		for (int i = 0; i < n; i++) {
			E.add(new ArrayList<>());
		}
		
		return E;
	}
	
	
	public static void addEdge(int u, int v, ArrayList<ArrayList<Integer>> E) {
		// u is source , v is destination so like edge u -> v
		E.get(u).add(v);
		
		// for undirected , aka to have u <- v at the same time
		// E.get(v).add(u)
	}
	
	
    public static void main(String[] args) {
    	
    	System.out.println("Bfs");
    }
	
}