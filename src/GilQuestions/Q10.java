package GilQuestions;

import java.util.Arrays;

public class Q10 {
	public static double[][] SPwithWeightOnVerticesAndEdges(double[][] g, double[] w) {
		int n = w.length;
		double[][] ans = new double[n][n];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				if(i == j) ans[i][j] = 0;
				else ans[i][j] = w[i] + w[j] + 2*g[i][j];
			}
		}
		FW(ans);
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				ans[i][j] = (w[i] + w[j] + ans[i][j]) / 2;
			}
		}
		return ans;
	}

	private static void FW(double[][] ans) {
		for (int k = 0; k < ans.length; k++) {
			for (int i = 0; i < ans.length; i++) {
				for (int j = 0; j < ans.length; j++) {
					ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		double[][] g = {
				{0, 3, Double.POSITIVE_INFINITY, 8},
				{3, 0, 4, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, 4, 0, 1},
				{8, Double.POSITIVE_INFINITY, 1, 0}
		};
		double[] w = {3, 6, 1, 2};
		System.out.println(Arrays.deepToString(SPwithWeightOnVerticesAndEdges(g, w)));
	}
}
