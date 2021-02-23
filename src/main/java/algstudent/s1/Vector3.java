 package algstudent.s1;

public class Vector3 {

	static int[] v;
	static int[] w;

	public static void main(String arg[]) {
		long t1, t2;
//		int sum = 0;
		
		for(int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			w = new int[2];
			Vector1.fillIn(v);
			
//			t1 = System.currentTimeMillis();
//			sum = Vector1.sum(v);
//			t2 = System.currentTimeMillis();
			
			t1 = System.currentTimeMillis();
			Vector1.maximum(v, w);
			t2 = System.currentTimeMillis();
			
//			System.out.printf("SIZE=%d TIME=%d milliseconds SUM=%d\n", n, t2-t1, sum);
			System.out.printf("SIZE=%d TIME=%d milliseconds SUM=%s\n", n, t2-t1, w.toString());
		}
	}
	
}