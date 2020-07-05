package Gil_All_Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	class Vertex implements Comparable<Vertex> {
		private int id;
		private double dist;
		public Vertex(int v, double dist) {
			this.id = v;
			this.dist = dist;
		}
		@Override
		public int compareTo(Vertex o) {
			if(dist < o.dist) return -1;
			if(dist == o.dist) return 0;
			return 1;
		}
	}

	
	
	private double[] dist; //distances 
	private int[] pred;	   // 
	private boolean[] visit;
	int source;
	int dest;

	public Dijkstra(ArrayList<Edge>[] graph,int s, int t) {
		int n = graph.length;
		source = s;
		dest = t;
		dist = new double[n];
		pred = new int[n];
		visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			pred[i] = -1;
			visit[i] = false;
		}
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(); //sorted by distance. 
		dist[s] = 0;
		q.add(new Vertex(s, 0));
		while(!q.isEmpty()) {
			Vertex v = q.poll(); //poll first element from P.Queue.
			if(!visit[v.id]) {
				for(Edge e : graph[v.id]) {
					int u = e.v1; // the first.
					if(u == v.id) u = e.v2; //check which side of the edge we need. (v1)?<---->?(v2)
					if(!visit[u] && dist[u] > dist[v.id] + e.weight) { //if its cheaper to go in a new path (throw u).  
						dist[u] = dist[v.id] + e.weight; //update distance
						pred[u] = v.id; 				//declare V as u's Parent.
						q.add(new Vertex(u, dist[u]));  // enter u to the P.queue. 
					}
				}
				visit[v.id] = true; // mark as visited. 
			}
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] graph = new ArrayList[6];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		graph[0].add(new Edge(0, 1, 7));
		graph[0].add(new Edge(0, 2, 9));
		graph[0].add(new Edge(0, 5, 14));
		graph[1].add(new Edge(1, 0, 7));
		graph[1].add(new Edge(1, 2, 10));
		graph[1].add(new Edge(1, 3, 15));
		graph[2].add(new Edge(2, 0, 9));
		graph[2].add(new Edge(2, 1, 10));
		graph[2].add(new Edge(2, 3, 11));
		graph[2].add(new Edge(2, 5, 2));
		graph[3].add(new Edge(3, 2, 11));
		graph[3].add(new Edge(3, 4, 6));
		graph[4].add(new Edge(4, 3, 6));
		graph[4].add(new Edge(4, 5, 9));
		graph[5].add(new Edge(5, 0, 14));
		graph[5].add(new Edge(5, 2, 2));
		graph[5].add(new Edge(5, 4, 9));
		Dijkstra d = new Dijkstra(graph, 0, 4);
		System.out.println(Arrays.toString(d.dist));
	}
	
}
