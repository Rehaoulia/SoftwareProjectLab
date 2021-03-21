package CoreClasses;

public class TeleportationGate {
	
	private boolean deployed;
	private boolean paired;
	private TeleportationGate pairedGate;
	private Asteroid neighbourAsteroid ;
	
	
	public TeleportationGate(){
		deployed = false;
		paired = false;
	}

	
	public TeleportationGate getPairedGate() {
		return (paired)? pairedGate: null;
	}
	
	public void setGate(Asteroid gate_na) {
		if(!paired)
		neighbourAsteroid= gate_na;
		deployed = true ;
	}
	
	public void setGate(Asteroid pGate_na ,TeleportationGate pair) {
		if(deployed == true && pair.isPaired()==false ) {				
		this.pairedGate = pair;	
		this.paired = true;	
		pair.pairedGate = this;
		pair.neighbourAsteroid = pGate_na;
		pairedGate.deployed =true ;	
		pairedGate.paired = true ;
							
		}
	}
	public boolean isPaired() {return paired; }
	
	public Asteroid getNeighbour() {
		return neighbourAsteroid ;
	}
	public Asteroid passThrough() {
		return pairedGate.getNeighbour();
	}

}
