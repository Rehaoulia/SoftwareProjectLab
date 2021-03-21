package CoreClasses;

public class Robot implements Craftable{
    String[] RequiredMinerals = {"Iron","Carbon","Uranium"}; //Again this should be an array of strings
    Asteroid currentAsteroid = new Asteroid();

    //Constructor
    ///////////////////////////////////////////////////////////////////////////////////
    //The Robot constructor should get the settler's current asteroid for the robot to get its initial asteroid
    ///////////////////////////////////////////////////////////////////////////////////
    public Robot(Asteroid settlerAsteroid) {
        setAsteroid(settlerAsteroid);
    }

    public Robot() {

    }

    //makes the robot travel to the destination asteroid
    public void travel(Asteroid destination) {
        setAsteroid(destination);
    }

    //Makes the robot drill its current asteroid (commented because the asteroid class is currently not implemented
    public void drill() {
		/*
		currentAsteroid.getsDrill();
		if((currentAsteroid.isDrillable==false)&&(currentAsteroid.radioactive()==true)&&(currentAsteroid.aphelion()==false)) {
			currentAsteroid.explode(); //explode is supposed to tell the robot to travel to a close destination
		}
		*/
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
    private void hide() {
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
    public String[] getRequiredMinerals() {
        return RequiredMinerals;
    }
    public Asteroid getCurrentAstroid() {
        return currentAsteroid;
    }
    public void setAsteroid(Asteroid a) {
        currentAsteroid = a;
    }
    public boolean checkMineral(Mineral mineral) {
        for(int i=0; i<RequiredMinerals.length;i++) {
            if(RequiredMinerals[i].equalsIgnoreCase(mineral.getClass().toString())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkStatus() {
        return false;
    }
}