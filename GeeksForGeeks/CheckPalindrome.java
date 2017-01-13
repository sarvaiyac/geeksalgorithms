package tempPrj;

import java.util.Scanner;

import com.sun.accessibility.internal.resources.accessibility;

public class CheckPalindrome {

	public static int isPalindrome(String str) {
		int len = str.length();
		for (int i = 0; i < len/2; i++)
				if (str.charAt(i) != str.charAt(len - i - 1)) 
					 return 0;
		
		return 1;
	}
	public static void main (String[] args)
	{
	      @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
          int t = Integer.parseInt(scan.nextLine());
          
          String answer="";
          
          
          while(t-- > 0)
          {

        	  int n = Integer.parseInt(scan.nextLine());
        	  String inputString = scan.nextLine().replace(" ", "");
              
        	  answer += isPalindrome(inputString) + "\n";
          }
          System.out.println(answer);
	}
}
