package Gil_All_Algorithms;

public class kadane_BiggestSubMatrix {


	public static void main(String[] args) {

		int[][] mat = {
			//	0		1		2		3		
//				{2,		 1,		-3,		-4,	},//0
//				{0,		 6,		 3,		 4,	},//1
//				{2,		-2,		-1,		 4,	},//2
//				{-3,	 3,		 1,		 0,	} //3
//				0		1		2		3	
				{0,		-3,		-3,		 -4},//0
				{0,		 6,		 6,		 -4},//1
				{0,		 6,		 6,		 -4},//2
				{0,		-2,		-2,		 -0} //3
				
		};
				
	

		Sum_Of_SubMatrix(mat);//n^6
		//Calculate2(mat);//n^4  uncomment to run
		//Calculate3(mat);//n^4  uncomment to run

		System.out.println("globalSum = " + globalSumM);
		System.out.println("startRow = " + startRowM);
		System.out.println("endRow = " + endRowM);
		System.out.println("startCol = " + startColM);
		System.out.println("endCol = " + endColM);

	}
	static int startRowM,startColM,endRowM,endColM,globalSumM; 
	static void  Sum_Of_SubMatrix(int  [][] mat) {  //Time complexity: O( m^2 * n ) 
		
		int n =mat.length; 
		int m = mat[0].length; 
		if (m>n) {
			int[][] mat1 = TransposeMat(mat); 
		}
		int max = 0 , temp; 
		int [] arr  = new int[n]; 
		
		for (int i = 0; i < mat[0].length; i++) {
			arr = new int[m];
			for (int j = i; j < mat[0].length; j++) {
				for (int k = 0; k < mat.length; k++) {
					arr[k] += mat[k][j]; 
				}
				kadaneIndex(arr);
						
				if(globalSumM<=globalSum) {
					
					globalSumM  = 	globalSum;
					startRowM 	= 	startRow;
					endRowM		= 	endRow; 
					startColM 	=	i;
					endColM 	=   j;
				}

			}
		}
		
	}
	private static int[][] TransposeMat(int [][] mat ) {
		int n =mat.length; 
		int m =mat[0].length; 
		int [][]ret = new int [m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret[j][i] = mat[i][j];
			}
		}
		return ret;
	}
	
 
	static int startRow,endRow,globalSum; 
	
	static void kadaneIndex (int a[]) 
	{ 
		int n = a.length; 
		int max_so_far = 0, max_ending_here = 0; 

		int s =0 ;		int e ;
		 startRow=0;		 endRow=0;
		for (int i = 0; i < n; i++) 
		{ 
			max_ending_here = max_ending_here + a[i]; 
			if (max_ending_here < 0) {
				max_ending_here = 0;
				s = i+1;
			}
			if (max_so_far <= max_ending_here) {
				max_so_far = max_ending_here;
				startRow =s;
				endRow =i;
			}
		} 
		globalSum= max_so_far; 
	} 
}
