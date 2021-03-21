package CoreClasses;

public abstract class Traveler {
    protected boolean hidden;
    protected Place currentPlace;



    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void unhide() {
        if (!hidden)
            System.out.println("already not hidden");
        setHidden(false);

    }

    public void teleport(TeleportationGate tg) {
        if (tg.isPaired()) {
            currentAsteroid = tg.getPairedGate().getneighbourAsteroid();
        }
    }



    public abstract void hide() throws InterruptedException;

    public abstract void drill();

    public abstract void die();
}
