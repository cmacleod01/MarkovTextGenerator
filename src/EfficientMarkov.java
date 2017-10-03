import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientMarkov extends MarkovModel {
		
		private Map<String,ArrayList<String>> myMap; //instance variables
		private int myOrder;

		
		public EfficientMarkov(int order) { //creates object
			super(order);
			myOrder = order;
			myRandom = new Random(RANDOM_SEED);
			
		}
		
		@Override
		public void setTraining(String text) {	 //creates map
			myMap = new HashMap<String, ArrayList<String>>();
			myText = text;
			myMap.clear();
//			int myOrder1 = myOrder;
			int pos = 0;
			while(pos<(myText.length()-myOrder)) {
				String current = myText.substring(pos,pos+myOrder);
				if(!myMap.containsKey(current)) {
					ArrayList<String> valueList = new ArrayList<String>();
					myMap.put(current, valueList);
				}
				String after = myText.substring(pos+myOrder,pos+(myOrder+1));
				myMap.get(current).add(after);
				pos++;
			}
			String lastBit = myText.substring(myText.length()-myOrder, myText.length());
			if(!myMap.containsKey(lastBit)) {
				ArrayList<String> valueList = new ArrayList<String>();
				myMap.put(lastBit, valueList);
			}
			myMap.get(lastBit).add(PSEUDO_EOS);
		}
		
		
		
		@Override
		public ArrayList<String> getFollows(String key){ //gets next
			if(myMap.keySet().contains(key)) {
				return myMap.get(key);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		
		
	}