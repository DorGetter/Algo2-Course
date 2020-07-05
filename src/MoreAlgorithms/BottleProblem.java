package MoreAlgorithms;
import java.util.Arrays;

/**
 * Bottle Problem
 */
public class BottleProblem {
	private int inf = Integer.MAX_VALUE;
	private int n,m,size;
	private int[][] mat;
	private String[][] paths;
	
	/**
	 * Constructor - n,m - the capacity of two bottles
	 * Build graph and get the best path between every two vertexes
	 * Complexity: O((n*m)^3)
	 */
	public BottleProblem(int n, int m) {
		this.n = n;
		this.m = m;
		this.size = (n+1)*(m+1);
		buildMatrix();
		bestPath();
	}

	private void buildMatrix() {
		mat = new int[size][size];
		paths = new String[size][size];
		for (int i = 0; i < size; i++) {
			Arrays.fill(mat[i],inf);
			Arrays.fill(paths[i],"");
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				setPath(i,j, 0,j);
				setPath(i,j, i,0);
				setPath(i,j, n,j);
				setPath(i,j, i,m);
				setPath(i,j, Math.max(0,i-(m-j)),Math.min(m, i+j));
				setPath(i,j, Math.min(n, i+j),Math.max(0,j-(n-i)));
			}
		}
	}
	
	private void setPath(int i1,int j1,int i2,int j2) {
		int from = index(i1,j1);
		int to = index(i2,j2);
		mat[from][to] = 1;
		paths[from][to] = "->("+i2+","+j2+")";
	}
	
	private int index(int i,int j) {
		return i*(m+1) + j;
	}
	
	/**
	 * The Floyd Warshel algorithm - all pair shortest path
	 */
	private void bestPath() {
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(mat[i][k] != inf && mat[k][j] != inf && mat[i][k] + mat[k][j] < mat[i][j]) {
						mat[i][j] = mat[i][k] + mat[k][j];
						paths[i][j] = paths[i][k] + paths[k][j];
					}
				}
			}
		}
	}
	
	/**
	 * Returns the string represents the path from (0,0) to (x,y)
	 */
	public String getPath(int x, int y) {
		if(mat[index(0,0)][index(x,y)] == inf) {
			return "no path!";
		}
		return "(0,0)" + paths[index(0,0)][index(x,y)];
	}
	
	/**
	 * Print the distance matrix between all vertexes
	 */
	public void printMat() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print((mat[i][j] == Integer.MAX_VALUE ? "∞" : mat[i][j]) + " ");
			}
			System.out.println();
		}
	}
}