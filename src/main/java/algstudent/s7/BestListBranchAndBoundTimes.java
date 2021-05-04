package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algstudent.s7.util.Song;

public class BestListBranchAndBoundTimes {

//	+-------------------------+
//	|    -*- CONSTANTS -*-    |
//	+-------------------------+
	
	private static final int[] SIZES = new int[]{25, 50, 100, 200, 400};
	
//	+----------------------------+
//	|    -*- MAIN METHODS -*-    |
//	+----------------------------+
	
	// -*- MAIN -*-
	
	public static void main(String args[]) {
		long t1, t2;
		
		// --*-- Branch and Bound --*--
		System.out.println("--*-- Branch and Bound --*--");
		for (int i = 0; i < SIZES.length; i++) {
			algstudent.s7.BestList bl1 = new BestList(getSongsRandomly(SIZES[i]));
	        BestListBranchAndBound blBNB = new BestListBranchAndBound(bl1);
	        
	        // We are going to compute the best list
	        t1 = System.currentTimeMillis();
        	blBNB.branchAndBound(blBNB.getRootNode());
	        t2 = System.currentTimeMillis();
	        
	        System.out.printf("SIZE: %d - The time for the Branch & Bound algorithm is: %d milliseconds%n", SIZES[i], (t2 - t1));
	        System.out.printf("\t* Processed Nodes: %d", blBNB.getNumberOfProcessedNodes());
	        System.out.printf("\n\t* Generated Nodes: %d", blBNB.getNumberOfGeneratedNodes());
	        System.out.printf("\n\t* Trimmed Nodes: %d\n", blBNB.getNumberOfTrimmedNodes());
		}
		
		// --*-- Backtracking --*--
		System.out.println("\n--*-- Backtracking --*--");
		for (int i = 0; i < SIZES.length; i++) {
	        algstudent.s6.BestList bl2 = new algstudent.s6.BestList(getSongsRandomly(SIZES[i]));
	        
	        // We are going to compute the best list
	        t1 = System.currentTimeMillis();
        	bl2.backtracking(0);
	        t2 = System.currentTimeMillis();
	        
	        System.out.printf("SIZE: %d - The time for the Backtracking algorithm is: %d milliseconds%n", SIZES[i], (t2 - t1));
		}
	}
	
	// -*- AUXILIARY METHODS -*-
	
	// -*- GETTERS -*-
	
	/* Method that generates n random songs, following:
     * 	--> Song time generated according a normal distribution; where the mean is 2 minutes, and the standard deviation is 1 minute.
     * 	--> Scores are generated according a normal distribution; where the mean is 2000, and the standard deviation is 1000.
     */
    private static List<Song> getSongsRandomly(int n) {
        List<Song> songs = new ArrayList<Song>();
        int t_secs, score;
        Random rand = new Random();
        
        for (int i=0; i<n; i++) {
            do
            	t_secs = (int) (rand.nextGaussian() * 120 + 60);
            while (t_secs < 30);

            do
            	score = (int) (rand.nextGaussian() * 2000 + 1000);
            while (score < 300);

            songs.add(new Song(String.valueOf(i), t_secs, score));
        }
        
        return songs;
    }
	
}
