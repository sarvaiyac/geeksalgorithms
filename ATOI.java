package tempPrj;

import java.util.Scanner;

public class ATOI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());

		String answer = "";

		while (t-- > 0) {

			String input = scan.nextLine();
			
			answer += atoi(input) + "\n";
		}
		System.out.println(answer);
	}

	static int atoi(String str)
    {
		int length = str.length()-1;
		int finalNumber = 0;
		boolean isNegative = false;
		int i=0;
		if (str.charAt(0) == '-') {
			isNegative = true; 
			i = 1;
			length--;
		}else if (str.charAt(0) == '+') {
			i = 1;
			length--;
		}
		
		for (; i < str.length(); i++) { 
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				finalNumber += ( str.charAt(i) - '0' )* Math.pow(10, length);
				length--;
			}else{
				finalNumber = -1;
				break;
			}
		}
		

		return (isNegative) ? -finalNumber : finalNumber;

	// Your code here
    }
}
