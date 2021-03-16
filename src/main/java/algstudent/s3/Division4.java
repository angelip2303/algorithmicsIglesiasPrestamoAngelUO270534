package algstudent.s3;

/* Params: a=4;b=3;k=2 (4 < 3²) --> 4 < 9 --> O(n^k)
 * The time complexity is O(n²)
 */
public class Division4 {
	
	/* a --> There will be 2 subproblems.
	 * b --> All the subproblems have a size of (n / 2).
	 * k --> The complexity of the rest of the code (not taking into account the recursive calls) is n².
	 */
	public static long rec4 (int n) {
		long cont = 0;
		if (n<=0) 
			cont++;
		else { 
			for(int i = 1; i < n; i++) {
				for(int j = 1; j < n; j++) {
					cont++;
				}
			}
			
			rec4(n/4);
			rec4(n/4);
			rec4(n/4);
			rec4(n/4);
		}
		return cont;   
	}
	
	public static void main (String arg []) {
		 long t1,t2,cont = 0;	 
		 for (int n=1;n<=10000000;n*=2) {
			  t1 = System.currentTimeMillis ();			   
			  cont = rec4(n);
			  t2 = System.currentTimeMillis ();
			
			  System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);	
		 }  // for
	} // main
} //class
