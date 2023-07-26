public class DoubleLetterReader
{
   public static String readDoubles(String s)
   {
      String doubleLetters = "";
      
      for(int i = 0; i < s.length(); i++) {
         if(s.indexOf("" + s.charAt(i)) != s.lastIndexOf("" + s.charAt(i)))
            doubleLetters = doubleLetters + s.charAt(i);
      }
      return doubleLetters;
   }
}