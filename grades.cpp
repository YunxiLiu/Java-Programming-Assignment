// Name:Yunxi Liu
// Loginid:yunxiliu
// CSCI 455 PA5
// Fall 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <string>
#include <cstdlib>

void doHelp();
void doInvalid();

int main(int argc, char * argv[]) {

    // gets the hash table size from the command line
    int hashSize = Table::HASH_SIZE;

    Table * grades;  // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.

    if (argc > 1) {
        hashSize = atoi(argv[1]);  // atoi converts c-string to int

        if (hashSize < 1) {
            cout << "Command line argument (hashSize) must be a positive number" << endl;
            return 1;
        }

        grades = new Table(hashSize);

    }
    else {   // no command line args given -- use default table size
        grades = new Table();
    }


    grades->hashStats(cout);


    // add more code here
    // Reminder: use -> when calling Table methods, since grades is type Table*
    const int MAX_LINE_CHAR = 100; //the longest string to read from the prompt
    const char *delim = " "; //the delimiter to split the string
    char commandLine[MAX_LINE_CHAR]; //the command line
    char *pointer; //temporary pointer to do the split with strtok() function
    string command = ""; //the user command
    string name = ""; //the name of an student
    string score = ""; //the score of an student
  
    while(true){
        cout << "cmd>";
        cin.getline(commandLine, MAX_LINE_CHAR);
        pointer = strtok(commandLine,delim); //split the command line
        if(pointer){
            command = pointer; //assign the first argument
            pointer = strtok(NULL,delim);
        }
        if(pointer){
            name = pointer; //assign the second argument
            pointer = strtok(NULL,delim);
        }
        if(pointer){
            score = pointer; // assign the third argument
        }
    
        if(command == "insert"){
            const char * scoreChar = score.data();
            if(!grades->insert(name, atoi(scoreChar))){
                cout << "FAIL: This name is already present." << endl;
            }
            delete scoreChar;
        }

        else if(command =="change"){
            const char * scoreChar = score.data(); //the new score
            int * prevScore = new int; // the old score
            prevScore = grades->lookup(name);
            if(prevScore == NULL){
                cout << "FAIL: This name is not in the table." << endl;
            }
            else{
                *prevScore = atoi(scoreChar);
            }
        }

        else if(command == "lookup"){
            int * studentScore = new int;
            studentScore = grades->lookup(name);
            if(studentScore == NULL){
                cout << "FAIL: The student is not in the table." << endl;
            }
            else{
                cout << "The student's score is " << *studentScore << endl;
            }
        }

        else if(command == "remove"){
            if(!grades->remove(name)){
                cout << "FAIL: The student is not in the table." << endl;
            }
        }

        else if(command == "print"){
            grades->printAll();
        }

        else if(command == "size"){
            cout << "The number of entries in the table is " << grades->numEntries() << "." << endl;
        }

        else if(command == "stats"){
            grades->hashStats(cout);
        }

        else if(command == "help"){
            doHelp();
        }

        else if(command == "quit"){
            exit(0);
        }

        else{
            doInvalid();
        }
    }
    return 0;
}

//show the command summary
void doHelp(){
    cout << "Here are the commands for the program (in the following a name will always be a single word):" << endl;
    cout << "insert name score" << endl;
    cout << "change name newscore" << endl;
    cout << "remove name" << endl;
    cout << "lookup name" << endl;
    cout << "print" << endl;
    cout << "size" << endl;
    cout << "stats" << endl;
    cout << "help" << endl;
    cout << "quit" << endl;
}  

//the input command is not valid
void doInvalid(){
    cout << "ERROR: invalid command" << endl;
    doHelp();
}
