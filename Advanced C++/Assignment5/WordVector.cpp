//
//  WordVector.cpp
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "WordVector.h"

WordVector::WordVector(istream& an_input_stream){
    istream_iterator<string> start(an_input_stream),finish;
    *this = for_each(start, finish, *this);
}

// use *this as function object in for_each algorithm
void WordVector::operator()(string word){
    insert(word);
}

// use *this as function object for accumulate function
int WordVector::operator()(int previous, const MyPair<string, int>& next) {
    return previous + next.second;
}

void WordVector::insert(const string& word){
    MyPair<string, int> tempPair(word,1);
    vector<MyPair<string, int>>::iterator found = find(wordvec.begin(), wordvec.end(), tempPair);
    
    if (found != wordvec.end())  found->second++;
    else   wordvec.push_back(tempPair);
}
bool WordVector::remove(const string& word){
    vector<MyPair<string, int>>::iterator found = find(wordvec.begin(), wordvec.end(), MyPair<string, int> (word,1));
    
    if (found != wordvec.end()){
        wordvec.erase(found);
        return true;
    }
    
    return false;
}

int WordVector::lookup(const string& word) const{
    vector<MyPair<string, int>>::const_iterator found = find(wordvec.begin(), wordvec.end(), MyPair<string, int> (word,1));
    if (found != wordvec.end()) return found->second;
    return 0;
}
int WordVector::size() const{
    return (int)wordvec.size();
}
void WordVector::print () const{
    for_each(wordvec.begin(), wordvec.end(),[](const MyPair<string, int>& printObj) {
        cout<<setw(2)<<right<<printObj.second<<" : "<<printObj.first<<endl;
    });
}

// it gives sum of all frequencies of words
// it uses int operator() as function object for adding frequencies

int WordVector::sum_frequency_count() const{
    return accumulate(std::begin(wordvec), std::end(wordvec), 0,*this);
}

void WordVector::sort(){
    std::sort(wordvec.begin(), wordvec.end(), MyPair<string, int>());
}