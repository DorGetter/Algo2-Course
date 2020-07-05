package MoreAlgorithms;

public class Best_Matrix_Dynamic {
	/**
	 * 'Best' algorithm for matrix - the best rectangle with maximum sum
	 * Using dynamic programming
	 * Complexity: O((m*n)^2)
	 * @param matrix
	 * @return [max sum, first i_index, first j_index, last i_index, last j_index]
	 */
	public static int[] bestMatrixDynamic(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		int max = Integer.MIN_VALUE;
		int[][] help = new int[n+1][m+1];
		for (int i = 0; i < n; i++) {
			help[i+1][1] = help[i][1] + mat[i][0]; 
		}
		for (int i = 0; i < m; i++) {
			help[1][i+1] = help[1][i] + mat[0][i]; 
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				help[i+1][j+1] = help[i][j+1] + help[i+1][j] - help[i][j] + mat[i][j];
			}
		}
		int si_index = -1, ei_index = -1, sj_index = -1, ej_index = -1;
		for (int iStart = 0; iStart < n; iStart++) {
			for (int jStart = 0; jStart < m; jStart++) {
				for (int iEnd = iStart; iEnd < n; iEnd++) {
					for (int jEnd = jStart; jEnd < m; jEnd++) {
						int sum = help[iEnd+1][jEnd+1] - help[iEnd+1][jStart] - help[iStart][jEnd+1] + help[iStart][jStart];
						if(sum > max) {
							max = sum;
							si_index = iStart;
							ei_index = iEnd;
							sj_index = jStart;
							ej_index = jEnd;
						}
					}
				}
			}
		}
		return new int[] {max, si_index, sj_index, ei_index, ej_index};
	}
}
