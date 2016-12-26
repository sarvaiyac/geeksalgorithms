//
//  WordMultiSet.h
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment5__WordMultiSet__
#define __Assignment5__WordMultiSet__

#include <iostream>
#include <set>
#include <iomanip>

using namespace std;
class CompareWords {
    
    
public:
    bool operator()(string s1,string s2){
        if (s1.length() == s2.length())  return s1<s2;
        return s1.length()<s2.length();
    }
};

class WordMultiSet {
    
    multiset<string, CompareWords> wordset;
    WordMultiSet(){}
public:
    WordMultiSet(istream&);
    void insert(const string&);
    bool remove(const string&);
    int lookup(const string& );
    int size() const;
    void print ();
    
    void operator()(string); // use *this as function object in for_each algorithm in constructor
};


#endif /* defined(__Assignment5__WordMultiSet__) */
