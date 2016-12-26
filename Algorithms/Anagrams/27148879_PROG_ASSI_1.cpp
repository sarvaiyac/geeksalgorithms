//
//  main.cpp
//  Assignment_1
//
//  Created by Chintan Sarvaiya on 2016-09-23.
//  Copyright © 2016 Chintan Sarvaiya. All rights reserved.
//
//
//  Student id : 27148879
//  COMP 6651, Programming Assignment 1
//
//

/* references
 
http://stackoverflow.com/a/1735321/1162201
http://stackoverflow.com/a/2808527/1162201
http://stackoverflow.com/a/21995693/1162201
http://stackoverflow.com/a/2395311/1162201

 Instructions to run the program:
 
 we have two constants as FILE_NAME and OUTPUT_FILE_NAME
 FILE_NAME : containts the location of the inputfile
 OUTPUT_FILE_NAME : contains the location of outputfile
 
 please configure these two constants before you run the program. Thank you.
 
*/

#include <iostream>
#include <time.h>
#include <vector>
#include <fstream>
#include <sstream>
#include <ctime>
#include <chrono>
#include <stdio.h>

using namespace std;

const string FILE_NAME = "/Users/chintansarvaiya/Documents/Masters/Fall 2016/Algorithm 2016/Assignments/Prog assi1/Assignment_1/Assignment_1/input2.txt";

const string OUTPUT_FILE_NAME = "/Users/chintansarvaiya/Documents/Masters/Fall 2016/Algorithm 2016/Assignments/Prog assi1/Assignment_1/Assignment_1/output.txt";

int main(int argc, const char * argv[]) {
    // insert code here...

    
    // start recording time.
    auto start = chrono::steady_clock::now();
    
    
    // fill up the vectors of words & anagrams from input file
    
    vector<string> words,anagrams;
    
    ifstream inputfile;
    inputfile.open(FILE_NAME.c_str());
    string currentline;
    int line=1;
    bool isAnagram = false;
    while (!inputfile.eof()) {
        line++;
        getline(inputfile, currentline);
        istringstream oneline(currentline);
        
        if (currentline != "") {
            if (currentline.at(0) == '-') {
                isAnagram = true;
            }else{
                if (isAnagram) {
                    anagrams.push_back(currentline);
                }else{
                    words.push_back(currentline);
                }
            }
        }
    }
    
    // end filling the words & anagrams
    
    
    // check for the jumpled words for each anagram.
    
    // ANAGRAM means the length of the string must be same and each of the character must be repeated EXACTLY ONCE.
    
    for (vector<string>::iterator st_anagrams = anagrams.begin(); st_anagrams != anagrams.end(); st_anagrams++) {
        string anagram = *st_anagrams;
        
        size_t length_anagram = anagram.length();
        
        vector<string> sameLengthAnagrams;
        
        // start from begining of all the words
        
        for (vector<string>::iterator st_words = words.begin(); st_words != words.end(); st_words++) {
            string word = *st_words;
            
            size_t length_word = word.length();
            
            // if the length of the word & length of the anagram are same then check for all the characters in anagram & the particular word
            // if any of the character is missing that means that word is not anagram but it's just another string with same lenght which
            // could be jumbled word of future anagrams.
            
            if (length_word == length_anagram) {
                
                bool isAnagram = false;
                string tempWord(word);
                for (int i =0; i<length_anagram; i++) {
                    
                    char charFromAnagram = anagram.at(i);
                    
                    size_t find = tempWord.find(charFromAnagram);
                    
                    if (find != string::npos) {
                        isAnagram = true;
                        tempWord.erase(find,1); // to remove the found character. (Testing : btqxn/xqqnn)
                    }else{
                        isAnagram = false;
                        break; //because it's not anagram. It's just another string with same length
                    }
                }
                
                // if the word is jumbled of anagram, push that string into a vector.
                if (isAnagram) {
                    sameLengthAnagrams.push_back(word);
                }

            }
        }
        
        
        // write all the jumbled words of anagram in outputfile.
        
        FILE *outputFile;
        outputFile = fopen(OUTPUT_FILE_NAME.c_str(), "a");
        if (sameLengthAnagrams.size() == 0) {
            cout<<"No Answer Found"<<endl;
            fprintf(outputFile, "No Answer Found\n");
        }else{
            sort(sameLengthAnagrams.begin(), sameLengthAnagrams.end());
            
            for (vector<string>::iterator s = sameLengthAnagrams.begin(); s != sameLengthAnagrams.end(); s++) {

                
                cout<<*s<<endl;
                string wordToPrint = *s;
                fprintf(outputFile, "%s\n",wordToPrint.c_str());
                
            }

        }
        cout<<"-------------------"<<endl;
        fprintf(outputFile, "-------------------\n");
        
        
        
    }
 
    // stop recording time & print the difference in miliseconds and seconds.
    
    auto end = chrono::steady_clock::now();
    
    auto elapsed = chrono::duration_cast<chrono::milliseconds>(end-start);
    
    cout << "taken time : " << elapsed.count() << " Milliseconds"<<endl;
    
    auto elapsedsec = chrono::duration_cast<chrono::seconds>(end-start);
    
    cout << "taken time : " << elapsedsec.count() << " Seconds"<<endl;
    
    return 0;
}
