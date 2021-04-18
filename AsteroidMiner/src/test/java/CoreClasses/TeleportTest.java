package CoreClasses;


import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import CoreClasses.Robot;
import CoreClasses.Asteroid;
import CoreClasses.Controller;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TeleportTest 
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
        player.travel(c.asteroids.get(1));
        player.getMatforGate();
        player.craftGate();
        player.putGate();
        player.travel(c.asteroids.get(4));
        player.putGate();
        player.teleport(player.gates.get(1));
        assertTrue("Success: player teleported ", player.getAsteroid().getID() == 1);

    }
}
