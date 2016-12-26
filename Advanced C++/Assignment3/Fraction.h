//
//  Fraction.h
//  Assignment3
//
//  Created by Chintan Sarvaiya on 2014-07-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#ifndef __Assignment3__Fraction__
#define __Assignment3__Fraction__

#include <iostream>
#include <queue>

using namespace std;

class Fraction {
private:
    long numerator;
    long denominator;
    
    void normalize();// normalize this fraction call at end of any operation that modifies fraction.
public:

    Fraction(long num=0,long den = 1);
    Fraction(const string&);
    Fraction(const char *);
    
    static queue<string> Tokenize(const string& infixExpressions); // tokenize specific infix expressions into queue of string tokens
    static Fraction evaluateInfix(queue<string> &infixQueue); // evaluate and return fraction value of an integer expressions represented by infix queue
    
    void setNumerator(long);
    long getNumerator();
    
    void setDenominator(long); // if denominator is 0 it should throw an exception  "division by zero"
    long getDenominator();

    static int precedence(string); // compure and return the precedence of given input 0-( || 1-+,- || 2-*,/
    static long gcd(long, long);//computer and return the greatest common diviser of two parameters
    
    
    // unary operator overloads
    
    friend Fraction operator+(const Fraction& rhs); // +f
    friend Fraction operator-(const Fraction& rhs); // -f
    
    // binary operator overloads

    friend Fraction operator+(const Fraction& lhs,const Fraction& rhs); // A+B
    friend Fraction operator-(const Fraction& lhs,const Fraction& rhs); // A-B
    friend Fraction operator*(const Fraction& lhs,const Fraction& rhs); // A*B
    friend Fraction operator/(const Fraction& lhs,const Fraction& rhs); // A/B

    
    Fraction& operator+=(const Fraction& rhs); // A+=B
    Fraction& operator-=(const Fraction& rhs); // A-=B
    Fraction& operator*=(const Fraction& rhs); // A*=B
    Fraction& operator/=(const Fraction& rhs); // A/=B
    
    friend bool operator== (const Fraction& lhs,const Fraction& rhs);  // A==B
    friend bool operator!= (const Fraction& lhs,const Fraction& rhs);  // A!=B
    friend bool operator< (const Fraction& lhs,const Fraction& rhs);   // A<B
    friend bool operator<= (const Fraction& lhs,const Fraction& rhs);  // A<=B
    friend bool operator> (const Fraction& lhs,const Fraction& rhs);   // A>B
    friend bool operator>= (const Fraction& lhs,const Fraction& rhs);  // A>=B
    
    
    Fraction& operator++();     // ++f
    Fraction operator++(int);   //f++
    Fraction& operator--();     // ++f
    Fraction operator--(int);   //f++
    
    string operator()();// string s = f() // assign value to string
    friend istream& operator>>(istream&,Fraction& f); //read infix operation evaluate and store result in f
    friend ostream& operator<<(ostream&, Fraction& f);
};



















#endif /* defined(__Assignment3__Fraction__) */
