package CoreClasses;


import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TeleportGateTest 
{
    
    /**
     * Rigorous Test :-)
     */
    static Controller c = new Controller();
    static Settler player;

    @Before
    public  void setUpTest(){
        String[] names = {"Tester"};
        c.startGame(names);
        player = c.settlers.get(0);
    }

    @Test
    public void TeleportTest()
    {
        player.travel(Controller.asteroids.get(1));
        player.getMatforGate();
        player.craftGate();
        player.putGate();
        player.travel(Controller.asteroids.get(4));
        player.putGate();
        player.teleport(player.gates.get(1));
        assertTrue("Success: Player teleported ", player.getAsteroid().getID() == 1);
    }
}
