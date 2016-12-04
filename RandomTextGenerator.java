/**
 * the class to generate random text
 * @author Yunxi Liu
 * USC loginid: yunxiliu
 * CS 455 PA4
 * Fall 2016
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class RandomTextGenerator {
	private Map<Prefix, ArrayList<String>> map;//contains prefixes and their successors
	private int numWords;//number of words to generate
	private Random rand;//random object to generate random words
	private boolean isDebug;//if it is in debugging mode
	private ArrayList<String> result;//words generated
	
	public RandomTextGenerator(Map<Prefix, ArrayList<String>> mapWords, int w, int debug){
		map = mapWords;
		numWords = w;
		result = new ArrayList<String>();
		rand = new Random();
		if(debug == 1){
			isDebug = true;
			rand = new Random(1);//set seed to 1 to make output predictable in debugging mode
		}
		else{
			isDebug = false;
			rand = new Random();
		}
	}
	
	/**
	 * randomly generate the initial prefix
	 * @return the initial prefix
	 */
	public Prefix initGenerate(ArrayList<Prefix> list){
		
		Prefix prefix = list.get(rand.nextInt(list.size()));
		if(isDebug){
			System.out.println("DEBUG: chose a new initial prefix: " + prefix);
		}
		return prefix;
	}
	
	/**
	 * generate the next word based on a prefix
	 * @return a list of words generated
	 */
	public ArrayList<String> nextGenerate(){
		ArrayList<Prefix> candidates = new ArrayList<>();//the list of all prefixes 
		for(Prefix prefix : map.keySet()){
			candidates.add(prefix);
		}
		Prefix currPrefix = initGenerate(candidates);//randomly generate the initial prefix
			
		//iteratively generate each word
		for(int i = 0; i < numWords; i++){
			if(isDebug){
				System.out.println("DEBUG: prefix: " + currPrefix);
			}
			ArrayList<String> successors = map.get(currPrefix);//get all successors of the current prefix
			
			//occurrence of the current prefix is at the exact end of the sample
			if(successors.size() == 0){
				if(isDebug){
					System.out.println("DEBUG: successors: <END OF FILE>");
				}
				
				//remove the prefix at the exact end of the sample from the initial prefixes list
				if(candidates.size() == map.size()){
					candidates.remove(currPrefix);
				}
				currPrefix = initGenerate(candidates);
				i--;
				continue;
			}
			
			if(isDebug){
				System.out.print("DEBUG: successors: ");
				for(String word : successors){
					System.out.print(word + " ");
				}
				System.out.println();
			}
			
			//randomly generate a word from the current successors
			String suffix = currPrefix.generate(successors, rand.nextInt(successors.size()));
			if(isDebug){
				System.out.println("DEBUG: word generated: " + suffix);
			}
			result.add(suffix);//add the new generated word to the result
			currPrefix = currPrefix.shiftIn(suffix);//update the current prefix
		}
	
		return result;
	}
}
