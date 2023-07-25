import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class

/*******
*The WordlePanelStubs class serves 
*/

public class WordlePanelStubs extends JPanel
{
   /**
   *The WordlePanelStubs' GameboardStubs
   @see GameboardStubs
   */
   private GameboardStubs gameboard;
   /**
   *The WordlePanelStubs' ScoreboardStubs
   @see ScoreboardStubs
   */
   private ScoreboardStubs scoreboard;
   /**
   *The WordlePanelStubs' KeyboardStubs
   @see KeyboardStubs
   */
   private KeyboardStubs keyboard;
   /**
   *A JButton that resets the WordlePanelStubs and its subpanels
   @see JButton
   */
   private JButton reset;
   /**
   *An int that determines the amount of guesses in the game
   @see int
   */
   
   //guessLimit starts at 6, as is traditional for Wordle
   private int guessLimit = 6;
   
   /****************************
   *Creates a WordlePanelStubs object containing a GameboardStubs object, a ScoreboardStubs object, a KeyBoardStubs object, and a JButton object
   */
   
   public WordlePanelStubs()
   {
      //initializing the objects we will need
      gameboard = new GameboardStubs();
      scoreboard = new ScoreboardStubs();
      keyboard = new KeyboardStubs();
      reset = new JButton("Reset");
      
      //adding an action listener for the reset button
      reset.addActionListener(new ResetListener());
   }
   /**
   *Resets the GameboardStubs and the KeyboardStubs by calling their reset methods
   @see GameboardStubs.reset()
   @see KeyboardStubs.reset()
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
      //this will set the number of guesses to x
   }

   /**
   *Returns the total amount of guesses in the current game
   @return     guessLimit
   */
   public int getGuessLimit()
   {
      //this will return the current guess limit
      
      //a return statement is required so it will compile
      return 0;
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
