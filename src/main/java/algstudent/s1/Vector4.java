package algstudent.s1;

public class Vector4 {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]);
		long t1, t2;
		int sum = 0;
		
		for(int n = 10; n <= Integer.MAX_VALUE; n *= 5) {

			Vector1.fillIn(v);
			
			t1 = System.currentTimeMillis();
			
			for(int repetitions = 1; repetitions <= nTimes; repetitions++) {
				sum = Vector1.sum(v);
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.printf("SIZE=%d TIME=%d microseconds SUM=%d NTIMES=%d\n", n, t2-t1, sum, nTimes);
		}
	}
	
}
