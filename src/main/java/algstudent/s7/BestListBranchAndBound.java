package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import algstudent.s7.util.BranchAndBound;
import algstudent.s7.util.Node;
import algstudent.s7.util.Util;
import algstudent.s7.util.Song;

public class BestListBranchAndBound extends BranchAndBound {
	
    public BestListBranchAndBound(BestList bl) {
    	rootNode = bl;
    }
    
}

class BestList extends Node {

//	+-------------------------+
//	|    -*- CONSTANTS -*-    |
//	+-------------------------+

	private static final String FILE_NAME = "src/main/java/algstudent/s7/";
	
//	+--------------------------+
//	|    -*- ATTRIBUTES -*-    |
//	+--------------------------+
	
	private List<Song> songs;
	
	private ArrayList<Song> blockA = new ArrayList<Song>(); // Best list of songs --> BLOCK A
	private ArrayList<Song> blockB = new ArrayList<Song>(); // Best list of songs --> BLOCK B
	
	private double maxDurationOfPlayList;
	
//	+---------------------------+
//	|    -*- CONSTRUCTOR -*-    |
//	+---------------------------+
	
	public BestList(String fileName) {
		List<String> lines = Util.read(FILE_NAME + fileName);
		this.songs = Util.parse(lines);
		maxDurationOfPlayList = 1200;
	}
	
	public BestList(List<Song> songs) {
		this.songs = songs;
		maxDurationOfPlayList = 0.4 * getDurationOfPlaylist(songs);
	}
	
    public BestList(ArrayList<Song> blockA, ArrayList<Song> blockB, List<Song> songs, 
    					double maxDurationOfPlayList, int depth, UUID ID) {
    	this.blockA = blockA;
    	this.blockB = blockB;
    	this.songs = songs;
    	this.maxDurationOfPlayList = maxDurationOfPlayList;
    	this.depth = depth;
    	this.parentID = ID;
        calculateHeuristicValue();
    }

//	+----------------------------+
//	|    -*- MAIN METHODS -*-    |
//	+----------------------------+
	
	// -*- MAIN B&B Methods -*-
	
	@Override
	public void calculateHeuristicValue() {
		if(prune()) {
			heuristicValue = Integer.MAX_VALUE;
		}else {
			int score = getTotalScore(blockA, blockB);
			int timeA = getDurationOfPlaylist(blockA);
			int timeB = getDurationOfPlaylist(blockB);
			
			// We compute the maximum expected score following this branch
			for(int i = depth; i < songs.size(); i++) {
				Song song = songs.get(i);
				boolean doesFitA = maxDurationOfPlayList - (timeA + song.getTime()) >= 0;
				boolean doesFitB = maxDurationOfPlayList - (timeB + song.getTime()) >= 0;
				
				if(doesFitA) { // if it fits A
					score += song.getScore();
					timeA += song.getTime();
				}else if (doesFitB) { // if it fits b
					score += song.getScore();
					timeB += song.getTime();
				}
			}
			
			heuristicValue = -score;
		}
	}
	
	private boolean prune() {
		return getDurationOfPlaylist(blockA) > maxDurationOfPlayList ||
				getDurationOfPlaylist(blockB) > maxDurationOfPlayList;
	}
	
	@Override
	public boolean isSolution() {
		return depth >= songs.size() - 1 &&
				getDurationOfPlaylist(blockA) <= maxDurationOfPlayList && 
				getDurationOfPlaylist(blockB) <= maxDurationOfPlayList;
	}
	
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		Song song = songs.get(depth);
		
		// Add it to block A.
		ArrayList<Song> playList = new ArrayList<Song>(blockA);
		playList.add(song);
		BestList temp = new BestList(playList, blockB, songs, maxDurationOfPlayList, depth + 1, this.ID);
		result.add(temp);
		
		// Add it to block B.
		playList = new ArrayList<Song>(blockB);
		playList.add(song);
		temp = new BestList(blockA, playList, songs, maxDurationOfPlayList, depth + 1, this.ID);
		result.add(temp);
			
		// Don't add it.
		temp = new BestList(blockA, blockB, songs, maxDurationOfPlayList, depth + 1, this.ID);
		result.add(temp);
		
		return result;
	}
	
	// -*- AUXILIARY METHODS -*-
	
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("--*-- BLOCK A --*--");
		for(int i = 0; i < blockA.size(); i++) {
			sb.append("\nSong: " + blockA.get(i));
		}
		
		sb.append("\n\n--*-- BLOCK B --*--");
		for(int i = 0; i < blockB.size(); i++) {
			sb.append("\nSong: " + blockB.get(i));
		}
		
		sb.append("\n");
		
		sb.append(String.format("\nDepth: %d", depth));
		sb.append(String.format("\nTotal Score: %d\n", getTotalScore(blockA, blockB)));
		
		return sb.toString();
    }
	
	// -*- GETTERS -*-
	
	private int getDurationOfPlaylist(List<Song> playList) {
		return playList
				.stream()
				.map(x -> x.getTime())
				.reduce(0, Integer::sum);
	}
	
	private int getTotalScore(List<Song> songs) {
		return songs
				.stream()
				.map(x -> x.getScore())
				.reduce(0, Integer::sum);
	}
	
	private int getTotalScore(List<Song> blockA, List<Song> blockB) {
		return getTotalScore(blockA) + getTotalScore(blockB);
	}

}