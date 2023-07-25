import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class
import java.util.*; //Scanner class
import java.io.*; //File class

public class GameboardStubs extends JPanel
{
   private JLabel[][] board;
   private String answer;
   private int guess;
   
   public GameboardStubs()
   {
      //initializing the objects we will need
      board = new JLabel[6][5];
   }
   
   public void assignAnswer()
   {
      //will randomly select a line in answers.txt and assign it to answer
   }
   
   public boolean checkWordValid(String input)
   {
      //will check the input word against guesses.txt and determine if it is a word or not
      //also accounts for words of incorrect length
      
      //a return is required or it will not compile
      return true;
   }
   
   public void checkWord(String input, String answer)
   {
      checkWordValid(input);
      
      //when called, the input string will be checked against the answer
      //this method will change the colors of the JLabels to signify the letter's status
      //it will also account for when the game has ended, either by guesses being 6 or a win
   }
   
   public void inputKeyChar(char c)
   {
      //will update the gameboard when a key is pressed
   }
   
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