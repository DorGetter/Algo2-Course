package MoreAlgorithms;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Finding Euler Path/Cycle in graph and returns array list contains the path or null if there is no path
 * Complexity: O(|E|) - if we save for each edges a flag for visiting instead of remove
 */
public class Euler {
	private ArrayList<Integer>[] graph, input_graph;
	private int[] deg;
	private boolean isPath, isCycle;
	private int v_start;
	
	public Euler(ArrayList<Integer>[] graph) {
		input_graph = graph;
	}
	
	private void init() {
		this.graph = copy(input_graph);
		deg = new int[graph.length];
		isPath = isCycle = false;
		v_start = 0;
		int numOfOddVertexes = 0;
		for (int i = 0; i < graph.length; i++) {
			deg[i] = graph[i].size();
			if(deg[i] % 2 == 1) {
				numOfOddVertexes++;
				v_start = i;
			}
		}
		if(numOfOddVertexes == 0) {
			isPath = isCycle = true;
		}
		else if(numOfOddVertexes == 2) {
			isPath = true;
		}
	}

	public ArrayList<Integer> eulerPath() {
		init();
		if(!isPath) {
			return null;
		}
		return eulerAlgo();
	}
	
	public ArrayList<Integer> eulerCycle() {
		init();
		if(!isCycle) {
			return null;
		}
		return eulerAlgo();
	}
	
	private ArrayList<Integer> eulerAlgo() {
		int v = v_start;
		Stack<Integer> st = new Stack<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		st.add(v);
		while(!st.isEmpty()) {
			v = st.peek();
			if(deg[v] == 0) {
				path.add(v);
				st.pop();
			}
			else {
				int u = graph[v].get(0);
				st.push(u);
				deg[v]--;
				deg[u]--;
				graph[v].remove((Integer)u);
				graph[u].remove((Integer)v);
			}
		}
		return path;
	}
	
	private ArrayList<Integer>[] copy(ArrayList<Integer>[] g) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] copy = new ArrayList[g.length];
		for (int i = 0; i < g.length; i++) {
			copy[i] = new ArrayList<Integer>();
			for (int j = 0; j < g[i].size(); j++) {
				copy[i].add(g[i].get(j));
			}
		}
		return copy;
	}
}
