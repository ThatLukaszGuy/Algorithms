import java.util.Arrays;

// Runtime O ( n^2 )
// -> n^2 swaps , n^2 comparisons
// Bubble Sort implementation to learn - based on Lecture 4 - notes 
// Intuition: we just go through every element and after always swapping if condition is met to place two elements 
// in correct order, do this n times and we reach correctness

public class BubbleSort {
	
	// "BubbleSort(A)"
	public static void bubbleSort(int[] A) {
		
		int n = A.length;
		
		// "for j in 1....n"
		for (int j = 0; j < n; j++ ) { // <- necessary as one way through isnt enough
			
			// "for i in 1...n-1"
			for (int i = 0; i < n-1; i++) {
				// "if A[i] > A[i+1]"
				if (A[i] > A[i + 1]) {
					// "tausche A[i] und A[i+1]"
					int temp = A[i];
					A[i] = A[i + 1];
					A[i + 1] = temp;
					// we need temp variable to acc be able to sort it
					
				}
			}
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		// unsorted Arr of length 40 - just auto generated from chatgpt
		int[] A = {12, 34, 7, 98, 45, 23, 67, 89, 32, 15, 76, 3, 9, 56, 72, 27, 11, 64, 18, 40, 88, 5, 36, 20, 70, 53, 29, 14, 83, 22, 94, 31, 47, 8, 77, 62, 13, 95, 50, 41};
		
		
		System.out.println("Bubble Sort:");
		System.out.println(Arrays.toString(A));
		bubbleSort(A);
		System.out.println(Arrays.toString(A));
		
	}
}
