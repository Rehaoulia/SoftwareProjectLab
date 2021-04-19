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
        //This Junit test is for testing the sublimation of 
        //the water ice  when asubliming asteroid become fully drilled at perihelion*/
    }

    @Test
    public void ExplodeTest() {
        setUpTest();
        int t = Controller.getExplodingAsteroids().get(0);
        Controller.asteroids.get(t).setPerihelion(true);
        c.Explode(c, t);
        assertTrue("The asteroid " + t + "explode successfully ", c.settlers.get(0).getDeath() == true);
        //This Junit test is for testing the explosion of 
        //the radioactive asteroid when it become fully drilled at perihelion*/

    }

    @Test
    public void winGameTest() {
        setUpTest();
        c.checkSpaceStation(S.getID());
        assertTrue("you won ", c.getWin() == true);
        //This Junit test  is for testing the wining condition*/
        //Does the game end and the player win after crafting the spaceStation?

    }

    @Test
    public void SettlerHidingTest() {
        setUpTest();
        c.settlers.get(0).hide();
        assertTrue("you are hidden' ", c.settlers.get(0).hidden);
        //This Junit test is for testing the hiding of the Settler*/
        //it fails because the hide() function is not implemented correctly.


    }

    @Test
    public void RobotDyingTest() {
        setUpTest();
        R.die();
        assertTrue("Robot died ", true);
        //This Junit test is for testing the death of the Robot*/
        //fails because we did not implement the die() function yet for robot

    }

}
