
#include "WordData.h"
#include <cstring>
#include <ctype.h>
#include <string.h>
using namespace std;

//default constructor

WordData::WordData(){
    word = NULL;
    frequency = 0;
}

//default constructor with params
WordData::WordData(char* wArray,int line){
    word = wArray;
	frequency = 1;
	lineNumberList.append(line);
}

//copy constructor

WordData::WordData(const WordData &objWD){
	word = objWD.word;
	frequency = objWD.frequency;
	lineNumberList = objWD.lineNumberList;
}

// destructor
WordData::~WordData(){
//	delete[] word;
}

//copy assignment operator

WordData& WordData::operator = (const WordData& objWD){
	word = objWD.word;
	frequency = objWD.frequency;
	lineNumberList = objWD.lineNumberList;
	return *this;
}


// public methods

//insert new number + line no

void WordData::append(int line){
	frequency += 1;
	lineNumberList.append(line);
}

//get frequency of the word

int WordData::getFrequencyCount(){
	return frequency;
}

//readonly pointer to word

const char* WordData::getWord(){
	return word;
}
//readonly reference

const IntList& WordData::getIntList(){
	return lineNumberList;
}

// internal method to compare two words
// what it does ? it internaly creates lower case of both words and returns strcmp value
int strcomparison(char *s1,char *s2)
{
    char *t1 = new char[100];
    char *t2 = new char[100];
    
    strcpy(t1, s1);
    strcpy(t2, s2);
    
    for(int i=0;i<strlen(t1);i++)
        if (t1[i] >= 'A' && t1[i] <= 'Z')   t1[i] = t1[i] - 'A' + 'a';
	
    t1[strlen(t1)] = '\0';
    
    for(int i=0;i<strlen(t2);i++)
	    if (t2[i] >= 'A' && t2[i] <= 'Z')   t2[i] = t2[i] - 'A' + 'a';

    t2[strlen(t2)] = '\0';
    
	return(strcmp(t1,t2));
}

//compares new word with current one by using internal method strcomparision

int WordData::compareWord(char* newWord){
    if (strcomparison(word, newWord) < 0) return -1;
    else if (strcomparison(word, newWord) > 0) return 1;
    else return 0;
}

//print all data

void WordData::printWordData(){
    cout<<"\t"<<word<<"("<<frequency<<")";
    for (int i=0; i<lineNumberList.size+1; i++) cout<<lineNumberList.list[i];
}

