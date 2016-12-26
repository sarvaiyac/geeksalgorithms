//
//  Rectangle.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__Rectangle__
#define __Assignment4__Rectangle__

#include <iostream>
#include "Shape.h"
class Rectangle: public Shape { // Recrangle has to implement all pure virtual functions defined in shape.
                               
    int width;
    int height;
    
    
    double computeGeometricArea() const;
    double computeGeometricPerimeter() const;
    int computeScreenArea() const;
    int computeScreenPerimeter() const;
    int computeHorizontalExtents() const;
    int computeVerticalExtents() const;

public:
    Rectangle(int r_width, int r_height, string descName = "Generic Rectangle"):width(r_width),height(r_height),Shape(typeid(this).name(),descName){}
    
    // Accessor
    int getWidth() const;
    int getHeight() const;
    
    // Mutator
    void setWidth(int);
    void setHeight(int);
    
    void scale(int);
    void draw(int c,int r, Canvas& canvas, char ch ='*') const;
    
};
#endif /* defined(__Assignment4__Rectangle__) */
