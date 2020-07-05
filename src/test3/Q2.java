package test3;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Q2 {



	private static boolean isGraphTree(ArrayList<Integer>[] graph) {

		int n = graph.length;
		int[] visit = new int[n];

		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		q.add(0);
		visit[0] = 1;
		int counter =0;
		while(!q.isEmpty()) {
			int v = q.poll();
			visit[v]=2;
			counter++;
			for(int u : graph[v]) {
				if(visit[u]==0) {
					visit[u] = 1;
					q.add(u);
				}
				else if(visit[u]==1) {
					return false; 
				}
			}
		}
		if(counter != graph.length) return false;
		return true;

	}




	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[5]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);		g[1].add(0);
		g[1].add(2);		g[2].add(1);
		g[2].add(3);		g[3].add(2);
		g[3].add(4);		g[4].add(3);


		System.out.println(isGraphTree(g));
	}


}
