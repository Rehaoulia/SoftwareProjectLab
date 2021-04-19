package CoreClasses;


import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SettlerDrillingTest 
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
    public void DrillingTest(){

        
        int destID = 1;
        player.travel(Controller.asteroids.get(1));
       Asteroid cur = player.getAsteroid();
        player.travel(cur);
        assertTrue("Success: Settler S1 moved to asteroid A1" , destID == cur.getID() );
        
        for(int i = 1 ; i< cur.radius+1; i++){
            player.drill();
            assertTrue("Success: Depth of asteroid A1 is : "+ cur.getDepth() , i == cur.getDepth() );
        }
        assertTrue("Success: A1 is drilled fully", cur.radius == cur.getDepth());
    }
}