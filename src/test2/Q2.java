package test2;
//Given G=(V,E) undirected connected graph.  
//question A ) Build algorithm to determined if G could be a aularian graph by adding Edges.

import java.util.ArrayList;

//question B ) if A is true 
//				what is the minimal number of vertexes need to be added to the graph 
//				that insure the existing of Eularian cycle? 
public class Q2 {
//part A
	static boolean canBeAularian(ArrayList<Integer>[] tree) {
		return true; 
	}
	
//part B
	static int counter; 
	static int[] degree; 
	
	static void HowManyEdgesNeed(ArrayList<Integer>[] graph) {
		
		int c =0 ;
		degree = new int [graph.length] ;
		for (ArrayList<Integer>  i : graph) {
			degree[c++] = i.size();   
		}
		
		for (int i = 0; i < degree.length; i++) {
			if(degree[i]%2 ==1) {
				counter++;
			}
		}
		System.out.println(counter/=2);
	}
	public static void main(String[] args) {
		
		ArrayList<Integer>[] g = new ArrayList[7]; 
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);		g[1].add(0);
		
		g[1].add(2);		g[2].add(1);
		
		g[1].add(3);		g[3].add(1);
		
		g[3].add(4);		g[4].add(3);
		
		g[5].add(3);		g[3].add(5);
		
		g[6].add(5);		g[5].add(6);
		
		System.out.println(canBeAularian(g));
		HowManyEdgesNeed(g);
	}
}
