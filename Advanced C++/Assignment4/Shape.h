//
//  Shape.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-21.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__Shape__
#define __Assignment4__Shape__

#include <iostream>
#include <iomanip> //setprecision
#include <math.h>
#include <typeinfo>
#include <string>
#include <sstream>
#include "Canvas.h"

using namespace std;

class Shape {   // This is abstract class. It has multiple pure virtual functions, so which ever class inherits Shape class, it has to implement
                //  all such methods
    long uid;
    string genericname;
    string descriptivename;
    
protected:
    Shape(string name, string descrname);
    
    //Accessor
    long getUid() const;
    string getGenericname() const;
    string getDescriptivename() const;
    
    //Mutator
    void setDescriptivename(string);
    
    // virtual methods
    
    string toString() const; // method that converts object into string representation
    virtual void scale(int) = 0; // scales the shape - must be implemented in derived class
    virtual double computeGeometricArea() const = 0;// computes geometric area of shape - must be implemented in derived class
    virtual double computeGeometricPerimeter() const = 0; // computes geometric perimeter of shape - must be implemented in derived class
    virtual int computeScreenArea() const = 0;// computes screen area of shape - must be implemented in derived class
    virtual int computeScreenPerimeter() const = 0;// computes screen perimeter of shape - must be implemented in derived class
    virtual int computeHorizontalExtents() const = 0;// computes horizontal extents of shape - must be implemented in derived class
    virtual int computeVerticalExtents() const = 0;// computes vertical extents of shape - must be implemented in derived class
   
    virtual void draw(int c,int r, Canvas& canvas, char ch ='*') const = 0;// it draws particular shape on the canvas, starts with row - r and column -c : must be implemented in derived class
    
    friend ostream& operator<<(ostream& out, Shape& shape); // overload operator <<
};


#endif /* defined(__Assignment4__Shape__) */
