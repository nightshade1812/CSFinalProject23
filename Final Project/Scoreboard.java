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
   *A JLabel object that displays the total amount of games played.
   @see JLabel
   */
   private JLabel gameCountLabel;
   
   /**
   *A JLabel object that displays various game statuses (invalid word, winner/loser, etc.).
   @see JLabel
   */
   private static JLabel gameStatusLabel;
   
   /**
   *A JLabel object that displays the answer when the player loses.
   @see JLabel
   */
   private static JLabel answerDisplay;
   
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
   *A panel that helps organize the statistics.
   @see JPanel
   */
   private JPanel subpanel;
   
   /**
   *A string that stores the current style
   */
   private String style;
   
   /****************************
   *Fills the panel with a color depending on the theme
   */
   public void paintComponent(Graphics g)
   {
      if(style.equals("Neon")) {
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, 2000, 1800);
      }
      else if(style.equals("Classic")) {
         g.setColor(new Color(247, 247, 247));
         g.fillRect(0, 0, 2000, 1800);
      }
   }
   
   /**
   *Creates a Scoreboard instance containing 2 JLabel objects
   */
   public Scoreboard()
   {
      setLayout(new GridLayout(3, 1));
      setOpaque(true);
         
      try {
         dataReader = new Scanner(new File("stats.txt"));
      }
      catch(FileNotFoundException e) {
         winCount = gameCount = 0;
      }
      
      try {
         winCount = dataReader.nextInt();
         gameCount = dataReader.nextInt();
         winPercentage = dataReader.nextDouble();
      }
      catch(NoSuchElementException e) {
         winCount = gameCount = 0;
         winPercentage = 0.0;
      }
      for(int i = 0; i < 8; i++)
         dataReader.nextLine();
      style = dataReader.nextLine();
      dataReader.close();
      
      percent = new DecimalFormat("0.0%");
      
      subpanel = new JPanel();
      subpanel.setLayout(new GridLayout(3, 1));
      subpanel.setOpaque(true);
      
      if(style.equals("Classic"))
      {
         subpanel.setBackground(new Color(247, 247, 247));
      }
      else if(style.equals("Neon"))
      {
         subpanel.setBackground(new Color(255, 240, 255));
      }
      
      winCountLabel = new JLabel("Total Wins: " + winCount, SwingConstants.CENTER);
      gameCountLabel = new JLabel("Games Played: " + gameCount, SwingConstants.CENTER);
      winPercentLabel = new JLabel("Percent of Games Won: " + percent.format(winPercentage), SwingConstants.CENTER);
      gameStatusLabel = new JLabel("    ", SwingConstants.CENTER);  
      answerDisplay = new JLabel("    ", SwingConstants.CENTER);
      
      gameCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
      winCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
      winPercentLabel.setFont(new Font("Arial", Font.BOLD, 14));
      gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 18));
      answerDisplay.setFont(new Font("Arial", Font.BOLD, 40));
      
      gameCountLabel.setOpaque(true);
      winCountLabel.setOpaque(true);
      winPercentLabel.setOpaque(true);
      gameStatusLabel.setOpaque(true);
      answerDisplay.setOpaque(true);
      
      if(style.equals("Neon"))
      {
         gameCountLabel.setBackground(new Color(255, 240, 255));
         winCountLabel.setBackground(new Color(255, 240, 255));
         winPercentLabel.setBackground(new Color(255, 240, 255));
         gameStatusLabel.setBackground(new Color(255, 240, 255));      //very light pink
         answerDisplay.setBackground(new Color(255, 240, 255));
         subpanel.setBackground(new Color(255, 240, 255));
            
         answerDisplay.setForeground(new Color(12, 245, 190));  //Neon turquoiseish green
      }
      else if(style.equals("Classic"))
      {
         gameCountLabel.setBackground(new Color(247, 247, 247));
         winCountLabel.setBackground(new Color(247, 247, 247));
         winPercentLabel.setBackground(new Color(247, 247, 247));      //very very very light grey
         gameStatusLabel.setBackground(new Color(247, 247, 247));
         answerDisplay.setBackground(new Color(247, 247, 247));
         subpanel.setBackground(new Color(247, 247, 247));
            
         answerDisplay.setForeground(new Color(106, 170, 100)); //Classic Wordle green
      }
      
      subpanel.add(gameCountLabel);
      subpanel.add(winCountLabel);
      subpanel.add(winPercentLabel);
      add(subpanel);
      add(gameStatusLabel);
      add(answerDisplay);
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
      
      int printNumCount = 0;
      boolean nullPrinted = false;
      
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[0].length; c++) {
            if(board[r][c].getText().equals("     ")) {
               if(nullPrinted == false) {
                  dataFile.print("null");
                  printNumCount = 5;
                  nullPrinted = true;
                  break;
               }
               else {
                  while(printNumCount < 5) {
                     dataFile.print("X");
                     printNumCount++;
                  }
               }
            }
            else {
               dataFile.print(board[r][c].getText().replace(" ", ""));
               printNumCount++;
               nullPrinted = true;
            }
         }
         printNumCount = 0;
         nullPrinted = false;
         dataFile.println();
      }
      dataFile.println(WordlePanel.getStyleName());
      dataFile.close();
   }
   
   public void clearData()
   {
      PrintWriter dataFile = null;
      try {
         dataFile = new PrintWriter(new FileWriter("stats.txt"));
      }
      catch(Exception e) {
         JOptionPane.showMessageDialog(null, "The data could not be cleared.");
      }
      
      JLabel[][] board = Gameboard.getBoard();
      
      winCount = gameCount = 0;
      winPercentage = 0.0;
      
      dataFile.println(winCount);
      dataFile.println(gameCount);
      dataFile.println(winPercentage);
      
      String answer = "";
      Scanner answerSelect = null;
      int line = (int)(Math.random() * 2039 + 1);
      try {
         answerSelect = new Scanner(new File("answers.txt"));
      }
      catch(FileNotFoundException e) {
         System.out.println("wrong file oopsies");
         System.exit(0);
      }
      for(int i = 0; i < line; i++) {
         answer = answerSelect.next();
      }
      dataFile.println(answer.toUpperCase());
      
      for(int i = 0; i < 6; i++)
         dataFile.println("null");
      dataFile.println("Classic");
      dataFile.close();
      winCountLabel.setText("Total Wins: " + winCount);
      gameCountLabel.setText("Games Played: " + gameCount);
      winPercentLabel.setText("Percent of Games Won: " + percent.format(winPercentage));
      gameStatusLabel.setText("    ");  
      answerDisplay.setText("    ");
   }
   
   /**
   *Updates the scoreboard after a game is completed
   */
   public void update()
   {
      if(Gameboard.getGameEnd())
      {
         gameCount++;
         if(Gameboard.winner())
            winCount++;
         winPercentage = (double)(winCount)/(double)(gameCount);
         winCountLabel.setText("Total Wins: " + winCount);
         gameCountLabel.setText("Games Played: " + gameCount);
         winPercentLabel.setText("Percent of Games Won: " + percent.format(winPercentage));
      }
      else {
         winCountLabel.setText("Total Wins: " + winCount);
         gameCountLabel.setText("Games Played: " + gameCount);
         winPercentLabel.setText("Percent of Games Won: " + percent.format(winPercentage));
      }
   }

   
   /**
   *Updates the colors of the scoreboard
   */
   public void changeStyle(String style)
   {
      repaint();
      if(style.equals("Neon"))
      {
         gameCountLabel.setBackground(new Color(255, 240, 255));
         winCountLabel.setBackground(new Color(255, 240, 255));
         winPercentLabel.setBackground(new Color(255, 240, 255));
         gameStatusLabel.setBackground(new Color(255, 240, 255));      //very light pink
         answerDisplay.setBackground(new Color(255, 240, 255));
         subpanel.setBackground(new Color(255, 240, 255));
            
         answerDisplay.setForeground(new Color(12, 245, 190));  //Classic Wordle Green
      }
      else if(style.equals("Classic"))
      {
         gameCountLabel.setBackground(new Color(247, 247, 247));
         winCountLabel.setBackground(new Color(247, 247, 247));
         winPercentLabel.setBackground(new Color(247, 247, 247));      //very very very light grey
         gameStatusLabel.setBackground(new Color(247, 247, 247));
         answerDisplay.setBackground(new Color(247, 247, 247));
         subpanel.setBackground(new Color(247, 247, 247));
            
         answerDisplay.setForeground(new Color(106, 170, 100)); //Neon turquoisish green
      }
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
   
   /**
   *Returns the answer display label
   @return     answerDisplay
   */
   public static JLabel getAnswerDisplay()
   {
      return answerDisplay;
   }
}
