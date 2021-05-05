

package CoreClasses;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public abstract class Traveler {
    public boolean hidden;
    protected Place currentPlace;
    private Spatial model;
    private Vector3f location;

    public Traveler(Vector3f loc) {
        location = loc ;
    }
    
    public Traveler(){}
    
     public void setModel(Spatial mod) {
         mod.setLocalTranslation(location);
        model = mod;
    }
     
    public Spatial getModel(){
        model.setLocalTranslation(location);
        return model ;
     }
    
    public void setLocation(Vector3f loc){
    
        location = loc ;
    }
  
     public Vector3f getLocation(){
    
       return location ;
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
