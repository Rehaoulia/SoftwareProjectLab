package CoreClasses;

import java.util.List;

public class Asteroid {
	
	private boolean isHollow;
	private boolean isRadioActive;
	private boolean isPerihelion;
	private boolean isMineable=false;
	private boolean isBeingDrilled=false;
	private boolean isDestroyed=false;
	
	private int ID;
	private List<TeleportationGate> gates;
	
	private Mineral mineral ;
	private int depth;
	private SpaceStation station;
	
	public int radius;
	
	
													//need to have location aswell
	public Asteroid(int _ID, Mineral _mineral) {
		ID=_ID;
		mineral=_mineral;
		isHollow =false;
		// isAphelion = ??;  we need location to set this
		// if uranium then radioActive
		
	} 
	public Asteroid(int _ID) {				// this constructor works without mineral and sets hollow
		ID=_ID;
		isHollow = true;
	} 
	
	public int getID() {
		return ID;
	}
	public boolean hollow() {return isHollow;}
	public boolean radioActive() { return  ((radius/2) <= depth) ?  isRadioActive : false ;}
	public boolean mineable() {return isMineable;}
	public boolean perihelion() {return isPerihelion;}
	public boolean drillable() {return ( !(isMineable || isBeingDrilled) )? true:false ;}
	
	public int getsDrill() 		// (Settler settler) we can add drillingsettler attr to
													// specify and limit drilling action
	{      													 // this function returns -1 if its mineable
		if(radius != depth && !isDestroyed) {
		isBeingDrilled = true;
		depth++;               								 // or any other mount we will see
		return depth;
		}else {
			if(isRadioActive && isPerihelion ) explode();   // if its radioactive then its not hollow
			else {
			isBeingDrilled = false; 
			if(!isHollow)isMineable =true;
			}
			return -1;
		}
	}
	
	public Mineral getsMine()     							// returns null if its not mineable
	{
		if( (!isHollow || isMineable) && !isDestroyed) {
		isHollow =true ;
		isMineable =false; 
		return mineral;
		}else return null;
	}
	
	public boolean getsFill(Mineral _mineral) {
		if(isHollow && getsDrill()== -1 && !isDestroyed) {
			mineral = _mineral ;
			return true ;
		}
		else return false ;	
	}
	public List<TeleportationGate> getGates() { return gates;}
	public void setGates(TeleportationGate nGate) { gates.add(nGate); }
	public SpaceStation getSpaceStation() {return station;}
	public void setSpaceStation(SpaceStation _station) {station = _station;}
	
	
	
	///////////////////////////// ive added these they are not in class diagram
	
	
	
	private void explode() {
		isDestroyed =true ;
		// animation();
	}
	public boolean destroyed() { return isDestroyed; }
	
	public String viewInfo() { 
		String str = "Radius: "+ this.radius + "\tHollow: "+ Boolean.toString(isHollow) +"\nDrillable: "+Boolean.toString(drillable()) 
		+ "\tMineable :"+ Boolean.toString(isMineable) ;
		return str; }
	
	//  public getLocation() {}
	
	
	
	
}
