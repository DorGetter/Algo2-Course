package Gil_All_Algorithms;

import java.util.ArrayList;

public class Fire {
	int radius, diameter;
	ArrayList<Integer> centers;
	
	public Fire(ArrayList<Integer>[] graph) {
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
}
