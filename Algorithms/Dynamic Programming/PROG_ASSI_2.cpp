//
//  main.cpp
//  Assignment_2
//
//  Created by Chintan Sarvaiya on 2016-11-01.
//  Copyright � 2016 Chintan Sarvaiya. All rights reserved.
//
//
//  COMP 6651, Programming Assignment 2
//
//

/* references

http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
http://stackoverflow.com/questions/12675919/dynamic-array-in-c-is-my-understanding-of-malloc-realloc-correct
http://www.geeksforgeeks.org/dynamically-allocate-2d-array-c/
 
Instructions to run the program:

we have two constants as FILE_NAME and OUTPUT_FILE_NAME
FILE_NAME : containts the location of the inputfile (put in the project directory for windows)
OUTPUT_FILE_NAME : contains the location of outputfile (it will create)

please configure these two constants before you run the program. Thank you.

TAKES 44 MS FOR INPUT1
TAKES 964 MS FOR INPUT2



*/

#define _CRT_SECURE_NO_DEPRECATE

#include <iostream>
#include <time.h>
#include <vector>
#include <fstream>
#include <sstream>
#include <ctime>
#include <chrono>
#include <stdio.h>
#include <malloc.h> // for dynamic allocation of array

using namespace std;

const string FILE_NAME = "input.txt";

const string OUTPUT_FILE_NAME = "output.txt";


int MatrixChainOrderModified(string p)
{
	size_t n = p.length();
	//int m[n][n]; // works in Mac, not in windows

	int ** m = new int*[n]; // for windows

	for (int i = 0; i < n; i++) //for windows
		m[i] = new int[n]; // for windows

	for (int i = 0; i<n; i++)
		m[i][i] = 1; // assuming atleast one brushstroke to paint

	for (int l = 1; l<n; l++) {
		for (int i = 0; i<n - l; i++) {
			int j = i + l;

			m[i][j] = 99999; // assuming as infinite

			for (int k = i; k<j; k++) {

				int q = m[i][k] + m[k + 1][j];

				if (q < m[i][j])
					m[i][j] = q;

				//                if (p[k] == p[k+1])
				//                    m[i][j]--;


			}

			if (p[i] == p[j])
				m[i][j]--;

		}
	}

	return m[0][n - 1];

}

int main(int argc, const char * argv[]) {
	auto start = chrono::steady_clock::now();

	string  words[20000]; // FILE HAS N <= 20, 000 STRINGS, STRING CONTAINS UPPER LETTER ONLY STRINGLENGTH = 1 TO 50 CHARACTERS
	
	fstream fs;
	fs.open(OUTPUT_FILE_NAME, ios::out);
	fs.close();

	ifstream inputfile;
	inputfile.open(FILE_NAME.c_str());
	string currentline;
	int pos = 0;
	while (!inputfile.eof()) {

		getline(inputfile, currentline);
		istringstream oneline(currentline);
		if (currentline.length() > 0)
			words[pos++] = currentline;// .push_back(currentline);

	}

	

	
	string str = "";
	
	for (int i = 0; i < pos; i++)
		str = str + to_string(MatrixChainOrderModified(words[i])) + "\n";
	
	ofstream outputFile(OUTPUT_FILE_NAME);
	outputFile << str;
	outputFile.close();
	/*
	for (vector<string>::iterator i = words.begin(); i < words.end(); i++) {
	cout<< MatrixChainOrderModified(*i) << endl;
	}

	for_each(words.begin(), words.end(), [](string &s){
	cout<<MatrixChainOrderModified_variation(s)<<endl;
	});
	*/


	auto end = chrono::steady_clock::now();

	auto elapsed = chrono::duration_cast<chrono::milliseconds>(end - start);

	cout << "taken time : " << elapsed.count() << " ms" << endl;

	auto elapsedsec = chrono::duration_cast<chrono::seconds>(end - start);

	cout << "taken time : " << elapsedsec.count() << " Seconds" << endl;

	string c;
	cin >> c;
	return 0;
}

