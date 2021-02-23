package algstudent.s1;

public class Vector4 {
	
	static int[] v;
	static int[] w;
	
	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]);
		long t1, t2;
		int sum = 0;
		
		for(int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			w = new int[2];
			Vector1.fillIn(v);
			
			t1 = System.currentTimeMillis();
			for(int repetitions = 1; repetitions <= nTimes; repetitions++) {
//				Vector1.fillIn(v);
//				sum = Vector1.sum(v);
				Vector1.maximum(v, w);
			}
			t2 = System.currentTimeMillis();
			
			sum = Vector1.sum(v);
			
			System.out.printf("SIZE=%d TIME=%d hundreds of micros SUM=%d NTIMES=%d\n", n, t2-t1, sum, nTimes);
		}
	}
	
}
