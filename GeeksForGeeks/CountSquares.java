package tempPrj;

import java.util.*;

public class CountSquares {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			int n = Integer.parseInt(scan.nextLine());
			
			int i = 1;
			while ((i * i) < n) i++;
			answer += (i-1) + "\n";
			
			
		}
		System.out.println(answer);

	}

}
