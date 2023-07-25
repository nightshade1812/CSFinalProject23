import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.util.*; //PrintWriter class
import java.io.*; //File class

/**
*The Scoreboard class keeps track of the total wins and the win percentage of the overall game. This data can be saved in an external
* text file along with the current game state, which can be later retrieved by the player. 
*/

public class Scoreboard extends JPanel
{
   /**
   *A double that stores the ratio of wins to total games played.
   */
   private double winPercentage;
   
   /**
   *An int that stores the amount of games won.
   */
   private int winCount;
   
   /**
   *An int that stores the total amount of games that have been played. 
   */
   private int gameCount;
   
   /**
   *A JLabel object that displays the percentage of games won.
   @see JLabel
   */
   private JLabel winPercentLabel;
   
   /**
   *A JLabel object that displays the total amount of games won.
   @see Jlabel
   */
   private JLabel winCountLabel;
   
   /**
   *Creates a Scoreboard instance containing 2 JLabel objects
   */
   public Scoreboard()
   {
      //initializing the objects we will need
      winPercentage = 0.0;
      winCount = gameCount = 0;
      
      winPercentLabel = new JLabel("---");
      winCountLabel = new JLabel("---");
      //these JLabels will show winPercentage and winCount for the user
   }
   
   /**
   *Saves the current game data in an external text file via a PrintWriter instance
   @see PrintWriter
   */
   public void saveData()
   {
      //this will save all score data and current gameboard state to a .txt file
   }
   
   /**
   *Updates the scoreboard after a game is completed
   */
   public void update()
   {
      //this will update the scoreboard when a game is completed
   }
   
   /**
   *Finds the average amount of guesses it takes the player to find a word, returns it as a double
   @return     avgGuesses
   */
   public double findAvgGuesses()
   {
      //this will find the average number of guesses needed to find a word
      //it can be used for user statistics and will be displayed
      
      //a return statement is required so it will compile
      return 0.0;
   }
   
   //modifier methods
   
   /**
   *Sets the win percentage to the double input x
   @param x    assigns x to winPercentage
   */
   public void setWinPercent(double x)
   {
      //sets winPercent to x
   }
   
   /**
   *Sets the total win count to the int input x
   @param x    assigns x to winCount
   */
   public void setWinCount(int x)
   {
      //sets winCount to x
   }
   
   /**
   *Sets the total game count to the int input x
   @param x    assigns x to gameCount
   */
   public void setGameCount(int x)
   {
      //sets gameCount to x
   }
   
   //accessor methods
   
   /**
   *Returns the percentage of games won as a double
   @return     winPercentage
   */
   public double getWinPercent()
   {
      //returns winPercentage
      return 0.0;
   }
   
   /**
   *Returns the number of games won as an int
   @return     winCount
   */
   public int getWinCount()
   {
      //returns winCount
      return 0;
   }
   
   /**
   *Returns the total number of games played as an int
   @return     gameCount
   */
   public int getGameCount()
   {
      //returns gameCount
      return 0;
   }
}