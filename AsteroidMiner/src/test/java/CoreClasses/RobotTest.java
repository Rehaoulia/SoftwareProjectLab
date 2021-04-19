package CoreClasses;

package CoreClasses;

import static org.junit.Assert.assertTrue;
import CoreClasses.Robot;
import CoreClasses.Asteroid;
import CoreClasses.Controller;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RobotTest {

    /**
     * Rigorous Test :-)
     */
    static Controller c = new Controller();
    static Robot R;
    static Asteroid a;
    static SpaceStation S;
    static Settler Settler;

    public static void setUpTest() {
        String[] names = { "S1" };
        c.startGame(names);
        Settler = c.getSettlers().get(0);
        S = (SpaceStation) Settler.craft(2, c);

    }

    @Test
    public void SublimeTest() {
        setUpTest();
        int t = Controller.getSublimingAsteroids().get(0);
        Controller.asteroids.get(t).setPerihelion(true);
        c.Sublime(c, t);
        assertTrue("Water Ice sublimed successfully", Controller.asteroids.get(t).hollow() == true);

    }

    @Test
    public void ExplodeTest() {
        setUpTest();
        int t = Controller.getExplodingAsteroids().get(0);
        Controller.asteroids.get(t).setPerihelion(true);
        c.Explode(c, t);
        assertTrue("The asteroid " + t + "explode successfully ", c.settlers.get(0).getDeath() == true);

    }

    @Test
    public void winGameTest() {
        setUpTest();
        c.checkSpaceStation(S.getID());
        assertTrue("you won ", c.getWin() == true);

    }

    @Test
    public void SettlerHidingTest() {
        setUpTest();
        c.settlers.get(0).hide();
        assertTrue("you are hidden' ", c.settlers.get(0).hidden);

    }

}
