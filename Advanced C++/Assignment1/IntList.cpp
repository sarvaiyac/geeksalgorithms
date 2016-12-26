#include"IntList.h"
#include <iostream>
using namespace std;

//default constructor

IntList::IntList(){
    size = 0;
	capacity = 1;
	list = new int[1];
}

IntList::~IntList(){	//destructor
//	delete[] list;
}

//copy constructor

IntList::IntList(const IntList &obj){
    size = obj.size;
	capacity = obj.capacity;
	list = obj.list;
}

//copy assignment operator

IntList& IntList::operator = (const IntList& obj){
	size = obj.size;
	capacity = obj.capacity;
	list = obj.list;
	return *this;
}

// public methods

//if list empty / not

bool IntList::isListEmpty(){
	if (size == 0)  return true;
	else  return false;
}

// if line empty / not

bool IntList::isLineExists(int line){
	bool isExists = false;
	for (int i = 0; i < size; i++)
	{
		if (line == list[i])
		{
			isExists = true;
			break;
		}
	}
	if (isExists) return true;
	else return false;
}

//append line at last

void IntList::append(int line){
	for (int i = 0; i < size; i++)
		if (line==list[i])    return;
	
	if (size == capacity)
		resizeList();


	list[size] = line;
	size += 1;
}

//get element
int IntList::getElement(int index){
	return list[index];
}

//set element
void IntList::setElement(int index, int line){
	if (size == capacity)
		resizeList();

	int* temp = new int[size];
	temp = list;

	size = size + 1;
	list = new int[size];

	for (int i = 0; i<index; i++) list[i] = temp[i];
    
	list[index] = line;

	for (int i = index + 1; i<size + 1; i++)    list[i] = temp[index++];
}

//get list size
int IntList::getSize(){
	return size;
}

//get list capacity
int IntList::getCapacity(){
	return capacity;
}

//get read only list

const int* IntList::getList(){
	return list;
}

// private methods

// resize list doubles the size of list

void IntList::resizeList(){
    
    int* temp = new int[capacity];
	temp = list; // copy list into temp
    
	capacity = capacity * 2;
    
	list = new int[capacity];	//restore temp to original list
	list = temp;
}


