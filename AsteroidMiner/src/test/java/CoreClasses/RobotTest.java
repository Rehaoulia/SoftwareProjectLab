package CoreClasses;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import static org.junit.jupiter.api.Test ;
import org.junit.Before;

import CoreClasses.Robot;
import CoreClasses.Asteroid;
import CoreClasses.Controller;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RobotTest 
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
    public void drillingTest(){

        
        int destID = 1;
        player.travel(c.asteroids.get(1));
       Asteroid cur = player.getAsteroid();
        player.travel(cur);
        assertTrue("Success: Settler S1 moved to asteroid A1" , destID == cur.getID() );
        
        for(int i = 1 ; i< cur.radius+1; i++){
            player.drill();
            assertTrue("Success: Depth of asteroid A1 is : "+ cur.getDepth() , i == cur.getDepth() );
        }
        assertTrue("Success: A1 is drilled fully", cur.radius == cur.getDepth());
    }



    @Test 
    public void miningTest(){
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

    @Test 
    public void fillingTest(){
        
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




    @Test
    public void gateTest()
    {
        player.travel(c.asteroids.get(1));
        player.getMatforGate();
        assertTrue("The mineral(s) added to the inventory of S1The mineral(s) added to the inventory of S1 ", player.getMinerals().size() == 4 );
        player.craftGate();
        assertTrue("Success: gates created ", player.getNumberOfGates() == 2 );
        player.putGate();
        assertTrue("Success: 1st gate deployed ", player.getNumberOfGates() == 1 );
        player.travel(c.asteroids.get(4));
        player.putGate();
        assertTrue("Success: 2nd gate deployed  ", player.getNumberOfGates() == 0 );

    }

    @Test
    public void teleportTest()
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
