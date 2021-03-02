package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		int min;
		Integer succ;
		
		for(int i = 0; i < elements.length; i++) {
			min = i;
			
			for (int j = i; j < elements.length; j++) {
				succ = (Integer) elements[j];
				if(succ.compareTo(elements[min]) < 0) min = j; // We store the value at which it is located
			}
			
			if(min != i) { // Elements must be replaced.
				Util.interchange(elements, i, min);
			}
		}
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 