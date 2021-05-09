package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class TeleportationGate extends Place {

	
	private boolean deployed;
	private boolean paired;
	private TeleportationGate pairedGate;
	private Asteroid neighbourAsteroid ;
	 private Node model; 
        final private Vector3f loc;
	
	public TeleportationGate(){
                super(new Vector3f(0,0,0));
		deployed = false;
		paired = false;
                loc = new Vector3f(0,0,0);
	}

        public TeleportationGate(Vector3f sPos){
                super(sPos);
                loc = sPos;
		deployed = false;
		paired = false;
	}
	
        @Override
        public Vector3f getLocation(){
          return loc;
        };
        
        
        public void setModel(Node mod){
            mod.setLocalTranslation(this.getLocation());
            model = mod ;
        }
        
        public Node getModel(){
            //model.setLocalTranslation(super.getLocation());
           return model.clone(true);
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
