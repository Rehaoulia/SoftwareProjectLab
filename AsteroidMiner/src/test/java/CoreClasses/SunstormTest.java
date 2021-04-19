/**************************************************************/
//This Junit test file is for testing the sunstorm*/
//Does it happen as expected?
//Does the alarm get displayed?
//Does the sunstorm kill the unhidden travelers?
/**************************************************************/

package CoreClasses;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

public class SunstormTest {
	static Controller c = new Controller();
    Sunstorm s = new Sunstorm();
	
	static Robot r1,r2;
	int numOfSettlers, numOfRobots;
	
	@Before
	public void setup() {
		//adding the settlers
        String[] names = {"S1","S2"};
        c.startGame(names);

		//adding the robots
        r1 = new Robot(c.settlers.get(0).getAsteroid());
        r2 = new Robot(c.settlers.get(0).getAsteroid());
		c.addRobot(r1);
		c.addRobot(r2);

		//making the asteroid hollow and completely drilled through
        r1.drill();
        c.settlers.get(0).mine();

		//setting the initial number of settlers and robots
		numOfSettlers = c.settlers.size();
		numOfRobots = c.robots.size();
	}
	
	@Test
	public void sunstormKillsUnhiddenSettlers() {
		c.settlers.get(0).hide();	//S1 is hiding

		//s.makeItHappen(c);		//make the sunstorm instantly
		s.behave(c,18000);			//make the sunstorm behave (with alarm)

		assertTrue("Success: hidingSettler survived", c.settlers.get(0).getDeath()==false);
        assertTrue("Success: unhiddenSettler is dead",  c.settlers.size()==numOfSettlers-1);
	}//the behave function should check if the wavelength is greater than 15000

	@Test
	public void sunstormKillsRobots() {
		//hide 1 robot (r1)
		c.robots.get(0).getCurrentAstroid();
		c.robots.get(0).hide();
		
		//s.makeItHappen(c);		//make the sunstorm instantly
		s.behave(c,18000);			//make the sunstorm behave (with alarm)
		
        assertTrue("Success: 1 robot is dead", c.robots.size()==numOfRobots-1);
	}//this means there is a problem with the dying function of the robots
}
