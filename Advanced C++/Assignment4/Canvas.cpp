//
//  Canvas.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-26.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Canvas.h"

int Canvas::getHeight() const{
    return height;
}
int Canvas::getWidth() const{
    return width;
}

// clears vectorCanvas with given character, if character is not given, default ' ' is taken
void Canvas::clear(char ch){
    for (int i = 0; i < height; i++)
        for (int j = 0; j < width; j++)
            vectorCanvas[i][j] = ch;
}

// put given character on row r and column c position, if character is not given, '*' is set as default
void Canvas::put (int c, int r, char ch){
    for (int i = 0; i < height; i++){
        for (int j = 0; j < width; j++){
            if (i==r && j==c){
                vectorCanvas[i][j] = ch;
                break;
            }
        }
    }
    
}
// gets character at row r and column c
char Canvas::get (int c, int r) const{
    for (int i=0; i<height; i++)
        for (int j=0; j<width; j++)
            if (i==r && j==c) return vectorCanvas[i][j];
    
    return ' ';
}
void Canvas::decorate(){ //decorate client area with borders, frame etc. according to canvas type
    clear(' ');
}

ostream& operator<<(ostream& out, Canvas& can){
    for (std::vector<vector<char>>::iterator it=can.vectorCanvas.begin(); it!=can.vectorCanvas.end(); ++it){
        for (std::vector<char>::iterator jt=it->begin(); jt!=it->end(); ++jt){
            out<<*jt;
        }
        out<<endl;
    }
    return out;
}


