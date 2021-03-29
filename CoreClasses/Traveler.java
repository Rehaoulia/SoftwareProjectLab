//package CoreClasses;
//
//import com.jme3.math.Vector3f;
//
//public abstract class Traveler {
//    protected boolean hidden;
//    protected Place currentPlace;
//    protected Vector3f location; 
//    
//    
//    public Traveler(Vector3f loc){
//            location = loc;
//        }
//
//    public boolean getHidden() {
//        return hidden;
//    }
//
//    public void setHidden(boolean hidden) {
//        this.hidden = hidden;
//    }
//
//    public void unhide() {
//
//        setHidden(false);
//        
//
//    }
//    
//     public Vector3f getLocation(){
//            return location;
//        };
//
//      public void setLocation(Vector3f loc){
//             location = loc;
//        };
//
//      
//    public void teleport(TeleportationGate tg) {
//        if (tg.isPaired()) {
//            currentPlace = tg.getPairedGate().getNeighbour();
//        }
//    }
//
//
//    public abstract void hide() throws InterruptedException;
//
//    public abstract void drill();
//
//    public abstract void die();
//}


package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public abstract class Traveler extends Place {
    protected boolean hidden;
    protected Place currentPlace;
    private Spatial model;

    public Traveler(Vector3f loc) {
        super(loc);
    }

    public void setModel(Spatial mod) {
        model = mod;
    }

    public Spatial getModel(){
        return model ;
     }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void unhide() {

        setHidden(false);

    }

    public void teleport(TeleportationGate tg) {
        if (tg.isPaired()) {
            currentPlace = tg.getPairedGate().getNeighbour();
        }
    }

    public abstract void hide() throws InterruptedException;

    public abstract void drill();

    public abstract void die();
}
