/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Asteroid;
import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author asus
 */
public class PerihelionState extends AbstractAppState {
    private final Node rootNode;
    private Spatial localRootNode;
    private final AssetManager assetManager;
    private final InputManager inputManager;
    private final AppStateManager stateManager;
    public ArrayList<Asteroid> explodingAsteroids = new ArrayList<Asteroid>();
    public ArrayList<Asteroid> sublimingAsteroids = new ArrayList<Asteroid>();
    
    ArrayList<Asteroid> tempExplodingAsteroids ;
    ArrayList<Asteroid> tempSublimingAsteroids;
    
    
    Random r = new Random();    //random number for generating the wavelength 
    private Settler settler;    //the settler in the game
    
    Application app;
    
    //State constructor
    public PerihelionState(SimpleApplication app) {
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        stateManager = app.getStateManager();
    }
    
    //state initializer
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        settler = stateManager.getState(SettlerPlace.class).s;
        
        this.app=app;
        
        //these contain all the asteroids that have uranium or waterIce in them
        tempExplodingAsteroids =  stateManager.getState(Placement.class).explodingAsteroids;
        tempSublimingAsteroids =  stateManager.getState(Placement.class).sublimingAsteroids;
        
        //random coordinates for the perihelion state
        double perihelionX = -100 + (200) * r.nextDouble(); //x is between -100 and 100
        double perihelionY = -100 + (200) * r.nextDouble(); //y is between -100 and 100
        
        double x,y;
        //only add those asteroids that are at the perihelion position (randomized for each game)
        //exploding asteroids
        for(int i=0; i<tempExplodingAsteroids.size();i++){
            
            //current asteroid x and y coordinates
            x = tempExplodingAsteroids.get(i).getLocation().x;
            y = tempExplodingAsteroids.get(i).getLocation().y;
            
            //if x and y are higher than the perihelion coordinates add the asteroid to the exploding asteroids
            //if((x > perihelionX)&&(y > perihelionY)){
                //just set the perihelion of that asteroid to true
                app.getStateManager().getState(Placement.class).explodingAsteroids.get(i).setPerihelion(true);
            //}
        }
        
        //subliming asteroids
        for(int i=0; i<tempSublimingAsteroids.size();i++){
            
            //current asteroid x and y coordinates
            x = tempSublimingAsteroids.get(i).getLocation().x;
            y = tempSublimingAsteroids.get(i).getLocation().y;
            
            //if x and y are higher than the perihelion coordinates add the asteroid to the exploding asteroids
            if((x > perihelionX)&&(y > perihelionY)){
                app.getStateManager().getState(Placement.class).sublimingAsteroids.get(i).setPerihelion(true);
            }
        }
    }
    
    //state update loop
    @Override
    public void update(float tpf) {
        /* EXPLOSION */
        
        /*THIS PART WORKS*/
        //It does find the destroyed asteroid and call the removing method

        //go through the exploding asteroids in the placement class
        for(int i=0; i<app.getStateManager().getState(Placement.class).explodingAsteroids.size();i++){
            
            //if an asteroid is destroyed
            if(app.getStateManager().getState(Placement.class).explodingAsteroids.get(i).destroyed()){
            
                //remove it from the screen
                app.getStateManager().getState(Placement.class).removeAsteroid(
                        app.getStateManager().getState(Placement.class).explodingAsteroids.get(i));
                
                //if it's removed then the one after it takes its place. That one should be checked too
                i--;
            }
        }
        
        /* SUBLIMATION */
        
        //go through the subliming asteroids in the placement class
        for(int i=0; i<app.getStateManager().getState(Placement.class).sublimingAsteroids.size();i++){
            
            //if an asteroid is at perihelion and drilled through
            if((app.getStateManager().getState(Placement.class).sublimingAsteroids.get(i).perihelion())&&
              (!app.getStateManager().getState(Placement.class).sublimingAsteroids.get(i).drillable())){
                System.out.println("\nhere\n");
                
                //make it hollow
                app.getStateManager().getState(Placement.class).sublimingAsteroids.get(i).getsMine();
                
                //remove it from the subliming asteroids
                app.getStateManager().getState(Placement.class).sublimingAsteroids.remove(i);
                
                //if it's removed then the one after it takes its place. That one should be checked too
                i--;
            }
        }
    }
    
    //state destructor
    @Override
    public void cleanup() {
        super.cleanup();
    }
}
