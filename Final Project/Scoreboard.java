import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.util.*; //PrintWriter class
import java.io.*; //File class
import java.text.DecimalFormat; //DecimalFormat class

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
   @see JLabel
   */
   private JLabel winCountLabel;
   
   /**
   *A DecimalFormat object that formats the win percentage.
   @see DecimalFormat
   */
   private DecimalFormat percent;
   
   /**
   *Creates a Scoreboard instance containing 2 JLabel objects
   */
   public Scoreboard()
   {
      setLayout(new FlowLayout()); //might change to another layout later on
      
      winPercentage = 0.0;
      winCount = gameCount = 0;
      
      percent = new DecimalFormat("0.0%");
      
      
      winCountLabel = new JLabel("Total Wins: " + winCount);
      winPercentLabel = new JLabel("Percent of Games Won: " + percent.format(100 * winPercentage));
      
      add(winCountLabel);
      add(winPercentLabel);
   }
   
   /**
   *Saves the current game data in an external text file via a PrintWriter instance
   @see PrintWriter
   */
   public void saveData()
   {
      PrintWriter dataFile = null;
      try{
         dataFile = new PrintWriter(new FileWriter("stats.txt"));
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "The game could not be saved.");
      }
      
      JLabel[][] board = Gameboard.getBoard();
      
      dataFile.println(winCount);
      dataFile.println(gameCount);
      dataFile.println(winPercentage);
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[0].length; c++) {
            if(board[r][c].getText().contains(" ")) {
               dataFile.println("null");
               break;
            }
            else
               dataFile.print(board[r][c].getText());
         }
         dataFile.print("\n");
      }
      dataFile.close();
   }
   
   /**
   *Updates the scoreboard after a game is completed
   */
   public void update()
   {
      gameCount++;
      if(Gameboard.winner())
         winCount++;
      winPercentage = (double)(winCount)/(double)(gameCount);
      winCountLabel.setText("Total Wins: " + winCount);
      winPercentLabel.setText("Percent of Games Won: " + percent.format(100 * winPercentage));
   }
   
   //modifier methods
   
   /**
   *Sets the win percentage to the double input x
   @param x    assigns x to winPercentage
   */
   public void setWinPercent(double x)
   {
      winPercentage = x;
   }
   
   /**
   *Sets the total win count to the int input x
   @param x    assigns x to winCount
   */
   public void setWinCount(int x)
   {
      winCount = x;
   }
   
   /**
   *Sets the total game count to the int input x
   @param x    assigns x to gameCount
   */
   public void setGameCount(int x)
   {
      gameCount = x;
   }
   
   //accessor methods
   
   /**
   *Returns the percentage of games won as a double
   @return     winPercentage
   */
   public double getWinPercent()
   {
      return winPercentage;
   }
   
   /**
   *Returns the number of games won as an int
   @return     winCount
   */
   public int getWinCount()
   {
      return winCount;
   }
   
   /**
   *Returns the total number of games played as an int
   @return     gameCount
   */
   public int getGameCount()
   {
      return gameCount;
   }
}
