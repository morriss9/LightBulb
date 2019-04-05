package Ch11Exceptions;
//********************************************************************
//  StopWatchPanel.java       Authors: Lewis/Loftus
//
//  Solution to Programming Project 9.13
//********************************************************************

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.text.*;

public class LightBulbControls extends JPanel
{
   private DecimalFormat fmt;
   private JLabel timeDisplay;
   private Timer timer;
   private double time;

   private final String ZERO_TIME = "0.0";
   private final int DELAY = 100;
   private final String START = "Start";
   private final String STOP = "Stop";
   private final String RESET = "Reset";
   
   private LightBulbPanel bulb;
   private JButton onButton, offButton;

   //-----------------------------------------------------------------
   //  Sets up the GUI for the stop watch.
   //-----------------------------------------------------------------
   public LightBulbControls(LightBulbPanel bulbPanel)
   {
      // initialize the time display
      time = 0.0;
      timeDisplay = new JLabel(ZERO_TIME);
      timeDisplay.setBackground(Color.white);
      timeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
      timeDisplay.setFont(new Font("SAN_SERIF",Font.BOLD,24));
      timeDisplay.setForeground(Color.white);
      timeDisplay.setBorder(new LineBorder(Color.black, 3));
      add(timeDisplay,BorderLayout.NORTH);

      fmt = new DecimalFormat("0.#");

      bulb = bulbPanel;

      onButton = new JButton("On");
      onButton.setEnabled(false);
      onButton.setMnemonic('n');
      onButton.setToolTipText("Turn it on!");
      onButton.addActionListener(new OnListener());

      offButton = new JButton("Off");
      offButton.setEnabled(true);
      offButton.setMnemonic('f');
      offButton.setToolTipText("Turn it off!");
      offButton.addActionListener(new OffListener());

      setBackground(Color.black);
      add(onButton);
      add(offButton);
      
      onButton.setEnabled(true);
      offButton.setEnabled(false);

      // create the timer
      timer = new Timer(DELAY, new TimerActionListener());
   }

   //-----------------------------------------------------------------
   //  Starts the stop watch.
   //-----------------------------------------------------------------
    private void startTime()
    {
       timer.start();
    }

   //-----------------------------------------------------------------
   //  Stops the stop watch.
   //-----------------------------------------------------------------
   private void stopTime()
   {
      timer.stop();
   }

   //-----------------------------------------------------------------
   //  Resets the stop watch.
   //-----------------------------------------------------------------
   private void resetTime()
   {
      timer.stop();
      timeDisplay.setText(ZERO_TIME);
      time = 0.0;
   }

   //********************************************************************
   //  Represents the action listener for the timer.
   //********************************************************************
   private class TimerActionListener implements ActionListener
   {
      //-----------------------------------------------------------------
      //  Updates the time by one-tenth second.
      //-----------------------------------------------------------------
      public void actionPerformed(ActionEvent actionEvent)
      {
    	  if(time<10.0)
    	  {
    		 time = time + 0.1;
    		 timeDisplay.setText(fmt.format(time)); 
    	  }
    	  else
    	  {
        	 resetTime(); 
        	 bulb.setOn(false);
             onButton.setEnabled(true);
             offButton.setEnabled(false);
             bulb.repaint();
    	  }
      }
   }

   //*****************************************************************
   //  Represents the listener for the On button.
   //*****************************************************************
   private class OnListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Turns the bulb on and repaints the bulb panel.
      //--------------------------------------------------------------
      public void actionPerformed(ActionEvent event)
      {
         bulb.setOn(true);
         onButton.setEnabled(false);
         offButton.setEnabled(true);
         bulb.repaint();
         timer.start();
      }
   }

   //*****************************************************************
   //  Represents the listener for the Off button.
   //*****************************************************************
   private class OffListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Turns the bulb off and repaints the bulb panel.
      //--------------------------------------------------------------
      public void actionPerformed(ActionEvent event)
      {
         bulb.setOn(false);
         onButton.setEnabled(true);
         offButton.setEnabled(false);
         bulb.repaint();
      }
   }
}
