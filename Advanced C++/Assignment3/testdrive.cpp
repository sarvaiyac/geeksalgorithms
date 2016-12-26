#include <iostream>
#include <iomanip>
#include <string>
#include <cassert>
using namespace std;
#include "Fraction.h"

int main()
{
    cout << "Test Fraction and Fractional Computation" << '\n';
    cout << "----------------------------------------\n\n";
    
    cout << "testing default ctor with Fraction f0;" << '\n';
    Fraction f0;         // test default ctor
    cout << "testing fraction == integer with f0 == 0" << '\n';
    assert(f0 == 0);   // test fraction == integer
    cout << "f0: " << f0 << '\n';
    cout << "ok\n\n";
    
    cout << "testing 1-arg ctor with Fraction f1(5);" << '\n';
    Fraction f1(5);      // test 1 arg ctor
    assert(5 == f1); // test integer =  fraction
    cout << "f1: " << f1 << "\n\n";
    cout << "ok\n\n";
    
    cout << "testing copy ctor with Fraction f2 = f1;" << '\n';
    Fraction f2 = f1;      // test ctor
    assert(f2 == f1);      // test fraction == fraction
    cout << "f2: " << f2 << "\n\n";
    cout << "ok\n\n";
    
    cout << "testing fraction == integer with f1 == 5;" << '\n';
    assert(f1 == 5);   // test fraction == integer
    cout << "ok\n\n";
    
    cout << "testing fraction == fraction with f1 == f2;" << '\n';
    assert(f1 == f2);   // test fraction == fraction
    cout << "ok\n\n";
    
    cout << "testing fraction != fraction with !(f1 != f2);" << '\n';
    assert(!(f1 != f2));   // test fraction != fraction
    cout << "ok\n\n";
    
    cout << "testing 2 args ctor with Fraction half = Fraction(1, 2);" << '\n';
    Fraction half = Fraction(1, 2);     // 2 args ctor
    cout << "half: " << half << "\n\n";
    
    cout << "testing operator+ with f2 = f1 + half;" << '\n';
    f2 = f1 + half;     // test operator+
    cout << "f2: " << f2 << "\n\n";
    
    cout << "testing operator< with f1 < f2;" << '\n';
    assert(f1 < f2);           // operator <
    cout << "ok\n\n";
    
    cout << "testing operator<= with f1 <= f2;" << '\n';
    assert(f1 <= f2);           // operator <=
    cout << "ok\n\n";
    
    cout << "testing operator> with f2 > f1;" << '\n';
    assert(f2 > f1);           // operator >
    cout << "ok\n\n";
    
    cout << "testing operator>= with f2 >= f1;" << '\n';
    assert(f2 >= f1);           // operator >=
    cout << "ok\n\n";
    
    cout << "testing operator!= with  f2 != f1;" << '\n';
    assert(f2 != f1);           // operator !=
    cout << "ok\n\n";
    
    cout << "testing operator==, operator- with f1 == f2 - half;" << '\n';
    assert(f1 == f2 - half);       // operator -
    cout << "ok\n\n";
    
    cout << "testing 2 args ctor with Fraction oneThird (1, 3);" << '\n';
    Fraction oneThird(1, 3);
    cout << "oneThird: " << oneThird << "\n\n";
    
    cout << "testing assignment=, binary  +, -, and unary - with \n"
    "                f2 = f1 + oneThird - ( - oneThird );" << '\n';
    f2 = f1 + oneThird - (-oneThird); // assignment=, binary  +, -, and unary -
    cout << "f2: " << f2 << '\n';
    assert(f2 == Fraction(17, 3));
    cout << "ok\n\n";
    
    cout << "testing fractional expression with f2 = f1 - oneThird + ( - oneThird );" << '\n';
    f2 = f1 - oneThird + (-oneThird); // assignment=, binary +, -, and unary +
    cout << "f2 *: " << f2 << '\n';
    assert(f2 == Fraction(13, 3));
    cout << "ok\n\n";
    
    
    cout << "testing post++ with f2 = f1++;" << '\n';
    f2 = f1++;
    cout << "f1 : " << f1 << '\n';
    cout << "f2 : " << f2 << '\n';
    assert(f1 == Fraction(6));
    assert(f2 == Fraction(5));
    cout << "ok\n\n";
    
    cout << "testing pre++ with f2 = ++f1;" << '\n';
    f2 = ++f1;
    cout << "f1 : " << f1 << '\n';
    cout << "f2 : " << f2 << '\n';
    assert(f1 == Fraction(7));
    assert(f2 == Fraction(7));
    cout << "ok\n\n";
    
    cout << "testing post-- with f2 = f1--;" << '\n';
    f2 = f1--;
    cout << "f1 : " << f1 << '\n';
    cout << "f2 : " << f2 << '\n';
    assert(f1 == Fraction(6));
    assert(f2 == Fraction(7));
    cout << "ok\n\n";
    
    cout << "testing pre-- with f2 = --f1;" << '\n';
    f2 = --f1;
    cout << "f1 : " << f1 << '\n';
    cout << "f2 : " << f2 << '\n';
    assert(f1 == Fraction(5));
    assert(f2 == Fraction(5));
    cout << "ok\n\n";
    
    cout << "testing conversion constructor with f1 = \"3/5\";" << '\n';
    cout << "testing infix evaluation with f1 = \"3/5\";" << '\n';
    f1 = "3/5";
    cout << "f1 : " << f1 << '\n';
    assert(f1 == Fraction(3, 5));
    cout << "ok\n\n";
    
    cout << "testing normalization with f1 == \"6/10\";" << '\n';
    assert(f1 == "6/10");
    cout << "f1 : " << f1 << '\n';
    cout << "ok\n\n";
    
    cout << "computing sum = 1/3 + 1/3 + 1/3 + 1/3 + 1/3 + 1/3 + 1/3 + 1/3 + 1/3 + 1/3\n";
    Fraction sum;     // sum = 0
    for (int k = 0; k < 10; ++k)
    {
        sum = sum + oneThird;
        cout << "sum: " << sum << '\n';
    }
    cout << "sum: " << sum << '\n';
    assert(sum == Fraction(10, 3));
    cout << "ok\n\n";
    
    cout << "testing operators * and / with sum = sum * oneThird / oneThird;" << '\n';
    sum = sum * oneThird / oneThird;
    cout << "sum: " << sum << '\n';
    assert(sum == Fraction(10, 3));
    cout << "ok\n\n";
    
    cout << "computing f3 =  1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/8 + 1/9 + 1/10;" << '\n';
    Fraction f3;
    f3 = "1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/8 + 1/9 + 1/10";
    cout << "f3: " << f3 << '\n';
    assert(Fraction("1/2 + 1/4 + 1/6 + 1/8 + 1/10")
           +
           Fraction("1/3 + 1/5 + 1/7 + 1/9")
           == f3);
    cout << "ok\n\n";
    
    cout << "computing (1/2) + (-1/3) + (1/4) + (-1/5) + (1/6) + (-1/7) + (1/8) + (-1/9)" << "\n\n";
    cout << setw(5) << 'k' << setw(15) << 'd' << " : " << 's' << '\n';
    cout << setw(5) << '-' << setw(15) << '-' << " : " << '-' << '\n';
    Fraction s;
    double d = 0.0;
    int one = 1;
    for (int k = 2; k < 10; ++k)
    {
        
        d = d + static_cast<double>(one) / k;
        s = s + Fraction(one, k);
        one = -one;
        cout << setw(5) << k << setw(15) << d << " : " << s << '\n';
    }
    assert(Fraction("1/2 + 1/4 + 1/6 + 1/8")
           -
           Fraction("1/3 + 1/5 + 1/7 + 1/9")
           == s);
    cout << "ok\n\n";
    
    cout << "testing operator>> with cin >> f;" << '\n';
    Fraction f;
    cin >> f;
    cout << "f : " << f << '\n';
    f = f + Fraction(6, 10) - f;
    
    cout << "\ntesting operator +=" << '\n';
    f += 1;
    cout << "f : " << f << '\n';
    assert( f == Fraction(8, 5) );
    cout << "f : " << f << '\n';
    cout << "ok\n\n";
    
    cout << "testing operator -=" << '\n';
    f -= Fraction(1);
    cout << "f : " << f << '\n';
    assert(f == Fraction(3, 5));
    cout << "ok\n\n";
    
    cout << "testing operator *=" << '\n';
    f *= Fraction(2, 7);
    cout << "f : " << f << '\n';
    assert(f == Fraction(6, 35));
    cout << "ok\n\n";
    
    cout << "testing operator /=" << '\n';
    f /= Fraction(3, 5);
    cout << "f : " << f << '\n';
    assert(f == Fraction(2, 7));
    cout << "ok\n\n";
    
    cout << "testing Fraction + int" << '\n';
    f = f + 1;
    cout << "f : " << f << '\n';
    assert(f == Fraction(9, 7));
    cout << "ok\n\n";
    
    cout << "testing Fraction - int" << '\n';
    f = f - 1;
    cout << "f : " << f << '\n';
    assert(f == Fraction(2, 7));
    cout << "ok\n\n";
    
    
    cout << "testing int + Fraction" << '\n';
    f = 1 + f;
    cout << "f : " << f << '\n';
    assert(f == Fraction(9, 7));
    cout << "ok\n\n";
    
    cout << "testing int - Fraction" << '\n';
    f = 1 - f;
    cout << "f : " << f << '\n';
    assert(f == Fraction(-2, 7));
    cout << "ok\n\n";
    
    cout << "Test over successfully!" << endl;
    
    return 0;
}