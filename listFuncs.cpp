// Name:Yunxi Liu
// Loginid:yunxiliu
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
	key = theKey;
	value = theValue;
	next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
	key = theKey;
	value = theValue;
	next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

//look up an element in a bucket
//return the reference to the score of the student if any, or NULL if not
int * listLookUp(ListType list, const string &target){
	while(list != NULL){
		if(list->key == target){
			return &list->value;
		}
		list = list->next;
	}
	return NULL;
}

//insert an element in a bucket
//return true if insert successfully, false if insert unsuccessfully
bool listInsert(ListType &list, const string &name, int score){
	ListType p = list;
	if(p == NULL){
		list = new Node(name, score);
		return true;
	}
	while(p->next != NULL){
		if(p->key == name){
			return false;
		}
		p = p->next;
	}
	if(p->key == name){ //check the last element in the bucket
		return false;
	}
	p->next = new Node(name, score);
	return true;
	
}

//remove an element in a bucket
//return true if remove successfully, false if remove unsuccessfully
bool listRemove(ListType &list, string target){
	
	if(list == NULL){
		return false;
	}
	if(list->next == NULL){ //if there is only one element in the bucket
		if(list->key == target){
			delete list;
			list = NULL;
			return true;
		}
		else{
			return false;
		}
	}

	ListType p = list;
	if(p->key == target){ //check the first element in the bucket
		list = list->next;
		delete p;
		return true;
	}
	while(p->next->next != NULL){
		if(p->next->key == target){
			ListType deadGuy = p->next;
			p->next = p->next->next;
			delete deadGuy;
			return true;
		}
		p = p->next;
	}
	if(p->next->key == target){ //check the last element in the bucket
		delete p->next;
		p->next = NULL;
		return true;
	}

	return false;
}

//print all elements in a bucket
void listPrint(ListType list){
	while(list != NULL){
		cout << list->key << " " << list->value << endl;
		list = list->next;
	}
}

