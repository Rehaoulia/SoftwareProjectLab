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
public class FillingTest 
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
    public void FillingTest(){
        
       Asteroid hallow = new Asteroid(0,1) ;
        player.travel(hallow);
        Asteroid cur = player.getAsteroid();
        player.drill();
        assertTrue(" Success: A3 is hollow and drilled through.", cur.radius == cur.getDepth() && cur.hollow() );
        
        player.addMineral("Uranium");
        assertTrue(" Success: Uranium is added to the inventory of S1 ", player.getMinerals().size() == 1 );
        player.fill("Uranium");
        assertTrue(" Success: Uranium is added to the hallow asteroid ", !cur.hollow() );
        assertTrue(" Success: Uranium is removed from S1 inventory ", player.getMinerals().size() == 0 );
    };

}