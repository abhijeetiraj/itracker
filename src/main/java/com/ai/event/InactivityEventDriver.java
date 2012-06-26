package com.ai.event;

import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class InactivityEventDriver
{
    private List<InactivityListener> listenerList = new Vector<InactivityListener>();


    public void addInactivityListener(InactivityListener listener)
    {
        listenerList.add(listener);
    }

    public void removeInactivityListener(InactivityListener listener)
    {
        listenerList.remove(listener);
    }

    public void removeAllInactivityListeners()
    {
        listenerList = new Vector<InactivityListener>();
    }

    public void fireInactivityEvent(InactivityEvent event)
    {
        for (InactivityListener listener : listenerList)
        {
            listener.inactivityDetected(event);
        }
    }

}
