package algstudent.s32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class InversionsParallel extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	public static final int THRESHOLD = 10;
	
	private List<Integer> ranking;
	int left, right;
	
	public InversionsParallel(List<Integer> ranking, int left, int right) {
		this.ranking = ranking;
		this.left = left;
		this.right = right;
	}

	public String start() {
		ForkJoinPool fjp = new ForkJoinPool();
		InversionsParallel ip = new InversionsParallel(ranking, 0, ranking.size() - 1);
		
		return fjp.invoke(ip) + "";
	}

	@Override
	protected Long compute() {
		long count = 0;
		
		if(left >= right) { return count; } // Base case.
		
		if(right - left < THRESHOLD) { count = new Inversions(ranking).mergeSort(left, right); } // Solve Sequentially. 
		
		// We divide the array into two halves. As stated during the s2 they must be as equal as possible.	
		int m = (left + right) / 2;
		
		InversionsParallel taskA = new InversionsParallel(ranking, left, m); // We divide the list until the base case is reached.
		InversionsParallel taskB = new InversionsParallel(ranking, m + 1, right);
		
		taskA.fork();
		taskB.fork();
		
		count += taskA.join();
		count += taskB.join();
		count += count(left, right, m);
		
		return count;
	}
	
	// Method to count the number of inversions when two halves are merged.
	long count(int l, int r, int mid) {
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