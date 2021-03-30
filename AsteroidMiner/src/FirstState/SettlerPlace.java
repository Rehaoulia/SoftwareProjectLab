/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstState;

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
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
            rotatex=false,rotatey=false,rotatexm =false,rotateym=false, rotate=false;
    private ChaseCamera chaseCam;
    private Geometry settlerGeom;
    private final Camera cam;
    private CharacterControl playerControl;
    private Spatial player;
    private final Vector3f playerDirection = Vector3f.ZERO;
    private final Quaternion playerRotation = Quaternion.ZERO;

    public SettlerPlace(SimpleApplication app) {
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

        flyCam.setMoveSpeed(4);

        //localRootNode.attachChild(loadAsteroids());
        localRootNode.attachChild(loadSettler());
        //localRootNode.attachChild(loadSky());

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
        inputManager.addListener(actionListener, "Forward");
        inputManager.addListener(actionListener, "Backward");
        inputManager.addListener(actionListener, "Left");
        inputManager.addListener(actionListener, "Right");
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
    }
    private final AnalogListener analogListener =  new AnalogListener() {

        @Override
        public void onAnalog(String name, float value, float tpf) {
            
            float x=0.0f,y=0.0f;
            rotatex = ( "RotateX".equals(name)) && value>0;
            rotatey = ( "RotateY".equals(name)) && value>0;
                        rotatexm = ( "RotateX_negative".equals(name)) && value>0;
            rotateym = ( "RotateY_negative".equals(name)) && value>0;
            rotate = rotatex || rotatey || rotatexm || rotateym;
            
            if( "RotateX".equals(name) ) {
                x= (float) (value*0.1f);

            }else if("RotateX_negative".equals(name)){
                x= (float) (-value*0.1f);
            };
            if( "RotateY".equals(name) ) {
                y= (float) (value*0.1f);


            }else if("RotateY_negative".equals(name)){
                y= (float) (-value*0.1f);
            };
             playerRotation.fromAngles(x,y,0);
        }
    };

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            front = name.equals("Forward") && keyPressed;
            back = name.equals("Backward") && keyPressed;
            left = name.equals("Left") && keyPressed;
            right = name.equals("Right") && keyPressed;
        }
    };

    public Node loadSettler() {
        Node pNode = new Node("pNode");
        settlerGeom = (Geometry) assetManager.loadModel("Models/SettlerModel/SettlerModel.j3o");

        Vector3f GenLoc = new Vector3f(0, -2, -20);
        settlerGeom.setLocalTranslation(GenLoc);
        settlerGeom.setLocalScale(0.3f);
        Quaternion quatA = new Quaternion();
        quatA = quatA.fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_Y);
        settlerGeom.rotate(quatA);
        Settler s = new Settler("Placeholder_Name", GenLoc);
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
        return pNode;
    }

    @Override
    public void update(float tpf) {
        // Quaternion quatA = new Quaternion();
        // quatA = quatA.fromAngleAxis(FastMath.HALF_PI * tpf / 900, Vector3f.UNIT_Y);
        // localRootNode.rotate(quatA);

        Vector3f camDir = cam.getDirection().clone();
        Vector3f camLeft = cam.getLeft().clone();
        Quaternion camRot = cam.getRotation().clone();
        //camRot.add(playerRotation);
        camRot.normalizeLocal();
        //camDir.y = 0;
        //camLeft.y = 0;
        camDir.normalizeLocal();
        camLeft.normalizeLocal();
        playerDirection.set(0, 0, 0);
        if (left)
            playerDirection.addLocal(camLeft);
        if (right)
            playerDirection.addLocal(camLeft.negate());
        if (front)
            playerDirection.addLocal(camDir);
        if (back)
            playerDirection.addLocal(camDir.negate());
        if (rotate)
            playerRotation.addLocal(camRot);
        
            
        if (player != null) {
            playerDirection.multLocal(30f).multLocal(tpf);
            playerRotation.multLocal(100f).multLocal(tpf);
            playerControl.setWalkDirection(playerRotation.mult(playerDirection));
            
            //playerControl.set
            //settlerGeom.rotate(playerRotation);
            settlerGeom.move(playerDirection);
            
            
        }

    }

    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);

        super.cleanup();
    }
}