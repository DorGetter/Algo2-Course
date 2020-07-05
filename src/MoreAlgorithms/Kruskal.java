package MoreAlgorithms;
import java.util.Arrays;

/**
 * Kruskal's algorithm for MST
 * Complexity: O(|E|log|V|) - using disjointsets
 */
public class Kruskal {
	private Edge[] graph, sTree;
	private DisjointSets vgroup;
	private int vertices, treeSize;
	
	public Kruskal(Edge[] g) {
		graph = copy(g);
		Arrays.sort(graph); // O(|E|log|E|)
		numOfVertices();
		sTree = new Edge[vertices-1];
		treeSize = 0;
		vgroup = new DisjointSets(vertices);
		for (int i = 0; i < vertices; i++) {
			vgroup.makeSet(i);
		}
		makeMST();
	}

	private void makeMST() {
		int i = 0;
		while(i < graph.length && treeSize < vertices - 1) {
			if(vgroup.union(graph[i].v1(),graph[i].v2())) {
				sTree[treeSize++] = graph[i];
			}
			i++;
		}
	}

	private void numOfVertices() {
		vertices = 0;
		for(Edge e : graph) {
			if(e.v1() > vertices) vertices = e.v1();
			if(e.v2() > vertices) vertices = e.v2();
		}
		vertices++;
	}

	public Edge[] getTree() {
		return sTree;
	}
	
	private Edge[] copy(Edge[] g) {
		Edge[] temp = new Edge[g.length];
		for (int i = 0; i < g.length; i++) {
			temp[i] = new Edge(g[i].v1(), g[i].v2(), g[i].weight());
		}
		return temp;
	}
	
}