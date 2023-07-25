import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects

public class KeyboardStubs extends JPanel
{
   private JLabel[] keyboard;
   
   public KeyboardStubs()
   {
      //layout to be added here
      
      //initializing the objects we will need
      keyboard = new JLabel[28];
      //the keyboard array will be filled in the order of a traditional QWERTY keyboard
      //this includes backspace and enter
      
      //the keyboard will be added to the panel in three different GridLayouts
   }
   
   public void reset()
   {
      //called in WordlePanel's reset
      //sets all the keyboard labels' colors back to default
   }
   
   public void updateKeyboard()
   {
      //will change the colors of the keys depending on if they are in the word or not
      //this makes the user's interactions simpler, because they can have an easy reference
   }
}