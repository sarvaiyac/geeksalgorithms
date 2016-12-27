/*
 * 
 * 
5
1324
3415436
1023422
03517
3555

Output:
4312
6543314
4322210
75310
5553
 */

package tempPrj;

import java.util.Scanner;


public class First
{
	public static int lucasNumber(int n) {
		if (n == 0) return 2;
		else if (n == 1) return 1;
		else return lucasNumber(n-1) + lucasNumber(n-2);
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
        	  int ans = lucasNumber(n) % 1000000007;
        	  answer+=  ans + "\n";
              
          }
          System.out.println(answer);
	}

}
/*
public class First
{
	public static void main (String[] args)
	{
	      @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
          int t = Integer.parseInt(scan.nextLine());
          
          String answer="";
          
          
          while(t-- > 0)
          {

        	  int n = Integer.parseInt(scan.nextLine());
        	  int tempN = n;
        	  int[] arr = new int[3];
        	  int counter = 0;
        	  for (int i = 2; tempN != 1; i++) {
				if (tempN%i == 0) {
					arr[counter++] = i;
					tempN = tempN/i;
				}
				if (counter == 3)
					break;
        	  }
        	  
        	  if(n == (arr[0] * arr[1] * arr[2]) )
        		  answer+= "1\n";
        	  else
        		  answer+= "0\n";
              
          }
          System.out.println(answer);
	}

}
*/
/*// largest even/odd number
 * 
 package tempPrj;

import java.util.*;


class First
 {

	public static int getMax(int[] a, int len){
		
		if (len > 0){
			return Math.max(a[len],getMax(a,len-1));
		}else{
			return a[0];
		}
	}
	public static void main (String[] args)
	 {
	      @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
          int t = Integer.parseInt(scan.nextLine());
          
          String answer="";
          
          
          while(t-- > 0)
          {
        	  String nString = scan.nextLine();
        	  int len = nString.length();
        	  int[] arr = new int[len];
        	  for (int i = 0; i < len; i++) {
				arr[i] = Integer.parseInt(nString.charAt(i) + "");
        	  }
        	  

              
              int smallestEven = -1;
              int smallestEvenIndex = -1;
              
              for (int j = 0; j < arr.length; j++) {
            	  
            	  if(arr[j]%2 == 0){
            		  if (smallestEven == -1) { smallestEven = arr[j]; smallestEvenIndex = j;}
            		  else if(Math.min(smallestEven,arr[j]) == arr[j] ){ // change when you find another minimum
            			  smallestEven = arr[j];
            			  smallestEvenIndex = j;
            		  }
            	  }
              }
              
              if (smallestEvenIndex != -1) {
            	  int temp = arr[smallestEvenIndex]; // swap smallest element with the last element;
                  arr[smallestEvenIndex] = arr[len-1];
                  arr[len-1] = -1;
              }else{
            	  // get the odd minimum
            	  for (int j = 0; j < arr.length; j++) {
                	  
                	  if(arr[j]%2 != 0){
                		  if (smallestEven == -1) { smallestEven = arr[j]; smallestEvenIndex = j;}
                		  else if(Math.min(smallestEven,arr[j]) == arr[j] ){ // change when you find another minimum
                			  smallestEven = arr[j];
                			  smallestEvenIndex = j;
                		  }
                	  }
                  }
            	  int temp = arr[smallestEvenIndex]; // swap smallest element with the last element;
                  arr[smallestEvenIndex] = arr[len-1];
                  arr[len-1] = -1;
              }
              
              Arrays.sort(arr);
              
              String result = "";
              for (int j = arr.length-1; j >0 ; j--) {
				result+=arr[j];
              }
              
              
              answer+= result+smallestEven+ "\n";
          }
          System.out.println(answer);
	 }
 }
 */