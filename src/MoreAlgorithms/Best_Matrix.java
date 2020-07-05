package MoreAlgorithms;

public class Best_Matrix {
	/**
	 * 'Best' algorithm for matrix - the best rectangle with maximum sum
	 * Using BEST algorithm
	 * Complexity: O(n*m^2)
	 * @param matrix
	 * @return [max sum, first i_index, first j_index, last i_index, last j_index]
	 */
	public static int[] bestMatrix(int[][] mat) {
		int si_index = -1, ei_index = -1, sj_index = -1, ej_index = -1;
		int n = mat.length;
		int m = mat[0].length;
		int max = Integer.MIN_VALUE;
		int[][] help = new int[n][m+1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				help[i][j+1] = help[i][j] + mat[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = i; j < m; j++) {
				int[] temp = new int[n];
				for (int k = 0; k < n; k++) {
					temp[k] = help[k][j+1] - help[k][i];
				}
				int[] best = best(temp);
				if(best[0] > max) {
					max = best[0];
					si_index = best[1];
					ei_index = best[2];
					sj_index = i;
					ej_index = j;
				}
			}
		}
		return new int[] {max, si_index, sj_index, ei_index, ej_index};
	}
	
	public static int[] best(int[] arr) {
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		int s_i = 0, s_index = -1, e_index = -1;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if(sum > max) {
				max = sum;
				s_index = s_i;
				e_index = i;
			}
			if(sum < 0) {
				sum = 0;
				s_i = i + 1;
			}
		}
		return new int[] {max, s_index, e_index};
	}
}
