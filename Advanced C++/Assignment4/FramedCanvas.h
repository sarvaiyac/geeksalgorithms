//
//  FramedCanvas.h
//  Assignment4
//
//  Created by Chintan Sarvaiya on 2014-07-27.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment4__FramedCanvas__
#define __Assignment4__FramedCanvas__

#include <iostream>
#include "Canvas.h"

class FramedCanvas : public Canvas {
    string canvastitle;
    
public:
    
    // rows = rows + 2  because it contains border
    // cols = cols + 4 because it contains border (1) + framed area (3)

    FramedCanvas(int rows,int cols,string title="A Framed Canvas"):canvastitle(title),Canvas(rows+2,cols+4){
        decorate();
    }
    void clear(char ch = ' ');
    void decorate();
    void put (int c, int r, char ch='*');
    char get (int c, int r) const;
};
#endif /* defined(__Assignment4__FramedCanvas__) */
