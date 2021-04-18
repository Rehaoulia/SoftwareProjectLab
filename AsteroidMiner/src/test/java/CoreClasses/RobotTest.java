package CoreClasses;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class RobotTest {

    /**
     * Rigorous Test :-)
     */
    static Controller c = new Controller();
    static Robot R;

    @Before
    public void setUpTest() {
        String[] names = { "samer" };
        c.startGame(names);
        R = new Robot(c.asteroids.get(4));
        c.robots.add(R);
    }

    @Test
    public void CreateTest() {
        Settler S = new Settler("samer", 99);
        Carbon C = new Carbon();
        Asteroid A1 = new Asteroid(99, (Mineral) C, 1);
        S.setAsteroid(A1);
        S.drill();
        S.mine();
        Uranium U = new Uranium();
        Asteroid A2 = new Asteroid(99, (Mineral) U, 1);
        S.setAsteroid(A2);
        S.drill();
        S.mine();
        assertFalse("Fail: Could not create the robot", S.checkRequiredMaterial(0)); // 0 is robot
        Iron I = new Iron();
        Asteroid A3 = new Asteroid(99, (Mineral) I, 1);
        S.setAsteroid(A3);
        S.drill();
        S.mine();
        S.craft(0, c);
        assertTrue("Success: Settler S1 created a robot", c.robots.size() == 2);

    }

    @Test
    public void TravelTest() {
        int nextAsteroid = R.getCurrentAstroid().getID() + 1;
        R.travel(c.asteroids.get(nextAsteroid));
        assertTrue("Success: Robot R1 moved to asteroid A" + nextAsteroid, nextAsteroid == 5);
    }

    @Test
    public void DrillTest() {
        assertTrue("Fail: Asteroid is not drilled", R.currentAsteroid.getDepth() == 0);
        R.drill();
        assertTrue("Success: Asteroid is drilled", R.currentAsteroid.getDepth() == R.currentAsteroid.radius);
    }

    @Test
    public void HideTest() {
        Carbon C = new Carbon();
        Asteroid A1 = new Asteroid(99, C, 9);
        Asteroid A2 = new Asteroid(98, 9); // hollow
        R.travel(A1);
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not drilled through", R.hidden);
        R.drill();
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not hollow", R.hidden);
        R.travel(A2);
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not drilled through", R.hidden);
        R.drill();
        R.hide();
        assertTrue("Success: Robot is hiding", R.hidden);
        assertFalse("Success: Asteroid is not hollow anymore", R.getCurrentAstroid().hollow());
    }

    @Test
    public void BehaviorTest() throws InterruptedException {
        Asteroid A1 = new Asteroid(98, 9); // hollow
        R.behave(A1);
        assertTrue("Success: Robot traveled to Asteroid" + A1.getID(), R.currentAsteroid.getID() == A1.getID());
        assertTrue("Success: Asteroid is drilled", A1.radius == A1.getDepth());
    }
}
