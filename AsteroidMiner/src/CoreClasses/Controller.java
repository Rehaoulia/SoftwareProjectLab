package CoreClasses;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    // Attributes
    public ArrayList<Settler> settlers;
    private static ArrayList<Robot> robots;
    private int numAsteroids;
    private int numSettlers;
    public static Map<Integer, Asteroid> asteroids;
    private boolean gamerOver;
    private boolean win;
    private static ArrayList<Integer> explodingAsteroids;
    private static ArrayList<Integer> sublimingAsteroids;
    private Random rand; // RNG
    private final int fps = 60; // necessary for sunstorm
    private Timer Thread; // all threads to stop them
    private Map<String, TimerTask> ThreadTasks; // all tasks to stop them
    public String information;

    // Methods

    public Controller() {
    }

    public void startGame(String[] names) {
        this.setupGame();
        settlers = new ArrayList<Settler>();

        for (String i : Arrays.asList(names)) {
            Settler set = new Settler(i);
            set.setAsteroid(asteroids.get(0));
            settlers.add(set);
            information = set.getAsteroid().viewInfo();
            System.out.println(information);

        }
    }

    public void setupGame() {
        rand = new Random();
        numAsteroids = rand.nextInt(10) + 40; // between 40 and 50
        asteroids = new HashMap<Integer, Asteroid>();
        explodingAsteroids = new ArrayList<Integer>();
        sublimingAsteroids = new ArrayList<Integer>();

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
                break;
            default:
                M = null;
            }
            Asteroid a;
            int radius = rand.nextInt(5) + 5;
            if (mineralSelector == 4)
                a = new Asteroid(i, radius);
            else
                a = new Asteroid(i, M, radius);

            asteroids.put(i, a);
            if (mineralSelector == 1)
                sublimingAsteroids.add(i);

            if (mineralSelector == 3)
                explodingAsteroids.add(i);

        }
    }

    public static void addRobot(Robot r) { // missing from the sequence diagram
        robots.add(r);
    }

    public void endGame() {
    }

    public void updateGame() {

    }

    public int getRobots() {
        return robots.size();
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
        TimerTask checkDeath = new TimerTask() {
            @Override
            public void run() {
                if (Sunstorm.getHappening()) {
                    for (Settler s : settlers) {
                        if (!s.getHidden() && !s.getDeath()) {
                            s.die();
                        }
                    }
                    for (Robot r : robots) {
                        if (!r.getHidden()) {
                            r.die();
                        }
                    }
                }
            }
        };
        ThreadTasks.put("sunstorm", checkDeath);
        Thread.schedule(ThreadTasks.get("sunstorm"), wavelength, 1000 / fps);
    }

    public void explodeAsteroids() {

    }

    public boolean checkGame() {
        boolean flag = false;
        return flag;
    }

    public static void updateAsteroid(Asteroid asteroid) {

    }

    public static void updateSettler() {

    }

    public static void robotBehave(int id) {
        int ast = robots.get(id).currentAsteroid.getID();
        robots.get(id).behave(asteroids.get((ast + 1) % asteroids.size()));
    }

    public static void removeSublimingAsteroid(int id) {
        sublimingAsteroids.remove(id);
    }

    public static void removeExplodingAsteroid(int id) {
        explodingAsteroids.remove(id);
    }
}
