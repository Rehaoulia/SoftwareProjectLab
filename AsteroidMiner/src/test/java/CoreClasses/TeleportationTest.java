package CoreClasses;


import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TeleportationTest 
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
    public void TeleportationTest()
    {
        player.travel(Controller.asteroids.get(1)); // player travels to an asteroid
        player.getMatforGate();                      // player gets matterials required for crafting Teleportation Gate
        player.craftGate();                             // crafting gate
        player.putGate();
        player.travel(Controller.asteroids.get(4));     // traveling to another asteroid
        player.putGate();
        player.teleport(player.gates.get(1));           //teleporting from gate two to its paired gate 
        assertTrue("Success: Player teleported ", player.getAsteroid().getID() == 1);   // current asteroid is nearby asteroid for second gate
    }
}
