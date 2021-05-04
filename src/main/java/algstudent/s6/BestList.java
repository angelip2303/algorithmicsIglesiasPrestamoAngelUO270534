package algstudent.s6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import algstudent.s7.util.Song;

public class BestList {

//	+-------------------------+
//	|    -*- CONSTANTS -*-    |
//	+-------------------------+
	
	private final String FILE_NAME = "src/main/java/algstudent/s6/";
	
//	+--------------------------+
//	|    -*- ATTRIBUTES -*-    |
//	+--------------------------+
	
	private List<Song> songs;
	
	private List<Song> blockA = new ArrayList<Song>();
	private List<Song> blockB = new ArrayList<Song>();

	private List<Song> pl1BestList;
	private List<Song> pl2BestList;
	
	private double maxDurationOfPlayList;
	private int totalNumberOfSongs;
	
	private int counter = 0;
	private int totalScore = 0;

//	+---------------------------+
//	|    -*- CONSTRUCTOR -*-    |
//	+---------------------------+
	
	public BestList(String fileName, double maxDurationOfPlayList) {
		List<String> lines = Util.read(FILE_NAME + fileName);
		this.songs = Util.parse(lines);
		
		this.maxDurationOfPlayList = maxDurationOfPlayList  * 60; 
		this.totalNumberOfSongs = songs.size();
	}
	
	public BestList(List<Song> songs) {
		this.songs = songs;
		maxDurationOfPlayList = 0.4 * getDurationOfPlaylist(songs);
	}
	
//	+----------------------------+
//	|    -*- MAIN METHODS -*-    |
//	+----------------------------+
	
	// -*- MAIN -*-
	
	public static void main(String[] args) {
		BestList bl = new BestList(args[0], Integer.parseInt(args[1]));
		
		// bl.recursive(0);
		bl.backtracking(0);
		bl.print();
	}

	// -*- AUXILIARY METHODS -*-
	
	private void print() {
		System.out.println(String.format("Number of songs: %d", totalNumberOfSongs));
		
		System.out.println("\nList of songs:");
		
		for(Song song : songs) {
			System.out.println(song.toString());
		}
		
		System.out.println(String.format("\nLength of the blocks: %f", maxDurationOfPlayList / 60));
		System.out.println(String.format("Total score: %d", totalScore));
		System.out.println(String.format("Total counter: %d", counter));
		
		System.out.println("\nBest block A:");
		pl1BestList.forEach(x -> System.out.println(x.toString()));
		System.out.println("\nBest block B:");
		pl2BestList.forEach(x -> System.out.println(x.toString()));
	}
	
	private void saveSolution() {
		if(getTotalScore(blockA, blockB) > totalScore) {
			totalScore = getTotalScore(blockA, blockB);
			
			pl1BestList = new ArrayList<Song>();
			pl2BestList = new ArrayList<Song>();
			
			for(Song song : blockA)
				pl1BestList.add(song);
			
			for(Song song : blockB)
				pl2BestList.add(song);
		}
	}

	// -*- GETTERS -*-
	
	private int getTotalScore(List<Song> blockA, List<Song> blockB) {
		return Stream
				.of(blockA, blockB)
				.flatMap(Collection::stream)
				.map(x -> x.getScore())
				.reduce(0, Integer::sum);
	}

	private int getDurationOfPlaylist(List<Song> playList) {
		return playList
				.stream()
				.map(x -> x.getTime())
				.reduce(0, Integer::sum);
	}
	
	private double getMinimumRating(List<Song> playList) {
		return playList
				.stream()
				.map(x -> x.rating())
				.min(Double::compare).get();
	}
	
	private void insertInBlock(List<Song> playList, int level) {
		playList.add(songs.get(level));
		backtracking(level + 1);
		playList.remove(songs.get(level));
		counter++;
	}
	
	private boolean doesSongFit(List<Song> playList, int level) {
		return getDurationOfPlaylist(playList) < maxDurationOfPlayList - songs.get(level).getTime();
	}
	
	// -*- RECURSIVE -*-
	
	/**
	 * I made this implementation for me to understand better what I was doing.
	 * Notice that I implemented a solution that used some optimizations throughout the rating.
	 * 
	 * @param level --> depth in the songs list.
	 */
	@SuppressWarnings("unused")
	private void recursive(int level) {
		if(level == totalNumberOfSongs - 1) { // BASE CASE.
			assert getDurationOfPlaylist(blockA) <= maxDurationOfPlayList;
			assert getDurationOfPlaylist(blockB) <= maxDurationOfPlayList;
			
			saveSolution();
		}else {
			double nextSongFit = maxDurationOfPlayList - songs.get(level).getTime();
			boolean nextSongFitA = getDurationOfPlaylist(blockA) < nextSongFit;
			boolean nextSongFitB = getDurationOfPlaylist(blockB) < nextSongFit;
			
			boolean isScoreForA = blockA.size() > 0 ? songs.get(level).rating() >= getMinimumRating(blockA) : true;
			boolean isScoreForB = blockB.size() > 0 ? songs.get(level).rating() >= getMinimumRating(blockB) : true;
			
			if(!nextSongFitA && !nextSongFitB || !isScoreForA && !isScoreForB) { // Don't insert it.
				recursive(level + 1);
				counter++;
			}if(nextSongFitA && isScoreForA) { // BLOCK A.
				blockA.add(songs.get(level));
				recursive(level + 1);
				blockA.remove(songs.get(level));
				counter++;
			}if(nextSongFitB && isScoreForB) { // BLOCK B.
				blockB.add(songs.get(level));
				recursive(level + 1);
				blockB.remove(songs.get(level));
				counter++;
			}
		}
	}
	
	// -*- BACKTRACKING -*-
	
	/* Implementation of the Backtracking solution.
	 * 
	 * @param level --> depth in the songs list.
	 */
	public void backtracking(int level) {
		if(level == totalNumberOfSongs) { // BASE CASE.
			// POSTCONDITIONS: -ea argument in VM --> you can delete it if you want.
			assert getDurationOfPlaylist(blockA) <= maxDurationOfPlayList : "The duration of the BLOCK A is wrong.";
			assert getDurationOfPlaylist(blockB) <= maxDurationOfPlayList : "The duration of the BLOCK B is wrong.";
			
			saveSolution();
		}else {
			for(int i = 0; i <= 2; i++) {
				// Don't insert it.
				if(i == 0) {
					backtracking(level + 1);
					counter++;
				}
				
				// BLOCK A.
				if(i == 1 && doesSongFit(blockA, level))
					insertInBlock(blockA, level);
				
				// BLOCK B.
				if(i == 2 && doesSongFit(blockB, level))
					insertInBlock(blockB, level);
			}
		}
	}
	
}
