import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

// UnionFind Kruskal implementation to learn - Lecture Notes 13 Steurer Notes
// Pseudocode from nodes to match implementation (hopefully)
// MST Alg to construct Minimum Spanning Tree
// Main idea to always just pick the smallest/lightest edge and keep doing that (unless)
// it forms cycle until its all connected


// tried sticking to naming conventions from codeexpert 
// n = vertices amount, m = edge count, To codeexpert traffix exercise so conventions for variables a bit diff

// Runtime: O( |E| * log|E| + |V| * log|V| )
//               sorting       Union Find


public class Kruskal {

	//***************************************************************************//
	// Boilerplate Graph creation for int nodes like 0 .. n 
	//***************************************************************************//
	
	// 1:1 as in CE implementation well without a tuple just making it straight up in edge class
    class Edge implements Comparable<Edge>{
    public int u;
    public int v;
    public int w;
    
    public Edge (int u, int v, int w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }
    @Override
    public int compareTo(Edge other) {
        return this.w - other.w;
    }
  }
    
    public static List<Edge> KruskalsAlg(int n, int m, int[] A, Edge[] E) {
        Collections.sort(E); // Sort edges by weight
        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : E) {
            if (uf.union(edge.u, edge.v)) {
                mst.add(edge);
            }
            if (mst.size() == n - 1) break;
        }

        return mst;
    }
	
    public static void main(String[] args) {
    	
    	int n = 4;
    	int m = 4;
    	Edge[] E = { 
    			new Edge(0,1,2), 
    			new Edge(1,2,4), 
    			new Edge(0,2,3), 
    			new Edge(2,3,5), 
    	};
    	
    	
    	
    	
    }
    
}
