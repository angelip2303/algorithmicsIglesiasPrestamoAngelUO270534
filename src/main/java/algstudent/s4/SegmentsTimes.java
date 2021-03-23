package algstudent.s4;

import java.util.Random;

public class SegmentsTimes {

	private static final int N_TIMES = 1000;
	
	public static void main(String args[]) {
		for (int i = 100; i <= Integer.MAX_VALUE; i *= 2) {
			// -*- GREEDY 1 -*-
			
	        SegmentsPlacement sp = new SegmentsPlacement(generateLengths(i));	
	        long t1 = System.currentTimeMillis();
	        
	        for(int j = 0; j < N_TIMES; j++) {
	        	sp.greedy1();
	        }
	        
	        long t2 = System.currentTimeMillis();          
	        System.out.printf("SIZE: %d  -  The time for the algorithm O(n) - greedy1 is: %d microseconds%n", i, (t2 - t1));
	        	
	        // -*- GREEDY 2 -*-
	        
	        sp = new SegmentsPlacement(generateLengths(i));	
	        t1 = System.currentTimeMillis();
	        
	        for(int j = 0; j < N_TIMES; j++) {
	        	sp.greedy2();
	        }
	        
	        t2 = System.currentTimeMillis();          
	        System.out.printf("SIZE: %d  -  The time for the algorithm O(n log(n)) - greedy2 is: %d microseconds%n", i, (t2 - t1));
	        
	        // -*- GREEDY 3 -*-
	        
	        sp = new SegmentsPlacement(generateLengths(i));
	        t1 = System.currentTimeMillis();
	        
	        for(int j = 0; j < N_TIMES; j++) {
	        	sp.greedy3();
	        }
	        
	        t2 = System.currentTimeMillis();          
	        System.out.printf("SIZE: %d  -  The time for the algorithm O(n log(n)) - greedy3 is: %d microseconds%n", i, (t2 - t1));
	        
	        // -*- NEW LINE -*-
	        
	        System.out.println("\n****************************\n");
		}
	}
	
	private static Integer[] generateLengths(int size) {
		Integer[] aux = new Integer[size];
		Random rd = new Random();
		
		for(int i = 0; i < size; i++) {
			aux[i] = rd.nextInt(Integer.MAX_VALUE);
		}
		
		return aux;
	}
	
}
