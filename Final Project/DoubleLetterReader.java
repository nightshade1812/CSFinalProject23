//Created by Sarah Trainer and Satik Karki, Final Version completed 7/27/2023

/**
*The DoubleLetterReader class checks for a special case involving the appearance of double letters in the answer versus in the guess
*/
public class DoubleLetterReader
{
  /**
   *Reads and returns the characters that appear more than once in the guess as a String
   @param   array - A <code>char</code> array 
   @return  doubledChars
   */ 
   public static String readDoubles(char[] array)
   {
      String doubledChars = "";
      for(int r = 0; r < array.length; r++) {
         for(int i = 0; i < array.length; i++) {
            if(array[r] == array[i] && r != i) {
               char addedChar = array[i];
               if(!doubledChars.contains("" + addedChar))
                  doubledChars = doubledChars + addedChar;
               break;
            }
         }
      }
      return doubledChars;
   }
   /**
   *Returns the amount of times that a character appears in the guess
   @param   array - a <code>char</code> array
   @param   doubled - The character that appears more than once
   @return  count
   */
   public static int countOfChar(char[] array, char doubled)
   {
      int count = 0;
      for(int i = 0; i < array.length; i++) {
         if(array[i] == doubled)
            count++;
      }
      return count;
   }
}
