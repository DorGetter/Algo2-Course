package test4;

import java.awt.List;
import java.util.ArrayList;

public class Q2 {

	static int RemoveV_andKeepTiedG(ArrayList<Integer>[] g) 
	{
		for (int i = 0; i < g.length; i++) {
			if(rank[i]==1) {
				return i;
			}
		}
		return -1;	
	}
	
	
	static int [] rank ; 
	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[4];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);
		g[1].add(2);
		g[2].add(3);
		
		g[1].add(0);
		g[2].add(1);
		g[3].add(2);
		
		rank = new int [4]; 
		rank[0]=1; rank[1]=2; rank[2]=2; rank[3]=1;
		System.out.println(RemoveV_andKeepTiedG(g));
	}
}
