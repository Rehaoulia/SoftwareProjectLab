package CoreClasses;

import com.jme3.math.Vector3f;

public class Robot extends Traveler implements Craftable{
	Asteroid currentAsteroid;
	
	//Constructor
	///////////////////////////////////////////////////////////////////////////////////
	//The Robot constructor should get the settler's current asteroid for the robot to get its initial asteroid
	///////////////////////////////////////////////////////////////////////////////////
	public Robot(Asteroid settlerAsteroid,Vector3f loc) {
                super(loc);
		currentAsteroid=settlerAsteroid;
		//Controller.addRobot(this);
	}
	
	//makes the robot travel to the destination asteroid
	public void travel(Asteroid destination) {
		setAsteroid(destination);
	}
	
	//Makes the robot drill its current asteroid
	@Override
	public void drill() {
		currentAsteroid.getsDrill();
		if(currentAsteroid.destroyed()==true) {
			//travel(Controller.getClosestAsteroid(currentAsteroid));	//this function does not exist yet
		}
	}
	
	//describes the behavior of the robot (commented because some classes are currently not implemented)
	//Actually this function doesn't make sense because it has a getGates function which is not right
	//It should instead check if the Place is a teleportationGate or not
	//So getClosestAsteroid should become getClosestPlace and currentAsteroid should become currentPlace instead
	public boolean behave() {
		//*travel(Controller.getClosestPlace(currentPlace));
		if(this.currentPlace.getClass().toString().equalsIgnoreCase("TeleportationGate")) {
			//teleport(currentPlace.pairedGate());
			
			//currentPlace should be protected in Traveler
			//currentAsteroid should not be in the Traveler
		}else {
			drill();
			hide();
		}
		return true;
	}
	
	/*Overridden methods*/
	
	//makes the robot hidden (commented because some classes are currently not implemented)
	@Override
	public void hide() {
		try {
			if((currentAsteroid.hollow())&&(currentAsteroid.getDepth()==currentAsteroid.radius)) {//depth is private
				setHidden(true);
				currentAsteroid.getHide();
				wait(10000);
				currentAsteroid.getUnhide();
				setHidden(false);
				currentAsteroid.getsFill(null); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//kills the robot (commented because some classes are currently not implemented)
	@Override
	public void die() {
		//Controller.removeRobot(this); //this function does not exist yet
	}

	/*craftable interface methods*/
	public Asteroid getCurrentAstroid() {
		return currentAsteroid;
	}
	public void setAsteroid(Asteroid a) {
		currentAsteroid = a;
	}
}

