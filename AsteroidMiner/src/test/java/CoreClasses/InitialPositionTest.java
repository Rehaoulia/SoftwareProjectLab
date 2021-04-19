package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

//Testing if the Settler will get an initial asteroid when created
public class InitialPositionTest {
	static Controller c = new Controller();
	
	@Test
	public void InitPositionTest() {
		String[] names = {"S"};
		c.startGame(names); //Creates settler and initializes asteroids
		Settler set = c.settlers.get(0);
		assertTrue("Settler has an initial position", set.getAsteroid() != null); //checks if the settlers current asteroid is not null
	}
}
