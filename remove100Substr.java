package tempPrj;

import java.util.Scanner;

public class remove100Substr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  @SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
	          int t = Integer.parseInt(scan.nextLine());
	          
	          String answer="";
	          
	          
	          while(t-- > 0)
	          {

	        	  
	        	  String inputString = scan.nextLine();
	        	  int len = inputString.length();
	        	  int counter = 0;
	        	  String tempString = inputString;
	        	  String prevTempString = tempString;
	        	  while (true) {
					tempString = tempString.replace("100", "");
					if (prevTempString == tempString) break;
					counter += prevTempString.length() - tempString.length();
					prevTempString = tempString;
	        	  }
	        	  
	        	  answer += counter + "\n";
	          }
	          System.out.println(answer);
	}

}
