package CoreClasses;


import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SettlerGateTest 
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
    public void GateTest()
    {
        player.travel(Controller.asteroids.get(1));
        player.getMatforGate();
        assertTrue("The mineral(s) added to the inventory of S1The mineral(s) added to the inventory of S1 ", player.getMinerals().size() == 4 );
        player.craftGate();
        assertTrue("Success: gates created ", player.getNumberOfGates() == 2 );
        player.putGate();
        assertTrue("Success: 1st gate deployed ", player.getNumberOfGates() == 1 );
        player.travel(Controller.asteroids.get(4));
        player.putGate();
        assertTrue("Success: 2nd gate deployed  ", player.getNumberOfGates() == 0 );

    }

}