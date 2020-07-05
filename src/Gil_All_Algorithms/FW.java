package Gil_All_Algorithms;


import java.util.ArrayList;
import java.util.Arrays;

public class FW {
	public static double[][] fw(int[][] graph) { // O(N^3)
		int n = graph.length;
		
		double[][] ans = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j) ans[i][j] = 0;
				else if(graph[i][j] == 0) ans[i][j] = Double.POSITIVE_INFINITY;
				else ans[i][j] = graph[i][j];
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < ans.length; i++) {
				for (int j = 0; j < ans.length; j++) {
					ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		double[][] ans = fw(new int[][] {
			 {0,3,0,8}
			,{0,0,2,1}
			,{6,0,0,0}
			,{0,0,0,0}
			
		});
		ArrayList<Edge>[] graph = new ArrayList[4];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		graph[0].add(new Edge(0, 1, -2));			graph[1].add(new Edge(1, 2, 3));			graph[2].add(new Edge(2, 3, 5));			graph[3].add(new Edge(3, 0, 1));		graph[3].add(new Edge(3, 1, 3));

	
		int[][] converted = CovertGraph_intMat(graph);
		double[][] ans2= fw(converted);
		
		for (int i = 0; i < ans.length; i++) {
			System.out.println(Arrays.toString(ans[i]));
		}	System.out.println("\n\n");
		for (int i = 0; i < ans2.length; i++) {
			System.out.println(Arrays.toString(ans2[i]));
		}
	}

	private static int[][] CovertGraph_intMat(ArrayList<Edge>[] graph) {
		int [][] res = new int[graph.length][graph.length];
		for (int i = 0; i < res.length; i++) {	for (int k = 0; k < res.length; k++) {res[i][k]=0;}	}
		
		
		for (int i = 0; i < graph.length; i++) {
			for(Edge e : graph[i]) {
				if(e.v1 == i )
					res[e.v1][e.v2] = e.weight;
				else
					res[e.v2][e.v1] = e.weight;
			}
		}
		return res;
	}
	
}
