

package CoreClasses;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
//import com.jme3.math.Vector3f;

public class Place {

        private Vector3f location; 
	private ArrayList<Traveler> currentTravelers;
        
        public Place(Vector3f loc){
            location = loc;
        }
        public Place (){};
	
	public void addTraveler(Traveler traveler) {
		this.currentTravelers.add(traveler);
	}
	
	public void removeTraveler(Traveler traveler) {
		this.currentTravelers.removeIf(t -> t.equals(traveler));
	}
        
        public Vector3f getLocation(){
            return location;
        };
        public void setLocation(Vector3f pos){
            location = pos;
        };
}

