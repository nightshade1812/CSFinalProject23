import javax.swing.*; //JPanel class
import java.awt.event.*; //WindowListener class

public class WordleDriver
{
   static Scoreboard scoreboard = new Scoreboard();
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Wordle - Final Project (Sarah and Satik)");
      frame.setSize(425, 625);
      frame.setLocation(200, 100);
      frame.addWindowListener(new SaveAndClose());
      frame.setContentPane(new WordlePanel());
      frame.setVisible(true);
   }
   
   private static class SaveAndClose extends WindowAdapter 
   {
      public void windowClosing(WindowEvent windowEvent) 
      {
         scoreboard.saveData();
         System.exit(0);
      }
   }
} 
