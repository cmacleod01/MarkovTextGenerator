import java.util.ArrayList;

public class WordGram implements Comparable<WordGram>{
	
	private int myHash;
	private String[] myWords;

	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		for(int start = index;start<size;start++) {
			System.out.println("test");
			myWords[start] = words[start];
			}
		System.out.println(myWords);
		myHash = 17; 
	}
	
	@Override
	public int hashCode() { 
		for(int k=0;k<myWords.length;k++) { 
			String current = myWords[k];
			char firstChar = current.charAt(0);
			int firstValue = (int) firstChar;
			char secondChar = current.charAt(-1);
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
		int firstSize = myWords.length;
		int secondSize = wg.myWords.length; //why this syntax???
		if(firstSize>secondSize) {
			return 2;
		}
		if(secondSize>firstSize) {
			return -1;
		}
		int lim = Math.min(firstSize,secondSize);
		int k = 0;
//		char c1[] = myWords.toCharArray();
//		while(k<lim) {
//			char c1 = myWords[k];
//			
//			
//		}
//		
//		for(String current : this.myWords) {
//			
//				return 2;
//			}
//			if(current < wg) {
//				return -1;	
//		}
		return lim;
		
		}
		
	

	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		WordGram obj;
		return new WordGram(null,0,0);
	}
}
