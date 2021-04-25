/*
 * auth : Mehdi Moazami
 * team : BugBuster
 * 
 */
package States;

import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author mehdimo
 */
public class InputMan extends AbstractAppState  {

    private final Node rootNode;
    private final AssetManager assetManager;
    private Spatial localRootNode;
    private final com.jme3.input.InputManager inputManager;
    private final AppStateManager stateManager;
    private Node curNode;
    

        public InputMan(SimpleApplication app){
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        stateManager = app.getStateManager();
        
        //flyCam = app.getFlyByCamera();
    }
    
    @Override
    public void initialize(AppStateManager stateManager , Application app){
        super.initialize(stateManager, app);
        
        curNode = new Node("curNode");
        rootNode.attachChild(curNode);
        inputManager.addMapping("Settler Drill", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addListener(actionListener, new String[]{"Settler Drill"});
        
        
        //rootNode.attachChild(localRootNode);
    }
    
    
    private ActionListener actionListener = new ActionListener() {
  public void onAction(String name, boolean keyPressed, float tpf) {
            float time = 0;
           
            if (name.equals("Settler Drill") && !keyPressed && getSettler().access()  ) {  
                    Settler s = getSettler(); 
                   /// heereee 
                         Spatial diged = s.getAsteroid().getModel();
                         diged.setLocalScale(6f);
                        
                        s.drill();
                       // rootNode.attachChild(diged);
                   
                    while (time<8f){
                        time +=tpf; 
                        curNode.detachChild(diged);
                       // if(time%1==0){
                        float rand = ThreadLocalRandom.current().nextFloat()*10 -5 +3  ;
                        diged.setLocalScale(rand);
                        curNode.attachChild(diged);
                       // }
                        
                    }
                    stateManager.getState(Placement.class).updateAsteroid(s.getAsteroid().getID(), s.getAsteroid());
                   System.out.println(s.getAsteroid().viewInfo());
                  curNode.detachChild(diged);
                   
                   
            }
            
      
        }
    };
    
    
    private Settler getSettler(){
          return this.stateManager.getState(SettlerPlace.class).getSettler();
    }
    private Node getCurNode(){
          return this.stateManager.getState(Placement.class).getCurNode();
    }
    
    
    
    @Override 
    public void update(float tpf){
         // curNode = getCurNode();
    }
    
    @Override 
    public void cleanup(){
        //rootNode.detachChild(localRootNode);
        
        super.cleanup();
    }
        
}
