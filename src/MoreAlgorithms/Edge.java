package MoreAlgorithms;

public class Edge implements Comparable<Edge> {
	public int v1;
	public int v2;
	public int weight;

	public Edge(int v1, int v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		return ((Integer)(this.weight)).compareTo((Integer)(e.weight));
	}
	
	public int v1() {
		return v1;
	}

	public int v2() {
		return v2;
	}

	public int weight() {
		return weight;
	}

	@Override
	public String toString() {
		return "(" + v1 + "," + v2 + ", w = " + weight + ")";
	}
}
