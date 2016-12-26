//
//  BorderedCanvas.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "BorderedCanvas.h"

// put given character on row r and column c position, if character is not given, '*' is set as default

void BorderedCanvas::put (int c, int r, char ch){
    int rows = getHeight()+2;   // increment by 2 because Canvas contains border
    int cols = getWidth()+2;    // increment by 2 because Canvas contains border
    
    for (int i = 1; i < rows-1; i++){ //row starts with 1 because actual canvas starts with 1
        for (int j = 1; j < cols-1; j++){ //column starts with 1 because actual canvas starts with 1
            if (i==r+1 && j==c+1){ // if condition matches, replace the character and break the loop.
                vectorCanvas[i][j] = ch;
                break;
            }
        }
    }
}

void BorderedCanvas::decorate(){
    vectorCanvas.clear(); // clear the vectorCanvas to recreate it
    
    int rows = getHeight()+2; // increment by 2 because Canvas contains border
    int cols = getWidth()+2;// increment by 2 because Canvas contains border
    
    vector<char> rowVector;
    for (int i = 0; i < rows; i++){
        rowVector.clear();
        for (int j = 0; j < cols; j++){
            if ((i==0 && j==0)|| (i== 0 && j==cols-1)||(i==rows-1 && j==0)|| (i== rows-1 && j==cols-1)) { // put '+' on each corner
                rowVector.push_back('+');
            }else if(j==0 || j==cols-1){ // put '|' on each first and last element of line
                rowVector.push_back('|');
            }else if (i==0 || i==rows-1){ // put '-' to create border
                rowVector.push_back('-');
            }else{
                rowVector.push_back(' '); // put ' ' to create empty area
            }
        }
        
        vectorCanvas.push_back(rowVector);
    }
}

void BorderedCanvas::clear(char ch){
    int rows = getHeight()+2;// increment by 2 because Canvas contains border
    int cols = getWidth()+2;// increment by 2 because Canvas contains border
    
    for (int i=1; i < rows-1; i++)//row starts with 1 because actual canvas starts with 1
        for (int j = 1; j < cols-1; j++)//column starts with 1 because actual canvas starts with 1
            vectorCanvas[i][j] = ch;
}

char BorderedCanvas::get (int c, int r) const{
    int rows = getHeight()+2; // increment by 2 because Canvas contains border
    int cols = getWidth()+2;// increment by 2 because Canvas contains border
    
    for (int i = 1; i < rows-1; i++)//row starts with 1 because actual canvas starts with 1
        for (int j = 1; j < cols-1; j++)//row starts with 1 because actual canvas starts with 1
            if (i==r+1 && j==c+1) // if condition matches, return value of that character on vectorCanvas[row][column];
               return vectorCanvas[i][j];
    
    return ' ';
}
