package CoreClasses;

import static org.junit.Assert.assertTrue;
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
    static Robot R;
    public static void setUpTest(){
        String[] names = {"samer"};
        c.startGame(names);
        R = new Robot(c.asteroids.get(4));
    }

    @Test
    public void TravelTest()
    {
        setUpTest();
        int nextAsteroid = R.getCurrentAstroid().getID()+1;
        R.travel(c.asteroids.get(nextAsteroid));
        assertTrue("Success: Robot R1 moved to asteroid A"+nextAsteroid, nextAsteroid == 5);
        
    }
}
