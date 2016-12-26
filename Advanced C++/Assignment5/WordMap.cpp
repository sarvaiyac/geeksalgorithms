//
//  WordMap.cpp
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "WordMap.h"

WordMap::WordMap(istream& an_input_stream){
    istream_iterator<string> start(an_input_stream),finish;
    *this = for_each(start, finish, *this);
}

void WordMap::operator()(string word){
    insert(word);
}
int WordMap::operator()(int previous, const pair<string, int>& next) {
    return previous + next.second;
}


void WordMap::insert(const string& word){
    map<string,int>::iterator found = mapData.find(word);
    if (found != mapData.end()) found->second++;
    else mapData[word] = 1;
}
bool WordMap::remove(const string& word){
    if (mapData.find(word) != mapData.end()){
        mapData.erase(word);
        return true;
    }
    return false;
}

int WordMap::lookup(const string& word) const {
    map<string,int>::const_iterator found = mapData.find(word);
    if (found != mapData.end()) return found->second;
    return 0;
}
int WordMap::size() const{
    return (int)mapData.size();
}
void WordMap::print () const{
    for_each(mapData.begin(), mapData.end(),[](const pair<string, int> &temp){
        cout<<setw(2)<<right<<temp.second<<" : "<<temp.first<<endl;
    });
}

// it gives sum of all frequencies of words
// it uses int operator() as function object for adding frequencies

int WordMap::sum_frequency_count(){
    return accumulate(std::begin(mapData), std::end(mapData), 0, *this);
}