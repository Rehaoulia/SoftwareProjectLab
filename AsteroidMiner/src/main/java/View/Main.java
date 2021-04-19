package View;

import CoreClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {



    public static int cAsteroid;
    public static boolean spacestation;
    public static boolean spacestationstatus;
    public static void main(String[] args) throws IOException, InterruptedException {

        Controller c = initialize();
        spacestation = false;
        while (c.getGameOver()==false) {
            mainMenu(c);
        }
        if(!c.getWin()){
            System.out.println("You lost :(");
        }else {
        		System.out.println("You won :D");
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
        	Sunstorm sun = new Sunstorm();
        	sun.makeItHappen(c);
        	break;
        case 3: // fill
            PerihelionMenu(c);
            break;
        default:
            System.out.println("you didn't choose any action");
        }
        if(c.settlers.size()>0)
        	System.out.println("\n\n-------Asteroid:" + c.settlers.get(0).getAsteroid().getID() + "\n"
        			+ c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()
        			+ "\n");
    }

        public static void PerihelionMenu(Controller c) throws IOException {
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("Subliming Asteroids");
        menuItems.add("Exploding Asteroids");
        Menu menu = new Menu(menuItems);
        switch (menu.display()) {

            case 0://Subliming Asteroids

                for (int i = 0; i < Controller.getSublimingAsteroids().size(); i++) {
                    int t = Controller.getSublimingAsteroids().get(i);
                    Random random = new Random();
                    boolean perihelion = random.nextBoolean();
                    Controller.asteroids.get(t).setPerihelion(perihelion);
                    System.out.print("\n\n-------Asteroid:" + t + "\n"
                            + Controller.asteroids.get(t).viewInfo()
                            + "\n" + "Perihelion : " + Controller.asteroids.get(t).perihelion() + "\n");
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n" + "choose a subliming asteroid to drill");
                int miningAsteroid = scanner.nextInt();
                System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                        + Controller.asteroids.get(miningAsteroid).viewInfo()
                        + "\n" + "Perihelion : " + Controller.asteroids.get(miningAsteroid).perihelion());

                ArrayList<String> menu1 = new ArrayList<String>();
                menu1.add("sublime");
                menu1.add("back");
                Menu menu2 = new Menu(menu1);
                while (Controller.asteroids.get(miningAsteroid).radius != Controller.asteroids.get(miningAsteroid).getDepth()) {
                    switch (menu2.display()) {
                        case 0:
                            Controller.asteroids.get(miningAsteroid).getsDrill();
                            break;
                        case 1:
                            PerihelionMenu(c);
                            break;
                        default:
                            System.out.println("\n" + "you didn't choose any action");

                    }
                    System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                            + Controller.asteroids.get(miningAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + Controller.asteroids.get(miningAsteroid).perihelion() + "\n");
                }
                if (Controller.asteroids.get(miningAsteroid).perihelion()) {
                    Controller.asteroids.get(miningAsteroid).setHollow(true);
                    Controller.removeSublimingAsteroid(miningAsteroid);
                    System.out.println("\n\n" + "WaterIce sublimed !! ");
                    System.out.print("\n\n-------Asteroid:" + miningAsteroid + "\n"
                            + Controller.asteroids.get(miningAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + Controller.asteroids.get(miningAsteroid).perihelion());
                } else {
                    System.out.println("\n" + " The asteroid is fully drilled and the WaterIce didn't sublime !");
                }
                menu2.display();
                break;


            case 1: // Exploding Asteroids
                for (int i = 0; i < Controller.getExplodingAsteroids().size(); i++) {

                    Integer t = Controller.getExplodingAsteroids().get(i);
                    Random random = new Random();
                    boolean perihelion = random.nextBoolean();
                    Controller.asteroids.get(t).setPerihelion(perihelion);
                    System.out.print("\n\n-------Asteroid:" + t + "\n"
                            + Controller.asteroids.get(t).viewInfo()
                            + "\n" + "Perihelion : " + Controller.asteroids.get(t).perihelion() + "\n");
                }
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("\n" + "choose a radioactive asteroid to explode");
                int explodingAsteroid = scanner2.nextInt();
                System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                        + Controller.asteroids.get(explodingAsteroid).viewInfo()
                        + "\n" + "Perihelion : " + Controller.asteroids.get(explodingAsteroid).perihelion() + "\n");
                ArrayList<String> menu11 = new ArrayList<String>();
                menu11.add("Explode");
                menu11.add("back");
                Menu menu22 = new Menu(menu11);
                while (Controller.asteroids.get(explodingAsteroid).radius != Controller.asteroids.get(explodingAsteroid).getDepth()) {
                    switch (menu22.display()) {
                        case 0:
                            Controller.asteroids.get(explodingAsteroid).getsDrill();
                            break;
                        case 1:
                            PerihelionMenu(c);
                            break;
                        default:
                            System.out.println("\n" + "you didn't choose any action");

                    }
                    System.out.print("\n\n-------Asteroid:" + explodingAsteroid + "\n"
                            + Controller.asteroids.get(explodingAsteroid).viewInfo()
                            + "\n" + "Perihelion : " + Controller.asteroids.get(explodingAsteroid).perihelion() + "\n");

                }
                if (Controller.asteroids.get(explodingAsteroid).perihelion()) {
                    Controller.removeExplodingAsteroid(explodingAsteroid);
                    c.settlers.get(0).dying(c);
                    Controller.asteroids.remove(explodingAsteroid);
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
        menuItems.add("setGate");
        menuItems.add("Teleport");
        
        if(spacestation) {
        	menuItems.add("Add Spacestation Material");
        }
        // menuItems.add("revive");

        Menu menu = new Menu(menuItems);
        switch (menu.display()) {
        case 0: 
            if (cAsteroid != Controller.asteroids.size()){
                cAsteroid= c.settlers.get(0).getAsteroid().getID() ;
                cAsteroid++;
            } else
                cAsteroid = 0;

           c.settlers.get(0).travel(Controller.asteroids.get(cAsteroid));


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
            int res = c.settlers.get(0).showCraftMenu(c);
            if(res == -1) {
            	spacestation = true;
            }
            break;
        case 6:
            c.settlers.get(0).putGate();
           break;
        case 7:
           c.settlers.get(0).teleport( c.settlers.get(0).gates.get(1));
           break;
         case 8: // add space station material
        	 c.settlers.get(0).displayResources(c);
        	break;
        default:
            System.out.println("you didn't choose any action");
        }

       if(c.settlers.size()>0)
            System.out.println("\n\n-------Asteroid:"+c.settlers.get(0).getAsteroid().getID()+"\n"+c.settlers.get(0).getAsteroid().viewInfo() + "\n-------Settler:\n" + c.settlers.get(0).viewInfo()+"\n");
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
