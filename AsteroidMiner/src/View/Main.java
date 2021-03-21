package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public Controller c ;
	public int cAsteroid ;
	
	public static void main(String[] args) throws IOException {
        initialize();
        int i=0;
        while(i<10){
            mainMenu();
        }
    }
	
    public static void mainMenu() throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Travel");
        menuItems.add("Drill");
        menuItems.add("Mine");
        menuItems.add("Hide");
        menuItems.add("Craft");
        menuItems.add("revive");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
        case 0: // c.settlers.getAt(0).travel(c.asteroids.get(cAsteroid));
        		//if(cAsteroid !== c.asteroids)cAsteroid++;
            break;
        case 1: // drill
            break;
        case 2: // mine
            break;
        case 3: // hide
            break;
        case 4: // craft
            break;
        case 5: // revive
            break;
        default:// bogus
        }
        //System.out.print(c.);
        
    }

    public static void craftMenu() throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Robot");
        menuItems.add("Teleportation Gate");
        menuItems.add("SpaceStation");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
        case 0: // robot
            break;
        case 1: // teleportation gate
            break;
        case 2: // space station
            break;
        default:// bogus
        }
    }

    public static void initialize() throws IOException {
    	cAsteroid = 0 ;
        System.out.println("please write your name to start the game");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        Controller c = new Controller();
        c.startGame(new String[]{name});
    }

    

}