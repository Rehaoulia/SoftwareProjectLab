package CoreClasses;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WinningTest {
    static Controller c = new Controller();
    static Robot R;
    static Asteroid a;
    static SpaceStation S;
    static Settler Settler;

    @Before
    public void setUpTest() {
        String[] names = { "S1" };
        c.startGame(names);
        Settler = c.getSettlers().get(0);
        S = (SpaceStation) Settler.craft(2, c);

    }

    @Test
    public void winGameTest() {
        c.checkSpaceStation(S.getID());
        assertTrue("Success: The players have won the game", c.getWin());
    }
}
