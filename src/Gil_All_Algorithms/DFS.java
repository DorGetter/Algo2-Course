package Gil_All_Algorithms;

import java.util.ArrayList;

public class DFS {
	private int[] color;
	private int[] pred;

	public DFS(ArrayList<Integer>[] graph, int s) {
		int n = graph.length;
		color = new int[n];
		pred = new int[n];
		for (int v = 0; v < graph.length; v++) {
			if(color[v] == 0) {
				color[v] = 1;
				visit(graph, v);
			}
		}
	}

	private void visit(ArrayList<Integer>[] graph, int v) {
		for(int u : graph[v]) {
			if(color[u] == 0) {
				color[u] = 1;
				pred[u] = v;
				visit(graph, u);
			}
		}
	}
}
