package CoreClasses;

import com.jme3.math.Vector3f;
import java.util.ArrayList;

public class Place {
	
        private Vector3f location; 
	private ArrayList<Traveler> currentTravelers;
        
        public Place(Vector3f loc){
            location = loc;
        }
	
	public void addTraveler(Traveler traveler) {
		this.currentTravelers.add(traveler);
	}
	
	public void removeTraveler(Traveler traveler) {
		this.currentTravelers.removeIf(t -> t.equals(traveler));
	}
        
        public Vector3f getLocation(){
            return location;
        };
}
