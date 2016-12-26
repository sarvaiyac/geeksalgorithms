//
//  Shape.cpp
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-21.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Shape.h"

using namespace std;


// Protected Constructor for shape.
Shape::Shape(string name, string descrname):genericname(name),descriptivename(descrname){
    static long tempID = 1;
    uid = tempID++;
}


long Shape::getUid() const{
    return uid;
}
string Shape::getGenericname() const{
    return genericname;
}
string Shape::getDescriptivename() const{
    return descriptivename;
}

string Shape::toString() const{
    ostringstream out;
    out<<setprecision(2) << fixed;
    out<<"Shape Information"<<endl;
    out<<"-----------------"<<endl;
    out<<setw(17)<<left<<"Type of this: "<<typeid(this).name()<<endl;
    out<<setw(18)<<left<<"Type of *this: "<<typeid(*this).name()<<endl;
    out<<setw(18)<<left<<"Generic name: "<<getGenericname()<<endl;
    out<<setw(18)<<left<<"Description: "<<getDescriptivename()<<endl;
    out<<setw(18)<<left<<"Id: "<<getUid()<<endl;
    out<<setw(18)<<left<<"H extent: "<<computeHorizontalExtents()<<endl;
    out<<setw(18)<<left<<"V extent: "<<computeVerticalExtents()<<endl;
    out<<setw(18)<<left<<"Scr Area: "<<computeScreenArea()<<endl;
    out<<setw(18)<<left<<"Geo Area: "<<computeGeometricArea()<<endl;
    out<<setw(18)<<left<<"Scr Perimeter: "<<computeScreenPerimeter()<<endl;
    out<<setw(18)<<left<<"Geo Perimeter: "<<computeGeometricPerimeter()<<endl;
    
    return out.str();
}
ostream& operator<<(ostream& out, Shape& shape){
    out<< shape.toString();
    return out;
}



