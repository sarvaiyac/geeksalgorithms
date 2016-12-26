#include<iostream>
using namespace std;

#include "Shape.h"
#include "Rhombus.h"
#include "Rectangle.h"
#include "Isosceles.h"
#include "RightIsosceles.h"
#include "Canvas.h"
#include "BorderedCanvas.h"
#include "FramedCanvas.h"

// function prototypes
void drawHouse();         
void testShapeAndCamvas();


int main()
{
   testShapeAndCamvas();
   drawHouse(); // draw house

   return 0;
}

// Uses different shapes to draw a textual image that looks line a house
void drawHouse()
{
   // draw a house front view
   FramedCanvas canvas(45, 50, "A Geometric House: Front View");      // a canvas to draw on
   Rectangle chimney(2, 14, "Chimeny on the roof"); // A vertical 14 x 2 chimney
   chimney.draw(4, 7, canvas, 'H');                 // Draw chimney on canvas

   Isosceles roof(21, "House roof");     // A triangular roof of height 21
   roof.draw(1, 1, canvas, '/');              // Draw roof

   Rectangle skylightFrame(9, 5, "Frame around skylight");         // A 9c x 5r skylight frame
   skylightFrame.draw(17, 11, canvas, 'H');                // Draw skylight frame

   Rectangle skylight(7, 3, "skylight");         // A 7c x 3r skylight
   skylight.draw(18, 12, canvas, ' ');                // Draw skylight


   Rectangle front(41, 22, "Front wall");  // A 41c x 22r rectangular front wall
   front.draw(1, 22, canvas, ':');               // draw front wall

   Rectangle leftDoors(7, 15, "Front Left Door");         // A 7c x 15r rectangle door
   leftDoors.draw(22, 28, canvas, 'd');                // Draw left door

   Rectangle rightDoors(7, 15, "Front Right Door");         // A 7c x 15r rectangle door
   rightDoors.draw(30, 28, canvas, 'd');                // Draw right door

   // visually split the two doors
   Rectangle doorsMiddle(1, 15, "Vertical center panel between front doors");
   doorsMiddle.draw(29, 28, canvas, '=');       // draw the middle vertical rectangle

   // windows above front door
   Rectangle doorTop = Rectangle(15, 2, "Top door window");  //  A 4c by 2r rectangle 
   doorTop.draw(22, 24, canvas, 'W');              // Draw top door window


   Rectangle doggyDoor = Rectangle(4, 2, "A rectangle  doggy door");  //  A 4c by 2r rectangle 
   doggyDoor.draw(4, 41, canvas, 'D');              // Draw doggy door

   Rhombus window(7, "Diamond shape window on front wall"); // A rhombus window on front wall
   window.draw(4, 25, canvas, 'O');            // Draw rhombus window

   cout << canvas << endl;            // polymorphic call

   cout << chimney << endl;
   cout << roof << endl;
   cout << skylightFrame << endl;
   cout << skylight << endl;
   cout << front << endl;
   cout << leftDoors << endl;
   cout << rightDoors << endl;
   cout << doorsMiddle << endl;
   cout << doggyDoor << endl;
   cout << window << endl;

}

// Code used in the examples given in the assignment
void testShapeAndCamvas()
{
   Canvas canvas(20, 10);          // a plain canvas of width 20 and height 10
   canvas.clear('-');              // clear canvas with the '-' character
   cout << canvas << endl;         // print canvas

   // a bordered canvas with client area of width 20 and height 10
   BorderedCanvas bCanvas(20, 10);
   cout << bCanvas << endl;

   // a framed canvas with client area of width 20 and height 10
   FramedCanvas fCanvas(20, 10, "A framed canvas");
   fCanvas.clear('^');
   cout << fCanvas << endl;

   // draw a right triangle with height 6 at col 8  row 2 on canvas
   RightIsosceles shp1 = RightIsosceles(6);
   shp1.draw(8, 2, canvas);
   cout << canvas << endl;

   // draw shp1 above at row 4 col 9 on bCanvas
   shp1.draw(9, 4, bCanvas);
   cout << bCanvas << endl;

   // draw shp1 above at col 3 row 0 on fCanvas
   shp1.draw(3, 0, fCanvas, 'O');
   cout << fCanvas << endl;

   // redraw shp1 above at col 16 row 6 on fCanvas
   shp1.draw(16, 6, fCanvas);
   cout << fCanvas << endl;

   // show scaling of a  Rhombus and  an Isosceles objects
   fCanvas.clear();
   Rhombus rhom(9);  // a Rhombus with diagonal length = 9
   rhom.draw(1, 1, fCanvas);
   Isosceles iso(1); // an Isosceles with height 1
   iso.draw(10, 4, fCanvas, 'O'); // draw iso on fCanvas at column 10 row 4 using 'O'
   cout << fCanvas << endl;
   for (int k = 0; k < 3; ++k)
   {
      fCanvas.clear();
      rhom.scale(-1);         // scale down
      rhom.draw(1, 1, fCanvas);
      iso.scale(+1);          // // scale up
      iso.draw(10, 4, fCanvas, 'O');
      cout << fCanvas << endl;
   }

}





