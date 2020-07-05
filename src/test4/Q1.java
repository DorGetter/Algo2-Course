package test4;

public class Q1 {
	public static void main (String[] args) 
	{ 

		/* Let us create following weighted graph 
				10 
			0--------1 
			| \	 | 
		6| 5\ |15 
			|	 \ | 
			2--------3 
				4	 */
		int V = 4; // Number of vertices in graph 
		int E = 5; // Number of edges in graph 
		Graph graph = new Graph(V, E); 

		// add edge 0-1 
		graph.edge[0].src = 0; 
		graph.edge[0].dest = 1; 
		graph.edge[0].weight = 3; 

		// add edge 0-2 
		graph.edge[1].src = 1; 
		graph.edge[1].dest = 2; 
		graph.edge[1].weight = 12; 

		// add edge 0-3 
		graph.edge[2].src = 2; 
		graph.edge[2].dest = 3; 
		graph.edge[2].weight = 1; 

		// add edge 1-3 
		graph.edge[3].src = 1; 
		graph.edge[3].dest = 3; 
		graph.edge[3].weight = 5; 
		
		for ( test4.Graph.Edge e :graph.edge) {
			e.weight = -1*e.weight;
		}
		
	
		graph.KruskalMST(); 
	} 
}
