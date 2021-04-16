package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.Console;
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
        c.startGame(new String[]{name});
        return c;
    }


    public static void mainMenu(Controller c) throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Settler Controls");
        menuItems.add("Robot Controls");
        menuItems.add("Sunstorm Controls");
        menuItems.add("Perihelion Controls");
        // menuItems.add("revive");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
            case 0:
                settlerMenu(c);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3: //Perihelion Controls
                PerihelionMenu(c);
                break;
            default:
                System.out.println("you didn't choose any action");
        }
       /* System.out.println("\n\n-------Asteroid:" + c.settlers.get(0).getAsteroid().getID() + "\n"
                + c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()
                + "\n");*/
    }

    public static void PerihelionMenu(Controller c) throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Subliming Asteroids");
        menuItems.add("Exploding Asteroids");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {

            case 0://Subliming Asteroids

                for (int i = 0; i < c.getSublimingAsteroids().size(); i++) {
                    int t = c.getSublimingAsteroids().get(i);
                    Random random = new Random();
                    boolean perihelion = random.nextBoolean();
                    c.asteroids.get(t).setPerihelion(perihelion);
                    System.out.print("\n\n-------Asteroid:" + t + "\n"
                            + c.asteroids.get(t).viewInfo()
                            + "\n" + "Perihelion : " + c.asteroids.get(t).perihelion() + "\n");
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n" + "choose a subliming asteroid to drill");
                int miningAsteroid = scanner.nextInt();
                System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                        + c.asteroids.get(miningAsteroid).viewInfo()
                        + "\n" + "Perihelion : " + c.asteroids.get(miningAsteroid).perihelion());

                ArrayList<String> menu1 = new ArrayList<String>();
                menu1.add("Drill");
                menu1.add("back");
                Menu menu2 = new Menu(menu1);
                while (c.asteroids.get(miningAsteroid).radius != c.asteroids.get(miningAsteroid).getDepth()) {
                    switch (menu2.display()) {
                        case 0:
                            c.asteroids.get(miningAsteroid).getsDrill();
                            break;
                        case 1:
                            PerihelionMenu(c);
                            break;
                        default:
                            System.out.println("\n" + "you didn't choose any action");

                    }
                    System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                            + c.asteroids.get(miningAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + c.asteroids.get(miningAsteroid).perihelion() + "\n");
                }
                if (c.asteroids.get(miningAsteroid).perihelion()) {
                    c.asteroids.get(miningAsteroid).setHollow(true);
                    c.removeSublimingAsteroid(miningAsteroid);
                    System.out.println("\n\n" + "WaterIce sublimed !! ");
                    System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                            + c.asteroids.get(miningAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + c.asteroids.get(miningAsteroid).perihelion());
                } else {
                    System.out.println("\n" + " The asteroid is fully drilled and the WaterIce didn't sublime !");
                }

                break;


            case 1: // Exploding Asteroids
                for (int i = 0; i < c.getExplodingAsteroids().size(); i++) {

                    Integer t = c.getExplodingAsteroids().get(i);
                    Random random = new Random();
                    boolean perihelion = random.nextBoolean();
                    c.asteroids.get(t).setPerihelion(perihelion);
                    System.out.print("\n\n-------Asteroid:" + t + "\n"
                            + c.asteroids.get(t).viewInfo()
                            + "\n" + "Perihelion : " + c.asteroids.get(t).perihelion() + "\n");
                }
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("\n" + "choose a radioactive asteroid to drill");
                int explodingAsteroid = scanner2.nextInt();
                System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                        + c.asteroids.get(explodingAsteroid).viewInfo()
                        + "\n" + "Perihelion : " + c.asteroids.get(explodingAsteroid).perihelion() + "\n");
                ArrayList<String> menu11 = new ArrayList<String>();
                menu11.add("Drill");
                menu11.add("back");
                Menu menu22 = new Menu(menu11);
                while (c.asteroids.get(explodingAsteroid).radius != c.asteroids.get(explodingAsteroid).getDepth()) {
                    switch (menu22.display()) {
                        case 0:
                            c.asteroids.get(explodingAsteroid).getsDrill();
                            break;
                        case 1:
                            PerihelionMenu(c);
                            break;
                        default:
                            System.out.println("\n" + "you didn't choose any action");

                    }
                    System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                            + c.asteroids.get(explodingAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + c.asteroids.get(explodingAsteroid).perihelion() + "\n");

                }
                if (c.asteroids.get(explodingAsteroid).perihelion()) {
                    c.removeExplodingAsteroid(explodingAsteroid);
                    c.settlers.get(0).dying();
                    c.asteroids.remove(explodingAsteroid);
                    System.out.println("\n" + "Asteroid " + explodingAsteroid + " explodes and settler dies ! !");
                } else {
                    System.out.println("\n" + " The asteroid is fully drilled and it didn't explode !");
                }
                break;

            default:
                System.out.println("you didn't choose any action");
        }
    }

    public static void settlerMenu(Controller c) throws IOException {
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

            default:
                System.out.println("you didn't choose any action");
        }
       /* System.out.println("\n\n-------Asteroid:" + c.settlers.get(0).getAsteroid().getID() + "\n"
                + c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()
                + "\n");*/
    }

    public static void robotMenu(Controller c) throws IOException {
        int ids = c.getRobots();
        ArrayList<String> menuItems = new ArrayList<String>();
        for (int i = 0; i < ids; i++) {
            menuItems.add("Robot " + i);
        }
        Menu menu = new Menu(menuItems, "Pick a robot to behave");
        if (menu.display() >= ids || menu.display() < 0) {
            System.out.println("you didn't choose any action");
            return;
        }

    }


}
