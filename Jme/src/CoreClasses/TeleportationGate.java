package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
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
		if(!paired){
		neighbourAsteroid= gate_na;
		this.deployed = true ;
                this.paired =false;
                }
	}
	
	public void setGate(Asteroid pGate_na ,TeleportationGate pair) {
		if(deployed == true && pair.isPaired()==false && this.isPaired()==false ) {				
		pair.setPair(this);
                this.pairedGate = pair;	
                this.paired = true;
                pairedGate.paired = true ;
		pair.neighbourAsteroid = pGate_na;
		pairedGate.deployed =true ;	
		
							
		}
	}
        public void setPair(TeleportationGate pair){
            if(!pair.isPaired())
            this.pairedGate =pair ;
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
