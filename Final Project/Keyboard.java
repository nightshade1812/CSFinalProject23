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
   private static JLabel[] keyboard;
  
  /****************************
   *Fills the panel with white
   */
   public void paintComponent(Graphics g)
   {
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, 600, 600);
   }
   
/**
*Creates a Keyboard instance with a JLabel array
*/
   public Keyboard()
   {
      Dimension keycap = new Dimension(45, 50);
      
      setLayout(new BorderLayout());
      
      keyboard = new JLabel[26];
      
      assignKeys(keyboard);
      for(int k = 0; k < 26; k++) {
         keyboard[k].setFont(new Font("Arial", Font.BOLD, 35));
         keyboard[k].setOpaque(true);
         keyboard[k].setBackground(Color.WHITE);
         keyboard[k].setPreferredSize(keycap);
      }
      
      JPanel sub1 = new JPanel();
      sub1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
         
      for(int k = 0; k < 10; k++)
         sub1.add(keyboard[k]);
       
      add(sub1, BorderLayout.NORTH);   
      
      JPanel sub2 = new JPanel();
      sub2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 4));
     
      
      JLabel shortBlank = new JLabel("     ");
      shortBlank.setFont(new Font("Arial", Font.PLAIN, 15));
      sub2.add(shortBlank);
      
      for(int k = 10; k < 19; k++)
         sub2.add(keyboard[k]);
      
      add(sub2, BorderLayout.CENTER);
         
      JPanel sub3 = new JPanel();
      sub3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
      JLabel longBlank = new JLabel("                   ");
      sub3.add(longBlank);
         
      for(int k = 19; k < 26; k++)
         sub3.add(keyboard[k]);
      
      add(sub3, BorderLayout.SOUTH);
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
      for(int k = 0; k < 26; k++) {
         keyboard[k].setBackground(Color.WHITE);
         keyboard[k].setForeground(Color.BLACK);
      }
   }
 
/**
*Updates the colors of individual letters on the QWERTY keyboard display depending on the player's guesses. 
*/  
   public static void updateKeyboard(String letter, int matchLevel)
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
   
   public static void changeStyle(String style)
   {
   
      for(int k = 0; k < keyboard.length; k++)
      {
         if(style.equals("Neon"))
         {
            
            if(keyboard[k].getBackground().equals(new Color(106, 170, 100)))  // Classic Wordle Green
               keyboard[k].setBackground(new Color(12, 245, 190)); //Neon turquoisish green
               
            else if(keyboard[k].getBackground().equals(new Color(201, 180, 88))) // Classic Wordle Yellow
               keyboard[k].setBackground(new Color(255, 196, 0)); //Neon orangeish yellow
               
            else if(keyboard[k].getBackground().equals(new Color(120, 124, 126))) //Classic Wordle cool grey
            {
              keyboard[k].setBackground(new Color(100, 10, 190)); //Neon blurple
            }
            else
            {
             keyboard[k].setBackground(new Color(255, 240, 255)); //Neon light pink
            }
         }
         else if(style.equals("Classic"))
         {
            
            if(keyboard[k].getBackground().equals(new Color(12, 245, 190)))  //Neon turquoisish green
               keyboard[k].setBackground(new Color(106, 170, 100)); // Classic Wordle Green 
               
            else if(keyboard[k].getBackground().equals(new Color(255, 196, 0))) //Neon orangeish yellow
               keyboard[k].setBackground(new Color(201, 180, 88)); // Classic Wordle Yellow
               
            else if(keyboard[k].getBackground().equals(new Color(100, 10, 190))) //Classic Wordle cool grey
            {
              keyboard[k].setBackground(new Color(120, 124, 126)); //Neon blurple
            }
            else
            {
             keyboard[k].setBackground(Color.white); //Neon light pink
            }
         }
      
      }
   
   }
}
