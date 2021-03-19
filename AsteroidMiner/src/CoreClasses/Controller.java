package CoreClasses;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private Random rand; // RNG
    private final int fps = 60; // necessary for sunstorm
    private Map<String, Timer> Threads; // all threads to stop them
    private Map<String, TimerTask> ThreadTasks; // all tasks to stop them

    // Methods
    public void startGame(String[] names) {
        for (String i : Arrays.asList(names)) {
            settlers.add(new Settler(i));
        }
    }

    public void setupGame() {
        rand = new Random();
        numAsteroids = rand.nextInt(10) + 40; // between 40 and 50
        asteroids = new HashMap<Int, Asteroid>();
        explodingAsteroids = new ArrayList<Int>();
        SublimingAsteroids = new ArrayList<Int>();
        settlers = new ArrayList<Settler>();
        robots = new ArrayList<Robots>();

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
        rand = new Random();
        int wavelength = (rand.nextInt(3) + 3) * 60 * 1000; // between 3 and 5 minutes
        Sunstorm.behave(wavelength);
        String threadName = new String("sunstorm",Sunstorm.getCount());
        TimerTask checkDeath = new TimerTask() {
            @Override
            public void run() {
                if (ss.isHappening()) {
                    for (Settler s : settlers) {
                        if (s.getHidden() == false || s.getDeath()) {
                            s.Die();
                        }
                    }
                    for (Robot r : robots) {
                        if (r.getHidden == false) {
                            s.Die();
                        }
                    }
                }

            }
        };

        Timer checkDeaththread = new Timer();
        checkDeaththread.schedule(checkDeath, wavelength, 1000 / fps);
    }

    public void explodeAsteroids() {

    }

    public boolean checkGame() {
        boolean flag = false;

    }
}
