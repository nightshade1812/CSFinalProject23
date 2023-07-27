//Created by Sarah Trainer, final version completed 7/24/2023

/**
*The Searcher class looks through <code>guesses.txt</code> and <code>answers.txt</code>, comparing it to the word that the user inputs.
*/
public class Searcher
{
   /**
   *A linear search that compares the input word against <code>guesses.txt</code>.
   @param   array - A String array, the list the search checks
   @param   target - The input word being searched for
   @return  boolean
   */
   public static boolean linear(String[] array, String target)
   {
      for(int i = 0; i < array.length; i++) {
         if(target.equalsIgnoreCase(array[i]))
            return true;
      }
      return false;
   }
   
   /**
   *A recursive binary search that compares the input word against <code>answers.txt</code>.
   @param   array - A String array, the list the search checks
   @param   target - The input word being searched for
   @param   first - An <code>int</code> that determines where the search starts
   @param   last - An <code>int</code> that determines where the search ends
   @return  boolean
   */
   public static boolean binary(String[] array, String target, int first, int last)
   {
      int middle = (first + last)/2;
      
      if(target.compareTo(array[middle]) < 0)
         return binary(array, target, first, middle + 1);
      else if(target.compareTo(array[middle]) > 0)
         return binary(array, target, middle - 1, last);
      else if(target.equalsIgnoreCase(array[middle]))
         return true;
      return false;
   }
}
