package CoreClasses;

import java.util.ArrayList;

public class SpaceStation implements Craftable {
    //required minerals for crafting the spaceStation:
    //String[] RequiredMinerals = {"Iron", "Carbon", "Uranium", "waterIce", "Iron", "Carbon", "Uranium", "waterIce", "Iron", "Carbon", "Uranium", "waterIce"};

    Asteroid currentAsteroid;

    //minerals designated for crafting the spaceStation:
    private ArrayList<String> spaceStationMinerals;

    public SpaceStation(Asteroid currentAsteroid) {
        this.currentAsteroid = currentAsteroid; //Constructor
    }


    //add the resource to the minerals designated for crafting the spaceStation
    public void addResource(Mineral resource) {
        this.spaceStationMinerals.add(resource.toString());
    }

    //get the minerals designated for crafting the spaceStation
    public ArrayList<String> getResources() {
        return spaceStationMinerals;
    }

    //remove the resource from the minerals designated for crafting the spaceStation
    public void removeResource(Mineral resource) {
        this.spaceStationMinerals.remove(resource.toString());

    }

    //check if we can craft the space station or not
    public boolean isCraftable() {
        int i = 0;
        int c = 0;
        int u = 0;
        int w = 0;
        for (int j = 0; j < spaceStationMinerals.size(); j++) {

            switch (spaceStationMinerals.get(i)) {
//                case "Iron" -> i++;
//                case "Carbon" -> c++;
//                case "Uranium" -> u++;
//                case "waterIce" -> w++;
            }
        }
        return (i == 3 && c == 3 && u == 3 && w == 3);
    }

    @Override
    public Asteroid getCurrentAstroid() {
        return currentAsteroid;
    }

    @Override
    public void setAsteroid(Asteroid a) {
        currentAsteroid = a;
    }


}

