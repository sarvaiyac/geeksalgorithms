//
//  RightIsosceles.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__RightIsosceles__
#define __Assignment4__RightIsosceles__

#include <iostream>
#include "Shape.h"
class RightIsosceles: public Shape {// RightIsosceles has to implement all pure virtual functions defined in shape.
    int height;
    int base;
    double computeGeometricArea() const;
    double computeGeometricPerimeter() const;
    int computeScreenArea() const;
    int computeScreenPerimeter() const;
    int computeHorizontalExtents() const;
    int computeVerticalExtents() const;
    
public:
    
    RightIsosceles(int r_height, string descName = "Generic RightIsoscele"):height(r_height),base(r_height),Shape(typeid(this).name(),descName){}
    
    // Accessor
    int getHeight() const;
    
    // Mutator
    void setHeight(int);
    
    void scale(int);
    
    void draw(int c,int r, Canvas& canvas, char ch ='*') const;
    
};
#endif /* defined(__Assignment4__RightIsosceles__) */
