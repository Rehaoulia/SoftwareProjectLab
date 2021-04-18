package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class InitializationTest {
	static Controller c = new Controller();
	static int numberofSettlers = 2;
	static int numberofAsteroids;
/*	public static void setupTest() {
		String[] names = {"S1", "S2"};
		c.startGame(names);
		numberofSettlers = c.getSettlers().size();
	}*/
	
	@Test
	public void PlayersCreated() {
		String[] names = {"S1", "S2"};
		c.startGame(names);
		assertTrue("Players have been sucessfully created", numberofSettlers == c.getSettlers().size());
	}
	
	@Test
	public void AsteroidsCreated() {
		c.setupGame();
		numberofAsteroids = c.asteroids.size();
		assertTrue(numberofAsteroids + " Asteroids Created", numberofAsteroids >= 40 && numberofAsteroids <= 50);
	}
}
