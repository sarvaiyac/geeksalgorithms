//
//  WordListDriver.cpp
//  Assignment1
//
//  Created by Chintan Sarvaiya on 2014-05-26.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include <iostream>
#include <assert.h>
#include <fstream>
#include "WordList.h"

 
int main(int argc, const char * argv[])
{
    using namespace std;

    WordList wList("Input.txt");
    ofstream fout("Output.txt");
    assert(fout.good());

    wList.printData(fout);
    wList.printData(fout,"wh");
    wList.printData(fout,"the");
    
    return 0;
}
