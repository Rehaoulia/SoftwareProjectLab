/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import CoreClasses.Asteroid;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author mehdimo
 * 
 */

public class CloseBox {
    
    Border bound;
    float limit;
    ArrayList<Asteroid> asteroids;

    private CloseBox unw;
    private CloseBox une;
    private CloseBox usw;
    private CloseBox use;
    private CloseBox dnw;
    private CloseBox dne;
    private CloseBox dsw;
    private CloseBox dse;
    private boolean divided ;
    Node branch;
    private AssetManager am;
    private static int depth = 0 ;
    
    public CloseBox(Border _bound, float _limit , AssetManager assetManager){
        bound =_bound ;
        limit =_limit;
        asteroids = new ArrayList<Asteroid>();
        
        divided = false;
        branch = new Node("branch");
        am = assetManager;
      
        
    }
    
    public void showBound(){
        if(!this.divided && !this.asteroids.isEmpty() ){
        branch.attachChild(bound.draw(am));
        
        }
    }
    
    public Node getBranch(){
        return branch;
    }
    
    
    
 public void insert(Asteroid asteroid){
        
        if(!this.bound.containsAsteroid(asteroid)){
        return;
        }
         
        if(this.asteroids.size()< this.limit){
        this.asteroids.add(asteroid);
        //System.out.println(depth);
        //this.showBound();
        }else{
            if(!this.divided){
            this.subdivide();
            
            }
            
            this.unw.insert(asteroid);
            this.une.insert(asteroid);
            this.usw.insert(asteroid);
            this.use.insert(asteroid);
            this.dnw.insert(asteroid);
            this.dne.insert(asteroid);
            this.dsw.insert(asteroid);
            this.dse.insert(asteroid);
            
        }
    }

 
 
     
 public void remove(Asteroid asteroid){
        
      if(!this.bound.containsAsteroid(asteroid)){
        return;
        }

        if(this.asteroids.contains(asteroid) ){
        this.asteroids.remove(asteroid);
        
        
        }else{
            
            this.unw.remove(asteroid);
            this.une.remove(asteroid);
            this.usw.remove(asteroid);
            this.use.remove(asteroid);
            this.dnw.remove(asteroid);
            this.dne.remove(asteroid);
            this.dsw.remove(asteroid);
            this.dse.remove(asteroid);
            
        }
    }


    private void subdivide() {
        
        Border bunw = new Border(new Vector3f(this.bound.pos.x - this.bound.hheigth/2,this.bound.pos.y + this.bound.hheigth/2 , this.bound.pos.z - this.bound.hheigth/2), this.bound.hheigth/2); 
        this.unw = new CloseBox(bunw, this.limit,am);
        this.branch.attachChild(this.unw.branch);
        Border bune = new Border(new Vector3f(this.bound.pos.x + this.bound.hheigth/2,this.bound.pos.y + this.bound.hheigth/2,this.bound.pos.z - this.bound.hheigth/2), this.bound.hheigth/2);
        this.une = new CloseBox(bune, this.limit,am);
        this.branch.attachChild(this.une.branch);
        Border busw = new Border(new Vector3f(this.bound.pos.x - this.bound.hheigth/2,this.bound.pos.y + this.bound.hheigth/2,this.bound.pos.z + this.bound.hheigth/2), this.bound.hheigth/2);
        this.usw = new CloseBox(busw, this.limit,am);
        this.branch.attachChild(this.usw.branch);
        Border buse = new Border(new Vector3f(this.bound.pos.x + this.bound.hheigth/2,this.bound.pos.y + this.bound.hheigth/2,this.bound.pos.z + this.bound.hheigth/2), this.bound.hheigth/2);
        this.use = new CloseBox(buse, this.limit,am);
        this.branch.attachChild(this.use.branch);
        Border bdnw = new Border(new Vector3f(this.bound.pos.x - this.bound.hheigth/2,this.bound.pos.y - this.bound.hheigth/2,this.bound.pos.z - this.bound.hheigth/2), this.bound.hheigth/2);
        this.dnw = new CloseBox(bdnw, this.limit,am);
        this.branch.attachChild(this.dnw.branch);
        Border bdne = new Border(new Vector3f(this.bound.pos.x + this.bound.hheigth/2,this.bound.pos.y - this.bound.hheigth/2,this.bound.pos.z - this.bound.hheigth/2), this.bound.hheigth/2);
        this.dne = new CloseBox(bdne, this.limit,am);
        this.branch.attachChild(this.dne.branch);
        Border bdsw = new Border(new Vector3f(this.bound.pos.x - this.bound.hheigth/2,this.bound.pos.y - this.bound.hheigth/2,this.bound.pos.z + this.bound.hheigth/2), this.bound.hheigth/2);
        this.dsw = new CloseBox(bdsw, this.limit,am);
        this.branch.attachChild(this.dsw.branch);
        Border bdse = new Border(new Vector3f(this.bound.pos.x + this.bound.hheigth/2,this.bound.pos.y - this.bound.hheigth/2,this.bound.pos.z + this.bound.hheigth/2), this.bound.hheigth/2);
        this.dse = new CloseBox(bdse, this.limit,am);
        this.branch.attachChild(this.dse.branch);
       
        
        this.divided = true;
        this.depth++;
        
    }
    
    
    public ArrayList<Asteroid> query(Border range , ArrayList<Asteroid> found ){
        
       
        if(!this.bound.intersects(range)){
            return found;
        }
        else{
            
            for(Asteroid p : this.asteroids){
                if(range.containsAsteroid(p))
                    found.add(p);
            }
            if(this.divided){
                 this.unw.query(range,found);
                 this.une.query(range,found);
                this.usw.query(range,found);
                 this.use.query(range,found);
                this.dnw.query(range,found);
                 this.dne.query(range,found);
                 this.dsw.query(range,found);
                this.dse.query(range,found);
            }
            
            return found ;
        }

    }
    
    
     public boolean searchSpace(Border range , ArrayList<Asteroid> found ){
        
       
        if(!this.bound.intersects(range)){
            return true;
        }
        else{
            
            for(Asteroid p : this.asteroids){
                if(range.containsAsteroid(p))
                    return false;
            }
            if(this.divided){
                 this.unw.query(range,found);
                 this.une.query(range,found);
                this.usw.query(range,found);
                 this.use.query(range,found);
                this.dnw.query(range,found);
                 this.dne.query(range,found);
                 this.dsw.query(range,found);
                this.dse.query(range,found);
            }
            
            return true ;
        }

    }
    

    
}