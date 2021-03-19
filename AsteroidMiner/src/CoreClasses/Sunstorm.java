package CoreClasses;

public class Sunstorm {
    // Attributes

    private float lastStormTime;
    private Timer timer;
    private double wavelength;    
    private boolean isHappening;

    // Methods
    
    public Sunstorm(double wavelength){
        this.wavelength = wavelength;
    }

    private double calculateTimeLeft(){

        return 0.0;
    }
    public void behave(){
        
    }

    public void setHappening(boolean value){
        isHappening = value;

    }
    public void display(){

    }
}
