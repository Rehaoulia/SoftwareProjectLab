package CoreClasses;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Test for the travel functionality of the settler
public class MovementTest {

	static Controller c = new Controller(); 
    static Settler S;
    static int nextAsteroid;
    
    public static void setUpTest(){
        String[] names = {"S"}; 
        c.startGame(names); //creates a new settler and initializes environment
        S  = c.getSettlers().get(0);
    }
    
    @Test
    public void TravelTest() {
    	setUpTest();
    	nextAsteroid =  S.getAsteroid().getID() + 1; //assigns the next asteroids
    	S.travel(Controller.asteroids.get(nextAsteroid)); //travels the settler to the next asteroid
    	assertTrue("S successfully moved to asteroid A"+nextAsteroid, S.getAsteroid().getID() == nextAsteroid); // verifies if the settler's current asteroid after traveling is the same as next asteroid
    }
}
