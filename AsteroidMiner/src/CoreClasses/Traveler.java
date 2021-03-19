package CoreClasses;

public abstract class Traveler {
    private boolean hidden;
    private Asteroid currentAsteroid;


    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void hide() throws InterruptedException {

        if (currentAsteroid.hollow() == true && !hidden && currentAsteroid.radius == currentAsteroid.depth) {
            setHidden(true);
            sleep(10);
            wait(10000);
            setHidden(false);
            System.out.println("You hide successfully");
        }
        System.out.println("You can't hide");
    }


    public void unhide() {
        if (!hidden)
            System.out.println("already not hidden");
        setHidden(false);

    }

    public void teleport(TeleportationGate tg) {



    }


    public abstract void sleep(int i);

    public abstract void drill();

    public abstract void dying();
}
