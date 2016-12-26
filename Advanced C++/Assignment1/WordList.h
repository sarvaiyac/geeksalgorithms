#ifndef WORDLIST_H
#define WORDLIST_H

#include "WordData.h"

class WordList
{
private:
    struct WordNode
    {
    public:
        WordData wdObj;
        struct WordNode *next;
        
        WordNode(const WordData & wd, struct WordNode* next) // constructor for wordnode
        {
            wdObj=wd;
            this->next=next;
        }
        
        WordNode(char * word, int line) // constructor
        {
            wdObj = WordData(word,line);
            next = NULL;
        }
        WordNode() // default constructor
        {
            next=NULL;
        }
    };
    
    struct WordNode *first;
    struct WordNode *last;
    int size;
    
    void fillList(string file); // loads all words from given file
    struct WordNode* getPointer(char* word); // pointer to the node whose word data object stores given word
    void updateList(char* word,int line,WordData** wd,WordNode** wn); // update current list
    
public:
    WordList(); // default constructor
    WordList(const WordList & ); // copy constructor
    WordList & operator=(const WordList & ); // assignment operator constructor - '=' operator overload
    WordList(string); // internaly it calls loadList
    ~WordList();
    
    int getSize(); // to get size of list
    void print(ostream &); // print the actual output
    
};


#endif
