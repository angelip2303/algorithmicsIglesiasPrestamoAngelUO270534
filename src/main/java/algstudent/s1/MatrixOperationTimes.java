package algstudent.s1;

import algstudent.s0.MatrixOperations;

public class MatrixOperationTimes {
	
	public static void main(String[] args) {
		int nTimes = Integer.parseInt(args[0]);
		long t1, t2;
		int ans = 0;
		
		for(int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			MatrixOperations mo = new MatrixOperations(n, 1, 4);
			
			t1 = System.currentTimeMillis();
			for(int repetitions = 1; repetitions <= nTimes; repetitions++) {
//				ans = mo.sumDiagonal1();
				ans = mo.sumDiagonal2();
			}
			t2 = System.currentTimeMillis();
			
			System.out.printf("SIZE=%d TIME=%d hundreds of micros SUM=%s\n", n, t2-t1, ans);
		}
	}

}
