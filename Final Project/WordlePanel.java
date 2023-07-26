
import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class

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
   * /////////////////INSERT COMMENT HERE//////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   private static String styleName;
   /****************************
   *Fills the panel with white
   */
   public void paintComponent(Graphics g)
   {
      g.setColor(Color.white);
      g.fillRect(0, 0, 2000, 1800);
   }
   
   /****************************
   *Creates a WordlePanel instance containing a Gameboard object, a Scoreboard object, a KeyBoard object, and a JButton object
   */
   public WordlePanel()
   {
      styleName = "Classic";
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      
      setLayout(gridbag);
      setOpaque(true);
      setBackground(Color.green);
      
      JPanel gameContainer = new JPanel();
      gameContainer.setLayout(new FlowLayout());
      gameContainer.setOpaque(true);
      gameContainer.setBackground(Color.white);
      
      title = new JLabel("     Wordle     ");
      title.setFont(new Font("Arial", Font.BOLD, 40));
      gameContainer.add(title);
   
      JPanel game = new JPanel();
      game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
         
      keyboard = new Keyboard();
      keyboard.setPreferredSize(new Dimension(500,180));
      keyboard.setOpaque(true);
      keyboard.setBackground(Color.BLACK);
      reset = new JButton("Reset");
      reset.setEnabled(false);
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
      
      JLabel styleTitle = new JLabel("Choose a Style:");
      styleTitle.setFont(new Font("Arial", Font.BOLD, 20));
      
      c.weightx = 1.0;
      c.gridx = 1;
      c.gridy = 1;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      add(styleTitle, c);
      
      String[] list = {"Classic", "Neon"};
      style = new JComboBox(list);       // for some reason this line gives the "unckecked or unsafe operations" message
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
      reset.setEnabled(false);
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
         keyboard.reset();
         gameboard.reset();
      }
   }
   
   public static void changeStyle(String s)
   {
      styleName = s;
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
