// Name:Yunxi Liu
// Loginid:yunxiliu
// CSCI 455 PA5
// Fall 2016


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;

struct Node {
	string key;
	int value;

	Node *next;

	Node(const string &theKey, int theValue);

	Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.
int * listLookUp(ListType list, const string &target); //look up an element in a bucket; 
bool listInsert(ListType & list, const string &name, int score); //insert an element in a bucket
bool listRemove(ListType &list, string target); //remove an element in a bucket
void listPrint(ListType list); //print all elements in a bucket

// keep the following line at the end of the file
#endif
