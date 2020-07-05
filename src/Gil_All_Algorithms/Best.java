package Gil_All_Algorithms;
/*
 * Kadane Algorithm
 */
public class Best {
	/*
	 * indexes. 
	 * max - maximum value found.
	 */
	int max, start, end;
	
	public Best(int[] arr) {
		int sum = 0, temp_start = 0;
		max = arr[0]; //temporary sum holds a[0].
		start = 0; end = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(sum > max) {
				max = sum;
				start = temp_start;
				end = i;
			}
			if(sum < 0) {
				sum = 0;
				temp_start = i+1;
			}
		}
	}
}
