package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel
{
  
  /************************************************
   * Displays the menu
   *
   * Controlled by the Driver class
   *
   * Generates components
   * Creates methods which allows action listeners to be set up
   ***********************************************/
  String username;
  
  private JPanel maincard = new JPanel();
  private JPanel buttons = new JPanel();
  
  private JLabel welcome = new JLabel();
  private JButton newGame = new JButton("Start a new Game");
  private JButton loadGame = new JButton("Load your previous game");
  private JButton viewStats = new JButton("View your Stats");
  private JButton viewLeaderBoard = new JButton("View the leaderboard");
  private JButton exit = new JButton("Exit");
  
  public HomePanel()
  {
    // Main panel will have a border layout
    this.maincard.setLayout(new BorderLayout(0, 40));
    
    // Buttons will have a box layout
    this.buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
  
    // Add welcome label to top, add border to align it with buttons
    welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.maincard.add(welcome, BorderLayout.NORTH);
    welcome.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
    
    // Add buttons to the box layout, align by center
    this.buttons.add(newGame);
    newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.buttons.add(loadGame);
    loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.buttons.add(viewStats);
    viewStats.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.buttons.add(viewLeaderBoard);
    viewLeaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.buttons.add(exit);
    exit.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    this.maincard.add(buttons, BorderLayout.CENTER);
    
    add(maincard);
  }
  
  // Get username for welcome label
  public void setName(String uname)
  {
    this.username = uname;
    welcome.setText("Welcome "+username+"!");
  }
  
  /******************
   * Event Listeners
   ******************/
  
  public void addExitListener(ActionListener exitListener)
  
  {
    exit.addActionListener(exitListener);
  }
  public void addNewListener(ActionListener newListener) { newGame.addActionListener(newListener);}
  public void addLoadGameListener(ActionListener loadGameListener) {loadGame.addActionListener(loadGameListener);}
}
