//
//  WordVector.h
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment5__WordVector__
#define __Assignment5__WordVector__

#include <iostream>
#include <vector>
#include <iomanip>
#include <numeric>
#include "MyPair.h"
using namespace::std;

class WordVector {
    vector<MyPair<string, int>> wordvec;
    

public:
    WordVector(){}
    WordVector(istream&);
    
    void insert(const string&);
    bool remove(const string&);
    int lookup(const string&) const;
    int size() const;
    void print () const;
    int sum_frequency_count() const;
    void sort();
    
    void operator()(string); // use *this as function object in for_each algorithm in constructor
    int operator()(int, const MyPair<string, int>& );// use *this as function object for accumulate function
} ;


#endif /* defined(__Assignment5__WordVector__) */
