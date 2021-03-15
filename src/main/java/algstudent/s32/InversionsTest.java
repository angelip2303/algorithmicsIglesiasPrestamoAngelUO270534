package algstudent.s32;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class InversionsTest {

	@Test
	public void test() {
		List<Integer> list1 = Arrays.asList(new Integer[] {1, 3, 2, 4, 5});
		List<Integer> list2 = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10});
		List<Integer> list3 = Arrays.asList(new Integer[] {5, 4, 2, 3, 1});
		List<Integer> list4 = Arrays.asList(new Integer[] {5, 4, 2, 3, 1, 6, 9, 15, 11, 12, 13, 14, 7, 8, 10});
		
		Inversions inv1 = new Inversions(new ArrayList<Integer>(list1));
		Inversions inv2 = new Inversions(new ArrayList<Integer>(list2));
		Inversions inv3 = new Inversions(new ArrayList<Integer>(list3));
		Inversions inv4 = new Inversions(new ArrayList<Integer>(list4));
		
		InversionsParallel invParalle1 = new InversionsParallel(new ArrayList<Integer>(list1), 0, list1.size());
		InversionsParallel invParalle2 = new InversionsParallel(new ArrayList<Integer>(list2), 0, list2.size());
		InversionsParallel invParalle3 = new InversionsParallel(new ArrayList<Integer>(list3), 0, list3.size());
		InversionsParallel invParalle4 = new InversionsParallel(new ArrayList<Integer>(list4), 0, list4.size());
		
		InversionsQuadratic invQuadratic1 = new InversionsQuadratic(new ArrayList<Integer>(list1));
		InversionsQuadratic invQuadratic2 = new InversionsQuadratic(new ArrayList<Integer>(list2));
		InversionsQuadratic invQuadratic3 = new InversionsQuadratic(new ArrayList<Integer>(list3));
		InversionsQuadratic invQuadratic4 = new InversionsQuadratic(new ArrayList<Integer>(list4));
		
		assertEquals("1",  inv1.start());
		assertEquals("0",  inv2.start());
		assertEquals("9",  inv3.start());
		assertEquals("30", inv4.start());
		
		assertEquals("1",  invParalle1.start()); 
		assertEquals("0",  invParalle2.start());
		assertEquals("9",  invParalle3.start());
		assertEquals("30", invParalle4.start());
		
		assertEquals("1",  invQuadratic1.start());
		assertEquals("0",  invQuadratic2.start());
		assertEquals("9",  invQuadratic3.start());
		assertEquals("30", invQuadratic4.start());
	}

}
