package algstudent.s32;

import java.util.ArrayList;
import java.util.List;

public class Inversions {

	private List<Integer> ranking;
	
	public Inversions(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		// TODO Parallelize
		return mergeSort(0, ranking.size() - 1) + "";
	}

	private long mergeSort(int left, int right) {
		long count = 0;
		
		if(left >= right) { return count; } // Base case.
		
		// We divide the array into two halves. As stated during the s2 they must be as equal as possible.	
		int m = (left + right) / 2;
		
		count += mergeSort(left, m); // We divide the list until the base case is reached.
		count += mergeSort(m + 1, right);
		count += count(left, right, m);
		
		return count;
	}
	
	// Method to count the number of inversions when two halves are merged.
	private long count(int l, int r, int mid) {
		long count = 0;
		
		List<Integer> left 	= new ArrayList<Integer>(ranking.subList(l, mid + 1));
		List<Integer> right = new ArrayList<Integer>(ranking.subList(mid + 1, r + 1));
		
		int i = 0; // Index of the first half.
		int j = 0; // Index of the second half.
		int k = l;
		
		while(i < left.size() && j < right.size()) {
			if (left.get(i) <= right.get(j)) { ranking.set(k++, left.get(i++)); }
			// In case there is a higher value in the first half: (mid - i) inversions. * As the list is sorted. *
			else { ranking.set(k++, right.get(j++)); count += (mid + 1) - (l + i); }
		}
		
		while (i < left.size())  { ranking.set(k++, left.get(i++));  }
		while (j < right.size()) { ranking.set(k++, right.get(j++)); }
		
		return count;
	}

}
