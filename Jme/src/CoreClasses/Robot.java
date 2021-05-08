package CoreClasses;

import View.Menu;
import java.util.ArrayList;
import java.io.IOException;

public class Robot extends Traveler implements Craftable {
	Asteroid currentAsteroid;

	// Constructor
	///////////////////////////////////////////////////////////////////////////////////
	// The Robot constructor should get the settler's current asteroid for the robot
	// to get its initial asteroid
	///////////////////////////////////////////////////////////////////////////////////
	public Robot(Asteroid settlerAsteroid) {
		setAsteroid(settlerAsteroid);
		// Controller.addRobot(this);
	}

	// makes the robot travel to the destination asteroid
	public void travel(Asteroid destination) {
		setAsteroid(destination);
		System.out.println("Robot R moved to asteroid A" + currentAsteroid.getID());
	}

	// Makes the robot drill its current asteroid
	@Override
	public void drill() {
		while (currentAsteroid.drillable()) {
			currentAsteroid.getsDrill();
		}
		// if (currentAsteroid.destroyed() == true) {
		// // travel(Controller.getClosestAsteroid(currentAsteroid)); //this function
		// does
		// // not exist yet
		// }
	}

	// describes the behavior of the robot (commented because some classes are
	// currently not implemented)
	// Actually this function doesn't make sense because it has a getGates function
	// which is not right
	// It should instead check if the Place is a teleportationGate or not
	// So getClosestAsteroid should become getClosestPlace and currentAsteroid
	// should become currentPlace instead
	public boolean behave(Asteroid ast) throws InterruptedException {
		// *travel(Controller.getClosestPlace(currentPlace));
		// if
		// (this.currentPlace.getClass().toString().equalsIgnoreCase("TeleportationGate"))
		// {
		// // teleport(currentPlace.pairedGate());

		// // currentPlace should be protected in Traveler
		// // currentAsteroid should not be in the Traveler
		// } else {
		System.out.println("robot is at asteroid  : " + currentAsteroid.getID());
		travel(ast);
		drill();
		hide();
		Thread.sleep(1000 - System.currentTimeMillis() % 1000);
		unhide();
		return true;
	}

	/* Overridden methods */

	// makes the robot hidden (commented because some classes are currently not
	// implemented)
	@Override
	public void hide() {
		if (currentAsteroid.getHide()) {
			setHidden(true);
			// currentAsteroid.getUnhide();
			// setHidden(false);
			// currentAsteroid.getsFill(null);
		}
	}

	// kills the robot (commented because some classes are currently not
	// implemented)
	@Override
	public void die() {
		// Controller.removeRobot(this); //this function does not exist yet
	}

	@Override
	public void unhide() {
		currentAsteroid.getUnhide();
		setHidden(false);
		currentAsteroid.getsFill(null);
	}

	/* craftable interface methods */
	public Asteroid getCurrentAstroid() {
		return currentAsteroid;
	}

	public void setAsteroid(Asteroid a) {
		currentAsteroid = a;
	}

	public void robotMenu(Asteroid ast) throws IOException, InterruptedException {
		ArrayList<String> menuItems = new ArrayList<String>();
		menuItems.add("Automatic");
		menuItems.add("Travel");
		menuItems.add("Drill");
		menuItems.add("Hide");
		menuItems.add("Unhide");
		menuItems.add("Teleport");
		Menu menu = new Menu(menuItems);
		switch (menu.display()) {
		case 0: // Automatic
			behave(ast);
			break;
		case 1: // Travel
			travel(ast);
			break;
		case 2: // Drill
			drill();
			break;
		case 3: // Hide
			hide();
			break;
		case 4: // Unhide
			unhide();
			break;
		case 5: // Teleport
			//teleport(null);
			break;
		default:
			System.out.println("you didn't choose any action");
		}
	}
}
