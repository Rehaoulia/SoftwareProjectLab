package CoreClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ExplodeTest {

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
    public void ExplodingTest() {
        int t = Controller.getExplodingAsteroids().get(0);
        c.getSettlers().get(0).setAsteroid(Controller.asteroids.get(t));
        Controller.asteroids.get(t).setPerihelion(true);

        c.Explode(c, t);
        assertTrue("The asteroid " + t + "explode successfully ", c.settlers.get(0).getDeath() );
    }
}
