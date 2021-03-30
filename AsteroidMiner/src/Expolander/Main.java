package Expolander;

import CoreClasses.Asteroid;
import CoreClasses.Carbon;
import CoreClasses.Iron;
import CoreClasses.Mineral;
import CoreClasses.Uranium;
import CoreClasses.WaterIce;
import FirstState.Placement;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    
    
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    private Placement p;
    private Jungle j;

    @Override
    public void simpleInitApp() {
       stateManager.attach(new Placement(this));
    }

    @Override
    public void simpleUpdate(float tpf) {
   

           
       
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
