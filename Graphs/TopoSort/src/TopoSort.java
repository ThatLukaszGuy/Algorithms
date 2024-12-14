import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.HashSet;

//Simple TopoSort implementation to learn - Lecture 10 - notes 
//tried sticking to naming conventions from codeexpert 
// two impls of topo sort both backed by dfs -> ill leave out pre post numbers -> cleaner
// simple Impl for nodes like 0 ... n
// more sofisticated for nodes like A,B,C,D .... Z etc using hash maps (also good for eprog)


public class TopoSort {
	
	
	//***************************************************************************//
	// Boilerplate Graph creation for int nodes like 0 .. n + said topo sort impl
	//***************************************************************************//
	
	// n = vertices amount, m = edge count, E = adjacency list etc etc 
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
	
	public static LinkedList<Integer> dfsInt(int n, ArrayList<ArrayList<Integer>> E) {
    	// "alle Knoten unmarkiert"
    	boolean[] visited = new boolean[n];
		
    	// Topo sort als Stack darstellen
    	LinkedList<Integer> stack = new LinkedList<>();
    	
    	// "fur u0 in V (unmarkiert)"
    	for (int i = 0; i < n; i++) {
    		// unmarked edges
    		if (!visited[i]) {
    			dfsIntTraverse(i,E, visited, stack);
    		}	
    	}
    	
    	return stack;
    	
    	
	}
	
	public static void dfsIntTraverse(int u, ArrayList<ArrayList<Integer>> E, boolean[] visited,LinkedList<Integer> stack ) {
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
    			dfsIntTraverse(v,E,visited, stack);
    		}
    		
    	}
    	// "Fuge u zur Top Sortierung hinzu"
    	stack.add(u);
    	
	}
	
	//***************************************************************************//
	// Topo Sort for String (easily changeable to arbitrary)
	// nodes like A,B, ... , Z etc using hashmaps
	//***************************************************************************//
	
	public static LinkedList<String> dfs(Map<String, List<String>> graph) {
		
    	// Topo sort als Stack darstellen
    	LinkedList<String> stack = new LinkedList<>();
		
    	// our visited array but as a Hashset
    	Set<String> visited = new HashSet<>();
    	
    	
    	for (String u: graph.keySet()) {
    		
    		// our visited array holds unique strings , our nodes and 
    		// we chekck if we visited them
    		if (!visited.contains(u)) {
    			dfsTraverse(u, graph, visited , stack);
    		}
    		
    	}
    	
    	
    	
    	return stack;
	}
	
	public static void dfsTraverse(String u,Map<String, List<String>> graph, Set<String> visited, LinkedList<String> stack) {
		// "markiere u"
		visited.add(u);
		
		
    	// "FOR nachfolger v unmarkiert, unmarkiert"
    	// 		"vist(u)" -> here dfsTraverse(u)
        for (String v : graph.getOrDefault(u, new ArrayList<>())) {
            if (!visited.contains(v)) {
                dfsTraverse(v, graph, visited, stack);
            }
        }
		
		// "Fuge u zur Top Sortierung hinzu"
    	stack.addFirst(u);
	}
	
	
    public static void main(String[] args) {
    	
    	
    	//***************************************************************************//
    	// Graph with nodes as nums 
    	//***************************************************************************//
    	
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
    	
    	//            4
    	//           ^
    	//			/
    	//  0 -> 1 -> 3  
    	//    \
    	//     > 2
    	//
    	// ^^ adding respective edges
    	// bit messy with the as Paramater but different environment than codeexpert etcetc conceptually itd be easier/cleaner
    	// in codeexpert as its alr done there automaticaully
    	
    	
    	System.out.println("TopoSort starting from vertex 0 (based on DFS):");
    	System.out.println("\n"+E.toString());
    	System.out.println("    	//           4\n"
    			+ "    	//          ^\n"
    			+ "    	//	   / \n"
    			+ "    	//  0 -> 1 -> 3  \n"
    			+ "    	//    \\\n"
    			+ "    	//     > 2\n");
    	System.out.print("TopoSort: ");
    	
    	LinkedList<Integer> TopoSort = dfsInt(n,E);
    	for (int i = n - 1; i >= 0 ; i--) {
    		System.out.print(TopoSort.get(i) + " ");
    	}
    	System.out.print("\n\n");
    	
    	//***************************************************************************//
    	// Topo Sort for String (easily changeable to arbitrary)
    	// nodes like A,B, ... , Z etc using hashmaps
    	//***************************************************************************//
    	
    	
    	// Set up a bit different 
    	// map containing String Node and its Neighbors
    	/*
    	    'A': ['B', 'C'],
		    'B': ['D'],
		    'C': ['D'],
		    'D': [],
		    'E': ['A', 'F'],
		    'F': ['C']
		    
		    E → A → B → D
			     ↘ C ↘ D
			F → C
    	 * */
    	
    	Map<String, List<String>> graph = new HashMap<>();
    	// Hashmap method -> Arrays.asList adds ArrayList with those exact elements
    	graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Arrays.asList());
        graph.put("E", Arrays.asList("A", "F"));
        graph.put("F", Arrays.asList("C"));
    	System.out.println("TopoSort starting from vertex A (based on DFS): \n");
    	System.out.println("		    E → A → B → D\n"
    			+ "			     ↘ C ↘ D\n"
    			+ "			F → C\n");
    	
    	System.out.print("TopoSort: " + dfs(graph));
    }
	
}