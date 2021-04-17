package CoreClasses;

//import com.jme3.math.Vector3f;

public class TeleportationGate extends Place {

	
	private boolean deployed;
	private boolean paired;
	private TeleportationGate pairedGate;
	private Asteroid neighbourAsteroid ;
	
	
	public TeleportationGate(){
               // super(new Vector3f(0,0,0));
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

    public boolean isDeployed() {
        return deployed;
    }

}
