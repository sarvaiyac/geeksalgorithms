//
//  RightIsosceles.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "RightIsosceles.h"
// Accessor
int RightIsosceles:: getHeight()  const{ return height;}

// Mutator
void RightIsosceles::setHeight(int r_height){ height = r_height; }

void RightIsosceles::scale(int n){  // it scales height and base of RightIsosceles with n, if perticular conidtion matches
    
    if (height+n >=  1) {
        height = height+n;
        base = height;
    }
}


double RightIsosceles::computeGeometricArea() const{
    return (double)height*height/2;
}
double RightIsosceles::computeGeometricPerimeter() const{
    return (double) (2+sqrt(2))*height;
}
int RightIsosceles::computeScreenArea() const{
    return height*(height+1)/2;
}
int RightIsosceles::computeScreenPerimeter() const{
    return 3*(height-1);
}
int RightIsosceles::computeHorizontalExtents() const{
    return height;
}
int RightIsosceles::computeVerticalExtents() const{
    return height;
}

// This method draws RightIsosceles with specified character on specified canvas, and that RightIsosceles starts with row r and column c
void RightIsosceles::draw(int c,int r, Canvas& canvas, char ch) const{
    
    int counter = c+height;
    for (int i=r+height-1; i>=r; i--) {
        
        for (int j=c; j<counter; j++)
            canvas.put(j, i,ch) ;
        
        counter--;
    }
    
}