package algstudent.s2;


/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {	
	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		// TODO: Implement this algorithm
		int tmp = 0;
		Integer actual, pred;
		
		for (int i = 1; i < elements.length; i++) {
			int pointer = i;
			
			while (pointer > 0) {
				actual = (Integer) elements[i];
				pred = (Integer) elements[pointer];
				
				if(actual.compareTo(pred) > 0) break; // It is sorted.
				
				tmp = actual;
				elements[i - pointer] = pred; // TODO: verify this works.
				elements[pointer] = tmp;
			}
		}
	} 
	
	@Override
	public String getName() {
		return "Insertion";
	} 
} 