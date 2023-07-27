import javax.swing.*; //JPanel class and other graphics objects
import java.awt.*; //Layouts and other graphics objects
import java.awt.event.*; //Listener class
import java.util.*; //Scanner class
import java.io.*; //File class

/*******
*The <code>WordlePanel</code> class serves as the organizing panel for the game, containing several subpanels, menus, and player options. 
*/

public class WordlePanel extends JPanel
{
   /**
   *The <code>WordlePanel</code>'s <code>Gameboard</code>.
   @see Gameboard
   */
   private static Gameboard gameboard;
   
   /**
   *The <code>WordlePanel</code>'s <code>Scoreboard</code>.
   @see Scoreboard
   */
   private static Scoreboard scoreboard;
   
   /**
   *The <code>WordlePanel</code>'s <code>Keyboard</code>.
   @see Keyboard
   */
   private static Keyboard keyboard;
   
   /**
   *A <code>JButton</code> that resets the <code>WordlePanel</code> and its subpanels.
   @see JButton
   */
   private static JButton reset;
   
   /**
   *A <code>JButton</code> that clears all data history and resets the game.
   @see JButton
   */
   private static JButton clear;
   
   /**
   *A <code>JComboBox</code> used in the WordlePanel constructor and in the StyleListener class, serves 
   *as a dropdown menu for the game style.
   @see  JComboBox
   */
   private static JComboBox style;
   
   /**
   *The title of the game
   @see   JLabel
   */
   private static JLabel title;
   
   /**
   *A header for the JComboBox <code>style</code>
   @see   JLabel
   */
   private static JLabel styleTitle;
   
   /**
   *A String used to commmunicate the game style between WordlePanel, Gameboard, Keyboard, and Scoreboard
   */
   private static String styleName;
   
   /**
   *2 JPanels used for the game layout
   @see   JPanel
   */
   private static JPanel game, gameContainer;
   
   /****************************
   *Fills the panel's background with a color depending on the theme
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

   /**
   *Returns the name of the current style for use in other classes.
   @return     style
   */
   public static String getStyle()
   {
      return styleName;
   }
   
   /****************************
   *Creates a WordlePanel instance containing a Gameboard object, a Scoreboard object, a KeyBoard object, 
   *several JLabels and JButtons, and a JComboBox. The WorldePanel utilizes a GridBagLayout to organize all of its 
   *components, with the main game on the left and the options on the right. 
   @see  Gameboard
   @see  Scoreboard
   @see  Keyboard
   @see  JLabel
   @see  JButton
   @see  JComboBox
   @see  GridBagLayout
   @see  GridBagConstraints
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
   *Activates once a game is completed (win or loss). Resets the Gameboard and the Keyboard by calling their reset methods, 
   *while also updating the Scoreboard. After resetting, the button disables untill another game is completed. 
   @see Gameboard.reset()
   @see Keyboard.reset()
   @see Scoreboard.update()
   */
   public void reset()
   {
      scoreboard.update();
      gameboard.reset();
      keyboard.reset(styleName);
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
   
   /**
   *Checks whether or not the reset button has been clicked. 
   *Once clicked, it will play the reset sound effect and reset the WordlePanel and its components.
   @see  ActionListener
   @see  SoundEffect
   @see  javax.sound.sampled.AudioSystem
   */
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
   
   /**
   *Checks whether or not the clear button has been clicked.
   *Once clicked, it will clear all saved game data and restart the game, playing the clear sound effect. 
   @see  ActionListener
   @see  SoundEffect
   @see  javax.sound.sampled.AudioSystem
   */
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

   /**
   *Updates the current game style based on <code>style</code>, a JComboBox. 
   @param   s - The name of the game style as a <code>String</code>.
   */ 
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
   
   /**
   *Checks what has been selected in the JComboBox 
   *<code>style</code>, changing the style of the game accodingly
   @see  ActionListener
   @see  changeStyle(String s)
   */
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
