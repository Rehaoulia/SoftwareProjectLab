/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Asteroid;
import CoreClasses.Carbon;
import CoreClasses.Iron;
import CoreClasses.Mineral;
import CoreClasses.Uranium;
import CoreClasses.WaterIce;
import CoreClasses.Settler;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;

import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
/**
 *
 * @author mehdimo
 */
public class SettlerPlace extends AbstractAppState  {

   
    private final Node rootNode;
    private final Node localRootNode = new Node("intro");
    private final AssetManager assetManager;
    private final FlyByCamera flyCam;
    private final InputManager inputManager;
    private boolean front = false, back = false, left = false, right = false,
            rotatex=false,rotatey=false,rotatexm =false,rotateym=false, rotate=false , rotating =false;
    private ChaseCamera chaseCam;
    private Geometry settlerGeom;
    private final Camera cam;
    private CharacterControl playerControl;
    private Spatial player;
    private final Vector3f playerDirection = Vector3f.ZERO;
    private final Quaternion playerRotation;
    private Vector3f settlerPos;
    private Settler s;
  

    public SettlerPlace(SimpleApplication app) {
        this.playerRotation = Quaternion.ZERO;
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        flyCam = app.getFlyByCamera();
        cam = app.getCamera();
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        rootNode.attachChild(localRootNode);

        flyCam.setMoveSpeed(3);

        localRootNode.attachChild(loadSettler());

       player = localRootNode.getChild("pNode");
        BoundingBox boundingbox = (BoundingBox) player.getWorldBound();
        CapsuleCollisionShape playerShape = new CapsuleCollisionShape(boundingbox.getXExtent(),
                boundingbox.getYExtent());
        playerControl = new CharacterControl(playerShape, 1.0f);
        player.addControl(playerControl);
        

        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Rotating",new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Forward");
        inputManager.addListener(actionListener, "Backward");
        inputManager.addListener(actionListener, "Left");
        inputManager.addListener(actionListener, "Right");
        inputManager.addListener(actionListener, "Rotating");
        inputManager.addMapping("RotateX", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("RotateX_negative", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("RotateY", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("RotateY_negative", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(analogListener,"RotateX");
        inputManager.addListener(analogListener,"RotateX_negative");
        inputManager.addListener(analogListener,"RotateY");
        inputManager.addListener(analogListener,"RotateY_negative");
           
        
        
        
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, settlerGeom, inputManager);
        chaseCam.setSmoothMotion(true);
        settlerPos = settlerGeom.getLocalTranslation();
       
    }
    
    
    private final AnalogListener analogListener =  new AnalogListener() {

        @Override
        public void onAnalog(String name, float value, float tpf) {
            
            float x=0.0f,y=0.0f , speed=0.09f;
           
            rotatex = ( "RotateX".equals(name)) ;
            rotatey = ( "RotateY".equals(name)) ;
            rotatexm = ( "RotateX_negative".equals(name)) ;
            rotateym = ( "RotateY_negative".equals(name));
            rotate = rotatex || rotatey || rotatexm || rotateym;
            if( "RotateX".equals(name) ) {
                x+=  (value*speed);
               
            }else if("RotateX_negative".equals(name)){
                x+= (-value*speed);
                
            }
            
            else if( "RotateY".equals(name) ) {
                y+=  (value*speed);
               
            }else if("RotateY_negative".equals(name)){
                y+=  (-value*speed);
                
            }
           
             playerRotation.fromAngles(y,y,y);
        
      }
    };

    private final ActionListener actionListener = new ActionListener()  {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            
            front = name.equals("Forward") && keyPressed ;
            back = name.equals("Backward") && keyPressed  ;
            left = name.equals("Left") && keyPressed   ;
            right = name.equals("Right") && keyPressed  ;
            rotating = name.equals("Rotating") && keyPressed ;
        }
    };

    public Node loadSettler() {
        Node pNode = new Node("pNode");
        Geometry model =(Geometry) assetManager.loadModel("Models/004/004.j3o");
        model.setLocalTranslation(0, 0, -5f);
        settlerGeom =model;

        Vector3f GenLoc = new Vector3f(0, -2, -30);
        settlerGeom.setLocalTranslation(GenLoc);
        settlerGeom.setLocalScale(0.3f);
        //settlerGeom.move(0,-30f,0);   // model 004 needed offset;
        Quaternion quatA = new Quaternion();
        quatA = quatA.fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_Y);
        settlerGeom.setLocalRotation(quatA);
        s = new Settler("Placeholder_Name", GenLoc);
        s.setModel(settlerGeom);
        pNode.attachChild(s.getModel());
 AmbientLight amb = new AmbientLight();
        amb.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 0.001f).mult(0.1f));
        pNode.addLight(amb);

        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-1f, 0f, 0f).normalizeLocal());
        pNode.addLight(sun);
        pNode.addLight(amb);
        playerDirection.set(0, 0, 0);
        return pNode;
    }

    @Override
    public void update(float tpf) {

        Vector3f camDir = cam.getDirection().clone();
        camDir.add(0, 5f, 5f) ;
        Vector3f camLeft = cam.getLeft().clone();
        Quaternion camRot = cam.getRotation().clone();
        camRot.normalizeLocal();
        camDir.normalizeLocal();
        camLeft.normalizeLocal();
        
        if (left)
            playerDirection.addLocal(camLeft);
        else if (right)
            playerDirection.addLocal(camLeft.negate());
        else if (front)
            playerDirection.addLocal(camDir);
        else if (back)
            playerDirection.addLocal(camDir.negate());
        
            
        if (player != null) {
            
            settlerGeom.setLocalRotation(camRot);
            
             
            playerDirection.multLocal(20f).multLocal(tpf);
            playerRotation.multLocal(50f).multLocal(tpf);
            playerControl.setWalkDirection(playerRotation.mult(playerDirection));
            settlerGeom.move(playerDirection); 
            s.setLocation(settlerGeom.getLocalTranslation());
            
             
            
            
        }
        
       
       

    }
    
    public Vector3f getSettlerLoc(){
        return settlerPos;
    }
    public Settler getSettler(){
        return s;
    }
    public void setCurAsteroid(Asteroid cur){
            s.setAsteroid(cur);
    }
  
    

    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);

        super.cleanup();
    }
}