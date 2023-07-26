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
   private static double winPercentage;
   
   /**
   *An int that stores the amount of games won.
   */
   private static int winCount;
   
   /**
   *An int that stores the total amount of games that have been played. 
   */
   private static int gameCount;
   
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
   *A JLabel object that displays various game statuses (invalid word, winner/loser, etc.).
   @see JLabel
   */
   private static JLabel gameStatusLabel;
   
   /**
   *A DecimalFormat object that formats the win percentage.
   @see DecimalFormat
   */
   private DecimalFormat percent;
   
   /**
   *A Scanner that reads a data file to put the game back at its last known state.
   @see Scanner
   */
   private Scanner dataReader;
   
   /**
   *Creates a Scoreboard instance containing 2 JLabel objects
   */
   public Scoreboard()
   {
      setLayout(new GridLayout(2, 1));
      
      try {
         dataReader = new Scanner(new File("stats.txt"));
      }
      catch(FileNotFoundException e) {
         winCount = gameCount = 0;
      }
      
      winCount = dataReader.nextInt();
      gameCount = dataReader.nextInt();
      winPercentage = dataReader.nextDouble();
      
      percent = new DecimalFormat("0.0%");
      
      JPanel subpanel = new JPanel();
      subpanel.setLayout(new GridLayout(2, 1));
      
      winCountLabel = new JLabel("Total Wins: " + winCount, SwingConstants.CENTER);
      winPercentLabel = new JLabel("Percent of Games Won: " + percent.format(winPercentage), SwingConstants.CENTER);
      gameStatusLabel = new JLabel("    ");
      winCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
      winPercentLabel.setFont(new Font("Arial", Font.BOLD, 14));
      gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
      
      subpanel.add(winCountLabel);
      subpanel.add(winPercentLabel);
      add(subpanel);
      add(gameStatusLabel);
   }
   
   /**
   *Saves the current game data in an external text file via a PrintWriter instance
   @see PrintWriter
   */
   public static void saveData()
   {
      PrintWriter dataFile = null;
      try {
         dataFile = new PrintWriter(new FileWriter("stats.txt"));
      }
      catch(Exception e) {
         JOptionPane.showMessageDialog(null, "The game could not be saved.");
      }
      
      JLabel[][] board = Gameboard.getBoard();
      
      dataFile.println(winCount);
      dataFile.println(gameCount);
      dataFile.println(winPercentage);
      
      dataFile.println(Gameboard.getAnswer().toUpperCase());
      
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[0].length; c++) {
            if(board[r][c].getText().equals("     ")) {
               dataFile.print("null");
               break;
            }
            else
               dataFile.print(board[r][c].getText().replace(" ", ""));
         }
         dataFile.println();
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
      winPercentLabel.setText("Percent of Games Won: " + percent.format(winPercentage));
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
   
   /**
   *Returns the game status label
   @return     gameStatusLabel
   */
   public static JLabel getGameStatusLabel()
   {
      return gameStatusLabel;
   }
}
