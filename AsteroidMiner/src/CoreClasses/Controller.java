package CoreClasses;

import java.util.*;

public class Controller<Int> {
    // Attributes
    private List<Settler> settlers;
    private List<Robot> robots;

    public int getNumAsteroids() {
        return numAsteroids;
    }

    private int numAsteroids;
    private int numSettlers;
    private Map<Integer, Asteroid> asteroids;
    private boolean gamerOver;
    private boolean win;
    private List<Integer> explodingAsteroids;

    public List<Integer> getExplodingAsteroids() {
        return explodingAsteroids;
    }

    public List<Integer> getSublimingAsteroids() {
        return SublimingAsteroids;
    }

    private List<Integer> SublimingAsteroids;
    int i;


    // Methods
    public void startGame(String[] names) {
        for (String i : Arrays.asList(names)) {
            settlers.add(new Settler(i));
        }
    }

    public void setupGame() {
        Random rand = new Random();
        numAsteroids = rand.nextInt(10) + 40; // between 40 and 50
        asteroids = new HashMap<Integer, Asteroid>();
        explodingAsteroids = new ArrayList<Integer>();
        SublimingAsteroids = new ArrayList<Integer>();
        settlers = new ArrayList<Settler>();
        robots = new ArrayList<Robot>();

        for (int i = 0; i < numAsteroids; i++) {
            Mineral M;
            int mineralSelector = rand.nextInt(5);
            switch (mineralSelector) {
                case 0:
                    M = new Carbon();
                    break;
                case 1:
                    M = new WaterIce();
                    break;
                case 2:
                    M = new Iron();
                    break;
                case 3:
                    M = new Uranium();
                default:
                    M = null;
            }
            Asteroid a;
            if (mineralSelector == 4)
                a = new Asteroid(i);
            else
                a = new Asteroid(i, M);

            asteroids.put(i, a);
            if (mineralSelector == 1)
                SublimingAsteroids.add(i);

            if (mineralSelector == 3)
                explodingAsteroids.add(i);

        }
    }

    public void endGame() {

    }

    public void updateGame() {

    }

    public void removePlayer(int playerID) {
        settlers.remove(playerID);
    }

    public void removeAsteroid(int asteroidID) {
        asteroids.remove(asteroidID);
    }

    public void triggerSunStorms() {
        for (Traveler T : settlers) {
            if (T.getHidden() == false) {
                T.dying();
            }

        }
    }

    public void explodeAsteroids() {

    }

    public boolean checkGame() {
        return false;

    }
}