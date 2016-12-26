//
//  BorderedCanvas.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__BorderedCanvas__
#define __Assignment4__BorderedCanvas__

#include <iostream>
#include "Canvas.h"
class BorderedCanvas:public Canvas {
    
    
public:
    BorderedCanvas(int rows,int cols):Canvas(rows,cols){
        decorate();
    }
    
    void decorate();
    void clear(char ch = ' ');
    void put (int c, int r, char ch='*');
    char get (int c, int r) const;
};


#endif /* defined(__Assignment4__BorderedCanvas__) */
