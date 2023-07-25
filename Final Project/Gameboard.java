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
   *Creates a new Gameboard object with a JLabel matrix
   */
   public Gameboard()
   {
      board = new JLabel[6][5];
      assignAnswer();
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
   *Checks if the word that the user inputs is equal to answer, will change the colors of Gameboard stubs or display a winner message accordingly
   */
   public void checkWord(String input, String answer)
   {
      checkWordValid(input);
      
      //when called, the input string will be checked against the answer
      //this method will change the colors of the JLabels to signify the letter's status
      //it will also account for when the game has ended, either by guesses being 6 or a win
   }
   
   /**
   *Updates the JLabel matrix to display the letter that the player has typed
   */
   public void inputKeyChar(char c)
   {
      //will update the gameboard when a key is pressed
   }
   
   /**
   *Sets the entire JLabel matrix to blank, resets guess and answer
   */
   public void reset()
   {
      //called in WordlePanel's reset
      //sets all the JLabels back to default and resets their colors
   }
   
   //the key listener that we will use to update the board as keys are typed
   private class KeyDetector extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         //this keyPressed will add the character of the key pressed to the game board
         //if enter is pressed, then it will check the entered word and enact that code
         //if backspace is pressed, it will remove the last character unless it is on the first square
      }
   }
}
