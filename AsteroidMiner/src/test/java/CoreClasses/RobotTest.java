package CoreClasses;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

public class RobotTest {

    // Creating the testing environment
    static Controller c = new Controller();
    static Robot R;

    @Before
    public void setUpTest() {
        String[] names = { "samer" };
        c.startGame(names);
        R = new Robot(Controller.asteroids.get(4));
        c.robots.add(R);
    }

    // Creating A Robot Test
    @Test
    public void CreateTest() {

        // Crafting Robot with not enough minerals
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

        // Crafting Robot with not enough minerals
        assertFalse("Fail: Could not create the robot", S.checkRequiredMaterial(0)); // 0 is robot
        Iron I = new Iron();
        Asteroid A3 = new Asteroid(99, (Mineral) I, 1);
        S.setAsteroid(A3);
        S.drill();
        S.mine();
        S.craft(0, c);
        assertTrue("Success: Settler S1 created a robot", c.robots.size() == 2);
    }

    // Traveling The Robot Testing
    @Test
    public void TravelTest() {
        // current asteroid is 4 from the setup
        int nextAsteroid = R.getCurrentAstroid().getID() + 1; // 5
        R.travel(Controller.asteroids.get(nextAsteroid));
        assertTrue("Success: Robot R1 moved to asteroid A" + nextAsteroid, R.currentAsteroid.getID() == 5);
    }

    // Drilling The Asteroid By The Robot Test
    // Self Explainatory
    @Test
    public void DrillTest() {
        assertTrue("Fail: Asteroid is not drilled", R.currentAsteroid.getDepth() == 0);
        R.drill();
        assertTrue("Success: Asteroid is drilled", R.currentAsteroid.getDepth() == R.currentAsteroid.radius);
    }

    // Hiding The Robot Inside Asteroids Test
    @Test
    public void HideTest() {
        // Trying to Hide Inside Undrilled Filled Asteroid
        Carbon C = new Carbon();
        Asteroid A1 = new Asteroid(99, C, 9);
        Asteroid A2 = new Asteroid(98, 9); // hollow
        R.travel(A1);
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not drilled through", R.hidden);
        // Trying to Hide Inside Drilled Filled Asteroid
        R.drill();
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not hollow", R.hidden);
        // Trying to Hide Inside Undrilled Hollow Asteroid
        R.travel(A2);
        R.hide();
        assertFalse("Success: Robot cannot hide because the asteroid is not drilled through", R.hidden);
        // Trying to Hide Inside Drilled Hollow Asteroid
        R.drill();
        R.hide();
        assertTrue("Success: Robot is hiding", R.hidden);
        assertFalse("Success: Asteroid is not hollow anymore", R.getCurrentAstroid().hollow());
        // Unhiding
        R.unhide();
        assertFalse("Success: Robot is not hidint", R.hidden);
    }


    // Automatic Behavior
    @Test

    public void BehaviorTest() throws InterruptedException {
        Asteroid A1 = new Asteroid(98, 9); // Hollow Asteroid
        R.behave(A1);
        assertTrue("Success: Robot traveled to Asteroid" + A1.getID(), R.currentAsteroid.getID() == A1.getID());
        assertTrue("Success: Asteroid is drilled", A1.radius == A1.getDepth());
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
