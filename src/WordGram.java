import java.util.ArrayList;
import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{
	
	private int myHash;
	private String[] myWords;

	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		for(int start = 0;start<size;start++) {
			myWords[start] = words[start+index];
			}
		
		myHash = 17; 
	}
	
	@Override
	public int hashCode() { 
		for(int k=0;k<myWords.length;k++) { 
			String current = myWords[k];
			char firstChar = current.charAt(0);
			int firstValue = (int) firstChar;
			char secondChar = current.charAt(1);
			int secondValue = (int) secondChar;
			myHash = (current.hashCode() + current.length() / firstValue * secondValue) + k;
		}
	
		return myHash;
	}
	
	@Override
	public String toString() {
		String stringReturn = "";
		for(int x=0;x<myWords.length;x++) {
			stringReturn = stringReturn + " " + myWords[x];
		}
		return stringReturn;
	}
	
	
	@Override
	public boolean equals(Object other) {
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
			if(myWords[x]!=wg.myWords[x]) {
				return false;
			}
		}
		
			return true;
	}
	
	
	
	@Override
	public int compareTo(WordGram wg) {
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
		
	

	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		WordGram obj;
		return new WordGram(null,0,0);
	}
}
