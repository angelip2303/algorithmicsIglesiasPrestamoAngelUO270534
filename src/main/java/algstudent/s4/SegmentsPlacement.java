package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Collections;

public class SegmentsPlacement {
	
	private static final String FILE_NAME = "src/main/java/algstudent/s4/game1.txt";
	
	private int n; // Number of segments.
	private Integer[] l; // Length of each segment.
	
	private LinkedList<Pair> sortedPairs1;
	private LinkedList<Pair> sortedPairs2;
	private LinkedList<Pair> sortedPairs3;
	
	public SegmentsPlacement(Integer[] l) {
		this.n = l.length;
		this.l = l;
		
		this.sortedPairs1 = new LinkedList<Pair>();
		this.sortedPairs2 = new LinkedList<Pair>();
		this.sortedPairs3 = new LinkedList<Pair>();
	}
	
	public SegmentsPlacement() {
		this.n = Integer.MAX_VALUE; // Number of segments.
		
		this.sortedPairs1 = new LinkedList<Pair>();
		this.sortedPairs2 = new LinkedList<Pair>();
		this.sortedPairs3 = new LinkedList<Pair>();
	}

	public static void main (String args []) {
		SegmentsPlacement sp = new SegmentsPlacement();
		
		sp.readFromFile(FILE_NAME);
		
		// Now we will start with the algorithms.
		double greedy1 = sp.greedy1();
		double greedy2 = sp.greedy2();
		double greedy3 = sp.greedy3();
		
		// IO --> through the console.
		sp.print(sp.sortedPairs1);
		System.out.printf("Cost of greedy 1 = %.2f pufosos%n%n", greedy1);
		
		sp.print(sp.sortedPairs2);
		System.out.printf("Cost of greedy 2 = %.2f pufosos%n%n", greedy2);
		
		sp.print(sp.sortedPairs3);
		System.out.printf("Cost of greedy 3 = %.2f pufosos%n%n", greedy3);
	}
	
	private void readFromFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			try {
				int line = Integer.parseInt(reader.readLine());
				
				for(int i = 0; i <= n; i++) {
					if(i == 0) {
						n = line;
						l = new Integer[n];
					}else {
						line = Integer.parseInt(reader.readLine());
						l[i - 1] = line;
					}
				}
			}finally {
				reader.close();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	double greedy1() {
		// We create a LinkedList of Pairs so that we store the value of the segment (number) and the length.
		for (int i = 0; i < l.length; i ++) {
			sortedPairs1.add(new Pair(i, l[i]));
		}
		
		// We sort the LinkedList of Pairs.
		
		// We perform the operations.
		return calculatePufosos(sortedPairs1);
	}
	
	double greedy2() {
		// We create a LinkedList of Pairs so that we store the value of the segment (number) and the length.
		for (int i = 0; i < l.length; i ++) {
			sortedPairs2.add(new Pair(i, l[i]));
		}
		
		// We sort the LinkedList of Pairs.
		Collections.sort(sortedPairs2, Collections.reverseOrder()); // --> Sorted in descending order. 
		
		// We perform the operations.
		return calculatePufosos(sortedPairs2);
	}
	
	double greedy3() {
		// We create a LinkedList of Pairs so that we store the value of the segment (number) and the length.
		for (int i = 0; i < l.length; i ++) {
			sortedPairs3.add(new Pair(i, l[i]));
		}
		
		// We sort the LinkedList of Pairs.
		Collections.sort(sortedPairs3); // --> Sorted in ascending order. 
		
		// We perform the operations.
		return calculatePufosos(sortedPairs3);
	}
	
	private double calculatePufosos(LinkedList<Pair> sortedPairs) {
		int x = 0;
		int y = 0;
		double mid = 0;
		
		for (Pair element : sortedPairs) {
			y = element.length + x;
			mid += ((double) (x + y)) / 2;
			x = y;
		}
		
		return mid;
	}
	
	private void print(LinkedList<Pair> sortedPairs) {
		int x = 0;
		int y = 0;
		int segmentPosition = 0;
		double midPoint = 0.0;
		
		for(Pair value : sortedPairs) {
			y = value.length + x;
			segmentPosition = value.segmentsPosition;
			midPoint = ((double) (x + y)) / 2;
			
			System.out.printf("S%d: (%d to %d), midpoint = %.2f%n", segmentPosition, x, y, midPoint);
			
			x = y;
		}
	}
	
	private class Pair implements Comparable<Pair> {
		private int segmentsPosition;
		private int length;
		
		private Pair(int segmentsPosition, int length) {
			this.segmentsPosition = segmentsPosition;
			this.length = length;
		}

		@Override
		public int compareTo(Pair o) {
			int ans = this.length - o.length;
			
			if(ans == 0) {
				return this.segmentsPosition - o.segmentsPosition;
			}
			
			return ans;
		}
	}
	
}
