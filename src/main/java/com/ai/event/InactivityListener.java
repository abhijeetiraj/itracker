package com.ai.event;

import java.util.EventListener;


/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public interface InactivityListener extends EventListener
{

    public void inactivityDetected(InactivityEvent e);

}
