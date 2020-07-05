package test4;

import java.util.ArrayList;

import Gil_All_Algorithms.Kadane_circul;

public class Q3 {

	static void findShortestSubArray(int[] circ){
		 ArrayList<ArrayList<Integer>> g =GilQuestions.Q8.allBest(circ);
		 System.out.println(g.toString());
		 double min = Double.POSITIVE_INFINITY;
		 ArrayList<Integer> s = null ; 
		 for (ArrayList<Integer> arrayList : g) {
			if(arrayList.size() < min) {
				s = arrayList;
				min  = arrayList.size();
			}
		}
		 System.out.println(s.toString());

	}
	
	
	
	public static void main(String[] args) {
		int[] circ = {4,2,-15,1,2,3,-100}; 
		findShortestSubArray(circ);
		
//		int [] rest = Kadane_circul.maxCircularSumIndex(circ);
//		System.out.println("sum : " + rest[0]);
//		System.out.println("Rindex : "+ rest[1]);
//		System.out.println("Lindex : "+ rest[2]);
		
		
	}
}
