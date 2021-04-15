package algstudent.passwords;

import java.util.List;
import org.junit.Test;

public class PasswordGeneratorTest {
	@Test
	public void test() {
		int numberOfTotalCharacters = 15;
		int numberOfNonLettersEnds = 2;
		int numberOfPasswords = 9;
		String consonantPairsPath = "src/main/java/algstudent/passwords/consonant_pairs.txt"; 
		
		PasswordGenerator generator = new PasswordGenerator(
				numberOfTotalCharacters,
				numberOfNonLettersEnds,
				numberOfPasswords,
				consonantPairsPath);
	
		generator.generate();
		
		List<String> passwords = generator.getPasswords();	
		
		for (int i = 0; i < numberOfPasswords; i++) {			
			String password = passwords.get(i);
			System.out.println((i+1) + ": " + password);
		}
	}
	
}
