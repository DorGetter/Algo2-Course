package MoreAlgorithms;
import java.util.ArrayList;

/**
 * Find the center of tree graph (connected without cycles) and the radius
 * Complexity: O(|V|) , (|E|=|V|-1)
 */
public class FireAlgo {
	private int center1,center2;
	private int radius;
	private ArrayList<Integer>[] tree;

	public FireAlgo(ArrayList<Integer>[] g){
		tree = g;
		center1=center2=-1;
		radius = -1;
		fire();
	}

	private void fire(){
		radius = 0;
		int n = tree.length;
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		int[] deg = new int[n];
		for (int i = 0; i < n; i++) {
			deg[i] = tree[i].size();
			if(deg[i]==1)leaves.add(i);
		}
		int v = n;
		while(v>2){
			int l = leaves.size();
			for (int i = 0; i < l; i++) {
				int index = leaves.remove(0);
				deg[index] = 0;
				v--;
				for (int j = 0; j < tree[index].size(); j++) {
					int ni = tree[index].get(j);
					deg[ni]--;
					if(deg[ni]==1)leaves.add(ni);
				}
			}
			radius++;
		}
		if(leaves.size()>1) {
			center1 = leaves.remove(0);
			center2 = leaves.remove(0);
			radius++;
		}
		else {
			center1 = center2 = leaves.remove(0);
		}
	}

	public int getRadius(){
		return radius;
	}

	public int getCenter1(){
		return center1;
	}

	public int getCenter2(){
		return center2;
	}
}
