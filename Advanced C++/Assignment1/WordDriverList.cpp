//
//  WordListDriver.cpp
//  Assignment1
//
//  Created by Chintan Sarvaiya on 2014-05-26.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include <iostream>
#include "WordList.h"

using namespace std;

 
int main(int argc, const char * argv[])
{	
	WordList wList("Input.txt");
	wList.print(cout);	
	return 0;
}
