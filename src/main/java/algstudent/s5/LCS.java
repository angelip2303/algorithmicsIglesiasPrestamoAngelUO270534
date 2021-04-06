package algstudent.s5;

import java.util.Random;

public class LCS {
	
	CellTable[][] table; //table with local values
	String str1; // 1st string
	String str2; // 2nd string
	String result; // result string
	int size1; //str1 size
	int size2; //str2 size
	
	/**
	 * Constructor
	 * @param s1 1st string to compare
	 * @param s2 2nd string to compare
	 */
	public LCS(String s1, String s2) {
		str1 = "*" + s1; //str1. 1st column is headed with asterisk
		str2 = "*" + s2; //str2. 1st row is headed with asterisk
		System.out.println("String1: " + str1);
		System.out.println("String2: " + str2);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming 
	}
	
	/**
	 * Constructor used to measure times
	 * @param n size for the strings that are generated randomly 
	 */
	public LCS(int n) {
		str1 = "*" + this.genRandomSeq(n);
		str2 = "*" + this.genRandomSeq(n);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming para guardar todos los valores de la tabla din�mica 
	}
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	private String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Table values initialization
	 */
	public void initTable() {
		for (int j=0; j<size2; j++)
			for(int i=0; i<size1; i++)
				table[i][j] = new CellTable();
	}
	
	/**
	 * Print the table with the results
	 */
	public void printTable() {
		System.out.printf("%11s", "*");
		for (int i=0; i<size1; i++) 
			System.out.printf("%11s", str1.substring(i, i+1)); // chars str1 (horizontal)
		System.out.println();
		
		for (int j=0; j<size2; j++) {
			System.out.printf("%11s", str2.substring(j, j+1)); // chars str2 (vertical)
			for(int i=0; i<size1; i++)
				System.out.printf("%4d(%2d,%2d)", table[i][j].value, table[i][j].iPrev, table[i][j].jPrev); // table values
			System.out.println();
		}
	}
	
	/**
	 * Print current MSC result
	 */
	public void printLongestSubseq(){
		System.out.println(result);
	} 

	public class CellTable{
		public int value; // value for dynamic programming
		public int iPrev; //"pointer" to string 1 character used to compute value 
		public int jPrev; //"pointer" to string 2 character used to compute value
	}
	
	/**
	 * Fill table values for dynamic programming
	 */
	public void fillTable(){
		char[] str1AsArray = str1.toCharArray();
		char[] str2AsArray = str2.toCharArray();
		
		char rowPointer, colPointer;
		int aboveCell, leftCell, cellToCompare;
		
		for(int i = 1; i < table.length; i++) {
			rowPointer = str1AsArray[i];
			
			for(int j = 1; j < table[0].length; j++) {
				if(i == 0 || j == 0) {
					table[0][i].value = 0;
					table[0][i].iPrev = 0;
					table[0][i].jPrev = 0;
				}else if (str1AsArray[i] == str2AsArray[j]) {
					table[i][j].value = table[i - 1][j - 1].value + 1;
					table[i][j].iPrev = i - 1;
					table[i][j].jPrev = j - 1;
				}else {
					colPointer = str2AsArray[j];
					
					leftCell  = table[i][j - 1].value;
					aboveCell = table[i - 1][j].value;
					cellToCompare = rowPointer == colPointer ? table[i - 1][j - 1].value + 1 : table[i - 1][j - 1].value;
					
					table[i][j].value = Integer.max(cellToCompare, Integer.max(leftCell, aboveCell));
					table[i][j].iPrev = rowPointer == colPointer || table[i][j].value == table[i - 1][j].value ? i - 1 : i;
					table[i][j].jPrev = rowPointer == colPointer || table[i][j].value == table[i][j - 1].value ? j - 1 : j;
				}
			}
		}
	}
	
	/**
	 * Find the MSC from the table (dynamic programming)
	 * @param v if True verbose mode activated (To show the path trough the different cells)
	 */
	public void findLongestSubseq(boolean v){
		int i = table.length - 1;
		int j = table[0].length - 1;
		int index = table[i][j].value;
		
		char[] str1AsArray = str1.toCharArray();
		char[] str2AsArray = str2.toCharArray();
		
		char[] lcs = new char[i + 1];
		
		while(i > 0 && j > 0) {
			if (str1AsArray[i] == str2AsArray[j]) {
				lcs[index--] = str1AsArray[i--];
				
				// Decrease the value of j which is the last not found.
                j--;
			}
			else if (table[i - 1][j].value > table[i][j - 1].value)
                i--;
            else
                j--;
		}
		
		for(int k = 0; k < lcs.length; k++)
			if(lcs[k] != '\u0000') result += lcs[k];
	}

}
