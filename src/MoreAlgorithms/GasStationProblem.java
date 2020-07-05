package MoreAlgorithms;
public class GasStationProblem {
	/**
	 * @param a - the gas station capacity
	 * @param b - the fuel that we waste in the way
	 * @return if there is a way to complete the road
	 * Complexity: O(n)
	 */
	public static boolean isWay(int[] a, int[] b) {
		int n = a.length;
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = a[i] - b[i];
		}
		int[] best = bestCycle(c);
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += c[(best[1]+i)%n];
			if(sum < 0) return false;
		}
		return true;
	}
	
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
