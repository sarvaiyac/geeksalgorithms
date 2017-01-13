package HackerRank;

import java.util.Scanner;

public class MiniMaxSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		long a = in.nextLong();
		long b = in.nextLong();
		long c = in.nextLong();
		long d = in.nextLong();
		long e = in.nextLong();
		
		long[] A = {a,b,c,d,e};
		
		long min = A[0];
		long max = A[0];
		long sum = A[0];
		
		for (int i = 1; i < A.length; i++) {
			if (A[i]< min ) min = A[i];
			if (A[i] > max ) max = A[i];
			
			sum += A[i];
		}
		
		System.out.println((sum - max) + " " +(sum - min));
	}

}
