package GilQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class Q1 {
	public static int[] numberOfShortestPath(ArrayList<Integer>[] graph, int s) {
		int n = graph.length;
		int[] numOfPaths = new int[n];
		double[] dist = new double[n];
		boolean[] visit = new boolean[n];
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			numOfPaths[i] = 0;
			dist[i] = Double.POSITIVE_INFINITY;
			visit[i] = false;
		}
		q.add(s);
		dist[s] = 0;
		visit[s] = true;
		numOfPaths[s] = 1;
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int v : graph[u]) {
				if(visit[v] && dist[u] + 1 == dist[v]) {
					numOfPaths[v] += numOfPaths[u];
				}
				else if(!visit[v]) {
					dist[v] = dist[u] + 1;
					numOfPaths[v] = numOfPaths[u];
					visit[v] = true;
					q.add(v);
				}
			}
			
		}
		return numOfPaths;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[6]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);g[0].add(2);
		g[1].add(0);g[1].add(3);
		g[2].add(0);g[2].add(3);g[2].add(4);
		g[3].add(1);g[3].add(2);g[3].add(5);
		g[4].add(2);g[4].add(5);
		g[5].add(3);g[5].add(4);
		System.out.println(Arrays.toString(numberOfShortestPath(g, 0)));
	}
}
