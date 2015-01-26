package async.capteur;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josian on 26/01/15.
 */

public class MyTimer {
    private final Timer t = new Timer();

    public TimerTask schedule(final Runnable r, long delay) {
        final TimerTask task = new TimerTask() { public void run() { r.run(); }};
        t.schedule(task, delay);
        return task;
    }

    public TimerTask scheduleAtFixedRate(final Runnable r, long delay, long period)
    {
        final TimerTask task = new TimerTask() { public void run() { r.run(); }};
        t.scheduleAtFixedRate(task, delay, period);
        return task;
    }
}
