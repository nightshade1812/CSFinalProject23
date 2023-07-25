import java.util.*;
import java.io.*;
import javax.swing.*;

public class SortStringFile
{
   public static void main(String[] args) throws Exception
   {
      String[] arr = input();
      Arrays.sort(arr);
      PrintWriter numFile = new PrintWriter(new FileWriter("answers.txt"));
      for(int k = 0; k < arr.length; k++)
         numFile.println(arr[k]);
      numFile.close();
   }
   
   public static String[] input() 
   {
      Scanner infile = null;
      String filename = JOptionPane.showInputDialog("Input file name:");
      
      try {
         infile = new Scanner(new File(filename));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be found.");
         System.exit(0);
      }
      String[] array = new String[2309];
      for(int k = 0; k < array.length; k++)
      {
         array[k] = infile.next();
      }
      infile.close();
      return array;
   }
}