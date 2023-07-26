
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
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      
      setLayout(gridbag);
      
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
      reset.addActionListener(new ResetListener());
      reset.setPreferredSize(new Dimension(100,100));
      gameboard = new Gameboard();
      scoreboard = new Scoreboard();      
          
      game.add(gameboard);
      game.add(keyboard);
                 
      gameContainer.add(game);
      
      c.fill = GridBagConstraints.VERTICAL;
      c.weightx = 1.0;
      c.weighty = 1.0;
      c.gridx = 0;
      c.gridy = 0;
      c.gridheight = 3;
      add(gameContainer, c);
      
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.weighty = 0.0;
      c.gridx = 1;
      c.gridy = 2;
      c.gridheight = 1;
      add(reset, c);
      
      String[] list = {"Classic Light", "Classic Dark", "Neon", "Rainbow"};
      style = new JComboBox(list);       // for some reason this line gives the "unckecked or unsafe operations" message
      style.setFocusable(false);
      style.addActionListener(new StyleListener());
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.weighty = 0.7;
      c.gridx = 1;
      c.gridy = 1; 
      add(style,c);
      
      
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 1;
      c.gridy = 0;
      add(scoreboard, c);
      
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
      keyboard.reset();
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
   
   public static void changeStyle(String styleName)
   {
      //gameboard.changeStyle(styleName);
      //keyboard.changeStyle(styleName);
      //scoreboard.changeStyle(styleName); 
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
