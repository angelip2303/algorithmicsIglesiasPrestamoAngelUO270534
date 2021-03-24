package algstudent.s5;

public class LCSTest {
	
	public static void main(String arg []) {
		
		String str1 = arg[0]; // 1st sequence
		String str2 = arg[1]; // 2nd sequence
		
		System.out.println("DYNAMIC PROGRAMMING:");
		LCS lcs = new LCS(str1, str2);
		System.out.println("Initializing table...");
		lcs.initTable();
		System.out.println("Filling table...");
		lcs.fillTable();
		System.out.println("Print table...");
		lcs.printTable();
		System.out.println("Finding longest subsequence...");
		lcs.findLongestSubseq(true);
		System.out.println("Printing longest subsequence...");
		lcs.printLongestSubseq();
		
		System.out.println("\n/****************/\n");
		
		System.out.println("RECURSIVE:");
		LCSRec lcsrec = new LCSRec();
		System.out.println("Finding longest subsequence...");
		String seq_rec = lcsrec.findLongestSubseq(str1, str2);
		System.out.println(seq_rec);
		
		System.out.println("Program terminated.");
	} 

}
