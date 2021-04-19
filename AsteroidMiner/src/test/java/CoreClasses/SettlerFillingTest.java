package CoreClasses;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SettlerFillingTest 
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
        
       Asteroid hallow = new Asteroid(0,1) ;   // a test hallow asteroid with radius 1 created
        player.travel(hallow);                       //travelling to hallow asteroid
        Asteroid cur = player.getAsteroid();
        player.drill();                             // drilling asteroid
        assertTrue(" Success: A3 is hollow and drilled through.", cur.radius == cur.getDepth() && cur.hollow() );  
        
        player.addMineral("Uranium");             // mineral to fill in added 
        assertTrue(" Success: Uranium is added to the inventory of S1 ", player.getMinerals().size() == 1 );
        player.fill("Uranium");                        // filling material
        assertTrue(" Success: Uranium is added to the hallow asteroid ", !cur.hollow() );
        assertTrue(" Success: Uranium is removed from S1 inventory ", player.getMinerals().size() == 0 );
    };

}