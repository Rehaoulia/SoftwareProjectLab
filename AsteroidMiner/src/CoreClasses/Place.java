package CoreClasses;

import java.util.ArrayList;

public class Place {
	
	private ArrayList<Traveler> currentTravelers;
	
	public void addTraveler(Traveler traveler) {
		this.currentTravelers.add(traveler);
	}
	
	public void removeTraveler(Traveler traveler) {
		this.currentTravelers.removeIf(t -> t.equals(traveler));
	}
}
