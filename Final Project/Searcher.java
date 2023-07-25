import java.io.*;
import java.util.*;

public class Searcher
{
   public static String linear(String[] array, String target)
   {
      for(int i = 0; i < array.length; i++) {
         if(target.equalsIgnoreCase(array[i]))
            return array[i];
      }
      return "not found >:(";
   }
   
   public static String binary(String[] array, String target, int first, int last)
   {
      int middle = (first + last)/2;
      
      if(target.compareTo(array[middle]) < 0)
         return binary(array, target, first, middle + 1);
      else if(target.compareTo(array[middle]) > 0)
         return binary(array, target, middle - 1, last);
      else if(target.equalsIgnoreCase(array[middle]))
         return array[middle];
      return "not found >:(";
   }
}