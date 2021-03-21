package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static int cAsteroid;

    public static void main(String[] args) throws IOException {
        Controller c = initialize();
        int i = 0;
        while (i < 10) {
            mainMenu(c);
        }
    }

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
        menuItems.add("Hide");
        menuItems.add("Craft");
        menuItems.add("revive");
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
        case 3: // hide
            c.settlers.get(0).hide();
            break;
        case 4: // craft
            break;
        case 5: // revive
            break;
        default:// bogus
        }
        System.out.println(c.settlers.get(0).getAsteroid().viewInfo());
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