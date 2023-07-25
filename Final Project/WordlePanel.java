import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class

/*******
*The WordlePanel class serves as the organizing panel for the game, containing several subpanels and menus/player options. 
*/

public class WordlePanel extends JPanel
{
   /**
   *The WordlePanel' Gameboard
   @see Gameboard
   */
   private Gameboard gameboard;
   /**
   *The WordlePanel' Scoreboard
   @see Scoreboard
   */
   private Scoreboard scoreboard;
   /**
   *The WordlePanel' Keyboard
   @see Keyboard
   */
   private Keyboard keyboard;
   /**
   *A JButton that resets the WordlePanel and its subpanels
   @see JButton
   */
   private JButton reset;
   /**
   *An int that determines the amount of guesses in the game
   */
   
   //guessLimit starts at 6, as is traditional for Wordle
   private int guessLimit = 6;
   
   /****************************
   *Creates a WordlePanel instance containing a Gameboard object, a Scoreboard object, a KeyBoard object, and a JButton object
   */
   
   public WordlePanel()
   {
      //initializing the objects we will need
      gameboard = new Gameboard();
      scoreboard = new Scoreboard();
      keyboard = new Keyboard();
      reset = new JButton("Reset");
      
      //adding an action listener for the reset button
      reset.addActionListener(new ResetListener());
   }
   /**
   *Resets the Gameboard and the Keyboard by calling their reset methods
   @see Gameboard.reset()
   @see Keyboard.reset()
   */
   public void reset()
   {
      gameboard.reset();
      keyboard.reset();
      //all variables will be reset and the button will be disabled
   }
   /**
   *Sets the amount of guesses per game to the input x
   @param x    assigns x to guessLimit
   */
   public void setGuessLimit(int x)
   {
      guessLimit = x;
   }
   
   /**
   *Returns the total amount of guesses in the current game
   @return     guessLimit
   */
   public int getGuessLimit()
   {
      return guessLimit;
   }
   
   //listener for the reset button
   private class ResetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //calls the WordlePanel's reset method
         reset();
      }
   }
}
