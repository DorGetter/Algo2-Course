package MoreAlgorithms;
import java.util.Arrays;


public class Best_Array {
	/**
	 * 'Best' algorithm for one dimension array
	 * Using whole search
	 * Complexity: O(n^3)
	 * @param array
	 * @return [max sum, first index, last index]
	 */
	public static int[] bestWholeSearch(int[] arr) {
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		int s_index = -1, e_index = -1;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += arr[k];
				}
				if(sum > max) {
					max = sum;
					s_index = i;
					e_index = j;
				}
			}
		}
		return new int[] {max, s_index, e_index};
	}

	/**
	 * 'Best' algorithm for one dimension array
	 * Using Dynamic programming
	 * Complexity: O(n^2)
	 * @param array
	 * @return [max sum, first index, last index]
	 */
	public static int[] bestDynamic(int[] arr) {
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		int s_index = -1, e_index = -1;
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			mat[i][i] = arr[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				mat[i][j] = mat[i][j-1] + arr[j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if(mat[i][j] > max) {
					max = mat[i][j];
					s_index = i;
					e_index = j;
				}
			}
		}
		return new int[] {max, s_index, e_index};
	}

	/**
	 * 'Best' algorithm for one dimension array
	 * Using Improved algorithm
	 * Complexity: O(n)
	 * @param array
	 * @return [max sum, first index, last index]
	 */
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
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(best(new int[] {3,-5,2,7,-4,5,-11,8})));
		System.out.println(Arrays.toString(best(new int[] {-8,-5,-2})));
		System.out.println(Arrays.toString(best(new int[] {4,-4,-2,1,-4,8})));
		System.out.println(Arrays.toString(best(new int[] {6,-1,-2,5,-8})));
	}
}
