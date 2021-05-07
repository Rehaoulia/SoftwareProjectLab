/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Asteroid;
import CoreClasses.Robot;
import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author User
 */
public class RobotState extends AbstractAppState {
    
    private final Node rootNode;
    private final Node localRootNode = new Node("robotinitNode");
    private final AssetManager assetManager;
    private BetterCharacterControl robotControl;
    private Spatial robotModel;
    private Spatial robotSpatial;
    private Vector3f robotPosition;
    private Robot robot;
    private Geometry robotGeometry;
    private Asteroid currentAsteroid;
    private final Vector3f robotDirection;
    private final Quaternion robotRotation;
    
    public RobotState(SimpleApplication app, Settler creator){
        this.rootNode = app.getRootNode();
        this.assetManager = app.getAssetManager();
        this.robotPosition = creator.getLocation();
        this.currentAsteroid = creator.getAsteroid();
        this.robotDirection = Vector3f.ZERO;
        this.robotRotation = Quaternion.ZERO;
    }
    
    @Override 
    public void initialize(AppStateManager stateManager, Application app){
        super.initialize(stateManager, app);
        
        rootNode.attachChild(localRootNode);
        localRootNode.attachChild(this.loadRobot());
        robotSpatial = localRootNode.getChild("robotNode");
        BoundingBox boundingbox = (BoundingBox) robotSpatial.getWorldBound();
        CapsuleCollisionShape robotShape = new CapsuleCollisionShape(boundingbox.getXExtent(),
            boundingbox.getYExtent());
        robotControl = new BetterCharacterControl(robotShape.getRadius(), robotShape.getHeight(), 10);
        robotSpatial.addControl(robotControl);
        
        robotPosition = robotModel.getLocalTranslation();
    }
    
    public Node loadRobot(){
        Node robotNode = new Node("robotNode");
        robotModel = assetManager.loadModel("Models/RobotModel/RobotModel.j3o");
        robotModel.setLocalTranslation(robotPosition);
        //robotGeometry = robotModel;
        robotModel.setLocalScale(0.3f);
        Quaternion quatA = new Quaternion();
        quatA = quatA.fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_Y);
        robotModel.setLocalRotation(quatA);
        
        robot = new Robot(currentAsteroid, robotPosition);
        robot.setModel(robotModel);
        robotNode.attachChild(robot.getModel());
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 0.001f).mult(0.1f));
        robotNode.addLight(ambient);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-1f, 0f, 0f).normalizeLocal());
        robotNode.addLight(sun);
        robotDirection.set(Vector3f.ZERO);
        
        return robotNode;
    }
}