package GilQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class Q9 {
	public static int[] paintTree(ArrayList<Integer>[] tree) {
		int n = tree.length;
		int[] color = new int[n];
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		q.add(0);
		color[0] = 1;
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int u : tree[v]) {
				if(color[u] == 0) {
					color[u] = 3 - color[v];
					q.add(u);
				}
			}
		}
		return color;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[11]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);
		g[1].add(0);g[1].add(2);g[1].add(5);
		g[2].add(1);g[2].add(3);
		g[3].add(2);
		g[4].add(5);
		g[5].add(1);g[5].add(4);g[5].add(6);g[5].add(8);
		g[6].add(5);g[6].add(7);
		g[7].add(6);
		g[8].add(5);g[8].add(9);
		g[9].add(8);g[9].add(10);
		g[10].add(9);
		System.out.println(Arrays.toString(paintTree(g)));
	}
}
