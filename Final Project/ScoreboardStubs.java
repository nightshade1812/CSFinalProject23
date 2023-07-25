import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.util.*; //PrintWriter class
import java.io.*; //File class

public class ScoreboardStubs extends JPanel
{
   private double winPercentage;
   private int winCount, gameCount;
   private JLabel winPercentLabel, winCountLabel;
   
   public ScoreboardStubs()
   {
      //initializing the objects we will need
      winPercentage = 0.0;
      winCount = gameCount = 0;
      
      winPercentLabel = new JLabel("---");
      winCountLabel = new JLabel("---");
      //these JLabels will show winPercentage and winCount for the user
   }
   
   public void saveData()
   {
      //this will save all score data and current gameboard state to a .txt file
   }
   
   public void update()
   {
      //this will update the scoreboard when a game is completed
   }
   
   public double findAvgGuesses()
   {
      //this will find the average number of guesses needed to find a word
      //it can be used for user statistics and will be displayed
      
      //a return statement is required so it will compile
      return 0.0;
   }
   
   //modifier methods
   public void setWinPercent(double x)
   {
      //sets winPercent to x
   }
   
   public void setWinCount(int x)
   {
      //sets winCount to x
   }
   
   public void setGameCount(int x)
   {
      //sets gameCount to x
   }
   
   //accessor methods
   public double getWinPercent()
   {
      //returns winPercentage
      return 0.0;
   }
   
   public int getWinCount()
   {
      //returns winCount
      return 0;
   }
   
   public int getGameCount()
   {
      //returns gameCount
      return 0;
   }
}