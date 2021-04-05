



package CoreClasses;

import Search.Border;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import View.Menu;

public class Settler extends Traveler {

	private String name;
	private ArrayList<String> minedMinerals; // minedMinerals should be changed to a string ArrayList, explanation below
												// in checkRequiredMaterial
	private TeleportationGate[] gates;
	private long timeOfDeath;
	private boolean dead;
	private Asteroid currentAsteroid;

	public Settler(String name,Vector3f loc ) {
            super(loc);
		this.name = name;
		this.timeOfDeath = 0;
		this.dead = false;
		minedMinerals = new ArrayList<String>();
	}

	public void setAsteroid(Asteroid asteroid) {
		this.currentAsteroid = asteroid;
	}

	public Asteroid getAsteroid() {
		return currentAsteroid;
	}
        
        

	public void travel(Asteroid asteroid) {
		
		if(this.getHidden()) {
		unhide();
		currentAsteroid.getUnhide();   
		// if we dont unhide asteroid, it gonna stay hollow false !!
		}
		
		setAsteroid(asteroid);
		Controller.updateAsteroid(this.currentAsteroid);

            
            
	}

	public void putGate() {
		if (!gates[0].isDeployed()) {
			gates[0].setGate(this.currentAsteroid);
		} else if (gates[0].isDeployed() && !gates[0].isPaired()) {
			gates[1].setGate(this.currentAsteroid, gates[0]);
		}
	}

	@Override
	public void drill() {
		this.currentAsteroid.getsDrill();
		Controller.updateAsteroid(this.currentAsteroid);
	}

	public void mine() {
		Mineral m = this.currentAsteroid.getsMine();
		if (m != null && this.getCapacityLeft() > 0) {
			// String pattern =
			this.minedMinerals.add(m.getClass().getSimpleName());
		}
		Controller.updateAsteroid(this.currentAsteroid);
	}

	public void fill(String mineral) {
		for (String m : minedMinerals) {
			if (m.equalsIgnoreCase(mineral)) {
				switch (mineral) {
				case "Uranium":
					this.currentAsteroid.getsFill(new Uranium());
					break;
				case "WaterIce":
					this.currentAsteroid.getsFill(new WaterIce());
					break;
				case "Carbon":
					this.currentAsteroid.getsFill(new Carbon());
					break;
				case "Iron":
					this.currentAsteroid.getsFill(new Iron());
					break;
				}
				this.minedMinerals.remove(m);
				break;
			}
		}
		Controller.updateAsteroid(this.currentAsteroid);
	}

	public boolean revive(Settler S) {
		long elapsedTime = System.currentTimeMillis() - S.getTimeOfDeath();
		if (elapsedTime < 10000) {
			S.setTimeOfDeath(0);
			Controller.updateSettler();
			return true;
		} else {
			return false;
		}
	}

	// minedMinerals needs to be a string arrayList to allow calling method
	// containsAll(), or else this part of the code will be much more complicated
	public boolean checkRequiredMaterial(int craftable) {
		ArrayList<Boolean> flags = new ArrayList<Boolean>();
		boolean canCraft = false;
		ArrayList<String> temp = new ArrayList<String>(minedMinerals);
		switch (craftable) {
		case 0:
			flags.add((temp.indexOf("Uranium")) != -1); // Uranium
			flags.add((temp.indexOf("Carbon")) != -1); // Carbon
			flags.add((temp.indexOf("Iron")) != -1); // Iron

			break;
		case 1:
			flags.add((temp.indexOf("Uranium")) != -1); // Uranium
			flags.add((temp.indexOf("WaterIce")) != -1); // WaterIce
			flags.add((temp.indexOf("Iron")) != -1); // Iron
			temp.remove(temp.indexOf("Iron"));
			flags.add((temp.indexOf("Iron")) != -1); // Iron

			break;
		}
		canCraft = flags.stream().reduce(true, (a, b) -> a && b);
		return canCraft;
	}

	// By making this method return craftable type we can get rid of craftRobot(),
	// craftTeleportationGate(), craftSpaceStation()
	public Craftable craft(int craftable) {
		Craftable c = null;
		if (checkRequiredMaterial(craftable)) {

			switch (craftable) {
			case 0:
				c = new Robot(this.currentAsteroid);
				minedMinerals.remove(minedMinerals.indexOf("Uranium"));
				minedMinerals.remove(minedMinerals.indexOf("Carbon"));
				minedMinerals.remove(minedMinerals.indexOf("Iron"));
				break;
			case 1:
				// check if you already have teleportation gates
				// add to gates attribute
				if (gates == null) {
					gates = new TeleportationGate[2];
					gates[0] = new TeleportationGate();
					gates[1] = new TeleportationGate();
					minedMinerals.remove(minedMinerals.indexOf("Uranium"));
					minedMinerals.remove(minedMinerals.indexOf("WaterIce"));
					minedMinerals.remove(minedMinerals.indexOf("Iron"));
					minedMinerals.remove(minedMinerals.indexOf("Iron"));

				}
				break;
			case 2:
				if (this.currentAsteroid.getSpaceStation() != null) {
					c = new SpaceStation(this.currentAsteroid);
				}
				break;
			default:
				return null;
			}
		} else
			System.out.println("Not enough materials\n");
		return c;
	}

	public void showCraftMenu() throws IOException {
		ArrayList<String> menuItems = new ArrayList<String>();
		menuItems.add("Robot || Uranium + Carbon + Iron");
		menuItems.add("Teleportation Gate || Uranium + WaterIce + 2 x Iron");
		menuItems.add("SpaceStation || 3xUranium + 3xCarbon + 3xIron + 3xWaterIce");
		Menu menu = new Menu(menuItems);
		Craftable c = craft(menu.display());
		if (c != null)
			System.out.println(c.getClass().getSimpleName() + " Created successfully");
	}

	// Removed pairGates() method as gates are paired by default

	private void setTimeOfDeath() {
		this.timeOfDeath = System.currentTimeMillis();
	}

	public void setTimeOfDeath(long time) {
		this.timeOfDeath = time;
	}

	// might be useless
	public long getTimeOfDeath() {
		return timeOfDeath;
	}

	@Override
	public void die() {
		this.dead = true;
	}

	public boolean getDeath() {
		return this.dead;
	}

	// add to mine
	public int getCapacityLeft() {
		return 10 - this.minedMinerals.size();
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<String> getMinerals() {
		return minedMinerals;
	}

	public void dying() {
		this.setTimeOfDeath();
	}

	public void hide() {
		if (this.currentAsteroid.getHide() == true) {
			this.hidden = true;
		}
	}
        
        public boolean access(){
            Border closeAstB = new Border(this.getLocation(), 50f);  
            if(closeAstB.contains(this.getAsteroid().getLocation()))
                return true ;
            
            return false;
        }

	public String viewInfo() {
		String str = "Name: " + this.name + "\t\tHidden:" + Boolean.toString(hidden) + "\nminedMinerals: "
				+ String.join(" - ", minedMinerals);
		return str;
	}
}
