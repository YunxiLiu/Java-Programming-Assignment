// Name:Yunxi Liu
// Loginid:yunxiliu
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

//initialize table object with the default hash size
Table::Table() {
	hashSize = HASH_SIZE;
	entryNum = 0;
	nonemptyBucket = 0;
	longestChain = 0;
	data = new ListType[hashSize];
	chainLen = new int[hashSize];
	for(int i = 0; i < hashSize; i++){
		data[i] = NULL;
		chainLen[i] = 0;
	}
}

//initialize table object with the input hash size
Table::Table(unsigned int hSize) {
	hashSize = hSize;
	entryNum = 0;
	nonemptyBucket = 0;
	longestChain = 0;
	data = new ListType[hashSize];
	chainLen = new int[hashSize];
	for(int i = 0; i < hashSize; i++){
		data[i] = NULL;
		chainLen[i] = 0;
	}
}

//lookup up an element in the table
int * Table::lookup(const string &key) {
	return listLookUp(data[hashCode(key)], key);
}

//remove an element in the table
bool Table::remove(const string &key) {
	int hash = hashCode(key);
	if(listRemove(data[hash], key)){
		entryNum--;
		chainLen[hash]--;
		nonemptyBucket = chainLen[hash] == 0 ? nonemptyBucket - 1 : nonemptyBucket;//check if an bucket becomes empty
		calcLongestChain();//calculate the longest chain in the table
		return true;
	}
	return false;
}

//insert an element in the table
bool Table::insert(const string &key, int value) {
	int hash = hashCode(key);
	if(listInsert(data[hash], key, value)){
		entryNum++;
		chainLen[hash]++;
		nonemptyBucket = chainLen[hash] == 1 ? nonemptyBucket + 1 : nonemptyBucket;//check if an bucket becomes non-empty
		longestChain = longestChain < chainLen[hash] ? chainLen[hash] : longestChain;//check if the length of an bucket exceeds the longest length
		return true;
	}
	return false;
}

//return the total number of elements in the table
int Table::numEntries() const {
	return entryNum;
}

//print all elements in the table
void Table::printAll() const {
	for(int i = 0; i < hashSize; i++){
		listPrint(data[i]);
	}
}

//show the statistical data of the table
void Table::hashStats(ostream &out) const {
    out << "number of buckets: " << hashSize << endl;
    out << "number of entries: " << entryNum << endl;
    out << "number of non-empty buckets: " << nonemptyBucket << endl;
    out << "longestChain: " << longestChain << endl;
}


// add definitions for your private methods here

//calculate the longest chain in the table
void Table::calcLongestChain(){
	longestChain = 0;
	for(int i = 0; i < hashSize; i++){
		longestChain = longestChain < chainLen[i] ? chainLen[i] : longestChain;
	}
}
