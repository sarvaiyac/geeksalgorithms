//
//  WordMultiSet.cpp
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "WordMultiSet.h"
WordMultiSet::WordMultiSet(istream& an_input_stream){
    istream_iterator<string> start(an_input_stream),finish;
    *this = for_each(start, finish, *this);
}
// use *this as function object in for_each algorithm in constructor

void WordMultiSet::operator()(string word){
    insert(word);
}
void WordMultiSet::insert(const string& word){
    wordset.insert(word);
}
bool WordMultiSet::remove(const string& word){
    if (wordset.find(word) != wordset.end()){
        wordset.erase(word);
        return true;
    }
    return true;
}


int WordMultiSet::lookup(const string& word) {
    auto p = wordset.equal_range(word);
    return (int) distance(p.first, p.second);
}


int WordMultiSet::size() const{
    return (int)wordset.size();
}
void WordMultiSet::print (){
    auto it = wordset.begin();
    while (it!= wordset.end()) {
        cout<<setw(2)<<right<<lookup(*it)<<" : "<<*it<<endl;
        it = wordset.equal_range(*it).second;
    }
    
}

