import java.util.Arrays;

// Runtime: O( n^2 ) worst case , best O( n )
// Insertion Sort implementation to learn - based on Lecture 4 - notes 
// Intuition: take elements from unsorted portion in array and place it in the correct position by inserting correct position

public class InsertionSort {

	// "InsertionSort(A)"
	public static void insertionSort(int[] A) {
		
        int n = A.length;

        // "for j = 2... n // A[1..j-1] already sorted"
        for (int i = 1; i < n; i++) {
        	
            int key = A[i]; // Element to be inserted
            int j = i - 1;

            // Shift elements of the sorted portion to the right
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }

            // Insert the key into its correct position
            A[j + 1] = key;
        }
		
	}
	// the lecture notes werent that good here so just left them out - less confusion
	public static void main(String[] args) {
		
		
		// unsorted Arr of length 40 - just auto generated from chatgpt
		int[] A = {12, 34, 7, 98, 45, 23, 67, 89, 32, 15, 76, 3, 9, 56, 72, 27, 11, 64, 18, 40, 88, 5, 36, 20, 70, 53, 29, 14, 83, 22, 94, 31, 47, 8, 77, 62, 13, 95, 50, 41};
		
		
		System.out.println("Selectio  Sort:");
		System.out.println(Arrays.toString(A));
		insertionSort(A);
		System.out.println(Arrays.toString(A));
		
	}
}
