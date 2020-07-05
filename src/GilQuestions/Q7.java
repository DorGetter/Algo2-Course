package GilQuestions;

import java.util.ArrayList;
import java.util.Arrays;

import Gil_All_Algorithms.*;



public class Q7 {
	static class Edge {
		int src, dest, weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	public static Edge[] secondMST(ArrayList<Vertex>[] g) { // O(|V||E|a)
		int n = g.length;
		Edge[] E = getArrayOfEdges(g); // O(|E|)
		Arrays.sort(E); // O(|E|log|E|)
		Edge[] t = kruskal(E,n); // O(|E|a)
		Edge[] ans = null;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < t.length; i++) { // O(|V|)
			Edge[] E2 = new Edge[E.length-1];
			int k = 0;
			for (int j = 0; j < E.length; j++) { // O(|E|)
				if(E[j] != t[i]) E2[k++] = E[j];
			}
			Edge[] t2 = kruskal(E2,n); // O(|E|a)
			int totalW = getTotalW(t2); // O(|V|)
			if(totalW < min) {
				min = totalW;
				ans = t2;
			}
		}
		return ans;
	}

	private static Edge[] kruskal(Edge[] E, int n) {
		DisjointSets DS = new DisjointSets(n);
		int k = 0;
		Edge[] T = new Edge[n-1];
		for (int i = 0; i < E.length; i++) {
			int u = E[i].src , v = E[i].dest;
			if(DS.union(u,v)) {
				T[k++] = E[i];
			}
			
		}
		return T;
	}

	private static int getTotalW(Edge[] t) {
		int sum = 0;
		for(Edge e : t) {
			sum += e.weight;
		}
		return sum;
	}

	private static Edge[] getArrayOfEdges(ArrayList<Vertex>[] g) {
		int count = 0;
		for (int i = 0; i < g.length; i++) {
			count += g[i].size();
		}
		Edge[] E = new Edge[count/2];
		int k = 0;
		for (int i = 0; i < g.length; i++) {
			for(Vertex e : g[i]) {
				E[k++] = new Edge(i, e.id, e.weight);
			}
		}
		return E;
	}
}
