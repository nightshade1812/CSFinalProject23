import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects

/**
*The KeyboardStubs class contains a QWERTY keyboard display that changes depending on which letters the player has guessed.
*/
public class KeyboardStubs extends JPanel
{
   /**
   *A JLabel array instance that stores individual letters
   @see JLabel
   @see Arrays
   */
   private JLabel[] keyboard;


   /**
   *Creates a KeyboardStubs instance with a JLabel array
   */
   public KeyboardStubs()
   {
      //layout to be added here
      
      //initializing the objects we will need
      keyboard = new JLabel[28];
      //the keyboard array will be filled in the order of a traditional QWERTY keyboard
      //this includes backspace and enter
      
      //the keyboard will be added to the panel in three different GridLayouts
   }

   /**
   *Resets the colors of a KeyboardStub object
   */   
   public void reset()
   {
      //called in WordlePanel's reset
      //sets all the keyboard labels' colors back to default
   }

   /**
   *Updates the colors of individual letters on the QWERTY keyboard display depending on the player's guesses. 
   */  
   public void updateKeyboard()
   {
      //will change the colors of the keys depending on if they are in the word or not
      //this makes the user's interactions simpler, because they can have an easy reference
   }
}
