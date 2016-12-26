//
//  FramedCanvas.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "FramedCanvas.h"

// put given character on row r and column c position, if character is not given, '*' is set as default

void FramedCanvas::put (int c, int r, char ch){
    int rows = getHeight();
    int cols = getWidth();
    
    for (int i = 3; i < rows-1; i++){ //row starts with 3 because actual canvas starts with 3
        for (int j = 1; j < cols-1; j++){//column starts with 1 because actual canvas starts with 1
            if (i==r+3 && j==c+1){// if condition matches, replace the character and break the loop.
                vectorCanvas[i][j] = ch;
                break;
            }
        }
    }
}
void FramedCanvas::decorate(){
    
    int rows = getHeight();
    int cols = getWidth();
    
    vectorCanvas.clear();
    
    vector<char> rowVector;
    int i=0;
    
    // iterate loop for first 2 lines in which
    // 1st line contains border
    // 2nd line contains title
    
    for (; i<2; i++) {
        rowVector.clear();
        
        for (int j=0; j<cols; j++) {
            
            if ((i==0 && j==0)|| (i== 0 && j==getWidth()-1)) { // put '+' on each corner
                rowVector.push_back('+');
            }else if((i==1 && j==0)){ // if iterator reaches second line, write the title into frame.
                rowVector.push_back('|');
                int len = 0;
                while (len != canvastitle.length()) {
                    rowVector.push_back(canvastitle[len]);
                    len++;
                }
                
                j = len+1;
                for (; j<cols; j++) {
                    if (j==cols-1) {
                        rowVector.push_back('|');
                    }else{
                        rowVector.push_back(' ');
                    }
                }
            }else{ // put '-' to create border
                rowVector.push_back('-');
            }
        }
        vectorCanvas.push_back(rowVector);
    }
    
    for (; i < rows; i++){
        rowVector.clear();
        for (int j = 0; j < cols; j++){
            if ((i==2 && j==0)|| (i== 2 && j==cols-1)){ // put '|' on each first and last element
                rowVector.push_back('|');
            }else if((i==rows-1 && j==0)|| (i== rows-1 && j==cols-1)) { // put '+' on each corner
                rowVector.push_back('+');
            }else if(j==0 || j==cols-1){  // put '|' on each first and last element
                rowVector.push_back('|');
            }else if (i==2 || i==rows-1){ // put '-' to create border
                rowVector.push_back('-');
            }else{
                rowVector.push_back(' '); // put ' ' to create empty area
            }
        }
        
        vectorCanvas.push_back(rowVector);
    }
}

void FramedCanvas::clear(char ch){
    for (int i=3; i < getHeight()-1; i++)//row starts with 3 because actual canvas starts with 3
        for (int j = 1; j < getWidth()-1; j++)//column starts with 1 because actual canvas starts with 1
            vectorCanvas[i][j] = ch;
}

char FramedCanvas::get (int c, int r) const{
    int rows = getHeight();
    int cols = getWidth();
    
    for (int i = 3; i < rows-1; i++)//row starts with 3 because actual canvas starts with 3
        for (int j = 1; j < cols-1; j++)//column starts with 1 because actual canvas starts with 1
            if (i==r+3 && j==c+1)// if condition matches, return value of that character on vectorCanvas[row][column];
                return vectorCanvas[i][j];
    
    return ' ';
}