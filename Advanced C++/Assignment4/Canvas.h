//
//  Canvas.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-26.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__Canvas__
#define __Assignment4__Canvas__

#include <iostream>
#include <vector>
using namespace std;
class Canvas {
    int width;
    int height;

protected:
    vector<vector<char>> vectorCanvas;
    int getHeight() const;
    int getWidth() const;
    
    virtual char get (int c, int r) const; // gets character at row r and column c
    virtual void decorate(); //decorate client area with borders, frame etc. according to canvas type
    
    friend ostream& operator<<(ostream& out, Canvas& can);

public:
    
    Canvas(int rows,int cols):width(rows),height(cols){
        vector<char> rowVector;
        
        for (int i=0; i<height; i++) {
            rowVector.clear();
            
            for (int j=0; j<width; j++)
                rowVector.push_back(' ');
            
            
            vectorCanvas.push_back(rowVector);
        }
    }
    virtual void clear(char ch = ' '); // clears all character from vectorCanvas
    virtual void put (int c, int r, char ch='*'); // put given character on row r and column c position, if character is not given, '*' is set as default
    

};



#endif /* defined(__Assignment4__Canvas__) */
