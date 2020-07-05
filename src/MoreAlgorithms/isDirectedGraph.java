package MoreAlgorithms;

public class isDirectedGraph {
	/**
	 * Is Directed graph
	 * Complexity: O(n^2)
	 * @param graph
	 * @return true if the graph is directed graph
	 */
	public static boolean isDirectedGrpah(boolean[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			for (int j = i + 1; j < graph.length; j++) {
				if(graph[i][j] != graph[j][i]) {
					return true;
				}
			}
		}
		return false;
	}
}
