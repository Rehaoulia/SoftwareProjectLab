package CoreClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Controller {
    // Attributes
    private List<Settler> settlers;
    private List<Robot> robots;
    private int numAsteroids;
    private int numSettlers;
    private Map<Int, Asteroid> asteroids;
    private boolean gamerOver;
    private boolean win;
    private List<Int> explodingAsteroids;
    private List<Int> SublimingAsteroids;

    // Methods
    public void startGame(String[] names) {
        for (String i : Arrays.asList(names)) {
            settlers.add(new Settler(i));
        }
    }

    public void setupGame() {
        Random rand = new Random();
        numAsteroids = rand.nextInt(10) + 40; // between 40 and 50
        asteroids = new HashMap<Int, Asteroid>();
        explodingAsteroids = new ArrayList<Int>();
        SublimingAsteroids = new ArrayList<Int>();
        settlers = new ArrayList<Settler>();
        robots = new ArrayList<Robots>();
        Mineral M;
        for (int i = 0; i < numAsteroids; i++) {
            int mineralSelector = rand.nextInt(4);
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
            default:
                M = new Uranium();
            }
            Asteroid a = new Asteroid(i, a);
            asteroids.put(i, a);
            if (mineralSelector == 1) {
                SublimingAsteroids.add(i);
            }
            if (mineralSelector == 3) {
                explodingAsteroids.add(i);
            }
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
        for (Settler s : settlers) {
            if (s.getHidden == false) {
                s.Die();
            }
        }
        for (Robot r : robots) {
            if (r.getHidden == false) {
                s.Die();
            }
        }
    }

    public void explodeAsteroids() {

    }

    public boolean checkGame() {

    }
}
