package GilQuestions;

import java.util.ArrayList;

public class Q4 {
	ArrayList<Integer> ans;
	int[] pred, color;
	private boolean found = false;
	
	
	public void findCycle(ArrayList<Integer>[] graph) {
		color = new int[graph.length];
		pred = new int[graph.length];
		ans = new ArrayList<Integer>();
		for (int i = 0; i < graph.length; i++) {
			pred[i] = -1;
		}
		for (int i = 0; i < graph.length && !found; i++) {
			if(color[i] == 0) visit(graph, i);
		}
	}

	private void visit(ArrayList<Integer>[] graph, int i) {
		color[i] = 1;
		for(int u : graph[i]) {
			if(color[u] == 0) {
				pred[u] = i;
				visit(graph, u);
			}
			else if(u != pred[i] && color[u] == 1 && !found ) {
				int v = i;
				ans.add(u);
				while(v != u) {
					ans.add(v);
					v = pred[v];
				}
				ans.add(u);
				found = true;
			}
		}
		color[i] = 2;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[11]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);
		g[1].add(0);g[1].add(2);g[1].add(5);
		g[2].add(1);g[2].add(3);
		g[3].add(2);
		g[4].add(5);
		g[5].add(1);g[5].add(4);g[5].add(6);g[5].add(8);
		g[6].add(5);g[6].add(7);
		g[7].add(6);g[7].add(10);
		g[8].add(5);g[8].add(9);
		g[9].add(8);g[9].add(10);
		g[10].add(9);g[10].add(7);
		Q4 a = new Q4();
		a.findCycle(g);
		System.out.println(a.ans);
	}
}
