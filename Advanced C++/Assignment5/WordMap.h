//
//  WordMap.h
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment5__WordMap__
#define __Assignment5__WordMap__

#include <iostream>
#include <map>
#include <numeric>
#include <iomanip>
using namespace::std;

class WordMap {
    map<string, int> mapData;
    
    WordMap(){}
public:
    WordMap(istream&);
    void insert(const string&);
    bool remove(const string&);
    int lookup(const string&) const;
    int size() const;
    void print () const;
    int sum_frequency_count();
    
    void operator()(string);// use *this as function object in for_each algorithm in constructor
    int operator()(int, const pair<string, int>& ); // use *this as function object for accumulate function
};

#endif /* defined(__Assignment5__WordMap__) */
