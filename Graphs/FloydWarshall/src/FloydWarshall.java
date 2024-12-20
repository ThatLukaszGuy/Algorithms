import java.util.ArrayList;
import java.util.Arrays;

// Simple Floyd Warshall implementation to learn - Lecture Notes 14 Lengler Notes
// Pseudocode from nodes to match exact implementation
// all pairs shortest path with numbered nodes 0....n

// tried sticking to naming conventions from codeexpert 
// n = vertices amount, m = edge count, E = adjacency list, W = weight list etc etc

// NOTE: (to myself) In hindsight with last codeexpert task to check for cycles or min value to change to find neg cycles
// better to not populate the diagnoal with zeroes as then its easier to read out cycles on the diagonal (maybe useful reminder for some
// future task :) )

//Runtime: O( n^3 )

public class FloydWarshall {

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
	
	// "Teilproblem: dist i [u][v] = Laenge vom kurzesten u-v-Weg, der nur zwischenknoten aus 1...i benutzen darf"
	// "FloydWarshall(G):"
	public static int[][] FloydWarshallAlg(int n,ArrayList<ArrayList<Integer>> E,ArrayList<ArrayList<Integer>> W ) {
		// "Initialisung"
		int[][] dist = new int[n][n];
 		
        // Fill the matrix with a high value (infinity)
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
		// "for u in V: dist i [u][u] <- 0"
        // we fill diagonal with zero because ... well no edges to self plus distance between e.g. node 1 and node 1 is well zero.
        // "bzw min{ 0, c(u,u) } if c(u,u) in E" as in if there ist for example direct edge from 1 to 1
		for (int i = 0; i < n; i++) {
			dist[i][i] = 0;
		}
		
        // Populate the initial distances based on E and W
		// The original line below is split for simplicity into three seperate parts. We populate first everything with value infinity
		// Then fill the diagonal with zero, and only then map the actual edges with their respective weights
		// "for u in V \ { u }: if (u,v) in E then dist i uv <- c(u,v); else dist 0 uv <- Infinity"
        for (int i = 0; i < E.size(); i++) {
            for (int j = 0; j < E.get(i).size(); j++) {
                int v = E.get(i).get(j);   // j-th outgoing vertex from i
                int weight = W.get(i).get(j); // Weight of the edge i -> to
                dist[i][v] = weight;
            }
        }
        
        // actual alg

        // "for i in 1....v"
        for (int i= 0; i < n; i++) {
        	// "for u in 1....n"
            for (int u = 0; u < n; u++) {
            	// "for v in 1....n"
                for (int v = 0; v < n; v++) {
                	// "dist i uv <- min{ d i-1 uv , (d i-1 ui) +  (d i-1 iv) }"
                	
                	// essentially if the distance from u to i is reachable (not infinity so an edge or way acc exists)
                	// And a way from i to node v also exists (analogue to before) and the sum of the paths is shorter
                	// the our initial distance between v and v we populate with this new shortest path
                	// if before it was infinity then its obv the shortest but if there was smth smaller than infinity but
                	// worse than the new value we replace it aswell since were trying to find the shortest paths
                    
                	if (dist[u][i] != Integer.MAX_VALUE && dist[i][v] != Integer.MAX_VALUE
                            && dist[u][i] + dist[i][v] < dist[u][v]) 
                    {
                    
                    	dist[u][v] = dist[u][i] + dist[i][v];
                    }
                }
            }
        }
        
		// "return dist n*n matrix with results"
		return dist;		
	}
	
	
	
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
    	
        // Resulting matrix returning shortest path from all s in |V| to all t in |V|
    	
        int[][] res = FloydWarshallAlg(n, E ,W);
         
    	System.out.println("Floyd Warshall Algorithm Implementation to get ALL shortest paths:");
    	System.out.print(E.toString()+ "\n");
    	System.out.print("The Resulting Matrix: \n");
    	
    	// print results
    	for (int i = 0;i < n;i++) {
    		
    		
    		System.out.print("Node: "+ i );
    		System.out.print(" Distance to Node: 0"+  ": "+ res[i][0] + " |");
    		System.out.print(" Distance to Node: 1"+  ": "+ res[i][1]+ " |");
    		System.out.print(" Distance to Node: 2"+  ": "+ res[i][2]+ " |");
    		System.out.print(" Distance to Node: 3"+  ": "+ res[i][3]+ " |");
    		System.out.print(" Distance to Node: 4"+  ": "+ res[i][4]+ " |");
    		
    		System.out.println("\n");
    		
    	}
    	
    	System.out.println("Shortest path matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (res[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(res[i][j] + " ");
                }
            }
            System.out.println();
        }
    	
    	
	}
	
}
