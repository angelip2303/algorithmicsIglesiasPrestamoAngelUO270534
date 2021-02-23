package algstudent.s0;

public class MatrixOperationsTest {

	public static void main(String[] args) {
		MatrixOperations m = new MatrixOperations("src/main/java/algstudent/s0/matrix.txt");
		m.write();
		m.travelPath(3, 0);
	}

}
