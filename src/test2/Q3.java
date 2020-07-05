package test2;

import Gil_All_Algorithms.FW;

//G(V,E) undirected connected and weightVed graph (on the vertexes). 

public class Q3 {
	static int weightV[] ; 
	static int weightE[] ; 


	static double[][]  distVert(int[][] graph) {
		int [][] floyed = new int [graph.length][graph.length];
		//arrange graph 
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j]!=0) {
					floyed[i][j] = weightV[i]+weightV[j];
				}

			}
		}
		return fw(floyed);
	}

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
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				if(i==j) ans[i][j]=0;
				else 
					ans[i][j] = (ans[i][j]+weightV[i]+weightV[j])/2;
				
			}
		}

		return ans;
	}

	static double[][]  distVert2(int[][] graph) {
		int [][] floyed = new int [graph.length][graph.length];
		//arrange graph 
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j]!=0) {
					floyed[i][j] = weightV[i]+weightV[j]+(graph[i][j]*2);
				}

			}
		}
		return fw2(floyed);
	}

	public static double[][] fw2(int[][] graph) { // O(N^3)
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
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				if(i==j) ans[i][j]=0;
				else 
					ans[i][j] = (ans[i][j]+weightV[i]+weightV[j])/2;
				
			}
		}

		return ans;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		int[][] graph = {
				{0,1,0,0,0},
				{1,0,1,1,1},
				{0,1,0,1,0},
				{0,1,1,0,0},
				{0,1,0,0,0}
		};
		weightV = new int [5]; 
		weightV[0]=3;
		weightV[1]=2;
		weightV[2]=5;
		weightV[3]=1;
		weightV[4]=8;
		
		
////		double [][] ans = distVert(graph);
//		System.out.print("[");
//		for (int i = 0; i < ans.length; i++) {
//			for (int j = 0; j < ans.length; j++) {
//				System.out.print(ans[i][j]+"  \t");
//			}
//			System.out.print("]\n[");
//		}
		
		System.out.println();
		int[][] graph2 = {
				{0,4,0,0},
				{4,0,2,0},
				{0,2,0,1},
				{0,0,1,0},
				
		};
		weightV = new int [4]; 
		weightV[0]=2;
		weightV[1]=5;
		weightV[2]=3;
		weightV[3]=7;
		double [][] ans2 = distVert2(graph2);
		System.out.print("[");
		for (int i = 0; i < ans2.length; i++) {
			for (int j = 0; j < ans2.length; j++) {
				System.out.print(ans2[i][j]+"  \t");
			}
			System.out.print("]\n[");
		}
	}

}
