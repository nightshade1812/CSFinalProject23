public class DoubleLetterReader
{
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
