package MoreAlgorithms;
import java.util.Arrays;


public class HavelHakimi {
	/**
	 * @param array of degrees
	 * @return if the degrees array represents a graph
	 * Complexity: O(log n * n^2)
	 */
	public static boolean isGraph(int[] degrees) {
		int n = degrees.length;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += degrees[i];
		}
		if(sum % 2 != 0) {
			return false;
		}
		Arrays.sort(degrees);
		for (int i = n-1; i >= 0; i--) {
			if(degrees[i] != 0) {
				int d = degrees[i];
				if(i-d < 0) return false;
				degrees[i] = 0;
				for (int j = i-1; j >= i-d; j--) {
					if(degrees[j] == 0) return false;
					degrees[j]--;
				}
				Arrays.sort(degrees,0,i-1);
			}
		}
		return true;
	}
	
	/**
	 * @param array of degrees
	 * @return if the degrees array represents a tree - connected graph without cycles
	 * Complexity: O(log n * n^2)
	 */
	public static boolean isTree(int[] degrees) {
		int sum = 0;
		for (int i = 0; i < degrees.length; i++) {
			if(degrees[i] == 0) return false;
			sum += degrees[i];
		}
		if(sum != 2*degrees.length - 2) return false;
		return isGraph(degrees);
	}
}
