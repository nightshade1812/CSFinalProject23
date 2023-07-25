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
   private JLabel[][] board;
   
   /**
   *A string that stores the word to be guessed
   */
   private String answer;
   
   /**
   *A string that stores the current word being guessed
   */
   private String wordGuess;
   
   /**
   *An int that stores the amount of guesses used
   */
   private int guess;
   
   /**
   *An int that stores the row the Wordle is typing in
   */
   private int row;
   
   /**
   *An int that stores which space is the one to be typed in on the next key input
   */
   private int space;
   
   /**
   *A string array that stores all valid key names
   */
   private String[] validKeys;
   
   /**
   *Creates a new Gameboard object with a JLabel matrix
   */
   public Gameboard()
   {
      board = new JLabel[6][5];
      assignAnswer();
      fillValidKeyCodeArray(validKeys);
   }
  
   /**
   *Randomly selects a 5 letter word from answers.txt and assigns it to answer
   */ 
   public void assignAnswer()
   {
      int line = (int)(Math.random() * 2039 + 1);
      Scanner temp = new Scanner("answers.txt");
      for(int i = 0; i < line; i++)
         answer = temp.next();
      System.out.println(answer);
   }
   
   /**
   *Checks if the word that the user inputs is a valid word. Also checks for special cases (incorrect word length, special characters, etc.). Returns a boolean value
   @return     boolean
   */
   public boolean checkWordValid(String input)
   {
      Scanner temp = new Scanner("guesses.txt");
      Scanner temp2 = new Scanner("guesses.txt");
      int count = 0;
      
      while(temp.hasNext())
         count++;
      String[] array = new String[count];
      for(int i = 0; i < count; i++)
         array[i] = temp2.nextLine();
         
      try {
         if(input.length() != 5)
            throw new StringBadLengthException("String is not five characters - try again");
      }
      catch(StringBadLengthException e) {
         return false;
      }
      
      if(Searcher.binary(array, input, 0, count))
         return true;
      return false;
   }
   
   /**
   *Checks if the word that the user inputs is equal to answer and will change the colors of Gameboard squares or display a winner message accordingly
   */
   public void checkWord(String input, String answer)
   {
      if(checkWordValid(input)) {
         row++;
         for(int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            char ans = answer.charAt(i);
            if(temp == ans)
               board[row][i].setBackground(Color.GREEN);
            else if(answer.contains("" + temp) && temp != ans)
               board[row][i].setBackground(Color.YELLOW);
            else if(temp != ans && !answer.contains("" + temp))
               board[row][i].setBackground(new Color(100, 100, 100));
         }
         space = 0;
         guess++;
         if(input.equalsIgnoreCase(answer))
            winner("win");
         else if(guess == 6)
            winner("lose");
         wordGuess = "";
      }
      else
         System.out.println("Invalid word. Please try again.");
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
      for(int r = 0; r < 5; r++) {
         for(int c = 0; c < 6; c++) {
            board[r][c].setBackground(Color.WHITE);
            board[r][c].setText(" ");
         }
      }
   }
   
   /**
   *Displays a message depending on whether the player has won or lost
   */
   public boolean winner(String s)
   {
      if(s.equalsIgnoreCase("win")) {
         JOptionPane.showMessageDialog(null, "Winner!");
         return true;
      }
      else if(s.equalsIgnoreCase("lose")) {
         JOptionPane.showMessageDialog(null, "You lost! The word was " + answer + ".");
         return true;
      }
      return false;
   }
   
   /**
   *Manually fills the array of valid key codes
   */
   public void fillValidKeyCodeArray(String[] arr)
   {
      validKeys = new String[28];
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
      arr[26] = "ENTER";
      arr[27] = "BACKSPACE";
   }
   
   //the key listener that we will use to update the board as keys are typed
   private class KeyDetector extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         int keyCode = e.getKeyCode();
         String keyName = KeyEvent.getKeyText(keyCode);
         keyName = keyName.toUpperCase();
         
         if((keyName != "ENTER" || keyName != "BACKSPACE") && Searcher.binary(validKeys, keyName, 0, 28)) {
            board[row][space].setText(keyName);
            space++;
            wordGuess = wordGuess + keyName;
         }
         else if(keyName == "BACKSPACE") {
            board[row][space - 1].setText(" ");
            space--;
            wordGuess = wordGuess.substring(0, wordGuess.length() - 1);
         }
         else if(keyName == "ENTER")
            checkWord(wordGuess, answer);
      }
   }
}
