package CoreClasses;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MovementTest {

	static Controller c = new Controller();
    static Settler S;
    static int nextAsteroid;
    
    public static void setUpTest(){
        String[] names = {"samer"};
        c.startGame(names);
        S  = c.getSettlers().get(0);
    }
    
    @Test
    public void TravelTest() {
    	setUpTest();
    	nextAsteroid =  S.getAsteroid().getID() + 1;
    	S.travel(Controller.asteroids.get(nextAsteroid));
    	assertTrue("S successfully moved to asteroid A"+nextAsteroid, S.getAsteroid().getID() == nextAsteroid);
    }
}
