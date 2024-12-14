import java.util.Arrays;
import java.util.ArrayList;

// Simple DFS implementation to learn
// tried sticking to naming conventions from codeexpert 
// n = vertices amount, m = edge count, E = adjacency list etc etc 

// Runtime: O( |V| + |E| )

public class Dfs {
	
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
	
	
	// lecture notes rip "Lecture 10"
	// i dont feel like return the pre post numbers and display in some fancy way
	// theyll just be printed during a loop
    public static void dfs(int n,int m, ArrayList<ArrayList<Integer>> E) {
    	// "alle Knoten unmarkiert"
    	boolean[] visited = new boolean[n];
    	
    	// pre / post nums
    	int[] T = new int[1]; // "T <- 1"
    	T[0] = 1; // as array so it can be a reference type - no pain
    	int[] pre = new int[n];
    	int[] post = new int[n];
    	
    	// "fur u0 in V (unmarkiert)"
    	for (int i = 0; i < n; i++) {
    		// unmarked edges
    		if (!visited[i]) {
    			dfsTraverse(i,E, visited,T , pre,post);
    		}
    		
    		
    	}
    	
    }
    
    public static void dfsTraverse(int u, ArrayList<ArrayList<Integer>> E, boolean[] visited, int[] T, int[] pre, int[] post) {
    	
    	// "pre[u] <- T; T <- T + 1"
    	pre[u] = T[0];
    	T[0] = T[0] + 1;
    	
    	// "markiere u"
    	visited[u] = true;
    	
    	// "FOR nachfolger v unmarkiert, unmarkiert"
    	// 		"vist(u)" -> here dfsTraverse(u)
    	
    	// E.get(u) returns a list of neighbors of u so we just iterate
    	// through those neighbors and thus explore the graph
    	for(int v: E.get(u)) {
    		// if the neighbor of u (here v) is alr explored we ignore it
    		if (!visited[v]) {
    			// we give neighbor and recursively explore whole graph
    			dfsTraverse(v,E,visited,T , pre,post);
    		}
    		
    	}
    	// "post[u] <- T; T <- T + 1"
    	post[u] = T[0];
    	T[0] = T[0] + 1;
    	
    	System.out.println("Node: " + u + " Pre N: " + pre[u]+" Post N: " + post[u]);
    	
    }
	
	
 
    // boiler plate necessary to create graph 
    public static void main(String[] args) {
    	
    	// Setup data
    	int n = 5; // Amount of vertices
    	int m = 4; // Amoount of Edges
    	// initialize graph -> create AdjacencyList 
    	// usually already done on codeexpert
    	ArrayList<ArrayList<Integer>> E = createGraph(n);
    	addEdge(0,1,E);
    	addEdge(0,2,E); // E.toString() -> [[1, 2], [], [], [], []]
    	
    	addEdge(1,3,E);
    	addEdge(1,4,E); // E.toString() -> [[1, 2], [3, 4], [], [], []]
    	
    	// ^^ adding respective edges
    	// bit messy with the as Paramater but different environment than codeexpert etcetc conceptually itd be easier/cleaner
    	// in codeexpert as its alr done there automaticaully
    	
    	
    	System.out.println("Depth-First Search starting from vertex 0:");
    	System.out.print(E.toString()+ "\n");
    	System.out.print("Visited nodes with their respective pre/post nums: \n");
    	dfs(n,m,E);
    	
    	/* Print statement here kinda misleading but doesnt affect correctness as print statement is at bottom of code block
    	 *  Intended output for n=5, m=4
    	  	Depth-First Search starting from vertex 0:
			[[1, 2], [3, 4], [], [], []]
			Visited nodes with their respective pre/post nums: 
			Node: 3 Pre N: 3 Post N: 4
			Node: 4 Pre N: 5 Post N: 6
			Node: 1 Pre N: 2 Post N: 7
			Node: 2 Pre N: 8 Post N: 9
			Node: 0 Pre N: 1 Post N: 10
    	 * 
    	 *  IMPORTANT the actual order of nodes visited is 0 1 3 4 2 , which can be read of pre, post nums 
    	 * */
    	
    
    }
    
}