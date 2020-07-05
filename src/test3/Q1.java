package test3;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Q1 {
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

	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[10]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);		g[1].add(0);
		g[1].add(2);		g[2].add(1);
		g[0].add(2);		g[2].add(0);


		g[3].add(4);		g[4].add(3);


		g[5].add(6);		g[6].add(5);
		g[6].add(7);		g[7].add(6);
		g[5].add(8);		g[8].add(5);
		g[8].add(7);		g[7].add(8);


		g[9].add(4);		g[4].add(9);
		System.out.println(minimuEdgesToEuleran(g));

	}

}