#ifndef WORDLIST_H
#define WORDLIST_H
#include<list>
#include<string>
#include<vector>
#include <iostream>
using namespace std;

class WordList
{
    
private:
    class WordData
    {
        string word;
        int frequenceyOfTheWord;
        vector<int> listOfLineNumbers;
        
    public:
        
        
        WordData();
        WordData(string,int);

        
        void addNumber(int); // add line number to end of the line number list
        int getFrequencyCount(); // get frequency count of particular word data object
        const string& getReadOnlyWordReference(); // get read only referecnce of word
        const vector<int>& getReadOnlyLineNumberListReference(); // get read only reference of linenumberlist

        int compareStrings(string); // compares string with own word and give result in negative number, 0 or positive number
        void printData(ostream&); // prints data of wordData object
        
        
    };
    
    WordData wd;
    list<WordData> listWordData;
    string filename;

public:

   
    WordList();
    WordList(string);

    
    int getSize(); // to get size of list
//    void printData(ostream &,string); // print the output with specific input string
    void printData(ostream & out,string inputstring = "");
    
    void updateList(string,int); // modify the worddata list
    string removeUnNecessaryCharacters(string); // remove special characters and digits from starting and ending index of string
};


#endif
