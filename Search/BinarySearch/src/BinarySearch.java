
// Runtime: O( log n )
// Simple Binary  search implementation to learn - Lecture 4 - notes 

public class BinarySearch {
    
	// for sorted array  A[1] <= A[2] <= A[3] <= ... <= A[n]
	// use Integer not int to be allowed to return null as our "not found"
	public static Integer binSearch(int[] A, int b) {
		
		// "l <- 1 ,r <- n" -> A[l...r] gibt den Bereich an, dem wir noch suchen muessen"
		int l = 0;
		int r = A.length - 1;
		
		// "while l <= r do"
		while(l <= r) {
			// "m <- [ (l+r) / 2 ]" rounding down
			int m = (int) Math.floor((l+r)/2 );
			
			// "if b = A[m]: return m"
			if (b == A[m]) {
				return m;
			}
			
			// "if b < A[m]: r <- m-1 else: l <- m+1"
			if (b < A[m]) {
				r = m - 1;
			} else {
				l = m + 1;
			}
			
		}
		
		
		// "return nicht gefunden"
		return null;
	}
	
    public static void main(String[] args) {
        System.out.println("Binary Search: ");
        int[] A = {1,2,4,9,20,40,55,90,100,890};
        
        int b = 20;
        
        System.out.println("Item: " +b+ " found at position: " + binSearch(A,b));
    }
}
