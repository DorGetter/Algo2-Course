package Gil_All_Algorithms;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Create Languages to words . 
 * 
 * 
 * Huffman algorithm - using two queue - assumption: the given freq array is sorted 
 * Complexity: O(n)
 */
public class Huffman {
	private static final int weight = 0, left = 1, right = 2, parent = 3;  
	private char[] chars;
	private int[] freq;
	private int[][] tree;
	private ArrayBlockingQueue<Integer> q1,q2;
	private String[] code;
	private int n;


	public Huffman(char[] chars, int[] freq) {
		n = chars.length;
		this.chars = chars;
		this.freq = freq;
		tree = new int[2*n-1][4];
		code = new String[n];
		q1 = new ArrayBlockingQueue<Integer>(n);
		q2 = new ArrayBlockingQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			tree[i][weight] = freq[i];
			q1.add(i);
		}
		createTree();
		buildCode("",2*n-2);
	}

	private void createTree() {
		int k = n;
		while(q1.size() + q2.size() > 1) {
			int l = getMin();
			int r = getMin();
			tree[l][parent] = k;
			tree[r][parent] = k;
			tree[k][weight] = tree[l][weight] + tree[r][weight];
			tree[k][left] = l;
			tree[k][right] = r;
			q2.add(k);
			k++;
		}
	}

	private int getMin() {
		if(q1.isEmpty() && q2.isEmpty()) return -1;
		if(q1.isEmpty()) return q2.poll();
		if(q2.isEmpty()) return q1.poll();
		if(tree[q1.peek()][weight] > tree[q2.peek()][weight]) return q2.poll();
		return q1.poll();
	}

	private void buildCode(String code, int i) {
		if(i < n) {
			this.code[i] = code;
			return;
		}
		buildCode(code + "0", tree[i][left]);
		buildCode(code + "1", tree[i][right]);
	}

	public String getCode() {
		String ans = "";
		for (int i = 0; i < n; i++) {
			ans += chars[i] + ": " + code[i] + "\n";
		}
		return ans;
	}
}


