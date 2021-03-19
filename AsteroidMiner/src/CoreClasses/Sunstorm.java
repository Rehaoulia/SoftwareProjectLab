package CoreClasses;

import java.util.Timer;
import java.util.TimerTask;

public static class Sunstorm {
    // Attributes

    private float lastStormTime;
    private Timer timer;
    private longint wavelength; // in milliseconds , change type
    private boolean isHappening;
    private static int count; // =0 by default

    private double calculateTimeLeft() {

        return 0.0;
    }

    public void behave(longint _wavelength) {
        count++;
        this.wavelength = _wavelength;
        TimerTask setAlarm = new TimerTask() {
            @Override
            public void run() {
                display();
            }
        };
        timer.schedule(setAlarm, wavelength - 10000);
        TimerTask startStorm = new TimerTask() {
            @Override
            public void run() {
                setHappening(true);
            }
        };
        timer.schedule(startStorm, wavelength);
        TimerTask stopStorm = new TimerTask() {
            @Override
            public void run() {
                setHappening(false);
            }
        };
        timer.schedule(stopStorm, wavelength + 5000);
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

    public static int getCount() {
        return count;
    }
}
