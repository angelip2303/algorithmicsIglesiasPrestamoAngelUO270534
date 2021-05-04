package algstudent.s7;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit Test for BestList by Branch and Bound.
 */
public class BestListBranchAndBoundTest {
	
//	+---------------------+
//	|    -*- TESTS -*-    |
//	+---------------------+
	
	@Test
	public void testLista01() {
		assertTrue(executeFromFile("Lista01.txt"));
	}
	
	@Test
	public void testLista02() {
		assertTrue(executeFromFile("Lista02.txt"));
	}
	
	@Test
	public void testLista03() {
		assertTrue(executeFromFile("Lista03.txt"));
	}
	
//	+----------------------------+
//	|    -*- MAIN METHODS -*-    |
//	+----------------------------+
	
	// -*- AUXILIARY METHODS -*-
	
	private boolean executeFromFile(String fileName) {
		BestList bl = new BestList(fileName);
		BestListBranchAndBound blBNB = new BestListBranchAndBound(bl);	
		
		blBNB.branchAndBound(blBNB.getRootNode());
		blBNB.printSolutionTrace();
		System.out.println();
		
		boolean result = blBNB.getBestNode() != null ? true : false; 
		
		return result;
	}
	
}
