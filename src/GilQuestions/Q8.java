package GilQuestions;

import java.util.ArrayList;
import java.util.Stack;

public class Q8 {
	public static ArrayList<ArrayList<Integer>> allBest(int[] arr) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		int sum = 0, max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(sum > max) max = sum;
			if(sum < 0) sum = 0;
		}
		ArrayList<Integer> s = new ArrayList<Integer>();
		s.add(0);
		sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(sum == max) {
				for (int j = 0; j < s.size(); j++) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					for (int k = s.get(j); k <= i; k++) {
						a.add(arr[k]);
					}
					ans.add(a);
				}
			}
			if(sum == 0) s.add(i+1);
			if(sum < 0) {
				sum = 0;
				s.clear();
				s.add(i+1);
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(allBest(new int[] {0,3,-3,2,4,9,-4,-50,15,-2,2,0,0}));
		System.out.println(allBest(new int[] {0,0,0,0,0,0}));
	}
}
