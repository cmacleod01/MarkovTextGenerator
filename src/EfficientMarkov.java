import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class EfficientMarkov extends MarkovModel {
		protected static String PSEUDO_EOS = "";
		
		public Map<String,ArrayList<String>> myMap; //instance variables

		
		public EfficientMarkov(int order) { //creates object
			super(order);
			//myOrder = order;
	
			
		}
		
		@Override
		public void setTraining(String text) {	 //creates map
			myMap = new HashMap<String, ArrayList<String>>();
			myText = text;
//			int myOrder1 = myOrder;
			for(int k=0;k<text.length() - myOrder + 1;k++) {
				String current = text.substring(k,k+myOrder);
				if(!myMap.containsKey(current)) {
					ArrayList<String> valueList = new ArrayList<String>();
					myMap.put(current, valueList);
				}
				else if (myOrder + k >= text.length()) {
					myMap.get(current).add(PSEUDO_EOS);
				}
				if((myOrder+k)<text.length()) {
					String after = text.substring(k+myOrder,k+(myOrder+1));
					myMap.get(current).add(after);
				}
			}
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