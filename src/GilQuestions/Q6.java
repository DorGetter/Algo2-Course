package GilQuestions;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

class Vertex {
	int id;
	int weight;

	public Vertex(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "(" + id + ", " + weight + ")";
	}
}

public class Q6 {
	public static double[] findDist(ArrayList<Vertex>[] g, int s) {
		ArrayList<Integer>[] graph = brakeEdges(g);
		
		int n = graph.length;
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
			for(int v : graph[u]) {
				if(!visit[v]) {
					dist[v] = dist[u] + 1;
					visit[v] = true;
					q.add(v);
				}
			}
			
		}
		int[] distg = new int[g.length];
		for (int i = 0; i < distg.length; i++) {
			distg[i] = (int) dist[i];
		}
		return dist;
	}

	private static ArrayList<Integer>[] brakeEdges(ArrayList<Vertex>[] g) {
		int count = 0;
		for (int i = 0; i < g.length; i++) {
			for(Vertex v : g[i]) {
				if(v.weight == 2) count++;
			}
		}
		ArrayList<Integer>[] ans = new ArrayList[g.length + count];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = new ArrayList<Integer>();
		}
		int k = g.length;
		
		for (int i = 0; i < g.length; i++) {
			for(Vertex v : g[i]) {
				if(v.weight == 2) {
					ans[i].add(k);ans[k].add(i);
					ans[k].add(v.id);ans[v.id].add(k);
					k++;
				}
				else {
					ans[i].add(v.id); ans[v.id].add(i);
				}
			}
		}
		return ans;
	}
}
