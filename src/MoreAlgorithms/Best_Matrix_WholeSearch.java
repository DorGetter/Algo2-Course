package MoreAlgorithms;

public class Best_Matrix_WholeSearch {
	/**
	 * 'Best' algorithm for matrix - the best rectangle with maximum sum
	 * Using whole search
	 * Complexity: O((m*n)^3)
	 * @param matrix
	 * @return [max sum, first i_index, first j_index, last i_index, last j_index]
	 */
	public static int[] bestMatrixWholeSearch(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		int max = Integer.MIN_VALUE;
		int si_index = -1, ei_index = -1, sj_index = -1, ej_index = -1;
		for (int iStart = 0; iStart < n; iStart++) {
			for (int jStart = 0; jStart < m; jStart++) {
				for (int iEnd = iStart; iEnd < n; iEnd++) {
					for (int jEnd = jStart; jEnd < m; jEnd++) {
						int sum = getSum(mat, iStart, jStart, iEnd, jEnd);
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

	private static int getSum(int[][] mat, int iStart, int jStart, int iEnd, int jEnd) {
		int sum = 0;
		for (int i = iStart; i <= iEnd; i++) {
			for (int j = jStart; j <= jEnd; j++) {
				sum += mat[i][j];
			}
		}
		return sum;
	}
}
