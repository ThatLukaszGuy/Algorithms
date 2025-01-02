import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


// Simple Dijkstra implementation to learn - Lecture Notes 11 Steuerer Notes
// Pseudocode from nodes to match exact implementation
// shortest path from s to all t with numbered nodes 0....n



public class Dijkstra {


	
	
	public static int[] dijkstra(int n , int s, ArrayList<ArrayList<Integer>> E,ArrayList<ArrayList<Integer>> W) {
		
		int[] dist = new int[n];
		// "d[v] <- infinity , fur v in V\{s}" -> all distance initialized as unreachable or infinite 
        Arrays.fill(dist, Integer.MAX_VALUE);
		// "d[s] <- 0" except starting node itself at zero distance
		dist[s] = 0;
		
		
        boolean[] visited = new boolean[n];
		
        // "S <- leere Menge" - mit Priorityqueue
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, s}); // {distance, vertex}
        
        // "WHILE S != V"
        while( !pq.isEmpty()) {
        	
        	// "waehle v in V\S mit d[v*] minimal"
        	// the V\S part means its not taken or visited yet, handled via visited array where iteration is just skipped
        	// "S <- S u {v}" not fully faithful to lecture notes 
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];

            if (visited[u]) continue;
            visited[u] = true;
            // "FOR (v, v*) in E, v not in S"
            // going over all neighbors to node u
            for (int j = 0; j < E.get(u).size(); j++) {
                int v = E.get(u).get(j);
                // getting the respective edge weight from u to j-th neighbor
                int weight = W.get(u).get(j);

                // "d[v] <- min{ d[v] , d[v*] + c(v,v*) } "
                // check if there is some smaller / better path
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{dist[v], v});
                }
            }
        	
        }
        
        
        
		return dist;
	}
	
	
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
	
	// Logic for adding edges / creating graph
	  // E.get(i).get(j) is the j-th outgoing vertex from vertex i
	  // E.get(i).size() is the number of outgoing vertices from vertex i
	  // W.get(i).get(j) is the cost of the edge from i and the j-th neighbor of vertex i
	  // W.get(i).size() is the number of outgoing vertices from vertex i
	
	public static void addEdge(int u, int v, ArrayList<ArrayList<Integer>> E) {
		// u is source , v is destination so like edge u -> v
		E.get(u).add(v);
		
		// for undirected , aka to have u <- v at the same time
		// E.get(v).add(u)
	}
	
	public static void addWeight(int u, int w, ArrayList<ArrayList<Integer>> W) {
		// the cost of the edge going from node "u" to this [j] neighbor with weight w 
		W.get(u).add(w);
		
	}
	// same parameters as on codeexpert (without the int m edge count but its not necessary in the implementation)
	public static void main(String[] args) {
		// Setup data
    	int n = 5; // Amount of vertices
    	int m = 4; // Amoount of Edges
    	
    	//***************************************************************************//
    	// additional comments on how exactly the mapping of weight to edges works
    	//***************************************************************************//
    	
    	// initialize graph -> create AdjacencyList 
    	// usually already done on codeexpert
    	ArrayList<ArrayList<Integer>> E = createGraph(n);
    	addEdge(0,1,E);
    	addEdge(0,2,E); // E.toString() -> [[1, 2], [], [], [], []]
    	
    	addEdge(1,3,E);
    	addEdge(1,4,E); // E.toString() -> [[1, 2], [3, 4], [], [], []]
    	// the weights and edges are alr provided so this explicit impl is kinda bad but doesnt matter
    	/*
    	E = [
    	     [1, 2], // Vertex 0 -> Vertices 1 and 2
    	     [3,4],    // Vertex 1 -> Vertex 4
    	     [],     // Vertex 2 has no outgoing edges
    	     [],     // Vertex 3 has no outgoing edges
    	     []      // Vertex 4 has no outgoing edges
    	 ];

    	 W = [
    	     [4, 3], // Weights for Vertex 0 -> 1 (4), Vertex 0 -> 2 (3)
    	     [2,7],    // Weight for Vertex 1 -> 4 (7)
    	     [],     // Vertex 2 has no outgoing edges
    	     [],     // Vertex 3 has no outgoing edges
    	     []      // Vertex 4 has no outgoing edges
    	 ]; */
    	//            4      
    	//           ^
    	//			/
    	//  0 -> 1 -> 3  
    	//    \
    	//     > 2
    	ArrayList<ArrayList<Integer>> W = new ArrayList<>(n);
        W.add(new ArrayList<>(Arrays.asList(4, 3))); // Weights of edges from 0 -> 1 and 0 -> 2
        W.add(new ArrayList<>(Arrays.asList(2,7)));    // Weight of edge from 1 -> 3 and 1 -> 4    	
    	
    	// ^^ adding respective edges
    	// bit messy with the as Paramater but different environment than codeexpert etcetc conceptually itd be easier/cleaner
    	// in codeexpert as its alr done there automaticaully
    	
        
    	int start = 1; // starting node s -> dist to all t's
        int[] res = dijkstra(n, start, E ,W);
         
    	System.out.println("Dijkstra Implementation to get shortest paths:");
    	System.out.print(E.toString()+ "\n");
    	System.out.println("Starting at node: "+ start);
    	for (int i = 0; i< res.length;i++) {
    		System.out.println("to: "+ i + " is " + (res[i] == Integer.MAX_VALUE ? "∞" : res[i]) );
    	}
    	
    	
    	System.out.print("The Resulting Matrix: \n");
	}
	
	
	
}
