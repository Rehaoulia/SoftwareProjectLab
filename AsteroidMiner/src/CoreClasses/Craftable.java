package CoreClasses;

public interface Craftable {
	//////////////////////////////////////////////////////////////////////
	//RequiredMinerals should be strings. having them as minerals does not make sense.
	//////////////////////////////////////////////////////////////////////
	String[] RequiredMinerals = {};
	Asteroid currentAsteroid = new Asteroid();
	
	boolean isBuilt = false;	//why is this useful? (maybe it should only be in the space station
	
	//returns the required materials of the craftable object
	String[] getRequiredMinerals ();//////////////////////////////////same
	//returns the current Asteroid of the object
	Asteroid getCurrentAstroid ();
	//sets the current asteroid of the object to a 
	void setAsteroid(Asteroid a);
	//checks if the mineral is required
	boolean checkMineral(Mineral mineral);
	
	//what does this function check????
	boolean checkStatus();
}
