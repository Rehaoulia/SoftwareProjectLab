

package CoreClasses;

import com.jme3.math.Vector3f;


import java.util.ArrayList;
//import com.jme3.math.Vector3f;

public class Place {

        private Vector3f location; 
	private ArrayList<Traveler> currentTravelers;
       // private Node model;
        
        public Place(Vector3f loc){
            location = loc;
        }
        public Place (){
        location = new Vector3f(0,0,0);
        };
	
	public void addTraveler(Traveler traveler) {
		this.currentTravelers.add(traveler);
	}
	
	public void removeTraveler(Traveler traveler) {
		this.currentTravelers.removeIf(t -> t.equals(traveler));
	}
        
        public Vector3f getLocation(){
            return this.location;
        };
        
//        public void setModel(Node mod){
//            mod.setLocalTranslation(location);
//            model = mod ;
//        }
//        
//        public Node getModel(){
//            //model.setLocalTranslation(super.getLocation());
//           return model.clone(true);
//        }

        
//        public void setLocation(Vector3f pos){
//            location = pos;
//        };
}

