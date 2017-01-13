package HackerRank;

import java.util.Scanner;

public class CountingSort {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        
        for(int i=0; i < n; i++) A[i] = in.nextInt();
        
        A = countingSort(A);
      
        for(int i=0; i < n; i++) System.out.print(A[i] + " ");
    }
    
    private static int[] countingSort(int[] A) {
		int[] C = new int[100];
		for (int i = 0; i < 100; i++) C[i]  = 0;
		for (int i = 0; i < A.length; i++) C[A[i]]++;
		for (int i = 1; i < 100; i++) C[i]+=C[i-1];
		
		int[] B = new int[A.length];
		for (int i = A.length-1; i >= 0; i--){
			B[C[A[i]]-1] = A[i];
			C[A[i]]--;
		}
		return B;
		
	}

}
