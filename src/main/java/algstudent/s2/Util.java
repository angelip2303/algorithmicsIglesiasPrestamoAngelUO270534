package algstudent.s2;

public class Util {

	public static void interchange(int[] elements, int i, int j) {
		int tmp = elements[j];
		elements[j] = elements[i];
		elements[i] = tmp;
	}
	
}
