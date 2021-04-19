package CoreClasses;

import java.io.IOException;
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

    public ArrayList<Robot> robots = new ArrayList<Robot>();
  
    private ArrayList<SpaceStation> spacestations = new ArrayList<SpaceStation>();


    public ArrayList<Settler> getSettlers() {
        return settlers;
    }

    public static ArrayList<Integer> getExplodingAsteroids() {
        return explodingAsteroids;
    }

    public static ArrayList<Integer> getSublimingAsteroids() {
        return sublimingAsteroids;
    }

    private int numAsteroids;
    private int numSettlers;

    public static Map<Integer, Asteroid> asteroids;
    private boolean gameOver;

    private boolean win;
    private static ArrayList<Integer> explodingAsteroids;
    private static ArrayList<Integer> sublimingAsteroids;
    private Random rand; // RNG
    private final int fps = 60; // necessary for sunstorm
    private Timer Thread; // all threads to stop them
    private Map<String, TimerTask> ThreadTasks; // all tasks to stop them


    public  String information;
    public Sunstorm sunstorm = new Sunstorm();


    // Methods

    public Controller() {
    }

    public void startGame(String[] names) {


        this.setupGame();
        settlers = new ArrayList<Settler>();
        robots= new ArrayList<Robot>();
        int count=0;
        for (String i : Arrays.asList(names)) {
            Settler set = new Settler(i, count);
            count++;
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

    public void addRobot(Robot r) { // missing from the sequence diagram

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
        if(settlers.size()>0){
            for(int i=0;i<settlers.size();i++){
                settlers.get(i).setID(i);
            }
        }
        checkGame();
    }

    public void removeAsteroid(int asteroidID) {
        asteroids.remove(asteroidID);
    }

    public void triggerSunStorms() {
        rand = new Random();
        int wavelength = (rand.nextInt(3) + 3) * 60 * 1000; // between 3 and 5 minutes
        sunstorm.behave(this, wavelength);
        TimerTask checkDeath = new TimerTask() {
            @Override
            public void run() {
                if (sunstorm.getHappening()) {
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



    //checks the conditions for ending the game
    public void checkGame() {
        if(settlers.size()==0){
            this.gameOver =true;
            win =false;
            endGame();
        }
    }

    public boolean getGameOver(){ return gameOver;}
    public boolean getWin(){ return win;}
    

    public static void updateAsteroid(Asteroid asteroid) {

    }

    public static void updateSettler() {

    }

    public void robotBehave(int id) throws IOException ,InterruptedException{
        int ast = robots.get(id).currentAsteroid.getID();
        robots.get(id).robotMenu(asteroids.get((ast + 1) % asteroids.size()));

    }
  
    public static void removeSublimingAsteroid(int id) {
        sublimingAsteroids.remove(new Integer(id));
    }

    public static void removeExplodingAsteroid(int id) {
        explodingAsteroids.remove(new Integer(id));
    }
    
    public void addMineralToSpaceStation(String spacestationID, String mineral) {
    	for(SpaceStation s : spacestations) {
    		if(s.getID().equals(spacestationID)) {
    			s.addResource(mineral);
    		}
    	}
    }
    
    public void addSpaceStationToAsteroid(SpaceStation s, int id){
    	asteroids.get(id).setSpaceStation(s);
    }
    
    public void addSpaceStation(SpaceStation s) {
    	this.spacestations.add(s);
    }
    public void checkSpaceStation(String spacestationID) {
    	for(SpaceStation s : spacestations) {
    		if(s.getID().equals(spacestationID)) {
    			win = true;
    			gameOver = s.isCraftable();
    		}
    	}
    }
        public void Sublime(Controller c, int sublimingAsteroid) {
        c.asteroids.get(sublimingAsteroid).setDepth(c.asteroids.get(sublimingAsteroid).radius);
        if (c.asteroids.get(sublimingAsteroid).perihelion()) {
            c.asteroids.get(sublimingAsteroid).setHollow(true);
            c.removeSublimingAsteroid(sublimingAsteroid);
            System.out.println("\n\n" + "WaterIce sublimed !! ");
            System.out.print("\n\n-------Asteroid:" + sublimingAsteroid + "\n"
                    + c.asteroids.get(sublimingAsteroid).viewInfo()
                    + "\n" + "Perihelion : " + c.asteroids.get(sublimingAsteroid).perihelion());
        } else {
            System.out.println("\n" + " The asteroid is fully drilled and the WaterIce didn't sublime !");
            System.out.print("\n\n-------Asteroid:" + sublimingAsteroid + "\n"
                    + c.asteroids.get(sublimingAsteroid).viewInfo()
                    + "\n" + "Perihelion : " + c.asteroids.get(sublimingAsteroid).perihelion());
        }

    }

    public void Explode(Controller c, int explodingAsteroid) {
        c.asteroids.get(explodingAsteroid).setDepth(c.asteroids.get(explodingAsteroid).radius);

        if (c.asteroids.get(explodingAsteroid).perihelion()) {
            c.removeExplodingAsteroid(explodingAsteroid);
            //c.asteroids.remove(explodingAsteroid);
            System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                    + c.asteroids.get(explodingAsteroid).viewInfo()
                    + "\n" + "Perihelion : " + c.asteroids.get(explodingAsteroid).perihelion() + "\n");
            System.out.println("\n" + "Asteroid " + explodingAsteroid + " explodes and settler dies ! !");
            c.settlers.get(0).die();

        } else {
            System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                    + c.asteroids.get(explodingAsteroid).viewInfo()
                    + "\n" + "Perihelion : " + c.asteroids.get(explodingAsteroid).perihelion() + "\n");
            System.out.println("\n" + " The asteroid is fully drilled and it didn't explode !");
        }

    }
}
