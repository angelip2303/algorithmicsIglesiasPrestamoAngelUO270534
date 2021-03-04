package algstudent.s3;

/* Class that models T(n)=2 T(n-1)+O(1) TODO 
 * Params: a=2;b=1;k=0
 * The time complexity is exponential O(2^n) 
 * and the waste of stack is O(n)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction4{
	
	public static long rec4(int n) {
		// TODO Implement.
		return 0;
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