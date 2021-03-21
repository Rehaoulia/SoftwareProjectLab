package CoreClasses;

public interface Craftable {
	Asteroid currentAsteroid = null;
	
	//returns the current Asteroid of the object
	Asteroid getCurrentAstroid ();
	//sets the current asteroid of the object to a 
	void setAsteroid(Asteroid a);
}
