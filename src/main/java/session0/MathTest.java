package session0;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathTest {
	private static Logger log = LoggerFactory.getLogger(MathTest.class);
	Math math;
	   
	/**
	 * Initializes the object to perform testsssss
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Chess Horse - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Chess Horse - Teardown");
	}
	
    @Before
    public void initialize() {
        math = new Math();
    }

	@Test
	public void test2Plus3Equals5() {
		assertEquals(5, math.sum(2, 3));
	}

}
