/*
 * auth : Mehdi Moazami
 * team : BugBuster
 * 
 */


package Search;

import CoreClasses.Asteroid;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author mehdimo
 */
public class Border {  
    
    Vector3f pos;
    Asteroid asteroid ;
    float hheigth;
    
    public Border(Vector3f _pos, float _hheigth){
    
        pos=_pos;
        hheigth =_hheigth;
    }
    
    

    public boolean contains(Vector3f point) {
        return( point.x < this.pos.x + this.hheigth && point.x > this.pos.x - hheigth &&
                point.y < this.pos.y + this.hheigth && point.y > this.pos.y - hheigth &&
                point.z < this.pos.z + this.hheigth && point.z > this.pos.z - hheigth 

                );
    }
    
    
     public int sphereContains(Vector3f point) {
         Vector3f dif = pos.add(point.negate()) ;
         float dist = dif.length();
         if(dist<hheigth)
         return 1 ;
         else if(dist>hheigth)
         return 0 ;
         else 
         return -1 ;
         
    }
    
     boolean containsAsteroid(Asteroid asteroidtf) {
        return( asteroidtf.getLocation().x < this.pos.x + this.hheigth && asteroidtf.getLocation().x > this.pos.x - this.hheigth &&
                asteroidtf.getLocation().y  < this.pos.y + this.hheigth && asteroidtf.getLocation().y > this.pos.y - this.hheigth &&
                asteroidtf.getLocation().z  < this.pos.z + this.hheigth && asteroidtf.getLocation().z > this.pos.z - this.hheigth    
                );
    }
    
    
    public Spatial draw(AssetManager assetManager){
        Box box1 = new Box(hheigth,hheigth,hheigth);
        box1.setMode(Mesh.Mode.Lines);
        Geometry green = new Geometry("Box", box1);
        green.setLocalTranslation(this.pos);
        Material mat1 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", new ColorRGBA(0.2f, 3f, 0.8f, 0.8f).mult(2f));
        green.setMaterial(mat1);
        
        
       return green ;
    }

    public Spatial draw1(AssetManager assetManager){
        Box box1 = new Box(hheigth,hheigth,hheigth);
        box1.setMode(Mesh.Mode.Lines);
        Geometry green = new Geometry("Box", box1);
        green.setLocalTranslation(this.pos);
        Material mat1 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Red);
        green.setMaterial(mat1);
        
        
       return green ;
    }
    
    boolean intersects(Border range) {
        if( range.pos.x-range.hheigth> this.pos.x+ this.hheigth||
                range.pos.x+range.hheigth< this.pos.x- this.hheigth || 
            range.pos.y-range.hheigth> this.pos.y+ this.hheigth||
                range.pos.y+range.hheigth< this.pos.y- this.hheigth ||  
            range.pos.z-range.hheigth> this.pos.z+ this.hheigth||
                range.pos.z+range.hheigth< this.pos.z- this.hheigth   
                )
            return false;
        else
            return true;
    }
}
