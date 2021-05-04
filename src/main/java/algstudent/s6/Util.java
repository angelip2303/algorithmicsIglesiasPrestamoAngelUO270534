package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algstudent.s7.util.Song;

public class Util {
	
	public static List<String> read(String fileName) {
		List<String> lines = new ArrayList<String>();
		BufferedReader rd = null;
		
		try {
			rd = new BufferedReader(new FileReader(fileName));
			
			String line = rd.readLine();
			while(line != null) {
				lines.add(line);
				line = rd.readLine();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rd != null) { // To avoid nullPointerException.
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
		return lines;
	}
	
	public static List<Song> parse(List<String> lines){
		List<Song> songs = new ArrayList<Song> ();
		
		try {
			int n = Integer.parseInt(lines.get(0));
			
			for (int i = 1; i <= n; i++) {
				try {
					songs.add(parseLine(lines.get(i)));
				}catch(IllegalStateException ise) { 
					ise.printStackTrace(); 
				}
			}
		}catch(Exception ex) { 
			ex.printStackTrace(); 
		}
		
		return songs;
	}
	
	private static Song parseLine(String lineToFormat) {
		String id = new String();
		int seconds = 0;
		int score = 0;
		
		String[] line;
		String[] secondsToFormat;
		
		try {
			line = lineToFormat.split("\t");
			
			id = line[0];
			
			secondsToFormat = line[1].split(":");
			seconds = Integer.parseInt(secondsToFormat[0]) * 60 + Integer.parseInt(secondsToFormat[1]);
			
			score = Integer.parseInt(line[2]);
			
			return new Song(id, seconds, score);
		}catch(Exception ex) {
			throw new IllegalStateException();
		}
	}

}