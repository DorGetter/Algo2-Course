package test5;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Q1 {
	static // Need to check : 
	// is connected
	// if all ranks are even 
	// dfs/bfs
	
	
	void getEuiler() {
		int counter=0 ;
		for (ArrayList<Integer> arrayList : g) {
			if (arrayList.size() %2 == 1) {
				System.out.println("sad");;  //throw 
			}
		}
		//some Vertex  
		int s = 0 ;
		
		if (isConnected(s)) {
			makecycle(s);
		}
		else 
			System.out.println("No cycle");
		
		
	}
	
	static boolean isConnected(int s) {
		int[] color = new int [g.length]; 
		for (int i = 0; i < g.length; i++) {
			color[i] = 0 ;
		} 
		color[s] = 1; 
		
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(g.length);
		q.add(s);
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int v : g[u]) {
				if(color[v]==0) {
					color[v] = 1;
					q.add(v);
				}
				color[v] =2;
			}
			
		}	for (int i = 0; i < color.length; i++) {
			if(color[i]==0 ) {
				return false; 
			}
		}
		return true;
		
	}
	
	static void makecycle(int s) {
		Stack<Integer> S = new Stack<>();
		Stack<Integer> U = new Stack<>();
		
		int i=0;
		int []deg2 = deg;
		for (ArrayList<Integer> arrayList : g) {
			deg2[i] = arrayList.size(); i++;
			
		} 
		S.push(s); 
		while(!S.empty()) {
			
			int u = S.peek();
			if(deg2[u]==0) {
				S.pop(); U.push(u); 
			}
			else { 
				int v = g[u].get(0); 
				S.push(v); 
				g[u].remove(0);
				for (int j = 0; j < g[v].size(); j++) {
					if(g[v].get(j)==u) {
						g[v].remove(j);
					}
				}
				deg2[u] --; deg2[v]--;
			}
			
		}
		
		System.out.println(U.toString());
		
	}

	static int [] deg ;
	static ArrayList<Integer>[] g;
	public static void main(String[] args) {
		g = new ArrayList[5];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(3); g[3].add(0);
		g[0].add(4); g[4].add(0);
		g[4].add(3); g[3].add(4);
		
		g[1].add(2); g[2].add(1);
		g[1].add(0); g[0].add(1);
		g[0].add(2); g[2].add(0);
		
		deg = new int [5]; 
//		deg[0]=1; deg[1]=2; deg[2]=2; deg[3]=1;
		getEuiler();
	}
}
