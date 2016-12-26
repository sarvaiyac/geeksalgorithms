//
//  Rhombus.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Rhombus.h"
// Accessor
int Rhombus::getDiagonal()  const{ return diagonal;}

// Mutator
void Rhombus::setDiagonal(int i_diagonal){ diagonal = i_diagonal; if (diagonal%2==0)  diagonal++;}

void Rhombus::scale(int n){ // it scales diagonal of Rhombus with n, if perticular conidtion matches
                            // if new diagonal becomes even, we have to choose best next diagonal based on n is less than 0 or greater than 0
    
    if (diagonal+n >=  1) {
        diagonal = diagonal+n;
        if (diagonal%2==0){
            if (n<0)    diagonal--;
            else    diagonal++;
        }
    }
}
double Rhombus::computeGeometricArea() const{
    return (double)diagonal*diagonal/2;
}
double Rhombus::computeGeometricPerimeter() const{
    return (double)(2*sqrt(2))*diagonal;
}
int Rhombus::computeScreenArea() const{
    int n = floor(diagonal/2);
    return (2*n)*(n+1)+1;
}
int Rhombus::computeScreenPerimeter() const{
    return 2*(diagonal-1);
}
int Rhombus::computeHorizontalExtents() const{
    return diagonal;
}
int Rhombus::computeVerticalExtents() const{
    return diagonal;
}

// This method draws Rhombus with specified character on specified canvas, and that Rhombus starts with row r and column c

void Rhombus::draw(int c,int r, Canvas& canvas, char ch) const{
    
    int middle = ceil((float)diagonal/2);
    int start = c;
    int end = c+ diagonal;
    
    for (int i=r+middle-1; i>=r; i--) {
        for (int j=start; j<end; j++) {
            canvas.put(j, i,ch);
        }
        start++;
        end--;
    }
    
    start =c+1;
    end = c+diagonal-1;
    
    for (int i=r+middle; i<r+diagonal; i++) {
        for (int j=start; j<end; j++) {
            canvas.put(j, i,ch);
        }
        start++;
        end--;
    }
    
}

