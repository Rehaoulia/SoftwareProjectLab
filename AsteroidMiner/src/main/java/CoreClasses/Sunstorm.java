package CoreClasses;

public class Sunstorm {
    // Attributes
    private long wavelength; // in milliseconds , change type
    private boolean isHappening;
    public int count; // =0 by default

    //behaviour of the sunstorm (waiting, displaying countdown and making the sunstorm happen)
    public void behave(Controller c, long _wavelength) {
        try {
            Thread.sleep(wavelength-15000);
            display();
            //for 5 seconds
            for(int i=0; i<5; i++){
                try {
                    long millis = System.currentTimeMillis();
                    makeItHappen(c);
                    Thread.sleep(1000 - millis % 1000); //every 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //makes the sunstorm happen (and kill unhidden travellers)
    public void makeItHappen(Controller c){
        count++;
        setHappening(true);
        for(int i=0; i< c.settlers.size(); i++){
            if(c.settlers.get(i).hidden==false){
                c.settlers.get(i).dying(c); //NOT FINISHED!!!
            }
        }
        if(c.robots.size()>0){
            for(int i=0; i< c.robots.size(); i++){
                if(c.robots.get(i).hidden==false){
                    c.robots.get(i).die();
                }
            }
        }
    }

    //Setter
    public void setHappening(boolean value) {
        isHappening = value;
    }

    //Getter
    public boolean getHappening() {
        return isHappening;
    }

    //displays how many seconds are left till the sunstorm happens
    public void display() {
        int i=10;
        while(i>0) {
            try {
                long millis = System.currentTimeMillis();
                System.out.println(i);
                i--;
                Thread.sleep(1000 - millis % 1000); //every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
