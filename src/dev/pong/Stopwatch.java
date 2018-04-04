package dev.pong;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
static int interval;
static Timer timer;

public Stopwatch()
{
	
}

public void beginTimer(int secs)
{
    int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = secs;
    System.out.println(secs);
    
    timer.scheduleAtFixedRate(new TimerTask() {

        public void run() {
            System.out.println(setInterval());
            System.out.println("gvdcukdwb");
        }
    }, delay, period);
}

private static final int setInterval() {
    if (interval == 1)
        timer.cancel();
    return --interval;
}
}