import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

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
		//E.get(v).add(u);
	}
	
	// "BFS(s)"
	// the enter leave numbers etc arent acc returned (could be tho)
	// i just want to return the BFS order itself 
	public static ArrayList<Integer> bfs(int n,ArrayList<ArrayList<Integer>> E, int s) {
		
		// enter[] , leave[] to remember when nodes enter and leave queue
 		
		// visited array as in dfs 
		boolean[] visited = new boolean[n];
 		
		// result / a bit diff than in lecture notes but just return the final order
		ArrayList<Integer> result = new ArrayList<>();
		
		
		// 
		// "Q <- {s}" we have a queue that we represent as a linked list - time efficiency ig
		Queue<Integer> queue = new LinkedList<>();
		// start node "s" first init queue ^ and now add {s} to it v
		queue.add(s);
		
		// "dist[s] <- 0"
		int[] dist = new int[n];
		dist[s] = 0;
		
		// "enter[s] <- 0; T <- 1"
		int[] enter = new int[n];
		int[] T = new int[1]; // "T <- 1"
		T[0] = 1; // as array so it can be a reference type - no pain
			
		int[] leave = new int[n];
    	
		// "WHILE != Leere Menge/ Empty"
		while (!queue.isEmpty()) {
			
			// "u <- dequeue(Q)"
			// our linkedlist acts as a queue (implements queue interface) so we can use methods like this to remove first el and return it
			int u = queue.poll();
			result.add(u);
			
			// "leave[u] <- T, T <- T + 1"
			leave[u] = T[0];
			T[0] = T[0] + 1;
		
			// "FOR (u,v) element/in E , enter[v] nicht zugewiesen (node cannot enter queue multiple times)"
			for (int v: E.get(u)) {
				
				//  same as dfs impl logic
				if (!visited[v]) {
					// "enqueue(Q,v)"
					queue.add(v);
					
					// "dist[v] <- dist[u] + 1"
					dist[v] = dist[u] + 1;
					
					// "enter[v] <- T; T <- T + 1"
					enter[v] = T[0];
					T[0] = T[0] + 1;
					
					// visited v 
					visited[v] = true;
					
				}
			}
		
				
			
		}
		// for simplicity sake so i dont have to return and process multiple lists w these values ill just print them at the end
		// before returning 
		// somehow present all arrays enter, leave, dist
		
		// not part of official implementation just nice visualization output in console
		// distance array
		System.out.println("\nDistance of other nodes to node " + s+"\n");
		for (int i = 0; i < n; i++) {
			System.out.println("Dist from Node " + s + " to Node " + i + ": " + dist[i]);
		}
		
		
		System.out.println("\nEnter and leave numbers\n");
		for (int i = 0; i < n; i++) {
			System.out.println("Node " + i + ": Enter: " + enter[i] + " Leave: " + leave[i]);	
		}
		
		
		
		return result;
		
	}
	
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
    	
    	//            4
    	//           ^
    	//			/
    	//  0 -> 1 -> 3  
    	//    \
    	//     > 2
    	
    	
    	System.out.println("Breadth-First Search starting from vertex 0:");
    	System.out.print(E.toString()+ "\n");
    	// ig we can assume we start at node 0 but could at any other
    	
    	System.out.println("\nFinal Bfs Order: " + bfs(n,E,0).toString());
    
    	/* Expected output should look smth like that
    	 * Breadth-First Search starting from vertex 0:
			[[1, 2], [3, 4], [], [], []]
			
			Distance of other nodes to node 0
			
			Dist from Node 0 to Node 0: 0
			Dist from Node 0 to Node 1: 1
			Dist from Node 0 to Node 2: 1
			Dist from Node 0 to Node 3: 2
			Dist from Node 0 to Node 4: 2
			
			Enter and leave numbers
			
			Node 0: Enter: 0 Leave: 1
			Node 1: Enter: 2 Leave: 4
			Node 2: Enter: 3 Leave: 7
			Node 3: Enter: 5 Leave: 8
			Node 4: Enter: 6 Leave: 9
			
			Final Bfs Order: [0, 1, 2, 3, 4]
    	 */
    	
    	
    
    }
	
}