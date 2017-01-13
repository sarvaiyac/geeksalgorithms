package HackerRank;
import java.util.*;
public class DesignerPdfViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Scanner in = new Scanner(System.in);
        int n = 26;
        int h[] = new int[n];
        for(int h_i=0; h_i < n; h_i++){
            h[h_i] = in.nextInt();
        }
        String word = in.next();
        
        int max = h[word.charAt(0) - 97];
        for (int i = 1; i < word.length(); i++)
			max = h[word.charAt(i) - 97] > max ? h[word.charAt(i) - 97] : max;

        
        System.out.println(max * word.length());
        */
		
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int i=0; i < n; i++) {
        	A[i] = in.nextInt();
        	String string = in.next();
        }
        int[] C = new int[100];
		for (int i = 0; i < 100; i++) C[i]  = 0;
		for (int i = 0; i < A.length; i++) C[A[i]]++;
		
		System.out.print(C[0] + " ");
		for (int i = 1; i < 100; i++) {
			C[i]+=C[i-1];
			System.out.print(C[i] + " ");
		}
		
//        
//        A = countingSort(A);
//      
//        for(int i=0; i < n; i++) System.out.print(A[i] + " ");
	}
	
	private static int[] countingSort(int[] A) {
		int[] C = new int[100];
		for (int i = 0; i < 100; i++) C[i]  = 0;
		for (int i = 0; i < A.length; i++) C[A[i]]++;
		for (int i = 1; i < 100; i++) C[i]+=C[i-1];
		
		int[] B = new int[A.length];
		for (int i = A.length-1; i >= 0; i--){
			System.out.println(A[i]);
			System.out.println(C[A[i]]);
			System.out.println(B[C[A[i]]-1]);
			System.out.println();
			
			B[C[A[i]]-1] = A[i];
			
			C[A[i]]--;
		}
		return B;
		
	}

}
