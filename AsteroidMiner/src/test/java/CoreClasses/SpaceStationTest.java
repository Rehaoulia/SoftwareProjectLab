package CoreClasses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

//Tests for both the creation and build of the spacestation
public class SpaceStationTest {
	static Controller c = new Controller();
	static SpaceStation S;
	static Settler player;

	@Before
	public void setupTest() {
		String[] names = { "S" };
		c.startGame(names); //creates settler and initializes environment
		player = c.getSettlers().get(0);
		S = (SpaceStation) player.craft(2, c); //starts the build of spacestation from this point settlers can add minerlas to the spacestation until it's built
	}

	@Test
	public void CreationTest() {
		// spacestation and settler are on the same asteroid
		assertTrue("SpaceStation building successfully started",
				player.getAsteroid().getID() == S.getCurrentAstroid().getID());
	}

	@Test
	public void BuildTest() {
		assertFalse("SpaceStation sucessfully built", c.getWin()); // no materials
		for (int i = 0; i < 2; i++) {
			S.addResource("WaterIce");
			S.addResource("Carbon");
			S.addResource("Uranium");
			S.addResource("Iron");
		}
		assertFalse("SpaceStation sucessfully built", c.getWin()); // few materials
		S.addResource("WaterIce");
		S.addResource("Carbon");
		S.addResource("Uranium");
		S.addResource("Iron");
		c.addSpaceStation(S);
		c.checkSpaceStation(S.getID());
		assertTrue("SpaceStation sucessfully built", c.getWin() == true); // all materials
	}

}
