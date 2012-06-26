package com.ai.tracker;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.ai.event.InactivityEvent;
import com.ai.event.InactivityEventDriver;

/**
 * @author Abhijeet Iraj
 * 
 */
public class InactivityTracker extends InactivityEventDriver implements AWTEventListener, ActionListener
{
    private static InactivityTracker tracker;
    private Timer timer;
    private int inactivityPeriod;

    private InactivityTracker(int period)
    {
        inactivityPeriod = period;
        timer = new Timer(inactivityPeriod, this);
        timer.setRepeats(false);
        trackSystemEvents();
    }

    public static synchronized InactivityTracker getInstance(int inactivityPeriod)
    {
        if (tracker == null)
        {
            tracker = new InactivityTracker(inactivityPeriod);
        }
        return tracker;
    }

    public void startTracking()
    {
        if (timer.isRunning())
        {
            timer.stop();
        }
        timer.start();
    }

    public void resetTracking()
    {
        startTracking();
    }

    public void stopTracking()
    {
        if (timer.isRunning())
        {
            timer.stop();
        }
    }

    // User action detected.
    public void eventDispatched(AWTEvent event)
    {
        if (timer.isRunning())
        {
            timer.restart();
        }
    }

    // Inactivity timeout occurred
    public void actionPerformed(ActionEvent e)
    {
        fireInactivityEvent(new InactivityEvent(this));
    }


    /**
     * Track Key and Mouse events
     */
    private void trackSystemEvents()
    {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK + AWTEvent.KEY_EVENT_MASK);
    }

}
