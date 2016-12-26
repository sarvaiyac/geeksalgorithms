//
//  Isosceles.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__Isosceles__
#define __Assignment4__Isosceles__

#include <iostream>
#include "Shape.h"

class Isosceles: public Shape { // Isosceles has to implement all pure virtual functions defined in shape.
    int height;
    int base;
    double computeGeometricArea() const;
    double computeGeometricPerimeter() const;
    int computeScreenArea() const;
    int computeScreenPerimeter() const;
    int computeHorizontalExtents() const;
    int computeVerticalExtents() const;
    
public:
    Isosceles(int i_height, string descName = "Generic Isoscele"):height(i_height),Shape(typeid(this).name(),descName){
        base=2*height-1;
    }
    
    // Accessor
    int getHeight()  const;
    
    // Mutator
    void setHeight(int);
    
    void scale(int);
    
    void draw(int c,int r, Canvas& canvas, char ch ='*') const;
    
};

#endif /* defined(__Assignment4__Isosceles__) */
