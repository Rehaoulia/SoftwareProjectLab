/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import java.util.Random;

/**
 *
 * @author asus
 */
public class SunstormState extends AbstractAppState {
    private final Node rootNode;
    private Spatial localRootNode;
    private final AssetManager assetManager;
    private final InputManager inputManager;
    private final AppStateManager stateManager;
    
    //gui text parameters
    AppSettings settings;
    private final Node guiNode;
    BitmapText alarmText;      //contains the alarm text
    
    //sunstorm scheduling parameters
    private boolean scheduling; //true if the cycle is not scheduled yet
    private boolean happening;  //true if the sunstorm is happening
    private int wavelength;     //wavelength of the sunstorm cycle
    Random r = new Random();    //random number for generating the wavelength 
    int alarmFlag, sunstormFlag;//contain the duration of the alarm and the sunstorm
    float time=0;               //used for calculating seconds               
    int waitingTime=0;          //contains the time before the alarm happens
    
    private Settler settler;    //the settler in the game
    
    //State constructor
    public SunstormState(SimpleApplication app, AppSettings _settings) {
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        stateManager = app.getStateManager();
    
        guiNode = app.getGuiNode();
        settings = _settings;
    }
    
    //state initializer
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        settler = app.getStateManager().getState(SettlerPlace.class).s;
        
        happening=false;    //the sunstorm is not happening at the start
        scheduling=true;    //the scheduling should be the activated
        
        /* ALARM TEXT CONFIGURATION */
        //import the font
        BitmapFont guiFont = assetManager.loadFont("Interface/Fonts/ROG2.fnt");
        //guiFont.getCharSet().setRenderedSize(81000);
        alarmText = new BitmapText(guiFont, false);

        alarmText.setSize(36);  //Font size
        alarmText.setColor(ColorRGBA.White);                        // font color
        //Position of the text
        
        guiNode.attachChild(alarmText);
    }
    
    //state update loop
    @Override
    public void update(float tpf) {
        //if the sunstorm cycle is not scheduled yet
        if(scheduling){
            //generating a random wavelength
            int low = 30;
            int high = 100;
            //wavelength = r.nextInt(high-low) + low;
            wavelength=16;
            
            //initializing the flags
            waitingTime=0;
            alarmFlag=10;
            sunstormFlag=5;
            
            //stop the scheduling
            scheduling=false;
            
        }else if((!scheduling)&&(waitingTime<wavelength-15)) {
            //wait for wavelength - 15 seconds
            time += tpf;
            if (time > 1) {
                waitingTime++;
                time = 0;
            }
        }
        
        //if 15 seconds are left in the wavelength
        if(waitingTime>=wavelength-15){
            //if the alarm did not end
            if(alarmFlag>0){
                //show how many seconds are left until the sunstorm
                time += tpf;
                if (time > 1) {
                    alarmText.setText(Integer.toString(alarmFlag));
                    alarmText.setLocalTranslation(settings.getWidth() / 2 - alarmText.getLineWidth()/2 , settings.getHeight() - 50 , 0);
                    alarmFlag--;
                    waitingTime++;
                    time = 0;
                }
            }
            //if the alarm is finished
            else{
                alarmText.setText("");
                happening=true; //start the sunstorm
                waitingTime=0;
            }
        }
        
        //if the sunstorm is happening
        if((happening==true)&&(sunstormFlag>0)){
            time += tpf;
            if (time > 1) { 
                sunstormFlag--;
                time = 0;
            }
            
            alarmText.setText("Sunstorm happening!!");
            alarmText.setLocalTranslation(settings.getWidth() / 2 - alarmText.getLineWidth()/2 , settings.getHeight() - 50, 0);
            //kill the unhidden settlers
            if(!settler.hidden)
                settler.die();
            
            //kill the unhidden robots
        }
        
        //if the sunstorm is finished 
        if(sunstormFlag==0){
            happening=false;    //stop the sunstorm
            scheduling=true;    //start the scheduling
            alarmText.setText("");
        }
    }
    
    //state destructor
    @Override
    public void cleanup() {
        super.cleanup();
    }
}
