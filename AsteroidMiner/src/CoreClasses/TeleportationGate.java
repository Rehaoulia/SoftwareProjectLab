package CoreClasses;

public class TeleportationGate {
	
	private boolean deployed;
	private boolean paired;
	private TeleportationGate pairedGate;
	
	public TeleportationGate(){
		deployed = false;
		paired = false;
	}

	
	public TeleportationGate getPairedGate() {
		return (paired)? pairedGate: null;
	}
	
	public void setGate() {
		if(!paired)
		deployed = true ;
	}
	
	public void setGate(TeleportationGate pair) {
		if(deployed == true && pair.isPaired()==false ) {				
		this.pairedGate = pair;	
		this.paired = true;	
		pair.pairedGate = this;
		pairedGate.deployed =true ;	
		pairedGate.paired = true ;
							// otherwise it turns to be recursive 
		}
	}
	public boolean isPaired() {return paired; }

}
