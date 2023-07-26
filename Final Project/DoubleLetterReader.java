public class DoubleLetterReader
{
   public String readString(String s)
   {
      String[] arr = new String[s.length()];
      
      for(int i = 0; i < s.length(); i++)
         arr[i] = "" + s.charAt(i);
      
      String letter1doubled = "" + Searcher.linear(arr, arr[0], 0);
      String letter2doubled = "" + Searcher.linear(arr, arr[1], 1);
      String letter3doubled = "" + Searcher.linear(arr, arr[2], 2);
      String letter4doubled = "" + Searcher.linear(arr, arr[3], 3);
      
      if(!letter1doubled.equals("-1"))
         letter1doubled = arr[0];
      if(!letter2doubled.equals("-1"))
         letter1doubled = arr[1];
      if(!letter3doubled.equals("-1"))
         letter1doubled = arr[2];
      if(!letter4doubled.equals("-1"))
         letter1doubled = arr[3];
      
      return "you messed it up skill issue";
   }
}