/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Asteroid;
import CoreClasses.Settler;
import CoreClasses.TeleportationGate;

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
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import java.util.Objects;

/**
 *
 * @author mehdimo
 */
public class SettlerPlace extends AbstractAppState {
    
    private final Node rootNode;
    private final Node localRootNode = new Node("intro");
    private final AssetManager assetManager;
    private final FlyByCamera flyCam;
    private final InputManager inputManager;
    private boolean front = false, back = false, left = false, right = false, hidden=false;
    public ChaseCamera chaseCam;
    private Geometry settlerGeom;
    private final Camera cam;
    private CharacterControl playerControl;
    private Spatial player;
    private final Vector3f playerDirection = Vector3f.ZERO;
    private final Quaternion playerRotation;
    private Vector3f settlerPos;
    public Settler s;
    private Vector3f scale;

    private Geometry[] gParts;
    private Node gateNode ;
    private int candGate;
    private TeleportationGate t;
    
    SimpleApplication application;
    //private final AppStateManager appStateManager;
    private final AppStateManager stateManager;
    
    public boolean craft, cannotCraft;
    
    public SettlerPlace(SimpleApplication app) {
        this.playerRotation = Quaternion.ZERO;
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        //appStateManager = app.getStateManager();
        stateManager = app.getStateManager();
        flyCam = app.getFlyByCamera();
        cam = app.getCamera();
        
        application=app;
    }
    
    Geometry model;
  
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        rootNode.attachChild(localRootNode);
        gateNode = new Node("gateNode");

        flyCam.setMoveSpeed(3);

        localRootNode.attachChild(loadSettler());
        localRootNode.attachChild(gateNode);

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

        inputManager.addMapping("Drill", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("Mine", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("Fill", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("Craft", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("del ast", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addMapping("Hide", new KeyTrigger(KeyInput.KEY_R));
        /*
        inputManager.addMapping("Drill", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("Mine", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("Craft", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("Hide", new KeyTrigger(KeyInput.KEY_Q));*/

        inputManager.addListener(actionListener, "Forward");
        inputManager.addListener(actionListener, "Backward");
        inputManager.addListener(actionListener, "Left");
        inputManager.addListener(actionListener, "Right");
        inputManager.addListener(actionListener, "Drill");
        inputManager.addListener(actionListener, "Mine");
        inputManager.addListener(actionListener, "Fill");
        inputManager.addListener(actionListener, "Craft");
        inputManager.addListener(actionListener, "del ast");
        inputManager.addListener(actionListener, "Hide");

        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, s.getModel(), inputManager);
        //chaseCam = new ChaseCamera(cam, settlerGeom, inputManager);
        chaseCam.setSmoothMotion(true);
        chaseCam.setDefaultDistance(20);
        chaseCam.setMinDistance(15);
        chaseCam.setMaxDistance(25);
        chaseCam.setDefaultVerticalRotation( 0);
        chaseCam.setMinVerticalRotation(-FastMath.PI/2);
        chaseCam.setMaxVerticalRotation(FastMath.PI/2);
        
        settlerPos = s.getLocation();
        //settlerPos = settlerGeom.getLocalTranslation();
        scale = settlerGeom.getLocalScale().clone();
        
        gParts = new Geometry[8];
        candGate = -1;
        
        craft=false;
        cannotCraft=false;
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {

            front = name.equals("Forward") && keyPressed;
            back = name.equals("Backward") && keyPressed;
            left = name.equals("Left") && keyPressed ;
            right = name.equals("Right") && keyPressed;
            
            /*
            if (name.equals("Drill") && keyPressed)
                s.drill();
            if (name.equals("Mine") && keyPressed)
                s.mine();*/
            
            if (name.equals("Hide") && keyPressed) s.hide();
            if(name.equals("Drill")&& !keyPressed)s.drill();
            else if(name.equals("Mine")&& !keyPressed)s.mine();
            else if(name.equals("del ast")&& !keyPressed) removeAsteroidb();
            else if(name.equals("Craft") && !keyPressed){
                craft=true;
            }
        }
    };
    
    //////////////////////////////////////////////////////////////
    //Added for crafting teleportation gates
    //////////////////////////////////////////////////////////////
    public void craftTeleGate(){
        if(s.checkRequiredMaterial(1)){
            //s.setLocation(settlerPos);
            if( s.craftGate()){
                Node temp = loadTeleGate();
                temp = s.putGate(temp);
                if(temp!=null)
                gateNode.attachChild(temp);
                //System.out.println("blaaaaaaaaaah");
            }
            else if(s.getNumberOfGates()==1){
                Node temp = loadTeleGate();
                temp = s.putGate(temp);
                if(temp!=null)
                gateNode.attachChild(temp); 
            }  
        }
        else if(!s.checkRequiredMaterial(1)){
            cannotCraft=true;
        }
    }
    ////////////////////////////////////////////////////////////
    
    public Node loadTeleGate(){
    
        Node localGateNode = new Node("localGateNode");
        // loc = s.getLocation();
        Geometry sample = (Geometry) assetManager.loadModel("Models/GatePart/01.j3o");
        //sample.setLocalTranslation(loc);
        
        for(int i =0 ;i<8;i+=2){
        sample.setLocalScale(8+(i-0.5f));
        gParts[i] = sample.clone();
        ColorRGBA col = new ColorRGBA(0.4f+i*0.05f, 0.4f+i*0.05f, 9.0f, 1.0f);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", col);
        gParts[i].setMaterial(mat);
       
        localGateNode.attachChild(gParts[i]);
        
        Quaternion roll180 = new Quaternion();
        roll180.fromAngleAxis( FastMath.PI , new Vector3f(0,0,1) );
        gParts[i+1] = sample.clone() ;
        gParts[i+1].setLocalRotation( roll180 );
        gParts[i+1].setMaterial(mat);
        
        
        localGateNode.attachChild(gParts[i+1]);
        }
       // gates.add(gParts);
        AmbientLight amb = new AmbientLight();
        amb.setColor(new ColorRGBA(1.0f, 1.0f, 6.0f, 0.001f).mult(10f));
        localGateNode.addLight(amb);
         return localGateNode;
        
    } 
        
    public Node loadSettler() {
        Node pNode = new Node("pNode");
        model = (Geometry) assetManager.loadModel("Models/004/004.j3o");
        //Geometry model =(Geometry) assetManager.loadModel("Models/004/004.j3o");
        model.setLocalTranslation(0, 0, -5f);
        settlerGeom = model;

        Vector3f GenLoc = new Vector3f(0, -2, -30);
        settlerGeom.setLocalTranslation(GenLoc);
        settlerGeom.setLocalScale(0.3f);
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
        camDir.add(0, 5f, 5f);
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
            

             if(!Objects.isNull(s.getAsteroid())){
                if(!Objects.isNull(s.getAsteroid().getCloseGate()) && s.getAsteroid().getCloseGate().isPaired()){
                    TeleportationGate curGate =s.getAsteroid().getCloseGate();
                    if(s.getLocation().distance( curGate.getLocation())<4f){
                       Vector3f nLoc =curGate.getPairedGate().getLocation();
                       System.out.println("......"+curGate.getPairedGate().getLocation().toString()+".....");
                       System.out.println("......"+curGate.getLocation().toString()+".....");
                       settlerGeom.setLocalTranslation(nLoc.add(0,0, 20));
                       s.setAsteroid(curGate.getPairedGate().getNeighbour());
                       s.setTeleported(true);
                    }
                }
             }
             
            s.setLocation(settlerGeom.getLocalTranslation().clone()); 
        }
    
        if(left || right ||front || back)  {
            s.unhide();
            s.getAsteroid().getUnhide();
        }
            

        if (s.getHidden()) {
            settlerGeom.setLocalScale(0);
        }
        if (!s.getHidden()) {
             settlerGeom.setLocalScale(scale);

        }

        if(s.getDeath()){
                       
        }
    }
    
    public Vector3f getSettlerLoc() {
        //return settlerPos;
        return s.getLocation();
    }
    
    public Settler getSettler() {
        return s;
    }

    public void setCurAsteroid(Asteroid cur) {
        s.setAsteroid(cur);
    }

    public int getPossibleGate(){
        return this.stateManager.getState(Placement.class).getPossibleGate();
    }
    
    public void removeAsteroidb(){
       if(s.getAsteroid()!=null){
        this.stateManager.getState(Placement.class).removeAsteroid(s.getAsteroid());
        s.setAsteroid(null);
       }    
    }

    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);

        super.cleanup();
    }
}