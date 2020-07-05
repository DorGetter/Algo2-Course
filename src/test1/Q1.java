package test1;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Given graph as a adjacent list 
 * Return true if G has a simple circule 
 * Return false otherwise./  
 */
public class Q1 {
	//								A						///
	static double[] dist; 
	static int[] color;
	static int[] pred;

	static boolean checkSimpleCircle (ArrayList<Integer>[] graph, int s) {
		int n = graph.length; //length of graph .
		dist  = new double[n]; 
		color = new int[n];  // has been visited. 
		pred  = new int[n];  // who's your daddy? 
		for (int i = 0; i < graph.length; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			color[i] = 0;
			pred[i] = -1;
		}
		//initialized starting point. 
		dist[s]  = 0; 
		color[s] = 1; //in queue. 

		/*
		 * color 0 = wasn't visited + not in queue.
		 * color 1 = wasn't visited but already in queue.
		 * color 2 = visited and already polled from queue. 
		 */
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		q.add(s); boolean hasCycle= false;
		while(!q.isEmpty()&& !hasCycle) {
			int Q_currentVertex = q.poll(); 
			for(int u : graph[Q_currentVertex]) {
				if(color[u] == 0) {	// Vertex doesn't been visited. 
					color[u] = 1;   // Assign Visited. 
					dist[u] = dist[Q_currentVertex] + 1; // Update distance. 
					pred[u] = Q_currentVertex; // define Q_currentVertex as the father. 
					q.add(u); // add the vertex to queue.
				}
				else {
					return true; 
				}
			}
			color[Q_currentVertex] = 2; // poll from queue. 
		}
		return false;
	}
//////////////////   		B 				///////
	
	
	static ArrayList<Integer> ans;
	static int[] pred2, color2;
	static  boolean found = false;
	
	
	static public void findCycle(ArrayList<Integer>[] graph) {
		color2 = new int[graph.length];
		pred2 = new int[graph.length];
		ans = new ArrayList<Integer>();
		for (int i = 0; i < graph.length; i++) {
			pred2[i] = -1;
		}
		for (int i = 0; i < graph.length && !found; i++) {
			if(color2[i] == 0) visit(graph, i);
		}
	}

	static private void visit(ArrayList<Integer>[] graph, int i) {
		color2[i] = 1;
		for(int u : graph[i]) {
			if(color2[u] == 0) {
				pred2[u] = i;
				visit(graph, u);
			}
			else if(u != pred2[i] && color2[u] == 1 && !found ) {
				int v = i;
				ans.add(u);
				while(v != u) {
					ans.add(v);
					v = pred2[v];
				}
				ans.add(u);
				found = true;
			}
		}
		color2[i] = 2;
	}
	
	
	
	public static void main(String[] args) {
//A
		ArrayList<Integer> mat[]= new ArrayList[5];
		mat[0]=new ArrayList<Integer>();
		mat[1]=new ArrayList<Integer>();
		mat[2]=new ArrayList<Integer>();
		mat[3]=new ArrayList<Integer>();
		mat[4]=new ArrayList<Integer>();
		mat[0].add(1);
		mat[1].add(2);
		mat[2].add(3);
		mat[3].add(4); 
		mat[4].add(2);
		checkSimpleCircle(mat,0);
//B
		
		findCycle(mat);
		System.out.println(ans);
	}
}

