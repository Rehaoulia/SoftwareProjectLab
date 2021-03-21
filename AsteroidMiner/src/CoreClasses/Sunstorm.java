package CoreClasses;

import java.util.Timer;
import java.util.TimerTask;

public static class Sunstorm {
    // Attributes

    private static longint lastStormTime;
    private static Timer timer;
    private static longint wavelength; // in milliseconds , change type
    private static boolean isHappening;
    public static int count; // =0 by default

    private static double calculateTimeLeft() {

        return 0.0; // better be implemented later
    }

    public static void behave(longint _wavelength) {
        count++;
        wavelength = _wavelength;
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

    public static void setHappening(boolean value) {
        isHappening = value;
    }

    public static boolean getHappening() {
        return isHappening;
    } // getter

    public static void display() {
        // left for discussion
    }
}
