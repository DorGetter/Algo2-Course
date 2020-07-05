package Gil_All_Algorithms;

public class DisjointSets {
	private int[] parent;
	private int[] rank;
	
	public DisjointSets(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
	
	public boolean union(int u, int v) {
		int ur = find(u) , vr = find(v);
		if(ur != vr) {
			if(rank[ur] < rank[vr]) parent[ur] = vr;
			else if(rank[vr] < rank[ur]) parent[vr] = ur;
			else {
				parent[vr] = ur;
				rank[ur]++;
			}
			return true;
		}
		else return false;
	}
	
}
