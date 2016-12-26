//
//  MyPair.h
//  Assignment5
//
//  Created by Chintan Sarvaiya on 2014-08-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment5__MyPair__
#define __Assignment5__MyPair__

#include <string>
#include <utility>

using namespace std;

template <class K, class V>

class MyPair : public pair<K, V> {
    
public:
    MyPair(){}
    MyPair(const K& k, const V& v): std::pair<K,V>(k,v){} // base class c'tor call
    
    friend bool operator==(const MyPair& pair1, const MyPair& pair2){
        return (pair1.first == pair2.first);
    }
    
    bool operator()(const MyPair& pair1, const MyPair& pair2){
        return (pair1.first < pair2.first);
    }
    
};

#endif /* defined(__Assignment5__MyPair__) */
