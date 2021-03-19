package CoreClasses;

public class Uranium extends Mineral {
    private Asteroid currentAsteroid;
    Controller controller = new Controller();

    public void explode() {
        if (currentAsteroid.radius == currentAsteroid.depth && currentAsteroid.isAphelion == false) {
            controller.explodingAsteroids.add(currentAsteroid);
            controller.numAsteroids--;
            currentAsteroid.isDestroyed = true;

        }
    }
}
