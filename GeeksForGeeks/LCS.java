package tempPrj;

import java.util.Scanner;

import com.sun.accessibility.internal.resources.accessibility;

public class LCS {

	public static int check_LCS(String str1, String str2 ) {
		int[][] A = new int[str1.length()+1][str2.length()+1];
		
		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				if (i == 0 || j ==0)
					A[i][j]= 0; 
				else
					A[i][j] = A[i-1][j-1] + 1 ; 
			}
		}
		
		int max= 0;
		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				max = Math.max(A[i][j], max);
			}
		}
		
		return max;
	}
	public final static void main(String[] args) {
		// TODO Auto-generated method stub
		 @SuppressWarnings("resource")
		 Scanner scan = new Scanner(System.in);
         int t = Integer.parseInt(scan.nextLine());
         
         String answer="";
         
         
         while(t-- > 0)
         {
        	 String line = scan.nextLine();
        	 String str1 = scan.nextLine();
        	 String str2 = scan.nextLine();
        	 
        	 answer += check_LCS(str1, str2) + "\n";
         }
         System.out.println(answer);
	}

}
