package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

//Test if the settler and asteroids have been created properly
public class InitializationTest {
	static Controller c = new Controller();
	static int numberofSettlers = 2;
	static int numberofAsteroids;
	
	@Test
	public void PlayersCreated() {
		String[] names = {"S1", "S2"};
		c.startGame(names); //Creates two players and initializes environment
		assertTrue("Players have been sucessfully created", numberofSettlers == c.getSettlers().size()); //checks if the two players have been created
	}
	
	@Test
	public void AsteroidsCreated() {
		c.setupGame(); //creates asteroids
		numberofAsteroids = Controller.asteroids.size(); //gets the number of created asteroids from controller class
		assertTrue(numberofAsteroids + " Asteroids Created", numberofAsteroids >= 40 && numberofAsteroids <= 50); //checks if  the number of created asteroids is between 40 and 50
	}
}
