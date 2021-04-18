package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SpaceStationTest {
	static Controller c = new Controller();
	static SpaceStation S;
	static Settler Settler;
	
	public static void setupTest() {
		String[] names = {"S"};
		c.startGame(names);
		Settler = c.getSettlers().get(0);
		S = (SpaceStation)Settler.craft(2 , c);
	}
	@Test
	public void CreationTest() {
		setupTest();
		assertTrue("SpaceStation building successfully started", Settler.getAsteroid().getID() == S.getCurrentAstroid().getID());
	}
	
	@Test
	public void BuildTest() {
		setupTest();
		for(int i=0; i<3; i++) {
			S.addResource("WaterIce");
			S.addResource("Carbon");
			S.addResource("Uranium");
			S.addResource("Iron");
		}
		c.addSpaceStation(S);
		c.checkSpaceStation(S.getID());
		assertTrue("SpaceStation sucessfully built", c.getWin()==true);
	}
	
	
}


