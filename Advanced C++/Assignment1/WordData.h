#ifndef WORDDATA_H
#define WORDDATA_H


#include<iostream>
#include<string>
#include<cstring>
#include "IntList.h"

using namespace std;

class WordData
{
public:
	char* word;
	int frequency;
	IntList lineNumberList;

    WordData(); //default constructor
	WordData(char* ,int ); //default constructor with params
	WordData(const WordData &);	//copy constructor
	~WordData();	//destructor
	WordData& operator = (const WordData& );	//copy assignment operator

	void append(int line);	//insert new number + line no
	int getFrequencyCount();	//get frequency of the word
	const char* getWord();	//readonly pointer
	const IntList& getIntList();	//readonly reference
	int compareWord(char* newWord);	//compares new word with current one
	void printWordData();	//print all data

};

#endif
