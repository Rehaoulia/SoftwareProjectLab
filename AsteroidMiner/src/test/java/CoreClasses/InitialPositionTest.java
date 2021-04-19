package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class InitialPositionTest {
	static Controller c = new Controller();
	
	@Test
	public void InitPositionTest() {
		String[] names = {"S"};
		c.startGame(names);
		Settler set = c.settlers.get(0);
		assertTrue("Settler has an initial position", set.getAsteroid() != null);
	}
}
