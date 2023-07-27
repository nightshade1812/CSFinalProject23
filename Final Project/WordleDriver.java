//Created by Sarah Trainer and Satik Karki, final version completed on 7/27/2023

import javax.swing.*; //JPanel class
import java.awt.event.*; //WindowListener class
import java.awt.*; //Dimension class

/**
*WordleDriver is the driver containing the main executable code for Wordle. 
*/
public class WordleDriver
{
   /**
   *A static instance of <code>Scoreboard</code>, used to keep track of game states and user statistics.
   @see  Scoreboard
   */
   static Scoreboard scoreboard;

   /**
   *The main executable method, containing a <code>JFrame</code> which contains a <code>WordlePanel</code> instance.
   *The main method also contains an instance of the <code>SaveAndClose</code> class, which implements <code>WindowAdaptor</code>.
   *This class checks for when the <code>JFrame</code> is closed and then saves the game state and player statistics
   @param   args - A array of strings created from written code. 
   @see  JPanel
   @see  WindowAdapter
   */
   public static void main(String[] args)
   {
      scoreboard = new Scoreboard();
      JFrame frame = new JFrame("Wordle - Final Project (Sarah and Satik)");
      frame.setSize(930, 650);
      frame.setLocation(200, 100);
      frame.setMinimumSize(new Dimension(890, 640));
      frame.setMaximumSize(new Dimension(910, 660));
      frame.setMaximizedBounds(new Rectangle(200, 0, 900, 715));
      frame.addWindowListener(new SaveAndClose());
      frame.setContentPane(new WordlePanel());
      frame.setVisible(true);
   }

   /**
   *A private class that implements <code>WindowAdaptor</code>. A <code>SaveAndClose</code> instance checks to see if the window gets closed, then saves the 
   *score by calling <code>Scoreboard</code>'s <code>saveData()</code> method. 
   @see  WindowAdapter
   @see  Scoreboard
   */  
   private static class SaveAndClose extends WindowAdapter 
   {
      public void windowClosing(WindowEvent windowEvent) 
      {
         Scoreboard.saveData();
         System.exit(0);
      }
   }
} 
