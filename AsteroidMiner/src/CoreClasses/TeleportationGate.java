package CoreClasses;

public class TeleportationGate {
	
	private boolean deployed;
	private TeleportationGate pairedGate;
	
	public TeleportationGate(){
		deployed = false;
	}

	
	public TeleportationGate getPairedGate() {
		return (deployed)? pairedGate: null;
	}
	
	public void setPairGate(TeleportationGate pair) {
		if(deployed == false && pair.isPaired()==false ) {				
		pairedGate = pair;						
		pairedGate.deployed =true ;				// paired gate connot have another pair !
		deployed = true;						// otherwise it turns to be recursive 
		}
	}
	public boolean isPaired() {return deployed; }

}
