//
//  Rhombus.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__Rhombus__
#define __Assignment4__Rhombus__

#include <iostream>
#include "Shape.h"

class Rhombus: public Shape { // Rhombus has to implement all pure virtual functions defined in shape.
    int diagonal;
    double computeGeometricArea() const;
    double computeGeometricPerimeter() const;
    int computeScreenArea() const;
    int computeScreenPerimeter() const;
    int computeHorizontalExtents() const;
    int computeVerticalExtents() const;
    
public:
    
    //check if diagonal is even, then increament it by one. As diagonal must be an odd number.
    
    Rhombus(int r_diagonal, string descName = "Generic Rhombus"):diagonal(r_diagonal),Shape(typeid(this).name(),descName){
        if (r_diagonal%2==0)  diagonal++;
    }
    
    // Accessor
    int getDiagonal() const;
    
    // Mutator
    void setDiagonal(int);
    
    void scale(int);
    
    void draw(int c,int r, Canvas& canvas, char ch ='*') const;
    
};

#endif /* defined(__Assignment4__Rhombus__) */
