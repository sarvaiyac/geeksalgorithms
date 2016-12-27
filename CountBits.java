package tempPrj;

import java.util.Scanner;

public class CountBits {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			long n = Integer.parseInt(scan.nextLine());
			int bitCounter = 0;
			n*=2;
			while ((n/=2) > 0)	
				if (n%2 == 1) 
					bitCounter++;
			
			answer += bitCounter + "\n";
		}
		System.out.println(answer);
	}

}
