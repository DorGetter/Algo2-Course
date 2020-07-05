package MoreAlgorithms;

/**
 * Floyd-Warshall algorithm with weight on vertices
 * Complexity: O(n^3)
 * @param graph and the vertices weight
 */
public class FW_WeightOnVertexes {
	public static final double inf = Double.POSITIVE_INFINITY;
	private boolean[][] graph;
	private double[][] fwMat;
	private String[][] paths;
	private int n;
	private double[] weight;

	public FW_WeightOnVertexes(boolean[][] graph,double[] weight) {
		this.weight = weight;
		n = graph.length;
		this.graph = new boolean[n][n];
		this.fwMat = new double[n][n];
		this.paths = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.graph[i][j] = graph[i][j];
				this.fwMat[i][j] = graph[i][j] ? (weight[i] + weight[j]) : inf;
				if(this.fwMat[i][j] != inf) {
					paths[i][j] = "->" + j;
				}
				if(i == j) fwMat[i][j] = 0;
			}
		}
		floydWarshall();
		fixMatrix();
	}

	private void floydWarshall() {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(fwMat[i][j] > fwMat[i][k] + fwMat[k][j]) {
						fwMat[i][j] = fwMat[i][k] + fwMat[k][j];
						paths[i][j] = paths[i][k] + paths[k][j];
					}
				}
			}
		}
	}
	
	public void fixMatrix() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fwMat[i][j] = (fwMat[i][j] + weight[i] + weight[j])/2;
			}
		}
	}

	public double getPathWeight(int source, int dest) {
		return fwMat[source][dest];
	}

	public String getPath(int source, int dest) {
		return source + paths[source][dest];
	}
}
