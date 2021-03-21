package CoreClasses;


public class WaterIce extends Mineral {

    private Asteroid currentAsteroid;
    Controller controller = new Controller();

    public void sublime() {
       if (currentAsteroid.radius == currentAsteroid.depth && currentAsteroid.isAphelion == false)

           controller.getSublimingAsteroids().add(currentAsteroid.getID());


    }}

