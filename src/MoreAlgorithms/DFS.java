package MoreAlgorithms;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Depth-first search
 * Complexity: O(|V|+|E|)
 */
public class DFS {
	public static final int white = 0 , gray = 1, black = 2;
	private int[] color, first, last, pred;
	private ArrayList<Integer>[] graph;
	private int size, time;
	
	public DFS(ArrayList<Integer>[] graph) {
		this.graph = copy(graph);
		size = graph.length;
		color = new int[size];
		first = new int[size];
		last = new int[size];
		pred = new int[size];
		Arrays.fill(pred, -1);
		time = 0;
		for (int i = 0; i < size; i++) {
			if(color[i] == white) {
				visitDFS(i);
			}
		}
	}
	
	private void visitDFS(int v) {
		first[v] = ++time;
		color[v] = gray;
		for(int u : graph[v]) {
			if(color[u] == white) {
				pred[u] = v;
				visitDFS(u);
			}
		}
		color[v] = black;
		last[v] = ++time;
	}

	private ArrayList<Integer>[] copy(ArrayList<Integer>[] g) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] copy = new ArrayList[g.length];
		for (int i = 0; i < g.length; i++) {
			copy[i] = new ArrayList<Integer>();
			for (int j = 0; j < g[i].size(); j++) {
				copy[i].add(g[i].get(j));
			}
		}
		return copy;
	}
}
