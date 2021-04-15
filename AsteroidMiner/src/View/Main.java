package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args)   throws IOException {
        Controller c = initialize();
        while (c.getGameOver()==false) {
            mainMenu(c);
        }
        if(c.getWin()==true){
            System.out.println("You Won! :D");
        }else{
            System.out.println("You lost :(");
        }
    }
	
    public static int cAsteroid;

    

    public static Controller initialize() throws IOException {
        Controller c = new Controller();
        System.out.println("please write your name to start the game");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        c.startGame(new String[] { name });
        return c;
    }

    public static void mainMenu(Controller c) throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Travel");
        menuItems.add("Drill");
        menuItems.add("Mine");
        menuItems.add("Fill");
        menuItems.add("Hide");
        menuItems.add("Craft");
        // menuItems.add("revive");
        menuItems.add("Make a sunstorm");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
        case 0:
            c.settlers.get(0).travel(c.asteroids.get(cAsteroid));
            if (cAsteroid != c.asteroids.size())
                cAsteroid++;
            else
                cAsteroid = 0;

            break;
        case 1: // drill
            c.settlers.get(0).drill();
            break;
        case 2: // mine
            c.settlers.get(0).mine();
            break;
        case 3: // fill
            Menu minerals = new Menu(c.settlers.get(0).getMinerals());
            int option = minerals.display();
            c.settlers.get(0).fill(c.settlers.get(0).getMinerals().get(option));
            break;
        case 4: // hide
            c.settlers.get(0).hide();
            break;
        case 5: // craft
            c.settlers.get(0).showCraftMenu();
            break;
        // case 6: // revive
        // break;
        case 6:
            c.sunstorm.makeItHappen(c);
            break;
        default:
            System.out.println("you didn't choose any action");
        }
        if(c.settlers.size()>0)
            System.out.println("\n\n-------Asteroid:"+c.settlers.get(0).getAsteroid().getID()+"\n"+c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()+"\n");
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

}