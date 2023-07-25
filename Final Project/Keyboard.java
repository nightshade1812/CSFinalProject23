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
      for(int k = 0; k < 26; k++) {
         keyboard[k].setFont(new Font("Arial", Font.BOLD, 35));
         keyboard[k].setOpaque(true);
      }
      
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
      arr[0] = new JLabel("Q", SwingConstants.CENTER);
      arr[1] = new JLabel("W", SwingConstants.CENTER);
      arr[2] = new JLabel("E", SwingConstants.CENTER);
      arr[3] = new JLabel("R", SwingConstants.CENTER);
      arr[4] = new JLabel("T", SwingConstants.CENTER);
      arr[5] = new JLabel("Y", SwingConstants.CENTER);
      arr[6] = new JLabel("U", SwingConstants.CENTER);
      arr[7] = new JLabel("I", SwingConstants.CENTER);
      arr[8] = new JLabel("O", SwingConstants.CENTER);
      arr[9] = new JLabel("P", SwingConstants.CENTER);
      arr[10] = new JLabel("A", SwingConstants.CENTER);
      arr[11] = new JLabel("S", SwingConstants.CENTER);
      arr[12] = new JLabel("D", SwingConstants.CENTER);
      arr[13] = new JLabel("F", SwingConstants.CENTER);
      arr[14] = new JLabel("G", SwingConstants.CENTER);
      arr[15] = new JLabel("H", SwingConstants.CENTER);
      arr[16] = new JLabel("J", SwingConstants.CENTER);
      arr[17] = new JLabel("K", SwingConstants.CENTER);
      arr[18] = new JLabel("L", SwingConstants.CENTER);
      arr[19] = new JLabel("Z", SwingConstants.CENTER);
      arr[20] = new JLabel("X", SwingConstants.CENTER);
      arr[21] = new JLabel("C", SwingConstants.CENTER);
      arr[22] = new JLabel("V", SwingConstants.CENTER);
      arr[23] = new JLabel("B", SwingConstants.CENTER);
      arr[24] = new JLabel("N", SwingConstants.CENTER);
      arr[25] = new JLabel("M", SwingConstants.CENTER);
   }  

/**
*Resets the colors of a Keyboard object
*/    
   public void reset()
   {
      for(int k = 0; k < 26; k++)
         keyboard[k].setBackground(Color.WHITE);
   }
 
/**
*Updates the colors of individual letters on the QWERTY keyboard display depending on the player's guesses. 
*/  
   public void updateKeyboard(String letter, int matchLevel)
   {
      String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";
      int i = alphabet.indexOf(letter);
      if(matchLevel == 0) {
         keyboard[i].setBackground(new Color(120, 124, 126));
         keyboard[i].setForeground(Color.WHITE);
      }
      else if(matchLevel == 1) {
         keyboard[i].setBackground(new Color(201, 180, 88));
         keyboard[i].setForeground(Color.WHITE);
      }
      else {
         keyboard[i].setBackground(new Color(106, 170, 100));
         keyboard[i].setForeground(Color.WHITE);
      }
   }
}
