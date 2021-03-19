package CoreClasses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class Settler extends Traveler implements Craftable 
{
	
	private String name;
	private ArrayList<String> minedMinerals; //minedMinerals should be changed to a string ArrayList, explanation below in checkRequiredMaterial
	private TeleportationGate[] gates;
	private long timeOfDeath;
	private boolean dead;
	
	public Settler(){
		
	}
	 //Change currentPlace to currentAsteroid because currentPlace does not have methods such as getsDrill, getsFill, getsMine
	public void setAsteroid(Asteroid asteroid) {
		this.currentAsteroid = asteroid; 
	}
	
	public void travel(Asteroid asteroid) {
		setAsteroid(asteroid);
	}
	
	//Add deploy method to gates (basically just a setter for the deployed attribute)
	public void putGate(TeleportationGate gate) {
		for(TeleportationGate g : this.gates) {
			if(gate.equals(g)) {
				g.deploy();
			}
		}
	}
	
	@Override
	public void drill() {
		this.currentAsteroid.getsDrill();
	}
	
	public void mine() {
		if(!this.currentAsteroid.hollow() && this.currentAsteroid.drillable()) {
			this.minedMinerals.add(this.currentAsteroid.getMineral());
		}
	}
	
	public void fill(String mineral) {
		if(this.currentAsteroid.hollow()==true) {
			for(String min : minedMinerals) {
				if(min.equals(mineral)) {
					this.currentAsteroid.getsFill(mineral);
				}
			}
		}
	}
	
	//timeOfDeath changed into long and revive just calculates the difference between current time and time of death also revive return type changed into boolean
	public boolean revive(Settler S) {
		long elapsedTime = System.currentTimeMillis() - S.timeOfDeath;
		if(elapsedTime < 10000) {
			S.timeOfDeath = 0;
			return true;
		}else {
			return false;
		}
		
	}
	
	
	//minedMinerals needs to be a string arrayList to allow calling method containsAll(), or else this part of the code will be much more complicated
	public boolean checkRequiredMaterial(String craftable) {
		boolean canCraft = false;
		switch(craftable) {
			case "Robot":
				if(minedMinerals.containsAll(Arrays.asList("Uranium", "Carbon", "Iron"))) {
					canCraft = true;
				}
				break;
			case "TeleportationGate":
				if(minedMinerals.containsAll(Arrays.asList("Uranium", "WaterIce", "Iron", "Iron"))) {
					canCraft = true;
				}
				break;
			case "SpaceStation":
				if(minedMinerals.containsAll(Arrays.asList("Uranium", "Uranium", "Uranium", "Carbon", "Carbon", "Carbon", "Iron", "Iron", "Iron", "WaterIce", "WaterIce", "WaterIce"))) {
					canCraft = true;
				}
				break;
		}
		return canCraft;
	}
	
	//By making this method return craftable type we can get rid of craftRobot(), craftTeleportationGate(), craftSpaceStation()
	public Craftable craft(String craftable) {
		Craftable object = null;
		if(checkRequiredMaterial(craftable)) {
			switch(craftable) {
			case "Robot":
				object = new Robot();
				break;
			case "TeleportationGate":
				object = new TeleportationGate();
				break;
			case "SpaceStation":
				object = new SpaceStation();
				break;
		}
		}
		return object;
	}
	
	//Return type changed to string instead of craftable
	public String showCraftMenu() {
		return null;
	}
	
	//Removed pairGates() method as gates are paired by default
	
	public void setTimeOfDeath(){
		this.timeOfDeath = System.currentTimeMillis();
	}
	
	//might be useless
	public long getTimeOfDeath() {
		return timeOfDeath;
	}
	
	public void setDeath() {
		this.dead = true;
	}
	
	public boolean getDeath() {
		return this.dead;
	}
	
	public int getCapacityLeft() {
		return 10 - this.minedMinerals.size();
	}
}
