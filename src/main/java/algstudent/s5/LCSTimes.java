package algstudent.s5;

public class LCSTimes {
	
	static String mode = "dynamic"; // "recursive";   
	static int nTimes = 10;
	
	public static void main(String args[]) {
		long t1 = 0;
		long t2 = 0;
		float total = 0;
		
		System.out.println("MODE: " + mode);
		
		if (mode.equals("dynamic")) {
			LCS lcs;	
			for (int n=100; n<=12800; n*=2) { 
				for (int i=0; i<nTimes; i++){
					lcs = new LCS(n);
					lcs.initTable();
					t1 = System.currentTimeMillis();              
				    lcs.fillTable();
				    lcs.findLongestSubseq(false);
				    t2 = System.currentTimeMillis();
				    total += (float) (t2 - t1);
				}	
				System.out.println("Time [ms]= " + total/nTimes + ", n=" + n);
			}
		}
		else {            
			LCSRec lcs_rec;
			String str1, str2;
			for (int n=1; n<=12800; n++) { 
				for (int i=0; i<nTimes; i++){
					lcs_rec = new LCSRec();
					str1 = lcs_rec.genRandomSeq(n);
					str2 = lcs_rec.genRandomSeq(n);
					t1 = System.currentTimeMillis();      
					lcs_rec.findLongestSubseq(str1, str2);
				    t2 = System.currentTimeMillis();
				    total += (float) (t2 - t1);
				}
				System.out.println("Time [ms]= " + total/nTimes + ", n=" + n);
			}
		}
		
	}

}
