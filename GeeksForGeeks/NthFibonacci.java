package tempPrj;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.javafx.collections.MappingChange.Map;

public class NthFibonacci {

	private static long getFib(long x) {
		if (x == 0)
			return 0;
		if (x == 1)
			return 1;
		else
			return (getFib(x - 1) + getFib(x - 2)) % (1000000007 * 2);
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			long n = Integer.parseInt(scan.nextLine());

			// why 3 ? see the pattern of fibonacci... even number gets repeated
			// after 3 :)
			// Plus remember 0 1 1 ... starting of the fibonacci pattern

			long a, b, actualValue, tempValue;
			a = 0;
			b = 1;
			actualValue = a + b;
			tempValue = 0;
			int i = 1;
			while (i++ != n * 3) {
				tempValue = a;
				a = actualValue;
				actualValue = tempValue + b;
				b = actualValue;
				actualValue = actualValue % (1000000007*2);

			}

			answer += actualValue % 1000000007 + "\n";

		}
		System.out.println(answer);
	}

}
