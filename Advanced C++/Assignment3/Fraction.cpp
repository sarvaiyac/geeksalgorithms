//
//  Fraction.cpp
//  Assignment3
//
//  Created by Chintan Sarvaiya on 2014-07-06.
//  Copyright (c) 2014 ___CHINTANSARVAIYA___. All rights reserved.
//

#include "Fraction.h"
#include <stack>

void Fraction::normalize(){
    
    long gcdValue = gcd(numerator, denominator);
    numerator = numerator/gcdValue;
    denominator = denominator/gcdValue;
    
}
Fraction::Fraction(long num, long deno){
    numerator = num;
    denominator = deno;
}
Fraction::Fraction(const string& infix){
    queue<string> infixQ = Tokenize(infix);
    Fraction f = evaluateInfix(infixQ);
    this->setNumerator(f.numerator);
    this->setDenominator(f.denominator);
}
Fraction::Fraction(const char* str){
    queue<string> infixQ = Tokenize(str);
    Fraction f = evaluateInfix(infixQ);
    this->setNumerator(f.numerator);
    this->setDenominator(f.denominator);
}

// tokenize specific infix expressions into queue of string tokens
queue<string> Fraction::Tokenize(const string& infixExpressions){
    queue<string> queueToken;
    
    for (int i = 0; i<infixExpressions.length(); i++) {
        char token = infixExpressions.at(i);
        
        if (token == ' ') continue; // if token is white space then ignore
        
        else if ( token == '+' || token == '-' || token == '*' || token == '/' || token == '(' || token == ')')  queueToken.push(string(1,token));
        
        else if (token >= '0' && token <= '9'){ // if token starts with number then check if token string has another number or not. it checks number with more than one digit
            
            string strNumbers;
            
            while (token >= '0' && token <= '9') {
                strNumbers.append(string(1,token));
                i++;
                
                if (i == infixExpressions.length()) break;
                
                token = infixExpressions.at(i);
//                cout<<token<<endl;
            }
            i--;

            queueToken.push(strNumbers);
        }
    }
    
    
    return queueToken;
}

// method which returns true if input string is number
bool checkIfDigit(string checkString){
    bool isDigit = false;
    for (int i = 0; i<checkString.length(); i++) {
        char token = checkString.at(i);
        while (token >= '0' && token <= '9') {
            isDigit = true;
            i++;
            if (i != checkString.length()) {
                token = checkString.at(i);
            }else break;
        }
    }
    return isDigit;
}

// compure and return the precedence of given input 0-( || 1-+,- || 2-*,/
int Fraction::precedence(string op){
    if (op == "(") return 0;
    else if (op == "+" || op == "-") return 1;
    else if (op == "*" || op == "/") return 2;
    
    return 404;
}

//computer and return the greatest common diviser of two parameters
long Fraction::gcd(long firstDigit, long secondDigit){
    long startDigit = firstDigit > secondDigit ? firstDigit : secondDigit;
    
    for (int i = (int)startDigit; i>= 1; i--)
        if ((firstDigit%i ==0) && (secondDigit%i==0)) return i;
    
        return 1;
}

// evaluate and return fraction value of an integer expressions represented by infix queue
Fraction Fraction::evaluateInfix(queue<string> &infixQueue){

    stack<Fraction> stackOperand;
    stack<string> stackOperator;
    
    queue<string> tempStringQueue = infixQueue;
    
    while (!tempStringQueue.empty()) {
        // create token of first element from input queue
        string token = tempStringQueue.front();
        tempStringQueue.pop();

        
        if (isdigit(token[0])) { // if first eelement is digit then create one fraction and add into operant stack
            Fraction fr(atol(token.c_str()));
            stackOperand.push(fr);
        }else if (token == "+" || token == "-" || token == "*" || token == "/") { // if token is special character then check weather operator stack empty or not and add into operator stack based on precedence comparision between first element of stack operator and token
            
            while (!stackOperator.empty() && (precedence(stackOperator.top()) >= precedence(token) )) {
                Fraction second = stackOperand.top();
                stackOperand.pop();
                Fraction first = stackOperand.top();
                stackOperand.pop();
                
                string op = stackOperator.top();
                stackOperator.pop();
                
                Fraction result;
                
                if (op == "+") result = first + second;
                if (op == "-") result = first - second;
                if (op == "*") result = first * second;
                if (op == "/") result = first / second;

                stackOperand.push(result);
            }
            stackOperator.push(token);
        }else if (token == "("){
            stackOperator.push(token);
        }else if (token == ")"){
            while (! stackOperator.empty() && (stackOperator.top() != "(")) {
                Fraction second = stackOperand.top();
                stackOperand.pop();
                Fraction first = stackOperand.top();
                stackOperand.pop();
                
                string op = stackOperator.top();
                stackOperator.pop();
                
                Fraction result;
                
                if (op == "+") result = first + second;
                if (op == "-") result = first - second;
                if (op == "*") result = first * second;
                if (op == "/") result = first / second;
                
//                cout<<first.numerator<<op<<second.numerator<<endl;
                stackOperand.push(result);
            }
            stackOperator.pop();
        }
    }
    
    while (! stackOperator.empty()) {
        Fraction second = stackOperand.top();
        stackOperand.pop();
        Fraction first = stackOperand.top();
        stackOperand.pop();
        
        string op = stackOperator.top();
        stackOperator.pop();
        
        Fraction result;
        
        if (op == "+") result = first + second;
        if (op == "-") result = first - second;
        if (op == "*") result = first * second;
        if (op == "/") result = first / second;
        
//        cout<<first.numerator<<op<<second.numerator<<endl;
        stackOperand.push(result);
    }
    
    
    Fraction finalResult = stackOperand.top();
    stackOperand.pop();
    return finalResult;
}


// setter to set numerator
void Fraction::setNumerator(long num){
    numerator = num;
}
// getter to get numerator
long Fraction::getNumerator(){
    return numerator;
}

// setter to denominator. if input is zero then it would throw an error

void Fraction::setDenominator(long den){ // if denominator is 0 it should throw an exception  "division by zero"
    if (den == 0)
        cout<<"invalid input. Division by zero";

    denominator = den;
}

//getter to get denominator
long Fraction::getDenominator(){
    return denominator;
}


// unary operator overloads

Fraction operator+(const Fraction& rhs){ // +f
    if (rhs.numerator < 0)  return Fraction( -rhs.numerator,rhs.denominator);
    
    return Fraction(rhs.numerator,rhs.denominator);
}
Fraction operator-(const Fraction& rhs){ // -f
    return Fraction(-rhs.numerator,rhs.denominator);
}

// binary operator overloads

Fraction operator+(const Fraction& lhs,const Fraction& rhs){ // A+B
    return Fraction((lhs.numerator * rhs.denominator) + (rhs.numerator * lhs.denominator),(lhs.denominator * rhs.denominator));
}
Fraction operator-(const Fraction& lhs,const Fraction& rhs){ // A-B
    return Fraction((lhs.numerator * rhs.denominator) - (rhs.numerator * lhs.denominator),(lhs.denominator * rhs.denominator));
}
Fraction operator*(const Fraction& lhs,const Fraction& rhs){ // A*B
    return Fraction((lhs.numerator * rhs.numerator),(lhs.denominator * rhs.denominator));
}
Fraction operator/(const Fraction& lhs,const Fraction& rhs){ // A/B
    return Fraction((lhs.numerator * rhs.denominator),(lhs.denominator * rhs.numerator));
}


Fraction& Fraction::operator+=(const Fraction& rhs){ // A+=B a = a+b
    *this = *this + rhs;
    return *this;
}

Fraction& Fraction::operator-=(const Fraction& rhs){ // A-=B
    *this = *this - rhs;
    return *this;
}
Fraction& Fraction::operator*=(const Fraction& rhs){ // A*=B
    *this = *this * rhs;
    return *this;
}
Fraction& Fraction::operator/=(const Fraction& rhs){ // A/=B
    *this = *this / rhs;
    return *this;
}




bool operator== (const Fraction& lhs,const Fraction& rhs){  // A==B
    return ((lhs.numerator * rhs.denominator) == (lhs.denominator * rhs.numerator));
}
bool operator!= (const Fraction& lhs,const Fraction& rhs){  // A!=B
    return (!(lhs == rhs));
}
bool operator< (const Fraction& lhs,const Fraction& rhs){   // A<B
    return ((lhs.numerator * rhs.denominator) < (lhs.denominator * rhs.numerator));
}
bool operator<= (const Fraction& lhs,const Fraction& rhs){  // A<=B
    return (lhs<rhs || lhs==rhs);
}
bool operator> (const Fraction& lhs,const Fraction& rhs){   // A>B
    return !((lhs == rhs) || (lhs <rhs));
}
bool operator>= (const Fraction& lhs,const Fraction& rhs){  // A>=B
    return !(lhs<rhs);
}



Fraction& Fraction::operator++(){     // ++f
    *this += 1;
    return *this;
}
Fraction Fraction::operator++(int){   //f++
    Fraction temp = *this;
    ++ (*this);
    return temp;
}
Fraction& Fraction::operator--(){     // ++f
    *this -= 1;
    return *this;
}
Fraction Fraction::operator--(int){   //f++
    Fraction temp = *this;
    -- (*this);
    return temp;
}


// operator () overload which assign value to string

string Fraction::operator()(){// string s = f() // assign value to string
    return to_string(numerator)+"/"+to_string(denominator);
}


// operator >> overload which takes expression as input and perform further operations

istream& operator>>(istream& in, Fraction& f){ //read infix operation evaluate and store result in f
    string inputString;
    cout<<"Input Expression : ";
    in>>inputString;
    f = Fraction(inputString);
    return in;
}

// operator << overload which used to print data as per requirement

ostream& operator<<(ostream& out, Fraction& f){
    f.normalize();
    if (f.numerator == 0 || f.denominator == 1)   out<<f.numerator;
    else{
        out<<f.numerator<<"/"<<f.denominator;
        if (f.numerator > f.denominator)
            out<<" = "<<f.numerator/f.denominator<<" + "<<f.numerator%f.denominator<<"/"<<f.denominator;
    }
    return out;
}







