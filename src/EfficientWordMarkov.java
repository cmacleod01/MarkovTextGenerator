import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class EfficientWordMarkov extends WordMarkovModel {
	
	private Map<WordGram,ArrayList<String>> newMap;
	private String myText;
	private String[] myWords;

	EfficientWordMarkov(int order) {
		super(order);
		setTraining(myText);
	}
		
	@Override
	public void setTraining(String text){
		myText = text;
		newMap = new HashMap<WordGram, ArrayList<String>>();
		myWords = myText.split("\\s+");
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
//		String lastBit = myWords[myWords.length-myOrder1, myWords.length);
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
		else {
			throw new NoSuchElementException();
		}
	}
}
