package CoreClasses;

public class Robot extends Traveler implements Craftable{
	Asteroid currentAsteroid;
	
	//Constructor
	///////////////////////////////////////////////////////////////////////////////////
	//The Robot constructor should get the settler's current asteroid for the robot to get its initial asteroid
	///////////////////////////////////////////////////////////////////////////////////
	public Robot(Asteroid settlerAsteroid) {
		setAsteroid(settlerAsteroid);
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
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Asteroid should know its closest asteroid because there is no other way for the robot to know it
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*travel(currentAsteroid.getClosestAsteroid());
		if(currentAsteroid.getGates().length()==0) {
			drill();
			hide();
		}
		else {
			teleport(currentAsteroid.getPair()); //currentAsteroid should be currentPlace instead. An asteroid doesn't have a pair
		}*/
		return true;
	}
	
	//makes the robot hidden (commented because some classes are currently not implemented)
	@Override
	public void hide() {
		/*if((currentAsteroid.isHollow())&&(currentAsteroid.depth==currentAsteroid.radius)) {
			setHidden(true);
			currentAsteroid.getsFill(this); //should we make a getHide?
			sleep(10);
			wait(10000);
			setHidden(false);
			currentAsteroid.getsFill(null); 
		}*/
	}

	//kills the robot (commented because some classes are currently not implemented)
	public void dying() {
		//Controller.removeRobot(this); //what do you think?
	}

	/*craftable interface methods*/
	public Asteroid getCurrentAstroid() {
		return currentAsteroid;
	}
	public void setAsteroid(Asteroid a) {
		currentAsteroid = a;
	}
}
