package com.ai.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ai.event.InactivityEvent;
import com.ai.event.InactivityListener;
import com.ai.tracker.InactivityTracker;

/**
 * 
 * @author Abhijeet Iraj
 * 
 */
public class TrackerDemo extends JFrame implements InactivityListener
{
    private static final long serialVersionUID = 1L;
    private InactivityTracker tracker;
    private JButton start, stop;

    public TrackerDemo()
    {
        super("TrackerDemo");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        start = new JButton("Start Tracking");
        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                start.setEnabled(false);
                // Notify when there is inactivity for 10 seconds
                tracker = InactivityTracker.getInstance(10000);
                tracker.addInactivityListener(TrackerDemo.this);
                tracker.startTracking();
            }
        });
        mainPanel.add(start);
        stop = new JButton("Stop Tracking");
        stop.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tracker.stopTracking();
                start.setEnabled(true);
            }
        });
        mainPanel.add(stop);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        setSize(new Dimension(400, 100));
        setVisible(true);
    }

    public static void main(String args[])
    {
        new TrackerDemo();
    }

    public void inactivityDetected(InactivityEvent e)
    {
        start.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Inactivity detected for 10 seconds");
    }

}
