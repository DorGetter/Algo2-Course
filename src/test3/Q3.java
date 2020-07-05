package test3;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Q3 {
	/*
	 * Given tree and a leaf , 
	 * Create algorithm that receive a tree by adjacent list 
	 * return true if leaf on the quarter , 
	 * otherwise return false; 
	 */

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
		System.out.println("dfi"+centers.get(0));
		if (centers.size() == 2) {
			radius = r+1;
			diameter = 2*radius-1;
		} else {
			radius = r;
			diameter = 2*r;
		}
	}

///////part B
	static boolean IsVertexOnDiemeter(ArrayList<Integer>[] g,int vertex) {
		
		Fire(g);
		System.out.println(radius);
		System.out.println(diameter);
		int Index = bfs(g,vertex);
		int prev_index = Index;
		int max1 = dist[Index];
		while(prev[prev_index] != vertex) {
			prev_index = prev[prev_index];
		}
		for (int i = 0; i < g[vertex].size(); i++) {
			if(g[vertex].get(i)==prev_index) g[vertex].remove(i);
		}
		for (int i = 0; i < g[prev_index].size(); i++) {
			if(g[prev_index].get(i)==vertex) g[prev_index].remove(i);
		}
		int Index2 = bfs(g,vertex); 
		int max2 = dist[Index2];

		System.out.println("max1 "+max1 + "+max2 "+max2+"=="+diameter);
		if(Index+Index2 == diameter)
			return true; 
		
		return false; 
	}
	
	
	static int []color;	static int []prev;	static int []dist;
	static int bfs(ArrayList<Integer>[] graph , int s) {
		color =new int[graph.length];
		prev = new int[graph.length];
		dist = new int[graph.length];
		for (int i = 0; i < dist.length; i++) {
			dist[i]=0;
		}
		
		System.out.println("\n\n------------------------------------");
		for(int i =0; i< graph.length; i++) {prev[i]=0; dist[i]=0;}
		int n = graph.length;
		int[] visit = new int[n];
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		q.add(s);
		visit[s] = 1;
		dist[s]=0;
		int max =0;
		int max_index =0;
		while(!q.isEmpty()) {
			int v = q.poll();
			visit[v]=2;
			for(int u : graph[v]) {
				if(visit[u]==0) {
					visit[u] = 1;
					dist[u]=dist[v]+1;
					prev[u]=v;
					q.add(u);
					System.out.println("v: "+v);
					System.out.println("u: "+u);
					System.out.println("dist[u]: "+dist[u]);
					System.out.println("max "+ max+"\n");}
					if(dist[u]>max) {max = dist[u]; max_index =u;			
				}

			}
		}
		System.out.println("max "+ max+"\n");
		return max;
		
	}
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[10]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);		g[1].add(0);

		g[1].add(2);		g[2].add(1);

		g[2].add(3);		g[3].add(2);

		g[3].add(4);		g[4].add(3);

		g[4].add(5);		g[5].add(4);

		g[6].add(5);		g[5].add(6);
		
		g[4].add(7);		g[7].add(4);

		g[7].add(8);		g[8].add(7);
		

		//System.out.println(isOnDiameter(g, 4));
		
		System.out.println(IsVertexOnDiemeter(g, 4));

	}
}
