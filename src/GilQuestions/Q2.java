package GilQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class Q2 {
	public static boolean isOnDiameter(ArrayList<Integer>[] tree, int x) {
		int count = 0, radius = 0;
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		int n = tree.length;
		int[] deg = new int[n];
		for (int i = 0; i < n; i++) {
			deg[i] = tree[i].size();
			if(deg[i] == 1) leaves.add(i);
		}
		boolean xFound = false;
		while(n > 2) {
			ArrayList<Integer> newLeaves = new ArrayList<Integer>();
			radius++;
			for (int v : leaves) {
				if(v == x) xFound = true;
				n--;
				deg[v] = 0;
				for(int u : tree[v]) {
					deg[u]--;
					if(deg[u] == 1) newLeaves.add(u);
				}
			}
			leaves = newLeaves;
			if(!xFound) count++;
		}
		int dist = Integer.MAX_VALUE;
		for(int c : leaves) {
			int d = bfs(tree, c, x);
			if(d < dist) dist = d;
		}
		return dist + count == radius;
	}

	private static int bfs(ArrayList<Integer>[] tree, int s, int t) {
		int n = tree.length;
		double[] dist = new double[n];
		boolean[] visit = new boolean[n];
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			visit[i] = false;
		}
		q.add(s);
		dist[s] = 0;
		visit[s] = true;
		while(!q.isEmpty()) {
			int u = q.poll();
			if(u == t) return (int)dist[u];
			for(int v : tree[u]) {
				if(!visit[v]) {
					dist[v] = dist[u] + 1;
					visit[v] = true;
					q.add(v);
				}
			}
			
		}
		return Integer.MAX_VALUE;
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
		g[7].add(6);
		g[8].add(5);g[8].add(9);
		g[9].add(8);g[9].add(10);
		g[10].add(9);
		System.out.println(isOnDiameter(g, 7));
	}
}
