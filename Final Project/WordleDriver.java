import javax.swing.*; //JPanel class
import java.awt.event.*; //WindowListener class
import java.awt.*; //Dimension class

public class WordleDriver
{
   static Scoreboard scoreboard;
   
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
   
   private static class SaveAndClose extends WindowAdapter 
   {
      public void windowClosing(WindowEvent windowEvent) 
      {
         Scoreboard.saveData();
         System.exit(0);
      }
   }
} 
