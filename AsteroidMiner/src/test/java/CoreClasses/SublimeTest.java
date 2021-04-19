package CoreClasses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SublimeTest {

    static Controller c = new Controller();
    static Settler player;
    static SpaceStation S;
    static Asteroid a;

    @Before
    public void setUpTest() {
        String[] names = { "S1" };
        c.startGame(names);
        player = c.getSettlers().get(0);
        S = (SpaceStation) player.craft(2, c);
    }

    @Test
    public void SublimingTest() {
        setUpTest();
        int t = Controller.getSublimingAsteroids().get(0);
        Controller.asteroids.get(t).setPerihelion(false);
        c.Sublime(c, t);
        assertFalse("Success: WaterIce did not disappear from Asteroid", Controller.asteroids.get(t).hollow());
        Controller.asteroids.get(t).setPerihelion(true);
        c.Sublime(c, t);
        assertTrue("Success: WaterIce disappeared from Asteroid", Controller.asteroids.get(t).hollow());

    }
}
