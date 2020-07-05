package GilQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge {
	int ne;
	double weight;

	public Edge(int ne, double weight) {
		this.ne = ne;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "(" + ne + ", " + weight + ")";
	}
}

public class Q5 {
	public static ArrayList<Integer> shortestPathFromLightestPath(ArrayList<Edge>[] g, int s, int t) {
		ArrayList<Edge>[] graph = changeWeights(g);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if(o1.weight < o2.weight) return -1;
				else if(o1.weight > o2.weight) return 1;
				return 0;
			}
		});
		double[] dist = new double[graph.length];
		boolean[] visit = new boolean[graph.length];
		int[] pred = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			pred[i] = -1;
			visit[i] = false;
		}
		dist[s] = 0;
		q.add(new Edge(s, 0));
		while(!q.isEmpty()) {
			Edge v = q.poll();
			if(!visit[v.ne])
			for(Edge e : graph[v.ne]) {
				if(!visit[e.ne] && dist[e.ne] > dist[v.ne] + e.weight) {
					dist[e.ne] = dist[v.ne] + e.weight;
					pred[e.ne] = v.ne;
					q.add(new Edge(e.ne, dist[e.ne]));
				}
			}
			visit[v.ne] = true;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		int v = t;
		while(v != -1) {
			path.add(0, v);
			v = pred[v];
		}
		System.out.println(Arrays.toString(dist));
		return path;
	}

	private static ArrayList<Edge>[] changeWeights(ArrayList<Edge>[] g) {
		ArrayList<Edge>[] ans = new ArrayList[g.length];
		for (int i = 0; i < g.length; i++) {
			ans[i] = new ArrayList<Edge>();
			for(Edge e : g[i]) {
				ans[i].add(new Edge(e.ne, e.weight + 1/(double)g.length));
			}
		}
		System.out.println(Arrays.toString(ans));
		return ans;
	}
	
	public static void main(String[] args) {
		ArrayList<Edge>[] g = new ArrayList[11]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Edge>();
		}
		g[0].add(new Edge(1, 1));
		g[1].add(new Edge(0, 1));g[1].add(new Edge(2, 1));g[1].add(new Edge(5, 1));
		g[2].add(new Edge(1, 1));g[2].add(new Edge(3, 1));
		g[3].add(new Edge(2, 1));
		g[4].add(new Edge(5, 1));
		g[5].add(new Edge(1, 1));g[5].add(new Edge(4, 1));g[5].add(new Edge(6, 2));g[5].add(new Edge(8, 1));
		g[6].add(new Edge(5, 2));g[6].add(new Edge(7, 2));
		g[7].add(new Edge(6, 2));g[7].add(new Edge(10, 1));
		g[8].add(new Edge(5, 1));g[8].add(new Edge(9, 1));
		g[9].add(new Edge(8, 1));g[9].add(new Edge(10, 1));
		g[10].add(new Edge(9, 1));g[10].add(new Edge(7, 1));
		System.out.println(shortestPathFromLightestPath(g, 5, 7));
	}
}
