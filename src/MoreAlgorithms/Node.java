package MoreAlgorithms;

public class Node implements Comparable<Node>{
	private int id,weight;
	
	public Node(int id,int weight) {
		this.id = id;
		this.weight = weight;
	}
	
	public Node(Node n) {
		this.id = n.id;
		this.weight = n.weight;
	}
	
	public void setWeight(int value) {
		weight = value;
	}
	
	public int weigth() {
		return weight;
	}
	
	public int id() {
		return id;
	}

	@Override
	public int compareTo(Node n) {
		return ((Integer)(this.weight)).compareTo((Integer)(n.weight));
	}
	
	@Override
	public boolean equals(Object n) {
		return id == ((Node)n).id;
	}
}
