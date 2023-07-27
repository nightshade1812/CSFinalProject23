import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class
import java.util.*; //Scanner class
import java.io.*; //File class

/*******
*The WordlePanel class serves as the organizing panel for the game, containing several subpanels and menus/player options. 
*/

public class WordlePanel extends JPanel
{
   /**
   *The WordlePanel' Gameboard
   @see Gameboard
   */
   private static Gameboard gameboard;
   
   /**
   *The WordlePanel' Scoreboard
   @see Scoreboard
   */
   private static Scoreboard scoreboard;
   
   /**
   *The WordlePanel' Keyboard
   @see Keyboard
   */
   private static Keyboard keyboard;
   
   /**
   *A JButton that resets the WordlePanel and its subpanels
   @see JButton
   */
   private static JButton reset;
   
   /**
   *A JButton that clears all data history and resets the game
   @see JButton
   */
   private static JButton clear;
   
   /**
   *An int that determines the amount of guesses in the game
   */   
   //guessLimit starts at 6, as is traditional for Wordle
   private int guessLimit = 6;
   
   /**
   * ///////////////////INSERT COMMENT HERE /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static JComboBox style;
   
   /**
   * //////////////////INSERT COMMENT HERE////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static JLabel title;
   
   /**
   * //////////////////INSERT COMMENT HERE////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static JLabel styleTitle;
   
   /**
   * /////////////////INSERT COMMENT HERE//////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static String styleName;
   
   /**
   * //////////////////INSERT COMMENT HERE////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static JPanel game, gameContainer;
   
   /****************************
   *Fills the panel with a color depending on the theme
   */
   public void paintComponent(Graphics g)
   {
      if(styleName.equals("Classic")) {
         g.setColor(Color.white);
         g.fillRect(0, 0, 2000, 1800);
      }
      else if(styleName.equals("Neon")) {
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, 2000, 1800);
      }
   }
   
   public static String getStyle()
   {
      return styleName;
   }
   
   /****************************
   *Creates a WordlePanel instance containing a Gameboard object, a Scoreboard object, a KeyBoard object, and a JButton object
   */
   public WordlePanel()
   {
      Scanner readStyle = null;
      
      try {
         readStyle = new Scanner(new File("stats.txt"));
      }
      catch(FileNotFoundException e) {
         styleName = "Classic";
      }
      for(int i = 0; i < 10; i++)
         readStyle.nextLine();
      styleName = readStyle.nextLine();
      if(styleName.equals("Classic")) {
         setBackground(Color.WHITE);
      }
      else if(styleName.equals("Neon")) {
         setBackground(Color.BLACK);
      }

      
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      
      setLayout(gridbag);
      setOpaque(true);
      setBackground(Color.green);
      
      gameContainer = new JPanel();
      gameContainer.setLayout(new FlowLayout());
      gameContainer.setOpaque(true);
      if(styleName.equals("Classic")) {
         gameContainer.setBackground(Color.WHITE);
      }
      else if(styleName.equals("Neon")) {
         gameContainer.setBackground(Color.BLACK);
      }
      
      title = new JLabel("     Wordle     ");
      title.setFont(new Font("Arial", Font.BOLD, 40));
      if(styleName.equals("Classic")) {
         title.setForeground(Color.BLACK);
      }
      else if(styleName.equals("Neon")) {
         title.setForeground(Color.WHITE);
      }
      gameContainer.add(title);
   
      game = new JPanel();
      game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
      game.setOpaque(true);
      if(styleName.equals("Classic")) {
         game.setBackground(new Color(238, 238, 238));
      }
      else if(styleName.equals("Neon")) {
         game.setBackground(Color.BLACK);
      }
         
      keyboard = new Keyboard();
      keyboard.setPreferredSize(new Dimension(500,180));
      keyboard.setOpaque(true);
      keyboard.setBackground(Color.BLACK);
      reset = new JButton("New Game");
      reset.setEnabled(true);
      reset.setFocusable(false);
      reset.addActionListener(new ResetListener());
      clear = new JButton("Clear Data");
      clear.setEnabled(true);
      clear.addActionListener(new ClearListener());
      clear.setFocusable(false);
      gameboard = new Gameboard();
      scoreboard = new Scoreboard();      
          
      game.add(gameboard);
      game.add(keyboard);
                 
      gameContainer.add(game);
      
      c.fill = GridBagConstraints.VERTICAL;
      c.weightx = 0.0;
      c.weighty = 1.0;
      c.gridx = 0;
      c.gridy = 0;
      c.gridheight = 5;
      add(gameContainer, c);
           
      c.fill = GridBagConstraints.NONE;
      c.weightx = 0.5;
      c.weighty = 0.1;
      c.gridx = 1;
      c.gridy = 3;
      c.gridheight = 1;
      c.anchor = GridBagConstraints.PAGE_START;
      add(reset, c);
      
      styleTitle = new JLabel("Choose a Style:");
      styleTitle.setFont(new Font("Arial", Font.BOLD, 20));
      if(styleName.equals("Neon"))
         styleTitle.setForeground(Color.WHITE);
      else if(styleName.equals("Classic"))
         styleTitle.setForeground(Color.BLACK);
      
      c.weightx = 1.0;
      c.gridx = 1;
      c.gridy = 1;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      add(styleTitle, c);
      
      String list2 = "";
      if(styleName.equals("Neon"))
         list2 = "Classic";
      else if(styleName.equals("Classic"))
         list2 = "Neon";
      String[] list = {styleName, list2};
      style = new JComboBox(list);       // for some reason this line gives the "unchecked or unsafe operations" message
      style.setFocusable(false);
      style.addActionListener(new StyleListener());
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.weighty = 0.5;
      c.gridx = 1;
      c.gridy = 2; 
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      add(style,c);
          
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 1;
      c.gridy = 0;
      c.weighty = 1;
      add(scoreboard, c);   
      
      c.fill = GridBagConstraints.NONE;
      c.weightx = 0.5;
      c.weighty = 1.2;
      c.gridx = 1;
      c.gridy = 4;
      c.gridheight = 1;
      c.anchor = GridBagConstraints.PAGE_START;
      add(clear, c);  
   } 
   
   /**
   *Resets the Gameboard and the Keyboard by calling their reset methods
   @see Gameboard.reset()
   @see Keyboard.reset()
   */
   public void reset()
   {
      scoreboard.update();
      gameboard.reset();
      keyboard.reset(styleName);
   }
   
   /**
   *Sets the amount of guesses per game to the input x
   @param x    assigns x to guessLimit
   */
   public void setGuessLimit(int x)
   {
      guessLimit = x;
   }
   
   /**
   *Returns the total amount of guesses in the current game
   @return     guessLimit
   */
   public int getGuessLimit()
   {
      return guessLimit;
   }
   
   /**
   *Returns the reset JButton for use in other classes
   @return     reset
   */
   public static JButton getResetButton()
   {
      return reset;
   }

   /**
   *Returns the name of the current style for use in other classes
   @return     styleName
   */
   public static String getStyleName()
   {
      return styleName;
   }
   
   //listener for the reset button
   private class ResetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //calls the WordlePanel's reset method
         
         SoundEffect reset = new SoundEffect("buttonclick.wav");
         reset.play();
         reset();
      }
   }
   
   //listener for the clear button
   private class ClearListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         SoundEffect clear = new SoundEffect("buttonclick.wav");
         clear.play();
         scoreboard.clearData();
         keyboard.reset(styleName);
         gameboard.reset();
      }
   }
   
   public void changeStyle(String s)
   {
      styleName = s;
      repaint();
      if(styleName.equals("Classic")) {
         gameContainer.setBackground(Color.white);
         game.setBackground(new Color(238, 238, 238));
         title.setForeground(Color.BLACK);
         styleTitle.setForeground(Color.BLACK);
      }
      else if(styleName.equals("Neon")) {
         game.setBackground(Color.BLACK);
         gameContainer.setBackground(Color.BLACK);
         title.setForeground(Color.white);
         styleTitle.setForeground(Color.white);
      }
      gameboard.changeStyle(s);
      keyboard.changeStyle(s);
      scoreboard.changeStyle(s);    
   }
   
   //listener for the dropdown menu
   private class StyleListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JComboBox source = (JComboBox)e.getSource();
         String style = source.getSelectedItem().toString();
         changeStyle(style);
      }
   }
}
