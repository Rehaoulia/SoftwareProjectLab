/**************************************************************/
//This Junit test file is for testing the losing condition*/
//Does the game end after losing?
//Does the game end when all settlers are dead?
//Does the game end when only 1 settler is dead?
/**************************************************************/

package CoreClasses;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

public class LosingTest {
	static Controller c = new Controller();
	
	@Before
	public void setup() {
		//adding the settlers
        String[] names = {"S1","S2"};
        c.startGame(names);
	}
	
	@Test
	public void gameContinuesWithOneSettler() {
        c.settlers.get(0).die(); //kills the first settler
		assertTrue("Success: game keeps going", c.getGameOver()==false); //check the game ends or not
	}

    @Test
	public void gameEndsWhenSettlersDie() {
	//kills both settlers
        c.settlers.get(0).dying(c);
        c.settlers.get(0).dying(c);
		assertTrue("Success: players lost the game", c.getGameOver()==true && c.getWin()==false); //check if the game ends and the status is a lose
	}
}
