package CoreClasses;
import java.util.ArrayList;
import java.util.Arrays;

public class Settler extends Traveler implements Craftable 
{
	
	private String name;
	private ArrayList<String> minedMinerals; //minedMinerals should be changed to a string ArrayList, explanation below in checkRequiredMaterial
	private TeleportationGate[] gates;
	private long timeOfDeath;
	private boolean dead;
	
	public Settler(Asteroid startingAsteroid, String name){
		this.currentAsteroid = startingAsteroid;
		this.name = name;
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
			if(gate.equals(g) && !gate.getDeployed()) {
				g.deploy(this.currentAsteroid);
			}
		}
	}
	
	@Override
	public void drill() {
		this.currentAsteroid.getsDrill();
		Controller.updateAsteroid();
	}
	
	public void mine() {
		if(this.currentAsteroid.getsMine()!= null && this.getCapacityLeft() > 0) {
			this.minedMinerals.add(this.currentAsteroid.getsMine().toString());
		}
		Controller.updateAsteroid();
	}
	
	public void fill(String mineral) {
		if(minedMinerals.contains(mineral)) {
			this.currentAsteroid.getsFill(mineral);
		}	
		Controller.updateAsteroid();
	}
	
	//timeOfDeath changed into long and revive just calculates the difference between current time and time of death also revive return type changed into boolean
	public boolean revive(Settler S) {
		long elapsedTime = System.currentTimeMillis() - S.getTimeOfDeath();
		if(elapsedTime < 10000) {
			S.setTimeOfDeath(0);
			Controller.updateSettler();
			return true;
		}else {
			return false;
		}
	}
	
	
	//minedMinerals needs to be a string arrayList to allow calling method containsAll(), or else this part of the code will be much more complicated
	public boolean checkRequiredMaterial(int craftable) {
		boolean canCraft = false;
		//remove material from settler
		switch(craftable) {
			case 1:
				if(minedMinerals.containsAll(Arrays.asList("Uranium", "Carbon", "Iron"))) {
					canCraft = true;
				}
				break;
			case 2:
				if(minedMinerals.containsAll(Arrays.asList("Uranium", "WaterIce", "Iron", "Iron"))) {
					canCraft = true;
				}
				break;
		}
		return canCraft;
	}
	
	//By making this method return craftable type we can get rid of craftRobot(), craftTeleportationGate(), craftSpaceStation()
	public Craftable craft(int craftable) {
		Craftable object = null;
		if(checkRequiredMaterial(craftable)) {
			switch(craftable) {
			case 1:
				object = new Robot(this.currentAsteroid);
				minedMinerals.removeAll(Arrays.asList("Uranium", "Carbon", "Iron"));
				break;
			case 2:
				//check if you already have teleportation gates
				// add to gates attribute
				if(gates == null) {
					gates = new TeleportationGate[2];
					gates[0] = new TeleportationGate();
					gates[1] = new TeleportationGate();
					minedMinerals.removeAll(Arrays.asList("Uranium", "WaterIce", "Iron", "Iron"));
				}
				break;
			case 3 :
				if(this.currentAsteroid.getSpaceStation()!=null) {
					object = new SpaceStation(this.currentAsteroid);
				}
				break;
			}
		}
		return object;
	}
	
	//Return type changed to string instead of craftable
	public String showCraftMenu() {
		return "1-Robot \n2-Teleportation Gate\n3-SpaceStation ";
	}
	
	//Removed pairGates() method as gates are paired by default
	
	private void setTimeOfDeath(){
		this.timeOfDeath = System.currentTimeMillis();
	}
	
	public void setTimeOfDeath(long time){
		this.timeOfDeath = time;
	}
	
	//might be useless
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
	
	//add to mine
	public int getCapacityLeft() {
		return 10 - this.minedMinerals.size();
	}
	
	public String getName() {
		return this.name;
	}

	public void dying() {
		this.setTimeOfDeath();
	}
	public void hide() {
		if(this.currentAsteroid.getHide() == true) {
			this.hidden = true;
		}
	}
}
