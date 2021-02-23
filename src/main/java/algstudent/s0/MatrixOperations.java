package algstudent.s0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MatrixOperations {

	private static final int MOVE_UP = 1;
	private static final int MOVE_RIGHT = 2;
	private static final int MOVE_DOWN = 3;
	private static final int MOVE_LEFT = 4;
	private static final int TRAVERSED_NUMBER = -1;
	
	private int[][] matrix;
	private int numberOfElements;
	
	public MatrixOperations(int n, int min, int max) {
		this.numberOfElements = n;
		matrix = new int[n][n];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = new Random().nextInt(max - min + 1) + min; 
			}
		}
	}
	
	public MatrixOperations(String fileName) {		
		ArrayList<String> lines = readLines(fileName);
		
		this.numberOfElements = Integer.parseInt(lines.remove(0));
		matrix = new int[numberOfElements][numberOfElements];
		int row = 0;
		
		for(String line : lines) {
			String[] lineRow = line.split("\t");
			
			for(int col = 0; col < lineRow.length; col++) {
				matrix[row][col] = Integer.parseInt(lineRow[col]);
			}
			
			row++;
		}
	}
	
	public int getSize() {
		return numberOfElements;
	}
	
	public void write() {
		System.out.println(numberOfElements);
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(j == matrix[0].length - 1)
					System.out.print(matrix[i][j]);
				else
					System.out.print(matrix[i][j] + "\t");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	public int sumDiagonal1() {
		int count = 0;
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(i == j)
					count += matrix[i][j];
			}
		}
		
		return count;
	}
	
	public int sumDiagonal2() {
		int count = 0;
		
		for(int i = 0; i < matrix.length; i++) {
			count += matrix[i][i];
		}
		
		return count;
	}
	
	public void travelPath(int i, int j) {
		int count = travelPath(i, j, 0);
		
		write();
		System.out.println("Number of movements= " + count + "\n");
	}
	
	private int travelPath(int i, int j, int count) {
		if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length)
			return count;
		
		switch (matrix[i][j]) {
			case MOVE_UP:
				count++;
				matrix[i][j] = TRAVERSED_NUMBER;
				return travelPath(i - 1, j, count);
			case MOVE_RIGHT:
				count++;
				matrix[i][j] = TRAVERSED_NUMBER;
				return travelPath(i, j + 1, count);
			case MOVE_DOWN:
				count++;
				matrix[i][j] = TRAVERSED_NUMBER;
				return travelPath(i + 1, j, count);
			case MOVE_LEFT:
				count++;
				matrix[i][j] = TRAVERSED_NUMBER;
				return travelPath(i, j - 1, count);
			default: // We cannot move any longer
				return count;
		}
	}
	
	private ArrayList<String> readLines(String fileName) {
		ArrayList<String> aux = new ArrayList<String>();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			
			try {
				String line = reader.readLine();
				while ( line != null) {
					aux.add(line);
					line = reader.readLine();
				}
			}finally {
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("Error occurred.");
			System.exit(0);
		}
		
		return aux;
	}
	
}
