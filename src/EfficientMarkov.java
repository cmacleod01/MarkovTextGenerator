import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends MarkovModel {
		
		private Map<String,ArrayList<String>> myMap;
		private String text;
		private int myOrder;

		
		EfficientMarkov(int order) {
			super(order);
			setTraining(text); 
		}
		
		@Override
		public void setTraining(String text) {	
			myMap = new HashMap<String, ArrayList<String>>();
			myText = text;
			myMap.clear();
			int myOrder1 = myOrder;
			int pos = 0;
			while(pos<(myText.length()-myOrder1)) {
				String current = myText.substring(pos,pos+myOrder1);
				if(!myMap.containsKey(current)) {
					ArrayList<String> valueList = new ArrayList<String>();
					myMap.put(current, valueList);
				}
				String after = myText.substring(pos+myOrder1,pos+(myOrder1+1));
				myMap.get(current).add(after);
				pos++;
			}
			String lastBit = myText.substring(myText.length()-myOrder1, myText.length());
			if(!myMap.containsKey(lastBit)) {
				ArrayList<String> valueList = new ArrayList<String>();
				myMap.put(lastBit, valueList);
			}
			myMap.get(lastBit).add(PSEUDO_EOS);
		}
		
		
		
		@Override
		public ArrayList<String> getFollows(String key){
			if(myMap.keySet().contains(key)) {
				return myMap.get(key);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		
		
	}