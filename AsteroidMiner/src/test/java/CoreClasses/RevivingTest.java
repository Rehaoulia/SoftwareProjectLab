/**************************************************************/
//This Junit test file is for testing the revival of a dying settler*/
//Does the settler get revived successfully?
/**************************************************************/

package CoreClasses;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

public class RevivingTest {
	static Controller c = new Controller();
	
	@Before
	public void setup() {
		//adding the settlers
        String[] names = {"S1","S2"};
        c.startGame(names);
	}
	
	@Test
	public void settlerRevivesOtherSettler() {
        c.settlers.get(0).dying(c);
        c.settlers.get(1).revive(c.settlers.get(0));

		assertTrue("Success: hidingSettler survived", c.settlers.get(0).getDeath()==false);
	}//fails because we did not implement the threads yet
}

