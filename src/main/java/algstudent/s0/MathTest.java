package algstudent.s0;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests mathematical operations
 * @version 1.0
 */
public class MathTest {
	private static Logger log = LoggerFactory.getLogger(MathTest.class);
	private Math math;
	   
	/**
	 * Initializes each test case
	 */
    @Before
    public void initialize() {
        math = new Math();
    }

    /**
     * Adds two numbers correctly
     * The sum should be 5 since 2+3=5
     */
	@Test
	public void test2Plus3Equals5() {
		int sum = math.sum(2, 3);
		log.debug("The sum is " + sum);
		assertEquals("The operation was not correct", 5, sum);
	}

}
