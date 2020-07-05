package MoreAlgorithms;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Prim's algorithm for MST
 * Complexity: O(|E|*(|V|+log|V|)) - using java's Priority Queue
 * O(|E|*log|V|)) - using min-heap
 */
public class Prim {
	private final int WHITE = 0;
	private final int GRAY = 1;
	private final int BLACK = 2;
	private final int infinity = Integer.MAX_VALUE;
	
	private ArrayList<Node>[] graph;
	private PriorityQueue<Node> queue;
	private Edge[] sTree;
	private int[] parent;
	private int[] color;
	private int[] weight;
	private int n;
	private int treeWeigth;
	
	public Prim(ArrayList<Node>[] g, int root) {
		graph = copy(g);
		n = g.length;
		sTree = new Edge[n-1];
		parent = new int[n];
		color = new int[n];
		weight = new int[n];
		treeWeigth = 0;
		for (int i = 0; i < n; i++) {
			parent[i] = -1;
			color[i] = WHITE;
			weight[i] = infinity;
		}
		queue = new PriorityQueue<Node>();
		findMST(root);
		makeMST();
	}

	private void findMST(int root) {
		queue.add(new Node(root,0));
		color[root] = GRAY;
		weight[root] = 0;
		while(!queue.isEmpty()) {
			Node v = queue.poll();
			int v_id = v.id();
			for(Node u : graph[v_id]) {
				int u_id = u.id();
				int uv_weigth = u.weigth();
				if(color[u_id] == WHITE) {
					color[u_id] = GRAY;
					parent[u_id] = v_id;
					weight[u_id] = uv_weigth;
					queue.add(new Node(u_id,uv_weigth));
				}
				else if(color[u_id] == GRAY) {
					if(weight[u_id] > uv_weigth) {
						parent[u_id] = v_id;
						weight[u_id] = uv_weigth;
						queue.remove(u);
						queue.add(new Node(u_id,uv_weigth));
					}
				}
			}
			color[v_id] = BLACK;
		}
	}
	
	private void makeMST() {
		int treeSize = 0;
		for (int i = 0; i < n; i++) {
			if(parent[i] != -1) {
				sTree[treeSize++] = new Edge(i, parent[i], weight[i]);
				treeWeigth += weight[i];
			}
		}
	}

	public Edge[] getTree() {
		return sTree;
	}
	
	public int getTreeWeigth() {
		return treeWeigth;
	}
	
	private ArrayList<Node>[] copy(ArrayList<Node>[] g) {
		@SuppressWarnings("unchecked")
		ArrayList<Node>[] copy = new ArrayList[g.length];
		for (int i = 0; i < g.length; i++) {
			copy[i] = new ArrayList<Node>();
			for (int j = 0; j < g[i].size(); j++) {
				copy[i].add(new Node(g[i].get(j)));
			}
		}
		return copy;
	}
}
