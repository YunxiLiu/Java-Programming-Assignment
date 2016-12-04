/**
 * the concept of prefix
 * @author Yunxi Liu
 * USC loginid: yunxiliu
 * CS 455 PA4
 * Fall 2016
 */

import java.util.ArrayList;

public class Prefix {
	private static final int ALPHA = 31;//a constant to generate hash code
	private ArrayList<String> words;//words consisting the prefix
	
	public Prefix(ArrayList<String> list){
		words = list;
	}
	
	@Override
	public int hashCode(){
		int code = 0;
		for(String str : words){
			code = ALPHA * code + str.hashCode();
		}
		return code;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null){
            return false;
        }
        if(!(other instanceof Prefix)){
            return false;
        }
        
        Prefix that = (Prefix) other;
		if(this.words.size() != that.words.size()){
			return false;
		}
		for(int i = 0; i < this.words.size(); i++){
			if(!this.words.get(i).equals(that.words.get(i))){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		String result = "";
		for(String word : words){
			result = result + word + " ";
		}
		return result;
	}
	
	/**
	 * generate a word from the successors
	 * @param successors
	 * @param index the generated random index in the successors list 
	 * @return the generated word
	 */
	public String generate(ArrayList<String> successors, int index){
		return successors.get(index);
	}
	
	/**
	 * shift the prefix to the right by 1
	 * @param newWord the new word generated
	 * @return the new shifted prefix
	 */
	public Prefix shiftIn(String newWord){
		ArrayList<String> newWords = new ArrayList<>();
		//add words to the new prefix but the first word
		for(int i = 1; i < words.size(); i++){
			newWords.add(words.get(i));
		}
		newWords.add(newWord);//add the new word generated to the prefix
		return new Prefix(newWords);
	}
}
