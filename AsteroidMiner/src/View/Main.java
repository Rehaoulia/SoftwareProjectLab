package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static int cAsteroid;

    public static void main(String[] args) throws IOException, InterruptedException {
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

    public static void mainMenu(Controller c) throws IOException, InterruptedException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Settler Controls");
        menuItems.add("Robot Controls");
        menuItems.add("Sunstorm Controls");
        menuItems.add("Perihelion Controls");
        // menuItems.add("revive");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
        case 0: // Settler
            settlerMenu(c);
            break;
        case 1: // Robot
            robotMenu(c);
            break;
        case 2: // mine
            c.settlers.get(0).mine();
            break;
        case 3: // fill
            Menu minerals = new Menu(c.settlers.get(0).getMinerals());
            int option = minerals.display();
            c.settlers.get(0).fill(c.settlers.get(0).getMinerals().get(option));
            break;
        default:
            System.out.println("you didn't choose any action");
        }
        System.out.println("\n\n-------Asteroid:" + c.settlers.get(0).getAsteroid().getID() + "\n"
                + c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()
                + "\n");
    }

    public static void settlerMenu(Controller c) throws IOException, InterruptedException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Travel");
        menuItems.add("Drill");
        menuItems.add("Mine");
        menuItems.add("Fill");
        menuItems.add("Hide");
        menuItems.add("Craft");
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
        default:
            System.out.println("you didn't choose any action");
        }
        System.out.println("\n\n-------Asteroid:" + c.settlers.get(0).getAsteroid().getID() + "\n"
                + c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()
                + "\n");
    }

    public static void robotMenu(Controller c) throws IOException, InterruptedException {
        int ids = c.getRobots();
        if (ids == 0) {
            System.out.println("you have no robots currently");
            return;
        }
        ArrayList<String> menuItems = new ArrayList<String>();
        for (int i = 0; i < ids; i++) {
            menuItems.add("Robot " + i);
        }
        Menu menu = new Menu(menuItems, "Pick a robot to behave");
        int option = menu.display();
        if (option >= ids || option < 0) {
            System.out.println("you didn't choose any action");
            return;
        }
        c.robotBehave(option);

    }
}