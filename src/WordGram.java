import java.util.ArrayList;
import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{
	
	public int myHash;
	public String[] myWords;

	
	public WordGram(String[] words, int index, int size) { //creates wordgram
		myWords = new String[size];
		for(int start = 0;start<size;start++) {
			myWords[start] = words[start+index];
			}
		
		myHash = 17; 
	}
	
	@Override
	public int hashCode() { //creates hashcode
		for(int k=0;k<myWords.length;k++) { 
			String current = myWords[k];
			myHash = current.length() + current.length() + k + myWords[k].hashCode();
		}
		return myHash;
	}
	
	@Override
	public String toString() { //turns to string
		String stringReturn = "";
		for(int x=0;x<myWords.length;x++) {
			stringReturn = stringReturn + " " + myWords[x];
		}
		return stringReturn.substring(1);
	}
	
	
	@Override
	public boolean equals(Object other) { //are they equal?
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other;
		int thisHash = this.hashCode();
		int wgHash = wg.hashCode();
		if(thisHash==wgHash) {
			return true;
		}
		
		for(int x=0;x<myWords.length;x++) {
			if(!myWords[x].equals(wg.myWords[x])) {
				return false;
			}
		}
		
			return true;
	}
	
	
	
	@Override
	public int compareTo(WordGram wg) { //bigger or smaller?
		int firstSize = this.myWords.length;
		int secondSize = wg.myWords.length; 
		if(firstSize==0) {
			return -1;
		}
		int lim = Math.min(firstSize,secondSize);
		int k = 0;
		String current1 = this.myWords[k];
		String current2 = wg.myWords[k];
		while(k<lim) { 
			int answer = current1.compareTo(current2);
			if(answer<0) {
				return -1;
			}
			if(answer>0) {
				return 1;
			}
			k++;
			}
		if(firstSize<secondSize) {
			return -1;
		}
		if(secondSize<firstSize) {
			return 1;
		}
		return 0;
		
		}
		
	

	public int length() { //length of myWords
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) { //shift wordgram
		String[] newArray = new String[this.length()];
		for(int i=0;i<this.length()-1;i++) {
			newArray[i] = this.myWords[i+1];
		}
		newArray[this.length()-1] = last;
//		WordGram obj;
	
		
		return new WordGram(newArray,0,newArray.length);
	}
}
