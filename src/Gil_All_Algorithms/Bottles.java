package Gil_All_Algorithms;

public class Bottles {
	public static double[][] bottleProblem(int a, int b) {
		int n = (a+1)*(b+1);
		int[][] graph = new int[n][n];
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				//j=3
				/////////from the put/////////
				//pore one 
				//			2				2
				graph[index(i,j,b)][index(0,j,b)] = 1;
				//			2				0
				graph[index(i,j,b)][index(i,0,b)] = 1;
				//fill max
				//			2				6
				graph[index(i,j,b)][index(a,j,b)] = 1;
				//			2				3
				graph[index(i,j,b)][index(i,b,b)] = 1;
				
				////////from one Bottle to the Other////////////
				//pore from A<-->B
				//			2 			    	(0				,			2  ,3)=2
				graph[index(i,j,b)][index(Math.max(0, i - (b-j)),Math.min(i + j, b),b)] = 1;
				//			2					(	1					2,3) =
				graph[index(i,j,b)][index(Math.min(i + j, a),Math.max(0, j - (a-i)),b)] = 1;
			}
		}
		for (int i = 0; i < graph.length; i++) {for (int j = 0; j < graph.length; j++) {System.out.print(graph[i][j]+",");}System.out.println();
		}
		for (int i = 0; i < n; i++) {
			graph[i][i] = 0;
		}
		
		double[][] ans = FW.fw(graph);
		return ans;
	}
	public static int index(int i, int j,int b) {
		return i*(b+1) + j;
	}
	
	public static double shortestpath(int a, int b, int i, int j, int x, int y) {
		double[][] ans = bottleProblem(a,b);
		return ans[index(i,j,b)][index(x,y,b)];
	}
	
	
	
	public static void main(String[] args) {
		int BottleA = 2 ;		int BottleB = 7 ; 
		// --> A=2 , B=4 
		//A=0,B=0 --> A=3,B=0 --> (A->B) A=2,B=1
		//
		double [][]ret = bottleProblem(BottleA, BottleB); 
		
		
		System.out.println();
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret.length; j++) {
				System.out.print(ret[i][j]+", ");
			}
			System.out.println();
		}
		
		
		System.out.println(shortestpath(BottleA,BottleB,0,0,1,7));
	}

	

}
