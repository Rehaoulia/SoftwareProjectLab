package CoreClasses;

public class Uranium extends Mineral {
    private Asteroid currentAsteroid;
    
    public void explode() {
        if (currentAsteroid.radius == currentAsteroid.getDepth() && !currentAsteroid.perihelion()) {
            currentAsteroid.destroyed();
            Controller.removeExplodingAsteroid(this.currentAsteroid.getID());
            Controller.updateAsteroid(this.currentAsteroid);
        }
    }
}

