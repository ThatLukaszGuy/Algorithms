import java.util.Arrays;

//Runtime O ( n^2 )
//-> n swaps , n^2 comparisons
// Selection Sort implementation to learn - based on Lecture 4 - notes 
// Intuition split array into sorted and unsorted parts, find largest element, put it at the back of the array/
// int "sorted" part and repeat until array is sorted

public class SelectionSort {
	
	// "Selectionsort(A)"
	public static void selectionSort(int[] A) {
		int n = A.length;

        // "for j= 1..n"
        for (int i = 0; i < n - 1; i++) {
            int k = 0; // "Index des maximums in A[1...n-j+1]"

            // actually finding that max item index
            for (int j = 0; j < n - i; j++) {
                if (A[j] > A[k]) {
                    k = j;
                }
            }

            // "tausche A[k] mit A[n-j+1]"
            int temp = A[k];
            A[k] = A[n - i - 1];
            A[n - i - 1] = temp;
        }
	}
	
	
	
	public static void main(String[] args) {
		
		
		// unsorted Arr of length 40 - just auto generated from chatgpt
		int[] A = {12, 34, 7, 98, 45, 23, 67, 89, 32, 15, 76, 3, 9, 56, 72, 27, 11, 64, 18, 40, 88, 5, 36, 20, 70, 53, 29, 14, 83, 22, 94, 31, 47, 8, 77, 62, 13, 95, 50, 41};
		
		
		System.out.println("Selection  Sort:");
		System.out.println(Arrays.toString(A));
		selectionSort(A);
		System.out.println(Arrays.toString(A));
		
	}

}
