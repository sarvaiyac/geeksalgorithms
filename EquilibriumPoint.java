package tempPrj;

import java.util.Scanner;

public class EquilibriumPoint {

	public static int EqPoint(int[] A) {
		if (A.length == 1)
			return 1;
		else if (A.length == 2)
			return -1;
		else {
			int sum = 0;
			for (int i = 0; i < A.length; i++)
				sum += A[i];

			int nextSum = 0;
			for (int j = 0; j < A.length; j++) {
				sum -= A[j];
				if (sum == nextSum)
					return j + 1;
				else
					nextSum += A[j];
			}
			return -1;
		}
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			int n = Integer.parseInt(scan.nextLine());
			String[] arrStrings = scan.nextLine().split(" ");
			int[] a = new int[n];
			for (int i = 0; i < arrStrings.length; i++) {
				a[i] = Integer.parseInt(arrStrings[i]);
			}

			answer += EqPoint(a) + "\n";
		}
		System.out.println(answer);
	}

}
