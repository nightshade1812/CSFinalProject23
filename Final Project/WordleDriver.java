import javax.swing.*; //JPanel class
import java.awt.event.*; //WindowListener class

public class WordleDriver
{
   static Scoreboard scoreboard;
   
   public static void main(String[] args)
   {
      scoreboard = new Scoreboard();
      JFrame frame = new JFrame("Wordle - Final Project (Sarah and Satik)");
      frame.setSize(550, 655);
      frame.setLocation(200, 100);
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
