import javax.swing.*; //JPanel class

public class WordleDriverStubs
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Wordle - Final Project (Sarah and Satik)");
      frame.setSize(1000, 1000);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new WordlePanelStubs());
      frame.setVisible(true);
   }
}