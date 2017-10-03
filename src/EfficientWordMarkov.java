import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeMap;

public class EfficientWordMarkov extends WordMarkovModel {
	
	private TreeMap<WordGram,ArrayList<String>> newMap = new TreeMap<WordGram, ArrayList<String>>();; //instance variables
	private String myText;
	private String[] myWords;

	public EfficientWordMarkov(int order) { //constructor
		super(order);
		myOrder = order;
//		newMap = new TreeMap<WordGram, ArrayList<String>>();
//		setTraining(myText);
		myRandom = new Random(RANDOM_SEED);
	}
		
	@Override
	public void setTraining(String text){ //creates map
		myText = text;
		myWords = text.split("\\s+");
		newMap.clear();
		int myOrder1 = myOrder;
		for(int i=0;i<(myWords.length-myOrder1);i++) {
			WordGram currentWG = new WordGram(myWords,i,myOrder);
			if(!newMap.containsKey(currentWG)) {
				ArrayList<String> valueList = new ArrayList<String>();
				newMap.put(currentWG, valueList);
			}
			String after = myWords[i+myOrder1];
			newMap.get(currentWG).add(after);
			}
//		String lastBit = myWords[myWords.length-myOrder, myWords.length];
//		if(!newMap.containsKey(lastBit)) {
//			ArrayList<String> valueList = new ArrayList<String>();
//			newMap.put(lastBit, valueList);
//		}
//		newMap.get(lastBit).add(PSEUDO_EOS);
				
}
	@Override
	public ArrayList<String> getFollows(WordGram key){
		if(newMap.keySet().contains(key)) {
			return newMap.get(key);
		}
//		else {
//			throw new NoSuchElementException();
//		}
	}
	
	public String getRandomText(int length){
		ArrayList<String> sb = new ArrayList<>();
		int index = myRandom.nextInt(myWords.length - myOrder);
		WordGram current = new WordGram(myWords,index,myOrder);

		
		sb.add(current.toString());
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0){
				break;
			}
			int index2 = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index2);
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.add(nextItem);
			current = current.shiftAdd(nextItem); //current.substring(1)+ nextItem;
		}
		return String.join(" ", sb);
	}
}
