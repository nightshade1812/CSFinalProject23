import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects

/**
*The Keyboard class contains a QWERTY keyboard display that changes depending on which letters the player has guessed.
*/
public class Keyboard extends JPanel
{

/**
*A JLabel array instance that stores individual letters
@see JLabel
@see java.util.Arrays
*/
   private JLabel[] keyboard;
  
/**
*Creates a Keyboard instance with a JLabel array
*/
   public Keyboard()
   {
      //layout to be added here
      setLayout(new GridLayout(3, 10, 1, 1));
      
      //initializing the objects we will need
      keyboard = new JLabel[26];
      
      assignKeys(keyboard);
      for(int k = 0; k < 26; k++)
         keyboard[k].setFont(new Font("Serif", Font.BOLD, 35));
      
      for(int k = 0; k < 10; k++)
         add(keyboard[k]);
      
      JLabel shortBlank = new JLabel(" ");
      add(shortBlank);
      
      for(int k = 10; k < 19; k++)
         add(keyboard[k]);
      
      JLabel longBlank = new JLabel("  ");
      add(longBlank);
      
      for(int k = 19; k < 26; k++)
         add(keyboard[k]);
   }

/**
*Adds JLabels to keyboard with letters in traditional QWERTY order
*/

   public static void assignKeys(JLabel[] arr)
   {
      arr[0] = new JLabel("Q");
      arr[1] = new JLabel("W");
      arr[2] = new JLabel("E");
      arr[3] = new JLabel("R");
      arr[4] = new JLabel("T");
      arr[5] = new JLabel("Y");
      arr[6] = new JLabel("U");
      arr[7] = new JLabel("I");
      arr[8] = new JLabel("O");
      arr[9] = new JLabel("P");
      arr[10] = new JLabel("A");
      arr[11] = new JLabel("S");
      arr[12] = new JLabel("D");
      arr[13] = new JLabel("F");
      arr[14] = new JLabel("G");
      arr[15] = new JLabel("H");
      arr[16] = new JLabel("J");
      arr[17] = new JLabel("K");
      arr[18] = new JLabel("L");
      arr[19] = new JLabel("Z");
      arr[20] = new JLabel("X");
      arr[21] = new JLabel("C");
      arr[22] = new JLabel("V");
      arr[23] = new JLabel("B");
      arr[24] = new JLabel("N");
      arr[25] = new JLabel("M");
   }  

/**
*Resets the colors of a KeyboardStub object
*/    
   public void reset()
   {
      for(int k = 0; k < 26; k++)
         keyboard[k].setBackground(Color.WHITE);
         
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
