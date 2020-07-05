package Gil_All_Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TreeIsomorphism {
	HashMap<Integer, Integer> map;	

	class Vertex {
		int id, dist, label;
		Vertex pred;
		ArrayList<Vertex> childs;
		ArrayList<Integer> labelChilds;

		public Vertex(int id) {
			this.id = id;
			this.dist = dist;
			label = 0;
			childs = new ArrayList<Vertex>();
			labelChilds = new ArrayList<Integer>();
		}


	}

	public TreeIsomorphism(ArrayList<Integer>[] t1, ArrayList<Integer>[] t2) {
		int n = t1.length;
		if(t1.length == t2.length) {
			Fire f1 = new Fire(t1);
			Fire f2 = new Fire(t2);
			for(int c1 : f1.centers) {
				BFS b1 = new BFS(t1, c1);
				for(int c2 : f2.centers) {
					if(map == null) {
						BFS b2 = new BFS(t2, c2);
						Vertex[] T1 = createVertexList(t1);
						Vertex[] T2 = createVertexList(t2);
						int h=0, h1=0, h2=0;
						System.out.println(Arrays.toString(b1.dist));
						System.out.println(Arrays.toString(b2.dist));
						for (int i = 0; i < n; i++) {
							T1[i].dist = (int) b1.dist[i]; 
							if(b1.pred[i] != -1) T1[i].pred = T1[b1.pred[i]];
							T2[i].dist = (int) b2.dist[i]; 
							if(b2.pred[i] != -1) T2[i].pred = T2[b2.pred[i]];
							h1 = Math.max(h1, (int) b1.dist[i]);
							h2 = Math.max(h2, (int) b2.dist[i]);
						}
						h = h1;
						if(h1 != h2) break;
						ArrayList<Vertex>[] levels = new ArrayList[h+1];
						for (int i = 0; i < h+1; i++) {
							levels[i] = new ArrayList<Vertex>();
						}
						for (int i = 0; i < n; i++) {
							levels[T1[i].dist].add(T1[i]);
							levels[T2[i].dist].add(T2[i]);
						}

						for (int i = h-1; i >= 0; i--) {
							for(Vertex u : levels[i+1]) {
								u.pred.childs.add(u);
								u.pred.labelChilds.add(u.label);
							}
							Collections.sort(levels[i], new Comparator<Vertex>() {
								@Override
								public int compare(Vertex o1, Vertex o2) {
									return o1.labelChilds.toString().compareTo(o2.labelChilds.toString());
								}
							});
							int k = 0;
							levels[i].get(0).label = 0;
							for (int j = 1; j < levels[i].size(); j++) {
								if(!levels[i].get(j-1).labelChilds.toString().equals(levels[i].get(j).labelChilds.toString())) {
									k++;
								}
								levels[i].get(j).label = k;
							}
						}
						if(T1[c1].label == T2[c2].label) {
							map = new HashMap<Integer, Integer>();
							makeMap(T1[c1],T2[c2]);
						}
					}
				}
			}
		}
	}

	private Vertex[] createVertexList(ArrayList<Integer>[] t1) {
		Vertex[] ans = new Vertex[t1.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = new Vertex(i);
		}
		return ans;
	}

	private void makeMap(Vertex v, Vertex u) {
		map.put(v.id, u.id);
		for (int i = 0; i < v.childs.size(); i++) {
			makeMap(v.childs.get(i), u.childs.get(i));
		}
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
		
		ArrayList<Integer>[] g2 = new ArrayList[11]; 
		for (int i = 0; i < g2.length; i++) {
			g2[i] = new ArrayList<Integer>();
		}
		g2[0].add(1);g2[0].add(10);
		g2[1].add(0);g2[1].add(2);g2[1].add(5);
		g2[2].add(1);g2[2].add(3);
		g2[3].add(2);
		g2[4].add(5);
		g2[5].add(1);g2[5].add(4);g2[5].add(6);g2[5].add(8);
		g2[6].add(5);g2[6].add(7);
		g2[7].add(6);
		g2[8].add(5);g2[8].add(9);
		g2[9].add(8);
		g2[10].add(0);
		
		TreeIsomorphism iso = new TreeIsomorphism(g, g2);
		System.out.println(iso.map);
	}
	
}