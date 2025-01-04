
public class LGT {

	
	
	public static int dp(int[] A, int[] B) {
		int lgt = 0;
		
		// init dp table DP[n+1][n+1]
		
		int[][] DP = new int[A.length + 1][B.length + 1];
		
		DP[0][0] = 0;
		
		for (int i = 1; i < A.length; i ++) {
			
			for (int j = 1; j< B.length;j++) {
				
				// 1 + L[i - 1][j - 1] if i > 0,j > 0 and ai == bi
				if (i > 0 && j > 0 && A[i] == B[i]) {
					DP[i][j] = 1 + DP[i - 1][j - 1];
				} else {
					
					// max{ L[i - 1][j] , L[i][j - 1] } if i > 0,j > 0 ai != bi
					
					DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
				}

				

				
				
			}
			
		}
		
		return DP[A.length - 1][B.length - 1];
	}
	
	
	
	public static void main(String[] args) {
		
		int[] A = {1,3,3,4,5,9,7};
		int[] B = {1,2,3,4,5,6,7,9,8  };
		
		int lgt = dp(A,B);
		
		System.out.println("LGT of two arrays A,B: " + dp(A,B));
		
	}
	
}
