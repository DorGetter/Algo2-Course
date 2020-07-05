package Gil_All_Algorithms;import java.util.ArrayList;
import java.util.PriorityQueue;

public class BiDijkstra {
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

	private double[] dist;
	private boolean[] visit;
	private double[] distR;
	private boolean[] visitR;
	
	int source;
	int dest;
	private double ans;
	
	public BiDijkstra(ArrayList<Edge>[] graph,int s, int t) {
		ArrayList<Edge>[] reverseGraph = reverseGraph(graph);
		int n  = graph.length;
		source = s;
		dest   = t;
		dist   = new double[n];
		distR  = new double[n]; 
		visit  = new boolean[n];
		visitR = new boolean[n];
		for (int i = 0; i < n; i++) {
			dist [i]  = distR [i]  = Double.POSITIVE_INFINITY;
			visit[i]  = visitR[i]  = false;
		}
		PriorityQueue<Vertex> q  = new PriorityQueue<Vertex>();
		PriorityQueue<Vertex> qR = new PriorityQueue<Vertex>();
		dist[s] = 0; distR[t] = 0;
		q.add(new Vertex(s, 0));
		qR.add(new Vertex(t, 0));
		boolean isFinnish = false;
		while(!isFinnish) {
			/**
			 *       0     1     2
  			 *     [ v1  , v2  , v3  ]
  			 *     	 |		|	   |
  			 *     	 |  	 |
			 */
			Vertex v = q.poll();
			if(!visit[v.id]) {
				for(Edge e : graph[v.id]) {
					int u = e.v1;
					if(u == v.id) u = e.v2; // בחירת הצד שהוא השכן
					if(!visit[u] && dist[u] > dist[v.id] + e.weight) {
						dist[u] = dist[v.id] + e.weight;
						q.add(new Vertex(u, dist[u]));
					}
				}
				visit[v.id] = true;
			}
			Vertex vR = qR.poll();
			if(!visitR[vR.id]) {
				for(Edge e : reverseGraph[vR.id]) {
					int u = e.v1;
					if(u == vR.id) u = e.v2; // בחירת הצד שהוא השכן
					if(!visitR[u] && distR[u] > distR[vR.id] + e.weight) {
						distR[u] = distR[vR.id] + e.weight;
						qR.add(new Vertex(u, distR[u]));
					}
				}
				visitR[vR.id] = true;
			}

			if((visit[v.id] && visitR[v.id]) || (visit[vR.id] && visitR[vR.id])) isFinnish = true;
		}
		/*					s	 <--      t

		*/
		ans = Double.POSITIVE_INFINITY;
		//find the minimal overlap position. 
		for (int i = 0; i < n; i++) {
			if(dist[i] + distR[i] < ans) ans = dist[i] + distR[i];
		}
	}
	
	private ArrayList<Edge>[] reverseGraph(ArrayList<Edge>[] graph) {
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] rev = new ArrayList[graph.length];
		for (int i = 0; i < graph.length; i++) { rev[i] = new ArrayList<Edge>(); } //initialized
		/*
		 * To reverse the graph . 
		 * check if i'm the destination make me target , do opposite otherwise .  
		 */
		for (int i = 0; i < graph.length; i++) {
			for(Edge e : graph[i]) {
				if(e.v1 == i) rev[e.v2].add(new Edge(e.v2, e.v1, e.weight));
				else rev[e.v1].add(new Edge(e.v1, e.v2, e.weight));
			}
		}
		return rev;
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
		BiDijkstra d = new BiDijkstra(graph, 0, 4);
		System.out.println(d.ans);
	}
}
