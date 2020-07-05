package Gil_All_Algorithms;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {
	double[] dist; 
	int[] color;
	int[] pred;

	public BFS(ArrayList<Integer>[] graph, int s) {
		
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
		q.add(s);
		while(!q.isEmpty()) {
			int Q_currentVertex = q.poll(); 
			for(int u : graph[Q_currentVertex]) {
				if(color[u] == 0) {	// Vertex doesn't been visited. 
					color[u] = 1;   // Assign Visited. 
					dist[u] = dist[Q_currentVertex] + 1; // Update distance. 
					pred[u] = Q_currentVertex; // define Q_currentVertex as the father. 
					q.add(u); // add the vertex to queue.
				}
			}
			color[Q_currentVertex] = 2; // poll from queue. 
		}
	}
}
