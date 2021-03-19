package CoreClasses;

import java.util.Timer;
import java.util.TimerTask;

public static class Sunstorm {
    // Attributes

    private float lastStormTime;
    private Timer start; // start and stop are two different timers
    private Timer stop;
    private Timer alarm; // needed for the alarm
    private longint wavelength; // in milliseconds , change type
    private boolean isHappening;

    // Methods

    // public Sunstorm(longint wavelength) {
    // this.wavelength = wavelength;
    // } // sunstorm decides its wavelength by itself

    private double calculateTimeLeft() {

        return 0.0;
    }

    public void behave(longint _wavelength) {
        this.wavelength = _wavelength;
        TimerTask setAlarm = new TimerTask() {
            @Override
            public void run() {
                display();
            }
        };
        alarm.schedule(setAlarm, wavelength - 10000);
        TimerTask startStorm = new TimerTask() {
            @Override
            public void run() {
                setHappening(true);
            }
        };
        start.schedule(startStorm, wavelength);
        TimerTask stopStorm = new TimerTask() {
            @Override
            public void run() {
                setHappening(false);
            }
        };
        start.schedule(stopStorm, wavelength + 5000);
    }

    public void setHappening(boolean value) {
        isHappening = value;
    }

    public boolean getHappening() {
        return isHappening;
    } // getter

    public void display() {
        // left for discussion
    }
}
