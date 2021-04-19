package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

//Test for the radioactive asteroids explosion
public class ExplodeTest {

    static Controller c = new Controller();
    static Settler player;
    static SpaceStation S;
    static Asteroid a;

    @Before
    public void setUpTest() {
        String[] names = { "S1" };
        c.startGame(names); //creates settler and initializes environment
        player = c.getSettlers().get(0);
    }

    @Test
    public void ExplodingTest() {
        int t = Controller.getExplodingAsteroids().get(0); //gets the id of a radioactive asteroid
        c.getSettlers().get(0).setAsteroid(Controller.asteroids.get(t)); //travel the settler to the radioactive asteroid
        Controller.asteroids.get(t).setPerihelion(true); //set the asteroid at perihilion

        c.Explode(c, t);
        assertTrue("The asteroid " + t + "explode successfully ", c.settlers.get(0).getDeath() ); //check if the settler dies after the explosion
    }
}
