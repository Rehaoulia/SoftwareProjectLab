package Expolander;

import CoreClasses.Asteroid;
import CoreClasses.Carbon;
import CoreClasses.Iron;
import CoreClasses.Mineral;
import CoreClasses.Uranium;
import CoreClasses.WaterIce;
import States.HUD;
import States.HelpScreen;
import States.InputMan;
import States.PerihelionState;
import States.Placement;
import States.SettlerPlace;
import States.StartScreen;
import States.SunstormState;

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
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * 
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private StartScreen startScreen;
    private boolean starting;
    
    private HelpScreen helpScreen; 
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        starting = true;
        startScreen = new StartScreen(this, settings);
        stateManager.attach(startScreen);
        
        helpScreen = new HelpScreen(this, settings);
    }

    @Override
    public void simpleUpdate(float tpf) {
        if (startScreen.start && starting) {  
            starting = false;
            new Thread(() -> {
                stateManager.attach(new SettlerPlace(this));
                stateManager.attach(new Placement(this));
                stateManager.attach(new InputMan(this));
                stateManager.attach(new PerihelionState(this));
                stateManager.attach(new HUD(this, settings));
                //stateManager.attach(new SunstormState(this, settings));

                startScreen.guiNode.detachAllChildren();
                startScreen.cleanup();
                stateManager.detach(startScreen);
            }).start();
            startScreen.starting = true;
        }else if(startScreen.help){     //if help is clicked
            //detach the start screen
            startScreen.guiNode.detachAllChildren();
            startScreen.cleanup();
            stateManager.detach(startScreen);
            
            //attach the help screen
            stateManager.attach(helpScreen);
            
            //set help to false (otherwise it will always get inside this if)
            startScreen.help=false;
        }
        
        //if the help screen is closed
        if(helpScreen.close){
            //detach the help screen
            helpScreen.guiNode.detachAllChildren();
            helpScreen.cleanup();
            stateManager.detach(helpScreen);

            //attach the start screen again
            starting = true;
            startScreen = new StartScreen(this, settings);
            stateManager.attach(startScreen);

            //set the close to false again (otherwise it will always get inside this if)
            helpScreen.close=false;
        }
        
        //if exit is clicked
        if(startScreen.exit){
            //detach the start screen
            startScreen.guiNode.detachAllChildren();
            startScreen.cleanup();
            stateManager.detach(startScreen);
            
            //exit
            System.exit(0);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // TODO: add render code
    }
}
