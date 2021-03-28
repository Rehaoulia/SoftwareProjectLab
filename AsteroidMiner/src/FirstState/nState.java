/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstState;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author mehdimo
 */
public class nState extends AbstractAppState  {

    private final Node rootNode;
    private final AssetManager assetManager;
    private Spatial localRootNode;
    

        public nState(SimpleApplication app){
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        //flyCam = app.getFlyByCamera();
    }
    
    @Override
    public void initialize(AppStateManager stateManager , Application app){
        super.initialize(stateManager, app);
        
        rootNode.attachChild(localRootNode);
    }
    
    
    @Override 
    public void update(float tpf){
    }
    
    @Override 
    public void cleanup(){
        rootNode.detachChild(localRootNode);
        
        super.cleanup();
    }
        
}
