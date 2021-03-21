package CoreClasses;


import java.util.List;
import java.util.Map;

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

    private boolean deployed;
    private TeleportationGate pairedGate;

    public TeleportationGate() {
        deployed = false;
    }


    public TeleportationGate getPairedGate() {
        return (deployed) ? pairedGate : null;
    }

    public void setPairGate(TeleportationGate pair) {
        if (deployed == false && pair.isPaired() == false) {
            pairedGate = pair;
            pairedGate.deployed = true;                // paired gate connot have another pair !
            deployed = true;                        // otherwise it turns to be recursive
        }
    }

    public boolean isPaired() {
        return deployed;
    }

}
