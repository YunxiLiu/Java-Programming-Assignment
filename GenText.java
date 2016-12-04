/**
 * main class to process I/O
 * @author Yunxi Liu
 * USC loginid: yunxiliu
 * CS 455 PA4
 * Fall 2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GenText {
	private static final int ARGS_LENGTH = 4;//the number of arguments to read in the command line
	private static final int LINE_LIMIT = 80;//the maximum number of characters in a line in the output file
	
	public static void main(String[] args) {
		int prefixLength;//the length of prefix declared in the command line
		int numWords;//the number of words to generate declared in the command line
		int isDebug = 0;//if it should be in debugging mode
		int index = 0;//increment operator
		
		try{
			//check if user missed arguments
			if(args.length < ARGS_LENGTH){
				printError("You are missing command-line arguments");
				return;
			}
			//missing arguments in debugging mode
			if(args[0].equals("-d")){
				if(args.length < ARGS_LENGTH + 1){
					printError("You are missing command-line arguments");
					return;
				}
				isDebug = 1;
			}
			
			//check the length of prefix
			prefixLength = Integer.parseInt(args[isDebug + index++]);
			if(prefixLength < 1){
				printError("The prefix length should not be less than 1.");
				return;
			}
			
			//check the number of words to generate
			numWords = Integer.parseInt(args[isDebug + index++]);
			if(numWords < 0){
				printError("The number of words should not be less than 0.");
				return;
			}
			
			RandomTextGenerator randomTextGenerator = new RandomTextGenerator(
					readFile(new Scanner(new File(args[isDebug + index++])), prefixLength), numWords, isDebug);
			writeFile(randomTextGenerator.nextGenerate(), args[isDebug + index]);
		}catch(NumberFormatException ex){
			printError("The prefix length and the number of words must be integer");
			return;
		 }
		 catch(FileNotFoundException ex){
			 printError("File not found.");
			 return;
		 }
		 catch(LargePrefixException ex){
			  System.out.println("The length of prefix should be less than the number of words in sourceFile");
			  return;
		 }
		 catch(IOException ex){
			 System.out.println("File reading/writing error.");
			 return;
		 }
	}
	
	/**
	 * print error message with respect to input arguments
	 * @param message error message
	 */
	private static void printError(String message){
		System.out.println(message);
		System.out.println("Your input format should be: java GenText [-d] prefixLength numWords sourceFile outFile");
	}
	
	/**
	 * 	open file and store information about prefixes and their successors
	 * @param fileScanner to scan the source file
	 * @param prefixLength length of prefix
	 * @return a map that contains information about prefixes and their successors
	 * @throws LargePrefixException if the prefix length is larger than total number of words in source text
	 */
	private static Map<Prefix, ArrayList<String>> readFile(Scanner fileScanner, int prefixLength) throws LargePrefixException{
		Map<Prefix, ArrayList<String>> map = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
		while(fileScanner.hasNext()){
			list.add(fileScanner.next());
		}
		
		//throw exception if the length of prefix is larger than the total number of words in source text
		if(prefixLength >= list.size()){
			throw new LargePrefixException();
		}
		for(int i = 0; i <= list.size() - prefixLength; i++){
			ArrayList<String> tempPrefixList = new ArrayList<>();
			
			for(int j = 0; j < prefixLength; j++){
				tempPrefixList.add(list.get(i + j));
			}
			Prefix tempPrefix = new Prefix(tempPrefixList);//iteratively find every prefix
			if(!map.containsKey(tempPrefix)){//the prefix does not exist in the map
				ArrayList<String> tempSuffix = new ArrayList<>();
				if(i + prefixLength < list.size()){//the prefix is not the end of source text
					tempSuffix.add(list.get(i + prefixLength));//the word after the prefix
				}
				map.put(tempPrefix, tempSuffix);
			}
			else{//the prefix exists in the map
				if(i + prefixLength < list.size()){
					map.get(tempPrefix).add(list.get(i + prefixLength));//add one more word to the corresponding words list of the prefix
				}
			}
		}
		return map;
	}
	
	/**
	 * to write words generated to a file
	 * @param words all words generated
	 * @param filename file to write to
	 * @throws IOException throws exception in writing file
	 */
	private static void writeFile(ArrayList<String> words, String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename);
		int charSoFar = 0;
		for(String word : words){
			charSoFar += word.length();
			if(charSoFar < LINE_LIMIT){//the word can be written in the current line
				writer.print(word + " ");
				charSoFar++;
			}
			else if(charSoFar == LINE_LIMIT){//the word can be written in the current line and will exactly reach the end of the line
				writer.println(word);//jump to the next line
				charSoFar = 0;
			}
			else{//the word can not be written in the current line because of the character limit
				writer.println();
				writer.print(word + " ");//write to the next line
				charSoFar = word.length() + 1;
			}
		}
	    writer.close();
	}
}
