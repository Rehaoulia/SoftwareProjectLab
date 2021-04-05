package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.util.List;

public class Asteroid extends Place {

	private boolean isHollow;
	private boolean isRadioActive;
	private boolean isPerihelion;
	private boolean isMineable;
	private boolean isBeingDrilled;
	private boolean isDestroyed;

	private int ID;
	private List<TeleportationGate> gates;
        private Spatial model;
	private Mineral mineral;
	private int depth;
	private SpaceStation station;
        

	public int radius;

	// need to have location aswell
	public Asteroid(int _ID,Vector3f loc , Mineral _mineral, int _radius) {
		super(loc);
                ID = _ID;
		mineral = _mineral;
		isHollow = false;
		isMineable = false;
		isBeingDrilled = false;
		isDestroyed = false;
		depth = 0;
		radius = _radius;
                
                
		if (mineral.toString().equals("Uranium"))
			isRadioActive = true;
		else
			isRadioActive = false;
		// isAphelion = ??; we need location to set this

	}

	public Asteroid(int _ID,Vector3f loc , int _radius) { // this constructor works without mineral and sets hollow
		super(loc);
                ID = _ID;
		isHollow = true;
		isMineable = false;
		isBeingDrilled = false;
		isDestroyed = false;
		isRadioActive = false;
		depth = 0;
		radius = _radius;
                //model.setLocalScale(_radius);
	}
        
        public void setModel(Spatial mod){
            mod.setLocalTranslation(super.getLocation());
            model = mod ;
        }
        
        public Spatial getModel(){
            model.setLocalTranslation(super.getLocation());
           return model.clone() ;
        }


	public int getID() {
		return ID;
	}
        

	public boolean hollow() {
		return isHollow;
	}

	public boolean radioActive() {
		return ((radius / 2) <= depth) ? isRadioActive : false;
	}

	public boolean mineable() {
		return isMineable;
	}

	public boolean perihelion() {
		return isPerihelion;
	}

	public boolean drillable() {
		return (this.getDepth() != radius) ? true : false;
	}

	public int getsDrill() // (Settler settler) we can add drillingsettler attr to
							// specify and limit drilling action
	{ // this function returns -1 if its mineable

		if (drillable() && !isDestroyed) {
			isBeingDrilled = true;
			depth++;
			if (!isHollow && !drillable())
				isMineable = true;
			return depth;
		} else {
			if (isRadioActive && isPerihelion)
				explode(); // if its radioactive then its not hollow
			else {
				isBeingDrilled = false;
				if (!isHollow)
					isMineable = true;
			}
			return -1;
		}
	}

	public Mineral getsMine() // returns null if its not mineable
	{
		if (isMineable && !isDestroyed) { // if its not hollow and its not mineable
			isHollow = true; // then someone is hiding !

			isMineable = false;
			return mineral;
		} else
			return null;
	}

	public boolean getsFill(Mineral _mineral) {

		if (isHollow && !drillable() && !isDestroyed) {
			mineral = _mineral;
			isHollow = false;
			isMineable = true;
			return true;
		} else
			return false;
	}

	public List<TeleportationGate> getGates() {
		return gates;
	}
        
        @Override
        public Vector3f getLocation(){
            return super.getLocation();
        }

	public void setGates(TeleportationGate nGate) {
		gates.add(nGate);
	}

	public SpaceStation getSpaceStation() {
		return station;
	}

	public void setSpaceStation(SpaceStation _station) {
		station = _station;
	}

	public boolean getHide() {
		if (isHollow && !drillable() && !isMineable && !isDestroyed) {
			isHollow = false;
			return true;
		} else
			return false;
	}
	
	public void getUnhide() {
		isHollow = true;
	}

	public int getDepth() {
		return depth;
	}

	private void explode() {
		isDestroyed = true;
		// animation();
	}

	public boolean destroyed() {
		return isDestroyed;
	}

	public String viewInfo() {
		String str = "Radius: " + this.radius + "\t\tDepth:" + depth + "\t\tHollow: " + Boolean.toString(isHollow)
				+ "\nDrillable: " + Boolean.toString(drillable()) + "\t\tMineable :" + Boolean.toString(isMineable);
		return str;
	}

	// public getLocation() {}
}
