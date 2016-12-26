#include <fstream>
#include <sstream>
#include <cstring>
#include <ctype.h>
#include <string>
#include <strings.h>
#include <stdio.h>
#include <locale>
#include "WordList.h"


// struct methods

// check if specific char is available or not

bool ifCharAvailable(char ch,char cArr[])
{
	for(int i=0;i<11;i++)
	        if(cArr[i]==ch) return true;
	return false;
}

// fills all words from given file
void WordList::fillList(string file)
{
	ifstream inputfile;
	inputfile.open(file.c_str());
	WordData* wd;
	WordNode* wn;
	string currentline;
    
	int line = 0;
	
    while (!inputfile.eof())
	{
		line++;
        getline(inputfile, currentline);
        istringstream onelinestring(currentline);
        char specialChar[11]={'.','?','+','-','(',')','/','*',',','|',';'};
        do
		{
			string substring;
            onelinestring>>substring;
            
            // create substrings from onelinestring
            int i=0;
            
			if(substring==" " || substring=="\n" || substring=="") continue;
            
            // remove special characters at starting of word
            
            while(ifCharAvailable(substring[i],specialChar) && i<substring.length())	i++;
            
            substring=substring.substr(i,substring.length());
            
			if(substring.length()>0)
			{
				i=substring.length()-1;
                // remove special characters at end of word
				while(ifCharAvailable(substring[i],specialChar) && i>=0)  i--;
				substring=substring.substr(0,i+1);
			}
            if(substring==" " || substring=="\n" || substring=="") continue;
            
			
			char * cArray = new char [substring.length()+1];
			strcpy (cArray, substring.c_str());
			
			updateList(cArray,line,&wd,&wn);
			
        } while (onelinestring);
	}

	inputfile.close();
}

int strcomparision(char *s1,char *s2)
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

// pointer to the node whose word data object stores given word
WordList::WordNode* WordList::getPointer(char* word)
{
	WordNode* tempWN=first;

	for(int i=0;i<size;i++)
	{
		if(strcomparision((tempWN->wdObj).word,word)==0)	return tempWN;
		tempWN=tempWN->next;
	}
	return NULL;
}

// update current list
void WordList::updateList(char* word,int line,WordData** wd,WordNode** wn)
{
	if (size == 0)
	{
		*wd=new WordData(word, line);
		*wn=new WordNode(**wd,NULL);
        first = *wn;
        last = *wn;
		size++;
	}
	else
	{
		WordNode* temp=getPointer(word);
		if(temp!=NULL)	//if already present then just increment the frequency
			temp->wdObj.append(line);
		else
		{
			temp=first;
			WordNode* prev=NULL;
			while( temp!=NULL && temp->wdObj.compareWord(word)<0 )
            {
                prev=temp;
                temp=temp->next;
            }
            if(temp==NULL)
			{
				*wd=new WordData(word, line);
				*wn=new WordNode(**wd,NULL);
				last->next =*wn;
				last=last->next;
			}
			else if(temp==first)
			{
				*wd=new WordData(word, line);
				*wn=new WordNode(**wd,temp);
				first =*wn;
                
			}
			else
			{
				*wd=new WordData(word, line);
				*wn=new WordNode(**wd,temp);
				prev->next =*wn;
			}
			size++;
		}
	}
}

// constructors

WordList::WordList() // default constructor
{ 
    size = 0;
    first=NULL;
    last=NULL;
}

// copy constructor
WordList::WordList(const WordList & objWL)
{
	this->first=objWL.first;
	this->last=objWL.last;
	this->size=objWL.size;
}

// assignment operator constructor - '=' operator overload
WordList & WordList::operator=(const WordList & objWL)
{
	this->first=objWL.first;
	this->last=objWL.last;
	this->size=objWL.size;
	return *this;
}

// internaly it calls fillList
WordList::WordList(string filename)
{
	size=0;
	fillList(filename);
}

//detructor
WordList::~WordList()
{
	WordNode* currentNode = first;
	while( currentNode != NULL )
	{
    		WordNode* next = currentNode->next; delete currentNode;currentNode = next;
    }
    first=NULL;
}

//public method

// to get size of list
int WordList::getSize()
{
    return size;
}

// print the actual output
void WordList::print(ostream & out)
{
	char c1,c2;
	WordNode *currentNode=first;
	for(int i=0;i<26;i++) // run loop from a to z
	{
        c1=i+'a';
		c2=i+'A';
		out<<"<"<<c2<<">"<<endl; // print character
        
        while( currentNode!=NULL && ((currentNode->wdObj.word)[0]==c1 || (currentNode->wdObj.word)[0]==c2 )) // iterates whole linked list
        {
			out.width(15);
            
            out << std::right <<currentNode->wdObj.word <<" "; // print word
            
            out<<"("<<currentNode->wdObj.frequency<<")"<<" "; // print frequency
            
            IntList ilist=currentNode->wdObj.getIntList();
            
            int len=ilist.getSize();
            
            const int* tempList=ilist.getList();
            
            for(int i=0;i<len;i++)
                out<<tempList[i]<<" "; // print line number
            
            out<<endl;
            
            currentNode=currentNode->next;
		}
	}
}




