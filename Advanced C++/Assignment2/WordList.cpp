#include <fstream>
#include <sstream>
#include <cctype>
#include <string>
#include <stdio.h>
#include "WordList.h"



// ---- word data code starts -----

// default constructor
WordList::WordData::WordData(){}

// constructor that takes input as word and linenumber.
// it initialize word and frequency and add a line number into listOfLineNumbers

WordList::WordData::WordData(string inputWord,int lineNumber){ // word & line number
    word = inputWord;
    frequenceyOfTheWord = 1;
    listOfLineNumbers.push_back(lineNumber);
}

// adds line number to list of line number of particular word. If line number exists , it doesnt add
// it increament frequency each time it adds line number
// if line number doesnt exist, add that line number into that list

void WordList::WordData::addNumber(int lineNumber){
    frequenceyOfTheWord++;
    bool isLineNumberExists = false;
    for (vector<int>::iterator jk = listOfLineNumbers.begin(); jk != listOfLineNumbers.end(); jk++) {
        if (*jk == lineNumber){
            isLineNumberExists = true;
            break;
        }
    }
    if (!isLineNumberExists) {
        listOfLineNumbers.push_back(lineNumber);
    }
}

// get frequency count
int WordList::WordData::getFrequencyCount(){
    return frequenceyOfTheWord;
}

// get read only word reference
const string& WordList::WordData::getReadOnlyWordReference(){
    return word;
}

// get read only line number list reference
const vector<int>& WordList::WordData::getReadOnlyLineNumberListReference(){
    return listOfLineNumbers;
}

// compare input string with current word
// it woulr return any number
// negative number : input string is less than current word
// zero : input string is exact match with current word
// positive number : input string is greater than current word

int WordList::WordData::compareStrings(string tempStr){
    string temp = word;

    transform(temp.begin(), temp.end(), temp.begin(), ::tolower);
    transform(tempStr.begin(), tempStr.end(), tempStr.begin(), ::tolower);
    
    return temp.compare(tempStr);
}

// print the proper data with format

void WordList::WordData::printData(ostream& out){
    out.width(15);
    out<<word<<" ("<<frequenceyOfTheWord << ") ";
    for (vector<int>::iterator jk = listOfLineNumbers.begin(); jk != listOfLineNumbers.end(); jk++)
        out<<*jk<<" ";
    
    out<<endl;
}

// wordlist



WordList::WordList(){
    
}

// constructor that takes filename as input
// it fetches all data line by line
// split that line into words
// and then remove unnecessary characters from that word
// send this word and linenumber to updatelist method
// keep it continue until file finishes

WordList::WordList(string fname){
    filename = fname;
    
    ifstream inputfile;
	inputfile.open(fname.c_str());
	string currentline;
    
	int line = 0;
	
    while (!inputfile.eof())
	{
		line++;
        getline(inputfile, currentline);
        istringstream onelinestring(currentline);
        do
		{
			string substring;
            onelinestring>>substring;
            substring = removeUnNecessaryCharacters(substring);
            
            if(substring==" " || substring=="\n" || substring=="") continue;
            
			updateList(substring,line);
			
        } while (onelinestring);
	}
    
	inputfile.close();
}


// creates word data object with inputstring and lineNumber
// add that object into list
// compare if current inputString is less or greater than words are in list
// check if object is already inserted then increamnet frequency and add linenumber


void WordList::updateList(string inputString, int lineNumber){
    
    WordData data(inputString,lineNumber);
    
    bool shouldInsert = true;
    for (list<WordList::WordData>::iterator it = listWordData.begin(); it != listWordData.end(); it++) {
        
        int result = it->compareStrings(inputString);
        if (result==0) {
            it->addNumber(lineNumber);
            shouldInsert = false;
            break;
        }else if (result > 0){
            listWordData.insert(it, data);
            shouldInsert = false;
            break;
        }
        
    }
    
    if (shouldInsert)
        listWordData.push_back(data);
    
}

// it removes unnecessary characters from starting and ending of inputstring
// it removes 0-9 and special characters

string WordList::removeUnNecessaryCharacters(string inputstring) {
    int i=0, length = inputstring.length();
    
    while (i<length) {
        char c = inputstring[i];
        if ((c >= 'A' && c <= 'Z') || (c>='a' && c<='z')) break;
        else{
            inputstring.erase(i, 1);
            --length;
        }
    }
    
    i = inputstring.length();
    while (i>0) {
        char c = inputstring[i];
        if ((c >= 'A' && c <= 'Z') || (c>='a' && c<='z')) break;
        else{
            inputstring.erase(i, 1);
            --i;
        }
    }
    return inputstring;
}

// get size of list
int WordList::getSize(){ // to get size of list
    return this->listWordData.size();
}

// prints data in proper format
// it takes one arguesment as osstream reference and another inputstring if you want to have specific output
// second parameter is nullable if you don't give any value it would take empty string as value

void WordList::printData(ostream & out,string inputstring){
    out<<endl;
    out<<"Word List Source File : "<<filename<<endl;
    if (inputstring != "") {
        out<<"Words that begin with \""<<inputstring<<"\""<<endl;
        out<<"================================="<<endl;
        
        transform(inputstring.begin(), inputstring.end(), inputstring.begin(), ::tolower);
        
        out<<endl;
        for (list<WordList::WordData>::iterator it = listWordData.begin(); it != listWordData.end(); it++) {
            
            string temp = it->getReadOnlyWordReference();
            
            transform(temp.begin(), temp.end(), temp.begin(), ::tolower);
            
            if (temp.compare(0, inputstring.length(),inputstring) == 0 )
            {
                it->printData(out);
                out<<endl;
            }
        }
    }else{

        out<<"================================="<<endl;

        for (int i=0; i<26; i++) {
            char c1=i +'a';
            char c2=i +'A';
            out<<"<"<<c2<<">"<<endl; // print character
            for (list<WordList::WordData>::iterator it = listWordData.begin(); it != listWordData.end(); it++) {
                if (it->getReadOnlyWordReference()[0] == c1 || it->getReadOnlyWordReference()[0] == c2)
                    it->printData(out);
            }

            out<<endl;
        }

    }

}