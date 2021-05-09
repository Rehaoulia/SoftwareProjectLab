package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.util.ArrayList;

public class Asteroid extends Place {

	private boolean isHollow;
	private boolean isRadioActive;
	private boolean isPerihelion;
	private boolean isMineable;
	private boolean isBeingDrilled;
	private boolean isDestroyed;

	private int ID;
	private ArrayList<TeleportationGate> gates = new ArrayList<TeleportationGate>();
	private Geometry model;
	private Mineral mineral;
	private int depth;
	private SpaceStation station;
        private TeleportationGate gate ;

	public int radius;

	// need to have location aswell
	public Asteroid(int _ID, Vector3f loc, Mineral _mineral, int _radius) {
		super(loc);
		ID = _ID;
		mineral = _mineral;
		isHollow = false;
		isMineable = false;
		isBeingDrilled = false;
		isDestroyed = false;
		depth = 0;
		radius = _radius;
                isRadioActive = mineral.getClass().getSimpleName().equals("Uranium");
		// isAphelion = ??; we need location to set this

	}

	public Asteroid(int _ID, Vector3f loc, int _radius) { // this constructor works without mineral and sets hollow
		super(loc);
		ID = _ID;
		isHollow = true;
		isMineable = false;
		isBeingDrilled = false;
		isDestroyed = false;
		isRadioActive = false;
		depth = 0;
		radius = _radius;
		// model.setLocalScale(_radius);
	}

	// need to have location aswell
	public Asteroid(int _ID, Mineral _mineral, int _radius) {
		ID = _ID;
		mineral = _mineral;
		isHollow = false;
		isMineable = false;
		isDestroyed = false;
		depth = 0;
		radius = _radius;
                isRadioActive = mineral.getClass().getSimpleName().equals("Uranium");
	}

	public Asteroid(int _ID, int _radius) { // this constructor works without mineral and sets hollow
		ID = _ID;
		isHollow = true;
		isMineable = false;
		isDestroyed = false;
		isRadioActive = false;
		depth = 0;
		radius = _radius;
		// model.setLocalScale(_radius);

	}

	public void setModel(Geometry mod) {
		mod.setLocalTranslation(super.getLocation());

		model = mod;
	}
        
        public void removeModel(){
            model.removeFromParent();
            
        }

	public Geometry getModel() {
		model.setLocalTranslation(super.getLocation());
		return model.clone();
	}
        
        	public Geometry getModelNotCloned() {
		return model;
	}

	public int getID() {
		return ID;
	}

        public void setCloseGate(TeleportationGate tg){
        gate = tg;
        }
        public TeleportationGate getCloseGate(){
          return gate;
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
			depth++;
			if (!isHollow && !drillable())
				isMineable = true;
			return depth;
		} else {
			if (isRadioActive && isPerihelion)
				explode(); // if its radioactive then its not hollow
			else {
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

	public ArrayList<TeleportationGate> getGates() {
		return gates;
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
                if(!drillable() && !isMineable && !isDestroyed)
		isHollow = true;
	}

	public int getDepth() {
		return depth;
	}

	private void explode() {
		isDestroyed = true;
	}

	public boolean destroyed() {
		return isDestroyed;
	}

	public void setHollow(boolean hollow) {
		isHollow = hollow;
	}

	public void setPerihelion(boolean perihelion) {
		isPerihelion = perihelion;
	}

	public String viewInfo() {

		String str = "Asteroid: " + this.ID + "\nRadius: " + this.radius + "\nDepth:" + depth + "\nHollow: "
				+ Boolean.toString(isHollow) + "\nDrillable: " + Boolean.toString(drillable()) + "\nMineable :"
				+ Boolean.toString(isMineable) + "\nRadioactive :"+ Boolean.toString(isRadioActive)
                                + "\nPerihelion :"+ Boolean.toString(this.isPerihelion)
                                + "\nDestroyed :"+ Boolean.toString(this.isDestroyed)
                        ;
		return str;
	}

	public void setDepth(int radius2) {
		this.depth = radius2;
	}
}
