///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package States;
//
//package mygame;
//
//import com.jme3.app.SimpleApplication;
//import com.jme3.light.AmbientLight;
//import com.jme3.material.Material;
//import com.jme3.math.ColorRGBA;
//import com.jme3.math.FastMath;
//import com.jme3.math.Quaternion;
//import com.jme3.math.Vector3f;
//import com.jme3.renderer.RenderManager;
//import com.jme3.scene.Geometry;
//import com.jme3.scene.Node;
//import com.jme3.scene.Spatial;
//import com.jme3.scene.shape.Box;
//
//
///**
// * This is the Main Class of your Game. You should only do initialization here.
// * Move your Logic into AppStates or Controls
// * @author normenhansen
// */
//public class Main extends SimpleApplication {
//    
//    Spatial g1;
//    Spatial g2;
//    Spatial g3;
//    Spatial g4;
//    Spatial g5;
//    Spatial[] gate ;
//    Spatial g6;
//    Spatial g7;
//    Spatial g8;
//    Spatial g9;
//    Spatial g10;
//     Node pivot ;
//    
//
//    public static void main(String[] args) {
//        Main app = new Main();
//        app.start();
//    }
//
//    @Override
//    public void simpleInitApp() {
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//        geom.setLocalTranslation(new Vector3f(1,-1,1));
//        
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(mat);
//        
//      
//        
//        pivot = new Node("pivot");
//        
//        AmbientLight al = new AmbientLight();
//        al.setColor(ColorRGBA.White.mult(1.3f));
//        rootNode.addLight(al);
//        
//        gate = new Spatial[10];
//        
//         g1 = assetManager.loadModel("Models/01/01.j3o");
//        pivot.attachChild(g1);
//         g2 = assetManager.loadModel("Models/02/02.j3o");
//        pivot.attachChild(g2);
//         g3 = assetManager.loadModel("Models/03/03.j3o");
//        pivot.attachChild(g3);
//         g4 = assetManager.loadModel("Models/04/04.j3o");
//        pivot.attachChild(g4);
//         g5 = assetManager.loadModel("Models/05/05.j3o");
//        pivot.attachChild(g5);
//        
//        Quaternion roll180 = new Quaternion();
//        roll180.fromAngleAxis( FastMath.PI , new Vector3f(0,0,1) );
// 
//        
//        
//         g6 = assetManager.loadModel("Models/01/01.j3o");
//         g6.setLocalRotation( roll180 );
//        pivot.attachChild(g6);
//         g7 = assetManager.loadModel("Models/02/02.j3o");
//         g7.setLocalRotation( roll180 );
//        pivot.attachChild(g7);
//         g8 = assetManager.loadModel("Models/03/03.j3o");
//         g8.setLocalRotation( roll180 );
//        pivot.attachChild(g8);
//         g9 = assetManager.loadModel("Models/04/04.j3o");
//         g9.setLocalRotation( roll180 );
//        pivot.attachChild(g9);
//         g10 = assetManager.loadModel("Models/05/05.j3o");
//         g10.setLocalRotation( roll180 );
//        pivot.attachChild(g10);
//        
//        gate[0]= g1;
//        gate[1]= g2;
//        gate[2]= g3;
//        gate[3]= g4;
//        gate[4]= g5;
//        gate[5]= g6;
//        gate[6]= g7;
//        gate[7]= g8;
//        gate[8]= g9;
//        gate[9]= g10;
//        
//
//        
//        
//        
//        
//        rootNode.attachChild(pivot);
//        
//        //pivot.attachChild(geom);
//        
//        
//    }
//    int time;
//    @Override
//    public void simpleUpdate(float tpf) {
//        //TODO: add update code
//        time += tpf;
//        int j=5;
//        for(int i = 1 ;i<6 ;i++){
//         //if((i+1)*time = FastMath.PI * 2*FastMath.DEG_TO_RAD)  {
//        gate[i-1].rotate(0,0,(5/i)*tpf);
//        gate[j].rotate(0,0,(5/i)*tpf);
//        
//        
//        
//        j++;
//         }
//       // }
//     
//     
//     
//     //pivot.rotate();
//        
//        
//        
//        
//    }
//
//    @Override
//    public void simpleRender(RenderManager rm) {
//        //TODO: add render code
//    }
//}
