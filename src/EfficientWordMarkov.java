import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class EfficientWordMarkov extends WordMarkovModel {
	
	public TreeMap<WordGram,ArrayList<String>> newMap; //instance variables
	private String[] myWords;
	public EfficientWordMarkov(int order) { //constructor
		super(order);

	}
		
	@Override
	public void setTraining(String text){ //creates map
		newMap = new TreeMap<WordGram, ArrayList<String>>();
		myWords = text.split("\\s+");
		for(int i=0;i<(myWords.length-myOrder+1);i++) {
			WordGram currentWG = new WordGram(myWords,i,myOrder);
			if(!newMap.containsKey(currentWG)) {
				ArrayList<String> valueList = new ArrayList<String>();
				newMap.put(currentWG, valueList);
			}
			
			if (myOrder + i >= myWords.length) {
				newMap.get(currentWG).add(PSEUDO_EOS);
			}
			if((myOrder+i)<myWords.length) {
			
				newMap.get(currentWG).add(myWords[myOrder+i]);
			}
		
			}
//System.out.println(newMap);
			
}
	@Override
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> myValues = newMap.get(key);
		if(myValues == null) {
			return new ArrayList<String>();
		}
		return myValues;
//		System.out.println(key);
//		System.out.println(newMap.keySet());
//		if(newMap.keySet().contains(key)) {
//			return newMap.get(key);
//		}
//		else {
//			ArrayList<String> 
//			throw new NoSuchElementException();
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
				break;
			}
			sb.add(nextItem);
			current = current.shiftAdd(nextItem); //current.substring(1)+ nextItem;
		}
		return String.join(" ", sb);
	}
}
