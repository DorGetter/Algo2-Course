package Gil_All_Algorithms;

public class Kadane_circul {
	public static int[] maxCircularSumIndex(int a[]) 
	{
		int n = a.length; 
		int max_kadane = kadaneIndex(a); 
		int max_wrap = 0; 
		for (int i = 0; i < n; i++){ 
			max_wrap += a[i]; // Calculate array-sum 
			a[i] = -a[i]; // invert the array (change sign) 
		} 
		int Fkadane_Rindex = Rindex;
		int Fkadane_Lindex = Lindex;
		
		max_wrap = max_wrap + kadaneIndex(a); 
		
		
		
		int[] rest = new int [3]; 
		if( max_kadane > max_wrap ) { 
			rest[0] = max_kadane;
			rest[1] = Fkadane_Rindex;
			rest[2] = Fkadane_Lindex; 
		}
		else { 
			rest[0] = max_wrap;
			rest[1] = Rindex;
			rest[2] = Lindex; 
		}
		return rest; 
	}


	// The function returns maximum circular contiguous sum 
	// in a[] 
	public static int maxCircularSum (int a[]) 
	{ 
		int n = a.length; 

		// Case 1: get the maximum sum using standard kadane's algorithm 
		int max_kadane = kadane(a); 

		// Case 2: Now find the maximum sum that includes 
		// corner elements. 
		int max_wrap = 0; 
		for (int i = 0; i < n; i++) 
		{ 
			max_wrap += a[i]; // Calculate array-sum 
			a[i] = -a[i]; // invert the array (change sign) 
		} 

		// max sum with corner elements will be: 
		// array-sum - (-max subarray sum of inverted array) 
		max_wrap = max_wrap + kadane(a); 

		// The maximum circular sum will be maximum of two sums 

		return (max_wrap > max_kadane)? max_wrap: max_kadane; 
	} 

	// Standard Kadane's algorithm to find maximum subarray sum 
	// See https://www.geeksforgeeks.org/archives/576 for details 
	static int kadane (int a[]) 
	{ 
		int n = a.length; 
		int max_so_far = 0, max_ending_here = 0; 
		for (int i = 0; i < n; i++) 
		{ 
			max_ending_here = max_ending_here + a[i]; 
			if (max_ending_here < 0) 
				max_ending_here = 0; 
			if (max_so_far < max_ending_here) 
				max_so_far = max_ending_here; 
		} 
		return max_so_far; 
	} 
	static int Rindex; 
	static int Lindex; 
	static int kadaneIndex (int a[]) 
	{ 
		int n = a.length; 
		int max_so_far = 0, max_ending_here = 0; 

		int s =0 ;		int e ;
		int sR=0;		int eR=0;
		for (int i = 0; i < n; i++) 
		{ 
			max_ending_here = max_ending_here + a[i]; 
			if (max_ending_here < 0) {
				max_ending_here = 0;
				s = i+1;
			}
			if (max_so_far <= max_ending_here) {
				max_so_far = max_ending_here;
				sR =s;
				eR =i;
			}
		} 
		System.out.println(sR+" "+eR);
		Rindex=sR; 
		Lindex=eR; 
		return max_so_far; 
	} 


	public static void main (String[] args) 
	{ 

		int a[] = {10, 2, -15, 3, 10, 0, 0}; 
		System.out.println("Maximum circular sum is " + 
				kadane(a)); 
		int b[] = {10, 2, -15, 3, 10, 0, 0}; 
		System.out.println("Maximum circular sum is " + 
				kadaneIndex(b)); 

	}
}
