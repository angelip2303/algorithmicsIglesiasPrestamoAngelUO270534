package algstudent.s2;

/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) { // TODO: central element
		if (left >= right)
			return;
 
		// pick the pivot
		int middle = left + (right - left) / 2;
		int pivot = elements[middle];
 
		// make left < pivot and right > pivot
		int i = left, j = right;
		while (i <= j) {
			while (elements[i] < pivot)	i++;
			while (elements[j] > pivot) j--;
 
			if (i <= j) {
				int temp = elements[i];
				elements[i] = elements[j];
				elements[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (left < j)
			quickSort(left, j);
 
		if (right > i)
			quickSort(i, right);
	}

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
	
} 