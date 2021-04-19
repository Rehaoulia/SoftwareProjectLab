package CoreClasses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SettlerHidingTest {

    /**
     * Rigorous Test :-)
     */
    Controller c = new Controller();
    Settler player;

    @Before
    public void setUpTest() {
        String[] names = { "Tester" };
        c.startGame(names);
        player = c.settlers.get(0);
    }

    @Test
    public void HidingTest() {
        // Trying to Hide Inside Undrilled Filled Asteroid
        Carbon C = new Carbon();
        Asteroid A1 = new Asteroid(99, C, 1);
        Asteroid A2 = new Asteroid(98, 1); // hollow
        player.travel(A1);
        player.hide();
        assertFalse("Success: Settler cannot hide because the asteroid is not drilled through", player.hidden);
        // Trying to Hide Inside Drilled Filled Asteroid
        player.drill();
        player.hide();
        assertFalse("Success: Settler cannot hide because the asteroid is not hollow", player.hidden);
        // Trying to Hide Inside Undrilled Hollow Asteroid
        player.travel(A2);
        player.hide();
        assertFalse("Success: Settler cannot hide because the asteroid is not drilled through", player.hidden);
        // Trying to Hide Inside Drilled Hollow Asteroid
        player.drill();
        player.hide();
        assertTrue("Success: Settler is hiding", player.hidden);
        assertFalse("Success: Asteroid is not hollow anymore", player.getAsteroid().hollow());
        // Unhiding
        player.unhide();
        assertFalse("Success: Settler is not hidden", player.hidden);
    };

}