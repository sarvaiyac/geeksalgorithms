#ifndef INTLIST_H
#define INTLIST_H

class IntList{
public:
	int* list;
	int size, capacity;
	int value;
	IntList* next;


    // constructors
    
	IntList();	//default constructor
	IntList(const IntList &obj);	//copy constructor
	~IntList();	//destructor
	IntList& operator = (const IntList& obj);	//copy assignment operator

	//public methods

	bool isListEmpty();	//check if list empty or not
	bool isLineExists(int line);	//check if line exist or not
	void append(int line);	//append line number at last
	int getElement(int index);	//get element of specific index
	void setElement(int index, int line);	//set element at speficfic index
	int getSize();	//get list size
	int getCapacity();	//get list capacity
	const int* getList();	//get read only list

private:
	void resizeList();	//resize list

};

#endif
