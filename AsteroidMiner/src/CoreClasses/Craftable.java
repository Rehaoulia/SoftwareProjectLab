package CoreClasses;

public interface Craftable {
	//////////////////////////////////////////////////////////////////////
	//RequiredMinerals should be strings. having them as minerals does not make sense.
	//////////////////////////////////////////////////////////////////////
	Asteroid currentAsteroid = new Asteroid();
	
	//returns the current Asteroid of the object
	Asteroid getCurrentAstroid ();
	//sets the current asteroid of the object to a 
	void setAsteroid(Asteroid a);
}
