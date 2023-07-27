import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class
import java.util.*; //Scanner class
import java.io.*; //File class

/**
*The Gameboard class is the main game container. The main player input happens here via the keyboard. The word is chosen and checked 
* against the player's input via several methods. 
*/
public class Gameboard extends JPanel
{
   /**
   *A JLabel matrix within a Gameboard instance, will display a word on each row
   */
   private static JLabel[][] board;
   
   /**
   *A string that stores the word to be guessed
   */
   private static String answer;
   
   /**
   *A string that stores the current word being guessed
   */
   private static String wordGuess;
   
   /**
   *An int that stores the amount of guesses used
   */
   private static int guess;
   
   /**
   *An int that stores the row the Wordle is typing in
   */
   private static int row;
   
   /**
   *An int that stores which space is the one to be typed in on the next key input
   */
   private static int space;
   
   /**
   *A boolean that stores whether or not the game has ended in a win
   */
   private static boolean win;
   
   /**
   *A boolean that stores whether or not the game has ended
   */
   private static boolean gameEnd;
   
   
   /**
   *A string array that stores all valid key names
   */
   private String[] validKeys;
   
   /**
   *A scanner that will be used to search a file and find a random word to be the answer.
   */
   private Scanner answerSelect;
   
   /**
   *A scanner that will be used to search a file and find whether or not a word is a valid guess.
   */
   private Scanner wordFinder;
   
   /**
   *A string that stores the current style
   */
   private String style = WordlePanel.getStyle();

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
         g.setColor(new Color(238, 238, 238));
         g.fillRect(0, 0, 2000, 1800);
      }
   }
   
   /**
   *Creates a new Gameboard object with a JLabel matrix
   */
   public Gameboard()
   {
      Scanner dataReader = null;
      
      setLayout(new GridLayout(6, 5, 2, 2));
      
      board = new JLabel[6][5];
      
      for(int r = 0; r < board.length; r++)
         for(int c = 0; c < board[0].length; c++)
         {
            board[r][c] = new JLabel("     ", SwingConstants.CENTER);
            board[r][c].setFont(new Font("Arial", Font.BOLD, 50));
            board[r][c].setOpaque(true);
            if(style.equals("Classic"))
               board[r][c].setBackground(Color.WHITE);
            else if(style.equals("Neon"))
               board[r][c].setBackground(new Color(255, 240, 255));
            add(board[r][c]);
         }
      
      try {
         dataReader = new Scanner(new File("stats.txt"));
      }
      catch(FileNotFoundException e) {
         
      }
      
      for(int i = 0; i < 3; i++)
         dataReader.nextLine();
      
      answer = dataReader.nextLine();
      
      for(int r = 0; r < 6; r++) {
         String word = dataReader.nextLine();
         if(word.equals("null"))
            break;
         else {
            for(int i = 0; i < word.length(); i++) {
               board[row][i].setText(word.charAt(i) + "");
            }
            if(checkWordValid(word) == true)
               checkWord(word, answer, WordlePanel.getStyleName());
            else
               for(int i = 0; i < word.length(); i++) {
                  board[row][i].setText("     ");
               }
         }
      }
      dataReader.close();
      System.out.println(answer);
      wordGuess = "";
      validKeys = new String[28];
      fillValidKeyCodeArray(validKeys);
      addKeyListener(new KeyDetector());
      setFocusable(true);
   }
  
   /**
   *Randomly selects a 5 letter word from answers.txt and assigns it to answer
   */ 
   public void assignAnswer()
   {
      int line = (int)(Math.random() * 2039 + 1);
      try {
         answerSelect = new Scanner(new File("answers.txt"));
      }
      catch(FileNotFoundException e) {
         System.out.println("wrong file oopsies");
         System.exit(0);
      }
      for(int i = 0; i < line; i++)
         answer = answerSelect.next();
      System.out.println(answer);
   }
   
   /**
   *Checks if the word that the user inputs is a valid word. Also checks for special cases (incorrect word length, special characters, etc.). Returns a boolean value
   @return     boolean
   */
   public boolean checkWordValid(String input)
   {
      try {
         wordFinder = new Scanner(new File("guesses.txt"));
      }
      catch(FileNotFoundException e) {
         System.out.println("wrong file oopsies");
         System.exit(0);
      }
      int count = 0;
      String[] array = new String[12947];
      for(int i = 0; i < 12947; i++)
         array[i] = wordFinder.nextLine();
         
      try {
         if(input.length() != 5)
            throw new StringBadLengthException("String is not five characters - try again");
      }
      catch(StringBadLengthException e) {
         return false;
      }
      
      if(Searcher.linear(array, input.toLowerCase()))
         return true;
      return false;
   }
   
   /**
   *Checks if the word that the user inputs is equal to answer and will change the colors of Gameboard squares or display a winner message accordingly
   */
   public void checkWord(String input, String answerin, String style)
   {
      if(checkWordValid(input)) {
         SoundEffect checker = new SoundEffect("pageturn.wav");
         checker.play();
         Scoreboard.getGameStatusLabel().setText("     ");
         answerin = answerin.toUpperCase();
         
         String[] doubleLettersChecker = new String[5];
         
         char[] inputChars = new char[5];
         char[] answerChars = new char[5];
         for(int i = 0; i < input.length(); i++) {
            inputChars[i] = input.charAt(i);
            answerChars[i] = answerin.charAt(i);
         }
         
         for(int i = 0; i < input.length(); i++) {
            if(inputChars[i] == answerChars[i]) {
               if(style.equals("Classic"))
                  board[row][i].setBackground(new Color(106, 170, 100));
               else if(style.equals("Neon"))
                  board[row][i].setBackground(new Color(12, 245, 190));
               doubleLettersChecker[i] = "green";
               board[row][i].setForeground(Color.WHITE);
               Keyboard.updateKeyboard("" + inputChars[i], 2, WordlePanel.getStyleName());
            }
            else if(answerin.contains("" + inputChars[i]) && inputChars[i] != answerChars[i]) {
               if(style.equals("Classic"))
                  board[row][i].setBackground(new Color(201, 180, 88));
               else if(style.equals("Neon"))
                  board[row][i].setBackground(new Color(255, 196, 0));
               doubleLettersChecker[i] = "yellow";
               board[row][i].setForeground(Color.WHITE);
               Keyboard.updateKeyboard("" + inputChars[i], 1, WordlePanel.getStyleName());
            }
            else if(inputChars[i] != answerChars[i] && !answer.contains("" + inputChars[i])) {
               if(style.equals("Classic"))
                  board[row][i].setBackground(new Color(120, 124, 126));
               else if(style.equals("Neon"))
                  board[row][i].setBackground(new Color(100, 10, 190));
               doubleLettersChecker[i] = "gray";
               board[row][i].setForeground(Color.WHITE);
               Keyboard.updateKeyboard("" + inputChars[i], 0, WordlePanel.getStyleName());
            }
         }
         int charCounter = 0;
         boolean skipChars = false;
         String doublesInput = DoubleLetterReader.readDoubles(inputChars);
         String doublesAnswer = DoubleLetterReader.readDoubles(answerChars);
         
         for(int d = 0; d < doublesInput.length(); d++) {
            if(doublesInput.length() == 0)
               break;
            for(int i = 0; i < 5; i++) {
               if(answerin.contains(inputChars[i] + "")) {
                  if(d != 0) {
                     if(input.charAt(i) != doublesInput.charAt(d - 1)) {
                        if(DoubleLetterReader.countOfChar(inputChars, inputChars[i]) > DoubleLetterReader.countOfChar(answerChars, answerChars[i])) {
                           int index = input.indexOf(inputChars[i] + "", i);
                           if(answerin.contains(inputChars[i] + ""))
                              charCounter++;
                           if(skipChars == true) {
                              if(answerin.contains(inputChars[i] + "")) {
                                 if(style.equals("Classic"))
                                    board[row][i].setBackground(new Color(120, 124, 126));
                                 else if(style.equals("Neon"))
                                    board[row][i].setBackground(new Color(100, 10, 190));
                              }
                           }
                           else {
                              if(inputChars[i] == answerChars[i]) {
                                 if(style.equals("Classic"))
                                    board[row][i].setBackground(new Color(106, 170, 100));
                                 else if(style.equals("Neon"))
                                    board[row][i].setBackground(new Color(12, 245, 190));
                                 Keyboard.updateKeyboard("" + inputChars[i], 2, WordlePanel.getStyleName());
                              }
                              else if(answerin.contains("" + inputChars[i]) && inputChars[i] != answerChars[i]) {
                                 if(style.equals("Classic"))
                                    board[row][i].setBackground(new Color(201, 180, 88));
                                 else if(style.equals("Neon"))
                                    board[row][i].setBackground(new Color(255, 196, 0));
                                 Keyboard.updateKeyboard("" + inputChars[i], 1, WordlePanel.getStyleName());
                              }
                           }
                           if(charCounter >= DoubleLetterReader.countOfChar(answerChars, answerChars[i])) {
                              skipChars = true;
                           }
                        }
                     }
                  }
                  else if(d == 0) {
                     if(DoubleLetterReader.countOfChar(inputChars, inputChars[i]) > DoubleLetterReader.countOfChar(answerChars, answerChars[i])) {
                        int index = input.indexOf(inputChars[i] + "", i);
                        if(answerin.contains(inputChars[i] + ""))
                           charCounter++;
                        if(skipChars == true) {
                           if(answerin.contains(inputChars[i] + "")) {
                              if(style.equals("Classic"))
                                 board[row][i].setBackground(new Color(120, 124, 126));
                              else if(style.equals("Neon"))
                                 board[row][i].setBackground(new Color(100, 10, 190));
                           }
                        }
                        if(charCounter >= DoubleLetterReader.countOfChar(answerChars, answerChars[i])) {
                           skipChars = true;
                        }
                     }
                  }
               }
            }
            charCounter = 0;
            skipChars = false;
         }
         
         space = 0;
         row++;
         guess++;
         if(input.equalsIgnoreCase(answerin)) {
            gameEnd = true;
            win = true;
            winner();
            Scoreboard.getGameStatusLabel().setText("You win! Congratulations.");
            SoundEffect winner = new SoundEffect("win.wav");
            winner.play();
         }
         else if(guess == 6) {
            gameEnd = true;
            win = false;
            winner();
            Scoreboard.getGameStatusLabel().setText("Sorry, you lost! The word was: ");
            Scoreboard.getAnswerDisplay().setText(answer.toUpperCase());
            SoundEffect loser = new SoundEffect("loser.wav");
            loser.play();
         }
         wordGuess = "";
      }
      else
         Scoreboard.getGameStatusLabel().setText("Word invalid. Please try again.");
   }
   
   /**
   *Updates the JLabel matrix to display the letter that the player has typed
   */
   public void inputKeyChar(char c)
   {
      board[row][space].setText("" + c);
   }
   
   /**
   *Sets the entire JLabel matrix to blank, resets guess and answer
   */
   public void reset()
   {
      assignAnswer();
      guess = row = space = 0;
      for(int r = 0; r < 6; r++) {
         for(int c = 0; c < 5; c++) {
            board[r][c].setBackground(Color.WHITE);
            board[r][c].setForeground(Color.BLACK);
            board[r][c].setText("     ");
         }
      }
      Scoreboard.getGameStatusLabel().setText("      ");
      Scoreboard.getAnswerDisplay().setText("      ");
      gameEnd = false;
      win = false;
   }
   
   /**
   *
   */
   public void changeStyle(String stylein)
   {
      style = stylein;
      repaint();
      if(style.equals("Neon"))
      {
         for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
            {
               if(board[r][c].getBackground().equals(new Color(106, 170, 100)))  // Classic Wordle Green
                  board[r][c].setBackground(new Color(12, 245, 190)); //Neon turquoisish green
                     
               else if(board[r][c].getBackground().equals(new Color(201, 180, 88))) // Classic Wordle Yellow
                  board[r][c].setBackground(new Color(255, 196, 0)); //Neon orangeish yellow
                     
               else if(board[r][c].getBackground().equals(new Color(120, 124, 126))) //Classic Wordle cool grey
               {
                  board[r][c].setBackground(new Color(100, 10, 190)); //Neon blurple
               }
               else if(board[r][c].getBackground().equals(Color.white))
               {
                  board[r][c].setBackground(new Color(255, 240, 255)); //Neon light pink
               }
            }
      }
      else if(style.equals("Classic"))
      {
         for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
            {
               if(board[r][c].getBackground().equals(new Color(12, 245, 190)))  //Neon turquoisish green
                  board[r][c].setBackground(new Color(106, 170, 100)); // Classic Wordle Green 
                     
               else if(board[r][c].getBackground().equals(new Color(255, 196, 0))) //Neon orangeish yellow
                  board[r][c].setBackground(new Color(201, 180, 88)); // Classic Wordle Yellow
                     
               else if(board[r][c].getBackground().equals(new Color(100, 10, 190))) //Neon blurple
               {
                  board[r][c].setBackground(new Color(120, 124, 126)); //Classic Wordle cool grey
               }
               else if(board[r][c].getBackground().equals(new Color(255, 240, 255)))//Neon light pink
               {
                  board[r][c].setBackground(Color.white); 
               }
            }
      }
   }
   
   /**
   *Displays a message depending on whether the player has won or lost
   */
   public static boolean winner()
   {
      WordlePanel.getResetButton().setEnabled(true);
      if(win == true)
         return true;
      return false;
   }
   
   /**
   *Returns the current status of the game
   */
   public static boolean getGameEnd()
   {
      return gameEnd;
   }
   
   /**
   *Returns the current answer to the Wordle
   */
   public static String getAnswer()
   {
      return answer;
   }
   
   /**
   *Manually fills the array of valid key codes
   */
   public void fillValidKeyCodeArray(String[] arr)
   {
      arr[0] = "Q";
      arr[1] = "W";
      arr[2] = "E";
      arr[3] = "R";
      arr[4] = "T";
      arr[5] = "Y";
      arr[6] = "U";
      arr[7] = "I";
      arr[8] = "O";
      arr[9] = "P";
      arr[10] = "A";
      arr[11] = "S";
      arr[12] = "D";
      arr[13] = "F";
      arr[14] = "G";
      arr[15] = "H";
      arr[16] = "J";
      arr[17] = "K";
      arr[18] = "L";
      arr[19] = "Z";
      arr[20] = "X";
      arr[21] = "C";
      arr[22] = "V";
      arr[23] = "B";
      arr[24] = "N";
      arr[25] = "M";
      arr[26] = "⏎";
      arr[27] = "⌫";
   }
   
   /**
   *Returns the current board matrix
   @return     JLabel[][]
   */
   public static JLabel[][] getBoard()
   {
      return board;
   }
   
   /**
   *Returns the current guess number
   @return     int
   */
   public static int getGuess()
   {
      return guess;
   }
   
   //the key listener that we will use to update the board as keys are typed
   private class KeyDetector extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         int keyCode = e.getKeyCode();
         String keyName = KeyEvent.getKeyText(keyCode);
         keyName = keyName.toUpperCase();
         //System.out.println(keyName);
         
         if((!keyName.equals("⌫") && !keyName.equals("⏎")) && Searcher.linear(validKeys, keyName)) {
            if(space == 5)
               return;
            board[row][space].setText(" " + keyName + " ");
            space++;
            wordGuess = wordGuess + keyName;
         }
         else if(keyName.equals("⌫")) {
            if(space == 0) {
               space = 1;
               wordGuess = wordGuess + " ";
            }
            board[row][space - 1].setText("   ");
            space--;
            wordGuess = wordGuess.substring(0, wordGuess.length() - 1);
         }
         else if(keyName.equals("⏎"))
            checkWord(wordGuess, answer, WordlePanel.getStyleName());
         else
            return;
      }
   }
}
