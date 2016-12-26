
/*
 * 
 * main.cpp
 * Assignment_3  
 * Created by Chintan Sarvaiya on 2016-11-24. 
 * Copyright � 2016 Chintan Sarvaiya. All rights reserved. 
 * Student id : 27148879 
 * COMP 6651, Programming Assignment 3   
 * 
 * https://www.youtube.com/watch?v=s6FhG--P7z0
 * http://stackoverflow.com/questions/8088626/depth-first-traversal-and-adj-matrix
 * http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
 * http://stackoverflow.com/questions/5204051/how-to-calculate-the-running-time-of-my-program
 * 
 * discussion & group collabration with ANISH TALWAR & GURPREET KAUR
 */
package tempPrj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("Hello world");
		long start = System.currentTimeMillis();
		String file_name = "/Users/chintansarvaiya/Documents/Masters/Fall 2016/Algorithm 2016/Assignments/Prog Assi 3/input2.txt";
		String output_file_name = "/Users/chintansarvaiya/Documents/Masters/Fall 2016/Algorithm 2016/Assignments/Prog Assi 3/output.txt";
		String currentLine = null;

		String answers = "";
		
		try {
			FileReader filereader = new FileReader(file_name);
			BufferedReader bufferedReader = new BufferedReader(filereader);
			while ((currentLine = bufferedReader.readLine()) != null) {
			
				// 6,3,{0,1,2,3,4,5},{1,2,3,4,5,0}
				
				currentLine = currentLine.replaceAll("\\s+","");
				
				String[] arr = currentLine.split(",");
				
				
				Graph g = new Graph();
				g.n = Integer.parseInt(arr[0]); // 6
				g.k = Integer.parseInt(arr[1]); // 3
				g.adjMatrix = new int[g.n][g.n];
				g.isPossible = false;
				g.isVisited = new boolean[g.n];
				
				
				int begin = currentLine.indexOf("{");
				int end = currentLine.indexOf("}");
				String firstSetOfVertices = currentLine.substring(begin+1,end);//{0,1,2,3,4,5}
				String[] arr1 = firstSetOfVertices.split(",");
				
				int begin2 = currentLine.indexOf("{",currentLine.indexOf("{")+1);
				int end2 = currentLine.indexOf("}",currentLine.indexOf("}")+1);
				String secondSetOfVertices = currentLine.substring(begin2+1,end2);//{1,2,3,4,5,0}
				String[] arr2 = secondSetOfVertices.split(",");
				
				for (int i = 0; i < arr1.length; i++) {
					int j = Integer.parseInt(arr1[i]) ;
					int k = Integer.parseInt(arr2[i]);
					
					g.adjMatrix[j][k] = 1;
					g.adjMatrix[k][j] = 1;
				}
				
				
				g.DFS();
				
				answers += g.isPossible?"Possible\n":"Impossible\n";
				
			}
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			PrintWriter write = new PrintWriter(output_file_name,"UTF-8");
			write.println(answers);
			write.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start + " ms");
		System.out.println((end-start)/1000 + " sec");
	}
}

class Graph{
	int n = 0;
	int k = 0;
	int adjMatrix[][];
	
	boolean isPossible = false;
	boolean isVisited[];
	
	
	ArrayList<Integer> connectedComponents = new ArrayList<Integer>();
	
	int counterForConnectedComponents;
	void applyRecursiveDFS(int i){
		
		isVisited[i] = true;
		
		for (int j = 0; j < n; j++) {
		
			if (adjMatrix[i][j] == 1 && !(isVisited[j])) {
			
				counterForConnectedComponents++;
				
				applyRecursiveDFS(j);
			
			}
		}
		
	}
	void DFS(){
 
		for (int i = 0; i < n; i++) { 
			
			if (!(isVisited[i])) {
			
				counterForConnectedComponents = 1;
				
				applyRecursiveDFS(i);
				
				connectedComponents.add(counterForConnectedComponents);
			}
		}
		
		subsetsum();
	}
	
	void subsetsum(){
		int len = connectedComponents.size();
		boolean isPossibleArr[][] = new boolean[len+1][k+1];
		
		for (int i = 0; i < isPossibleArr.length; i++)
			isPossibleArr[i][0] = true;
		
		
		for (int i = 1; i <= len; i++) {
			int sum = connectedComponents.get(i-1);
			for (int j = 1; j <= k; j++) {
				if (j >= sum)
					isPossibleArr[i][j] = isPossibleArr[i-1][j]  || isPossibleArr[i-1][j- sum];
				else
					isPossibleArr[i][j] = isPossibleArr[i-1][j];	
			}
		}
		
		isPossible = isPossibleArr[len][k];
	}
	
}
