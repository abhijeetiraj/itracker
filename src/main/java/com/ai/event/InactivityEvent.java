package com.ai.event;

import java.util.EventObject;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class InactivityEvent extends EventObject
{
    private static final long serialVersionUID = -7263723374198879967L;

    public InactivityEvent(Object source)
    {
        super(source);
    }

}
