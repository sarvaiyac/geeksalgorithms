package tempPrj;

import java.util.*;

public class MinimizeSumOfProducts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			int n = Integer.parseInt(scan.nextLine());
			
			String[] input_A = scan.nextLine().split(" ");
			String[] input_B = scan.nextLine().split(" ");
			
			Integer[] A = new Integer[n];
			Integer[] B = new Integer[n];
			
			for (int i = 0; i < n; i++) {
				A[i] = Integer.parseInt(input_A[i]);
				B[i] = Integer.parseInt(input_B[i]);
			}
			
			Arrays.sort(A);
			Arrays.sort(B,Collections.reverseOrder());
			
			int sum = 0;
			for (int i = 0; i < n; i++)
				sum += A[i] * B[i];
			
			answer += sum + "\n";
			
		}
		System.out.println(answer);
	}

}
