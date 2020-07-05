package MoreAlgorithms;
import java.util.PriorityQueue;

class Vertex implements Comparable<Vertex> {
	public int id, parent;
	public boolean isVisited;
	public double distance;
	public D_Edge[] edges;
	
	public Vertex(int id,D_Edge[] edges) {
		this.id = id;
		this.edges = edges;
		isVisited = false;
		distance = Double.POSITIVE_INFINITY;
		parent = -1;
	}
	
	public Vertex(Vertex vertex) {
		id = vertex.id;
		edges = new D_Edge[vertex.edges.length];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new D_Edge(vertex.edges[i].vertex, vertex.edges[i].weigth);
		}
		isVisited = vertex.isVisited;
		distance = vertex.distance;
		parent = vertex.parent;
	}

	@Override
	public int compareTo(Vertex v) {
		return ((Double)this.distance).compareTo(v.distance);
	}
}

class D_Edge {
	public int vertex;
	public double weigth;
	
	public D_Edge(int vertex, double weigth) {
		this.vertex = vertex;
		this.weigth = weigth;
	}
}

public class Dijkstra {
	private Vertex[] graph;
	private int start;
	
	public Dijkstra(Vertex[] g, int start) {
		this.start = start;
		this.graph = copy(g);
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		graph[start].distance = 0;
		queue.add(graph[start]);
		while(!queue.isEmpty()) {
			int v = queue.poll().id;
			graph[v].isVisited = true;
			for(D_Edge edge : graph[v].edges) {
				int u = edge.vertex;
				if(!graph[u].isVisited) {
					if(graph[u].distance > graph[v].distance + edge.weigth) {
						queue.remove(graph[u]);
						graph[u].parent = v;
						graph[u].distance = graph[v].distance + edge.weigth;
						queue.add(graph[u]);
					}
				}
			}
		}
	}

	public String getPathTo(int vertex) {
		if(vertex >= graph.length || vertex < 0) return "";
		String ans = "" + vertex;
		int v = vertex;
		while(graph[v].parent != -1) {
			ans = graph[v].parent + "->" + ans;
			v = graph[v].parent;
		}
		if(v == start) {
			return ans;
		}
		return "";
	}
	
	public String getAllDistance() {
		String ans = "[";
		for (int i = 0; i < graph.length; i++) {
			ans += graph[i].distance + (i != graph.length-1 ? "," : "");
		}
		return ans + "]";
	}
	
	private Vertex[] copy(Vertex[] g) {
		int n = g.length;
		Vertex[] temp = new Vertex[n];
		for (int i = 0; i < n; i++) {
			temp[i] = new Vertex(g[i]);
		}
		return temp;
	}
}
