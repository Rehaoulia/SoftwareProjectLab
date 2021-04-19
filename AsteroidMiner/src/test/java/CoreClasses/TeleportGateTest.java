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
    public void TeleportGateTest()
    {
        player.travel(Controller.asteroids.get(1));  // player travels to an asteroid
        player.getMatforGate();                      // player gets matterials required for crafting Teleportation Gate
        assertTrue("The mineral(s) added to the inventory of S1The mineral(s) added to the inventory of S1 ", player.getMinerals().size() == 4 );
        player.craftGate();                             // crafting gate
        assertTrue("Success: gates created ", player.getNumberOfGates() == 2 );
        player.putGate();
        assertTrue("Success: 1st gate deployed ", player.getNumberOfGates() == 1 );
        player.travel(Controller.asteroids.get(4));    // traveling to another asteroid
        player.putGate();
        assertTrue("Success: 2nd gate deployed  ", player.getNumberOfGates() == 0 );

    }

}