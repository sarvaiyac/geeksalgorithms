//
//  Rectangle.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Rectangle.h"
// Accessor
int Rectangle::getWidth()  const{ return width;}
int Rectangle::getHeight()  const{ return height;}

// Mutator
void Rectangle::setWidth(int r_width){ width = r_width; }
void Rectangle::setHeight(int r_height){ height = r_height; }

void Rectangle::scale(int n){ // it scales height - width of rectangle with n, if perticular conidtion matches
    if (width+n >=  1 || height+n >=1) {
        width = width+n;
        height = height+n;
    }
}
double Rectangle::computeGeometricArea() const{
    return (double)width*height;
}
double Rectangle::computeGeometricPerimeter() const{
    return (double)2*(width+height);
}
int Rectangle::computeScreenArea() const{
    return width*height;
}
int Rectangle::computeScreenPerimeter() const{
    return 2*(width+height)-4;
}
int Rectangle::computeHorizontalExtents() const{
    return width;
}
int Rectangle::computeVerticalExtents() const{
    return height;
}

// This method draws rectangle with specified character on specified canvas, and that rectangle starts with row r and column c
void Rectangle::draw(int c,int r, Canvas& canvas, char ch) const{
    for (int i=r; i < r+height; i++)
        for (int j=c; j < c+width; j++)
            canvas.put(j, i,ch);
}

