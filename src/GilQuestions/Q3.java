package GilQuestions;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Q3 {
	public static int minimuEdgesToEuleran(ArrayList<Integer>[] graph) {
		ArrayList<Integer> comp = getNumberInComp(graph);
		int ans = 0;
		for (int i = 0; i < graph.length; i++) { // count odd vertices
			if(graph[i].size() % 2 == 1) ans++;
		}
		ans = ans/2;
		if(comp.size() == 1) return ans; // connected graph
		for (int c : comp) { // count even comp
			if(c == 0) ans++;
		}
		return ans;
		
	}

	private static ArrayList<Integer> getNumberInComp(ArrayList<Integer>[] graph) {
		int n = graph.length;
		boolean[] visit = new boolean[n];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			if(!visit[i]) {
				int count = 0;
				ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
				q.add(i);
				visit[i] = true;
				if(graph[i].size() % 2 == 1) count++;
				while(!q.isEmpty()) {
					int v = q.poll();
					for(int u : graph[v]) {
						if(!visit[u]) {
							if(graph[u].size() % 2 == 1) count++;
							visit[u] = true;
							q.add(u);
						}
					}
				}
				ans.add(count);
			}
		}
		return ans;
	}
}
