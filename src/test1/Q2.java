package test1;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Given tree and a leaf , 
 * Create algorithm that receive a tree by adjacent list 
 * return true if leaf on the quarter , 
 * otherwise return false; 
 */
public class Q2 {
	public static boolean isOnDiameter(ArrayList<Integer>[] tree, int x) {
		Fire(tree); 
		for(int c : centers) {
			int dist = bfs(tree,c,x);
			System.out.println("dist :"
					+dist);
			if(dist == radius ) {
				return true; 
			}
		}
		
		return false ;
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
	static int radius;
	static int diameter;
	static ArrayList<Integer> centers;
	
	static public void Fire(ArrayList<Integer>[] graph) {
		int n = graph.length;
		int[] deg = new int[n];
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		ArrayList<Integer> newLeaves = new ArrayList<Integer>();
		int r = 0;
		for (int i = 0; i < n; i++) {
			deg[i] = graph[i].size();
			if (deg[i] == 1) leaves.add(i);
		}
		while (n > 2) {
			newLeaves = new ArrayList<Integer>();
			r++;
			while (!leaves.isEmpty()) {
				int u = leaves.remove(0);
				deg[u]--;
				if (deg[u] == 0) n--;
				for(int v : graph[u]) {
					if(deg[v] != 0) {
						deg[v]--;
						if (deg[v] == 1) { newLeaves.add(v); }
					}
				}
			}
			leaves = newLeaves;	
		}
		centers = leaves;
		if (n == 2) {
			radius = r+1;
			diameter = 2*radius-1;
		} else {
			radius = r;
			diameter = 2*r;
		}
	}
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[7]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);		g[1].add(0);
		
		g[1].add(2);		g[2].add(1);
		
		g[1].add(3);		g[3].add(1);
		
		g[3].add(4);		g[4].add(3);
		
		g[5].add(3);		g[3].add(5);
		
		g[6].add(5);		g[5].add(6);
		
		System.out.println(isOnDiameter(g, 2));
		

	}
}











