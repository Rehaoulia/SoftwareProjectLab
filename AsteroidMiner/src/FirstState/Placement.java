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
import Search.Border;
import Search.CloseBox;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author mehdimo
 */
public class Placement extends AbstractAppState {
    
  private final Node rootNode;
    private final Node localRootNode;
    private final Node AsteroidNode;
    private final AssetManager assetManager;
    private final FlyByCamera flyCam;
    private Camera Cam;
    private SpotLight spot; 
    private CloseBox cBox ;
    private Node selectedNode ;
    private Node curAsteroidNode;
    private SpotLight Guid;
    Spatial asteroid;
    private AmbientLight ambiant;
    private AmbientLight ambiantCur;
    private Asteroid[] closter ;
   private ArrayList<Asteroid> closeOnes;
   private Asteroid curAsteroid ;
   private ArrayList<Asteroid> NeiAst ;
   private Border NeiAstB;
    private float fDist;
    private final Spatial asteroid1;
    private final AppStateManager stateManager;
    
    public Placement(SimpleApplication app){
        
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        flyCam = app.getFlyByCamera();
        Cam = app.getCamera();
        stateManager = app.getStateManager();
        asteroid = assetManager.loadModel("Models/AsteroidModel/LowP0002.j3o");
        asteroid1 = assetManager.loadModel("Models/LowP04/LowP04.j3o");
        localRootNode= new Node("intro");
        AsteroidNode= new Node("intro");
        selectedNode = new Node("selected");
        curAsteroidNode = new Node("selected");
       //ArrayList<Asteroid> closeOnes
    }
    
    @Override
    public void initialize(AppStateManager stateManager , Application app){
        super.initialize(stateManager, app);
        
        rootNode.attachChild(localRootNode);
        Border n = new Border(new Vector3f(0,0,0),400f);
        cBox = new CloseBox(n, 1 , assetManager);
        NeiAst = new ArrayList<Asteroid>(); 
        closeOnes = new ArrayList<Asteroid>();
        flyCam.setMoveSpeed(14);
       this.loadAsteroids(800);
      
       
       
       
        localRootNode.attachChild(loadSky());
        localRootNode.attachChild(AsteroidNode);
        AsteroidNode.attachChild(selectedNode);
        AsteroidNode.attachChild(curAsteroidNode);
        
          
    }   
        
        
//        Node pNode = new Node("pNode");
//        Spatial settler = assetManager.loadModel("Models/SettlerModel/SettlerModel.j3o");
//        settler.setLocalTranslation(0,-2,-20);
//        settler.setLocalScale(0.4f);
//         Quaternion quatA = new Quaternion();
//            quatA = quatA.fromAngleAxis(FastMath.PI , Vector3f.UNIT_Y);
//            settler.rotate(quatA);
//        
//        pNode.attachChild(settler);
        //localRootNode.attachChild(pNode);
        
        
  
    
    
    public void loadAsteroids(int NumberOfAsteroid){
    
       //Node AsteroidNode = new Node("Asteroids");
       Spatial asteroid = assetManager.loadModel("Models/AsteroidModel/LowP0002.j3o");
       
       Random rand = new Random();
       this.closter = new Asteroid[NumberOfAsteroid]; 
       
       
       closter[0] = new Asteroid(0 , new Vector3f(-50.1f ,1.1f , 2.2f),6);
       Spatial n = asteroid.clone();
           n.setLocalScale(3f);
           closter[0].setModel(n);
           
           curAsteroid =closter[0];
           AsteroidNode.attachChild(curAsteroid.getModel());
           Spatial cA = closter[0].getModel();
                                   
           cA.setLocalScale(3.9f);
           curAsteroidNode.attachChild(cA);
           cBox.insert(closter[0]);
           fDist = curAsteroid.getLocation().distance(this.getSettlerLoc());
           
      
       for(int i=1;i<NumberOfAsteroid;i++ ){
           
           float max = 400f;

          
           
           float randx = ThreadLocalRandom.current().nextFloat()*max*2 -max ;
           while( Math.round(randx) == randx ) randx = ThreadLocalRandom.current().nextFloat()*max*2 -max ;
           float randy = ThreadLocalRandom.current().nextFloat()*max*2 - (max) ;
           while(Math.round(randy) == randy ) randy = ThreadLocalRandom.current().nextFloat()*max*2 -max ;
           float randz = ThreadLocalRandom.current().nextFloat()*max*2 -max ;
           while(Math.round(randz) == randz ) randz = ThreadLocalRandom.current().nextFloat()*max*2 -max ;
           int randSize = ThreadLocalRandom.current().nextInt(100, 300);
            int randMod = ThreadLocalRandom.current().nextInt(1, 2);
            
           Vector3f GenLoc = new Vector3f(randx ,randy , randz);

           
           
           Mineral M;
            int mineralSelector = rand.nextInt(10);
            switch (mineralSelector) {
            case 0:
                M = new Carbon();
                break;
            case 1:
                M = new WaterIce();
                break;
            case 2:
                M = new Iron();
                break;
            case 3:
                 M = new Uranium();
                 
                 
          
                break;
            default:
                M = null;
            }
            
            int radius = rand.nextInt(5) + 5;
            if (mineralSelector >= 4)
                closter[i] = new Asteroid(i , GenLoc,radius);
            
            else
                closter[i] = new Asteroid(i , GenLoc, M,radius);
           if(randMod==1)
               n = asteroid.clone();
           if(randMod==2)
               n = asteroid1.clone();
           
           Quaternion roll = new Quaternion();
            roll.fromAngleAxis( FastMath.PI , new Vector3f(randy,randx,randz) );
            
            n.setLocalRotation( roll );
           n.setLocalScale(3f);
          
           closter[i].setModel(n);
           cBox.insert(closter[i]);

           AsteroidNode.attachChild(closter[i].getModel());
            
       }
       
       
       AsteroidNode.attachChild(cBox.getBranch());
     
      
       
       
       
       AmbientLight amb = new AmbientLight();
        amb.setColor(new ColorRGBA(0.9f,1.0f,0.9f,0.1f).mult(0.6f));
         AsteroidNode.addLight(amb);
        
        
            
            spot = new SpotLight();
            spot.setSpotRange(25f);                           // distance
            spot.setSpotInnerAngle(15f * FastMath.DEG_TO_RAD); // inner light cone (central beam)
            spot.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
            //spot.setColor(new ColorRGBA(0.1f,0.6f,1f,1f).mult(15f));   
            spot.setColor(new ColorRGBA(0.6f,0.4f,0.7f,1f).mult(15f));   
            spot.setPosition(Cam.getLocation());               // shine from camera loc
            spot.setDirection(Cam.getDirection());             // shine forward from camera loc
            curAsteroidNode.addLight(spot);



      

        DirectionalLight sun = new DirectionalLight();
        sun.setColor(new ColorRGBA(0.9f,1.0f,0.9f,0.01f).mult(0.8f));
        sun.setDirection(new Vector3f(-1f,0f,0f).normalizeLocal());
        AsteroidNode.addLight(sun);
        
        
         NeiAstB = new Border (curAsteroid.getLocation(), 100f);
       NeiAst = this.nearBy(NeiAstB);
        for(int j =0 ;j< NeiAst.size() ; j++){
                  
            Spatial nca = NeiAst.get(j).getModel();
                    nca.setLocalScale(3.3f);
                    selectedNode.attachChild(nca);
            
        }
       
        
        
    }
    
    
    public Node loadSky(){
        
        Node base = new Node("Sky");
       
        Texture west, east, north, south, up, down;
        west = assetManager.loadTexture("sky/skyBlue/right.png");
        east = assetManager.loadTexture("sky/skyBlue/left.png");
        north = assetManager.loadTexture("sky/skyBlue/back.png");
        south = assetManager.loadTexture("sky/skyBlue/front.png");
        up = assetManager.loadTexture("sky/skyBlue/top.png");
        down = assetManager.loadTexture("sky/skyBlue/bottom.png");
        base.attachChild(SkyFactory.createSky(assetManager, west, east, north, south, up, down));
        
        
        ambiant = new AmbientLight();
             ambiant.setColor(new ColorRGBA(0.4f,0.9f,1f,1f).mult(1f));
            
            selectedNode.addLight(ambiant);
            
              ambiantCur = new AmbientLight();
                        ambiantCur.setColor(new ColorRGBA(0.5f,1.5f,2f,0.5f).mult(2f));

                        curAsteroidNode.addLight(ambiantCur);
                       
        
        
        
        return base;
    }
    
    
    @Override 
    public void update(float tpf){
     
//         Quaternion quatA = new Quaternion();
//            quatA = quatA.fromAngleAxis(FastMath.HALF_PI * tpf/900, Vector3f.UNIT_Y);
//            localRootNode.rotate(quatA);
            
            
            spot.setPosition(Cam.getLocation());               // shine from camera loc
            spot.setDirection(Cam.getDirection()); 
            
            
             
        Vector3f settlerPos =this.getSettlerLoc();     
       this.placeGuides(closeOnes , settlerPos);
       AsteroidNode.updateGeometricState();
   
       closeOnes.clear(); 
            
            
    }
    
    
    public ArrayList<Asteroid> nearBy(Border selected){
        ArrayList<Asteroid> closeOne = new ArrayList<>();
       //Border selected = new Border (point, 100f);
       closeOne = cBox.query(selected, closeOne);
        return closeOne;
    }
    
    
    
     public ArrayList<Asteroid> nearBy( Vector3f pos , float bound){
        
       Border selected = new Border (pos, bound);
        ArrayList<Asteroid> closeOnes = new ArrayList<>();
       closeOnes = cBox.query(selected, closeOnes);
        return closeOnes;
    }
     
     public Vector3f getSettlerLoc(){
     
         return this.stateManager.getState(SettlerPlace.class).getSettlerLoc();
     }
     
 
     
         
    public void placeGuides(ArrayList<Asteroid> closeAsteroids, Vector3f _sPos){
        
            Vector3f sPos =_sPos ;
        
            Border closeAstB = new Border(sPos, 25f);
            closeAsteroids = this.nearBy(closeAstB);
            
            fDist = sPos.distance(curAsteroid.getLocation());
         
            for(int i = 0; i<closeAsteroids.size();i++ ){
                if( NeiAst.contains(closeAsteroids.get(i)) ){
                   
                             float dist = sPos.distance(closeAsteroids.get(i).getLocation());
                             
                        if(fDist>dist ){

                            curAsteroid = closeAsteroids.get(i) ;
                            System.out.println(curAsteroid.viewInfo());
                             Spatial nc = curAsteroid.getModel();
                               curAsteroidNode.detachAllChildren();                          
                                nc.setLocalScale(3.9f);
                               curAsteroidNode.attachChild(nc);
                              //fDist = Cam.getLocation().distance(curAsteroid.getLocation());
                            NeiAstB = new Border(curAsteroid.getLocation(), 100f);
                            NeiAst.clear();
                            NeiAst = this.nearBy(NeiAstB);

                            selectedNode.detachAllChildren();
                            for(int j =0 ; j<NeiAst.size();j++){

                                 Spatial n = NeiAst.get(j).getModel();
                                         n.setLocalScale(3.3f);
                                         selectedNode.attachChild(n);

                              }

                        }
                    }
                }

       }   

            
        
    
    
    public void placeGuidesV2(ArrayList<Asteroid> closeAsteroids){
        

            float fDist = 10000;
            
            Border selected = new Border (Cam.getLocation(), 100f);
            closeAsteroids = this.nearBy(selected);
            
       
       for(int i =0 ;i< closeAsteroids.size() ; i++){
           
           float dist = Cam.getLocation().distance(closeAsteroids.get(i).getLocation());

          if(fDist>dist){
                       
                        
                        Spatial nc = closeAsteroids.get(i).getModel();
                           curAsteroidNode.detachAllChildren();                          
                            nc.setLocalScale(3.9f);
                            curAsteroidNode.attachChild(nc);
                            
                            
                           
                            ArrayList<Asteroid> NeiAst = new ArrayList<Asteroid>();
                             Border bound = new Border (closeAsteroids.get(i).getLocation(), 100f);
                             NeiAst = cBox.query(bound, NeiAst);
                             
                              selectedNode.detachAllChildren();
                            
                             
                             for(int j =0 ;j< NeiAst.size() ; j++){
                            
                                           Spatial n = NeiAst.get(j).getModel();
                                           n.setLocalScale(3.3f);
                                           selectedNode.attachChild(n);
                             }
                            
                             
                            System.out.println("===================\n");
                            
                             System.out.println(closeAsteroids.get(i).viewInfo());
                             
                             System.out.println("\n===================\n");
                            fDist = dist;
          }
           
       }   

            // selectedNode.addLight(ambiant);
        
    }
    
    
    @Override 
    public void cleanup(){
        rootNode.detachChild(localRootNode);
        
        super.cleanup();
    }
}
