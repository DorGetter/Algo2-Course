package MoreAlgorithms;
public class BestCycle {
	/**
	 * 'Best' algorithm for one dimension array - cycle
	 * Using best algorithm
	 * Complexity: O(n)
	 * @param array
	 * @return [max sum, first index, last index]
	 */
	public static int[] bestCycle(int[] arr) {
		int n = arr.length;
		int sum = 0;
		int[] neg_arr = new int[n];
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			neg_arr[i] = -arr[i];
		}
		int[] best1 = best(arr);
		int[] best2 = best(neg_arr);
		if(best1[0] < 0 || best1[0] >= sum + best2[0]) {
			return best1;
		}
		else {
			return new int[] {sum + best2[0], (best2[2] + 1)%n , best2[1] - 1};
		}
	}
	
	private static int[] best(int[] arr) {
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
