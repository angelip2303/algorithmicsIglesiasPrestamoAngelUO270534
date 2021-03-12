package algstudent.s3;

/* Params: a=3;b=2;k=0
 * The time complexity is exponential O(3^(n/2)) 
 */
public class Subtraction4{
	
	/* a --> There will be 3 subproblems.
	 * b --> All the subproblems have a size of (n - 2).
	 */
	public static long rec4(int n) {
		long cont = 0;
		if (n<=0) 
			cont++;
		else {
			cont++;  //O(1)    
			
			// 3 Recursive calls --> 3 Subproblems.
			rec4(n - 2); // n - 2 --> According to b.
			rec4(n - 2);
			rec4(n - 2);
		}
		return cont;  
	}
	
	public static void main(String arg []) {
		long t1,t2,cont=0;
		for (int n=1;n<=100;n++) {
			t1 = System.currentTimeMillis();
			cont=rec4(n);
			t2 = System.currentTimeMillis();
			
			System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);
		} // for
	} // main
} //class