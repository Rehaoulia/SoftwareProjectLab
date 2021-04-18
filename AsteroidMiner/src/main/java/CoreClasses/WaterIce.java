package CoreClasses;


public class WaterIce extends Mineral {

    private Asteroid currentAsteroid;

    public void sublime() {
       if (currentAsteroid.radius == currentAsteroid.getDepth() && currentAsteroid.perihelion() == false) {
    	   Controller.updateAsteroid(this.currentAsteroid);
       }
    }
}

