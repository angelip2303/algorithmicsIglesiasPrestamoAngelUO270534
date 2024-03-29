﻿package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	
	public Bubble(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		Integer actual, pred;
		
		for (int i = 1; i < elements.length; i++)  // We iterate over the whole array as many times as needed.
			for (int j = elements.length - 1; j >= i; j--) { // We iterate over this value and its predecessors.
				actual = (Integer) elements[j];
				pred = (Integer) elements[j - 1];
				
				// If the actual value goes before its predecessor: EXCHANGE.
				if(actual.compareTo(pred) < 0) { // I'm using this because this way we could implement generics in an easier way.
					Util.interchange(elements, j - 1, j); //A value with its predecessor.
				}
		}
	}  
	
	@Override
	public String getName() {
		return "Bubble";
	} 
} 