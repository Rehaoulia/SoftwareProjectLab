package CoreClasses;

public abstract class Traveler {
    private boolean hidden;
    private  Place currentPlace;


    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void hide() {

        if (currentPlace.hollow() == true && !hidden)
            hidden = true;
        System.out.println("You can't hide");
    }

    public void unhide() {
        if (!hidden)
            System.out.println("already not hidden");
        hidden = false;

    }

    public void teleport(TeleportationGate tg) {
    }


    public abstract void drill();

    public abstract void dying();
}
