package CoreClasses;

import java.util.ArrayList;
import java.io.IOException;

import View.Menu;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.Objects;

public class Settler extends Traveler {

	private String name;
	public int ID;
	private ArrayList<String> minedMinerals; // minedMinerals should be changed to a string ArrayList, explanation below										// in checkRequiredMaterial         public  ArrayList<TeleportationGate> gates;
        //public  Vector3f[] arrGates;
	private long timeOfDeath;
	private boolean dead;
	private Asteroid currentAsteroid;
	private int nGate;
        public ArrayList<TeleportationGate> gates;
        private boolean teleported;

        public Settler(String name,Vector3f loc ) {
                super(loc);
		this.name = name;
		this.timeOfDeath = 0;
		this.dead = false;
		minedMinerals = new ArrayList<>();
                gates = new ArrayList<>();
                nGate =0 ;
                
                
	}
        
	public Settler(String name, int ID) {
		this.ID = ID;
		this.name = name;
		this.timeOfDeath = 0;
		this.dead = false;
		minedMinerals = new ArrayList<>();
                gates = new ArrayList<>();
                nGate =0 ;
               
		
	}

	public void setAsteroid(Asteroid asteroid) {
		this.currentAsteroid = asteroid;
	}

	public Asteroid getAsteroid() {
		return currentAsteroid;
	}

//	public void travel(Asteroid asteroid) {
//		
//		if(this.getHidden()) {
//		unhide();
//		currentAsteroid.getUnhide();   
//		// if we dont unhide asteroid, it gonna stay hollow false !!
//		}
//		
//		setAsteroid(asteroid);
//		Controller.updateAsteroid(this.currentAsteroid);
//	}

	public Node putGate(Node gate) {
            Vector3f loc;
 
            if(Objects.isNull(currentAsteroid.getCloseGate())){

                        loc = this.getLocation().add(0,0,6) ;
            System.out.println("  puting Gate ");
            if(nGate ==2 && Objects.isNull(currentAsteroid.getCloseGate()) ){
            
            System.out.println("  put first ");
        
          // Vector3f loc = this.getLocation().add(0,0,6) ;
            TeleportationGate firstG = new TeleportationGate(loc);
            Node newGate = gate;
            firstG.setModel(newGate);
            firstG.setGate(this.currentAsteroid);
            gates.add(firstG);
            int i = gates.size()-1;
            currentAsteroid.setCloseGate(firstG);
            gates.get(i).setGate(this.currentAsteroid);
            System.out.println("index : "+i +" loc: "+ gates.get(i).getLocation()+ "    " );
            nGate--;

            return firstG.getModel();

            }else
                if(nGate ==1   ){
            System.out.println("  put sec ");
            int oldG = gates.size()-1;
  
            TeleportationGate secG = new TeleportationGate(loc);
            
            Node newGate = gate.clone(true);
            secG.setModel(newGate);
           
            currentAsteroid.setCloseGate(secG);
            gates.get(oldG).setGate(this.currentAsteroid , secG );
            
            gates.add(secG);
            int index = gates.size()-1;
           
          
            System.out.println("index : "+index +" loc: "+ gates.get(index).getLocation()+ "    " );
            System.out.println("index : "+(index) +" loc: "+ gates.get(index).getPairedGate().getLocation()+ "    " );
            nGate--;

            return secG.getModel();
            }else{
			System.out.println("\n\n ============ you connot place a gate here");
                        System.out.println("size : "+ gates.size() +"  ");
                        System.out.println("nGate : "+ nGate +"  ");
                        return null;
                }
            }
            return null;
 }

            
        
	 public void teleport(TeleportationGate tg) {
	        if (tg.isPaired()) {
	            currentAsteroid = tg.getPairedGate().getNeighbour();
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
	public Craftable craft(int craftable, Controller con) {
		Craftable c = null;
		if (checkRequiredMaterial(craftable)) {

			switch (craftable) {
			case 0:
				c = new Robot(this.currentAsteroid);
				minedMinerals.remove(minedMinerals.indexOf("Uranium"));
				minedMinerals.remove(minedMinerals.indexOf("Carbon"));
				minedMinerals.remove(minedMinerals.indexOf("Iron"));
				con.addRobot((Robot)c);
				break;
			case 1:
//				if(this.craftGate()){
//					this.putGate();
//				}
				break;
			case 2:
				if (this.currentAsteroid.getSpaceStation() == null) {
					c = new SpaceStation(this.currentAsteroid);
					con.addSpaceStation((SpaceStation)c);
					con.addSpaceStationToAsteroid((SpaceStation)c, this.currentAsteroid.getID());
				}
				break;
			default:
				return null;
			}
		} else
			System.out.println("Not enough materials\n");
		return c;
	}

	public int showCraftMenu(Controller con) throws IOException {
		int res = 1;
		ArrayList<String> menuItems = new ArrayList<String>();
		menuItems.add("Robot || Uranium + Carbon + Iron");
		menuItems.add("Teleportation Gate || Uranium + WaterIce + 2 x Iron");
		menuItems.add("SpaceStation || 3xUranium + 3xCarbon + 3xIron + 3xWaterIce");
		Menu menu = new Menu(menuItems);
		Craftable c = craft(menu.display(), con);
		if (c!=null && c.getClass().getSimpleName().equals("SpaceStation")) {
			System.out.println("Spacestation building started!");
			res = -1;
		}else if(c != null) {
			System.out.println(c.getClass().getSimpleName() + " Created successfully");
			res = 1;
		}
		return res;
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

	@Override
	public void die() {
		this.dead = true;
		System.out.println("You are dead :(");
	}

	public void dying(Controller c) {
		this.setTimeOfDeath();
		try {
			//wait for revival for index seconds
			int i=3; 
			while(i>0){
				long millis = System.currentTimeMillis();
                System.out.println(name+" waiting for revival...");
                i--;
                Thread.sleep(1000 - millis % 1000); //every 1 second
				if(timeOfDeath==-1) break;	//if revived then break;
			}
			//if not revived then die
			if(timeOfDeath!=-1){
				die();
				c.removePlayer(ID);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void hide() {
		if (this.currentAsteroid.getHide() == true) {
			this.hidden = true;
		}
	}

	public String viewInfo() {
		String str = "Name: " + this.name + "\t\tHidden:" + Boolean.toString(hidden) + "\nminedMinerals: "
				+ String.join(" - ", minedMinerals) + "\n#ofTeleportationGate: " + nGate;
		return str;
	}


	public void setID(int newID){ ID=newID;}

	
	public void displayResources(Controller c) throws IOException {
		Menu menu = new Menu(this.minedMinerals);
		int choice = menu.display();
		for(int i = 0; i < this.minedMinerals.size(); i++) {
			if(i == choice) {
				addResourceToSpaceStation(minedMinerals.get(i), c);
			}
		}
	}
	
	public void addResourceToSpaceStation(String mineral, Controller c) {
		minedMinerals.remove(minedMinerals.indexOf(mineral));
		c.addMineralToSpaceStation(this.currentAsteroid.getSpaceStation().getID(), mineral);
		c.checkSpaceStation(this.currentAsteroid.getSpaceStation().getID());
	}
	
	public boolean craftGate(){
            System.out.println("craft");
            switch (nGate) {
                case 0:
                      if( minedMinerals.contains("Uranium") &&
                        minedMinerals.contains("WaterIce") &&
                        minedMinerals.contains("Iron") &&
                        minedMinerals.contains("Iron") ){
                    minedMinerals.remove(minedMinerals.indexOf("Uranium"));
                    minedMinerals.remove(minedMinerals.indexOf("WaterIce"));
                    minedMinerals.remove(minedMinerals.indexOf("Iron"));
                    minedMinerals.remove(minedMinerals.indexOf("Iron"));
                    
                    nGate =2;
                    return true ;
                    } else return false;
                    
                case 1:
                    return true;
                default:
                    return false;
            }
        }


    public void addMineral(String s) {
		minedMinerals.add(s);
    }

    public void getMatforGate() {
		minedMinerals.add("Iron");
		minedMinerals.add("Iron");
		minedMinerals.add("WaterIce");
		minedMinerals.add("Uranium");
    }

    public int getNumberOfGates() {
        return nGate;
    } 

    public void setTeleported(boolean b) {
        teleported = b;
    }
    public boolean getTeleported() {
       return teleported;
    }
   
}
