package CoreClasses;


import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SettlerMiningTest 
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
    public void MiningTest(){
        Mineral m = new Iron();
       Asteroid ironCore = new Asteroid(0,m,1) ;
        player.travel(ironCore);
        Asteroid cur = player.getAsteroid();
        player.drill();
        assertTrue(" Success: A3 is hollow and drilled through.", cur.radius == cur.getDepth() );
        player.mine();
        ArrayList<String> x = player.getMinerals();
        assertTrue("Success: player mined  ", x.contains(m.getClass().getSimpleName() ));
        assertTrue("Success: asteroid is not hollow ", cur.hollow() );
    };
}