package algstudent.s5;

import java.util.Random;

public class LCSRec {
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	public String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Find a MSC directly by a recursive approach 
	 */
	public String findLongestSubseq(String s1, String s2){
		int s1Length = s1.length() - 1;
		int s2Length = s2.length() - 1;
		
		if(s1Length < 0 || s2Length < 0) {
			return ""; 
		}
		
		if(s1.substring(s1Length).equals(s2.substring(s2Length))) {
			return findLongestSubseq(s1.substring(0, s1Length), s2.substring(0, s2Length)) + s1.substring(s1Length);
		} else {
			String first = findLongestSubseq(s1, s2.substring(0, s2Length));
	        String second = findLongestSubseq(s1.substring(0, s1Length), s2);
	        
	        if(first.length() > second.length()){
	            return first;
	        }
	        
	        return second;
		}
	}

}
