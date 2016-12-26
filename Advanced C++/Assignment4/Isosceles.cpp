//
//  Isosceles.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Isosceles.h"
// Accessor
int Isosceles::getHeight() const { return height;}

// Mutator
void Isosceles::setHeight(int i_height){ height = i_height; }

void Isosceles::scale(int n){ // it scales height and base of Isosceles with n, if perticular conidtion matches
    
    if (height+n >=  1) {
        height = height+n;
        base = 2*height - 1;
    }
}

double Isosceles::computeGeometricArea() const{
    return (double)height*base/2;
}
double Isosceles::computeGeometricPerimeter() const{
    return (double) base + (2*(sqrt((0.25*base*base) + (height*height))));
}
int Isosceles::computeScreenArea() const{
    return height*height;
}
int Isosceles::computeScreenPerimeter() const{
    return 4*(height-1);
}
int Isosceles::computeHorizontalExtents() const{
    return base;
}
int Isosceles::computeVerticalExtents() const{
    return height;
}

// This method draws Isosceles with specified character on specified canvas, and that Isosceles starts with row r and column c

void Isosceles::draw(int c,int r, Canvas& canvas, char ch) const{
    int start = c;
    int end = c+base;
    
    for (int i = (r+height-1); i>=r; i--) {
        for (int j=start; j<end; j++)
            canvas.put(j, i,ch);
        
        start++;
        end--;
    }
    
}

