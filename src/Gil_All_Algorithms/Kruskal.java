package Gil_All_Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
	ArrayList<Edge> ans;
	
	public Kruskal(Edge[] graph) {
		Arrays.sort(graph);
		int n = maxVertex(graph);
		DisjointSets u = new DisjointSets(n);
		ans = new ArrayList<Edge>();
		for (int i = 0; i < graph.length && ans.size() < n - 1; i++) {
			Edge e = graph[i];
			if(u.union(e.v1, e.v2)) ans.add(e);
		}
	}

	private int maxVertex(Edge[] graph) {
		int max = 0;
		for(Edge e : graph) max = Math.max(max, Math.max(e.v1, e.v2));
		return max;
	}
}
