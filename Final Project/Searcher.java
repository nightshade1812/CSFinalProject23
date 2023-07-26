public class Searcher
{
   public static boolean linear(String[] array, String target)
   {
      for(int i = 0; i < array.length; i++) {
         if(target.equalsIgnoreCase(array[i]))
            return true;
      }
      return false;
   }
   
   public static int linear(String[] array, String target, int index)
   {
      for(int i = 0; i < array.length; i++) {
         if(target.equalsIgnoreCase(array[i]) && i != index)
            return i;
      }
      return -1;
   }
   
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
