package MoreAlgorithms;
	
public class FloydWarshall {
	public static final double inf = Double.POSITIVE_INFINITY;
	private double[][] graph, fwMat;
	private String[][] paths;
	
	/**
	 * Floyd-Warshall algorithm
	 * Complexity: O(n^3)
	 * @param graph
	 */
	public FloydWarshall(double[][] graph) {
		int n = graph.length;
		this.graph = new double[n][n];
		this.fwMat = new double[n][n];
		this.paths = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.graph[i][j] = fwMat[i][j] = graph[i][j];
				if(this.graph[i][j] != inf) {
					paths[i][j] = "->" + j;
				}
			}
		}
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
	
	public double getPathWeight(int source, int dest) {
		return fwMat[source][dest];
	}
	
	public String getPath(int source, int dest) {
		return source + paths[source][dest];
	}
}
