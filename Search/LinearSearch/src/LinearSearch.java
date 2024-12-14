
// Runtime: O( n )
// Simple Linear search implementation to learn - Lecture 4 - notes 

public class LinearSearch {
	// use Integer not int to be allowed to return null as our "not found"
	
	// "LinSearch(A,b)" / A = A[1...n] can be unsorted array
	public static Integer linSearch(int[] A, int b) {
		// "for i = 1...n"
		for (int i = 0; i < A.length; i++) {
			// "if A[i] = b: return i"
			if (A[i] == b) {
				return i;
			}
		}
		// " return "nicht gefunden" "
		return null;
	}
	
	
    public static void main(String[] args) {
        System.out.println("Linear Search (just for completion) :");
        
        int[] A = {1, 5 ,9,120,213, 320}; 
        int b = 9;
        
        System.out.println("Item: " +b+ " found at position: " + linSearch(A,b));
        
    }
}
