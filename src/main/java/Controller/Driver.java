package Controller;

import Model.Player;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

import java.io.*;

public class Driver
{
  
  // Create instances of the model and other controllers
  private File file;
  private CustomFileParser parser;
  private Player player;
 
  // Create instances for each of GUI panels
  private MainWindow view;
  private JPanel card;
  private CardLayout cardLayout;
  private LoginPanel loginPanel;
  private HomePanel menu;
  private GamePanel game;
  private GameChoice gameChoice;
  
  /*************************************
   * Construct display and set up login
   *************************************/
  
  public Driver()
  {
    this.view = new MainWindow(); // The main window that handles the layout
    this.cardLayout = new CardLayout(); // The layout that will handle window switching
    this.card = new JPanel(cardLayout);
    
    this.loginPanel = new LoginPanel(); // The panel for the login screen
    this.loginPanel.addActionListener(new LoginListener());
    this.card.add("loginPanel", loginPanel);
  
    this.menu = new HomePanel(); // The home panel which contains the main menu option
    // Event listeners for each of the buttons, classes are below, which are passed to the components
    menu.addNewListener(new NewListener());
    menu.addLoadGameListener(new LoadGameListener());
    menu.addExitListener(new ExitListener());
    this.card.add("menu", menu);
  
    // The game choice menu which is called only if new game is selected from the menu
    this.gameChoice = new GameChoice();
    gameChoice.addStartListener(new StartListener());
    this.card.add("gameChoice", gameChoice);
    
    // The game itself, called if new game or load game are selected
    this.game = new GamePanel();
    this.card.add("game", game);
  
    
    this.view.add(card); // Add the layout to the frame, begin with the login screen
    this.cardLayout.show(card, "loginPanel");
    this.view.setVisible(true);
    
    try // Incase the file is non-existent
    {
      this.file = new File("players.txt"); //load the file
      if(!file.isFile()){
        file.createNewFile();
      }
      this.parser = new CustomFileParser(file); // Create an instance of the file parser for players
    } catch (IOException e)
    {
      JOptionPane.showMessageDialog(view, "No File Found.");
    } catch (NullPointerException npe)
    {
      JOptionPane.showMessageDialog(view, "Empty File");
    }
  }
  
  
  /*********************
   * Utility functions
   *********************/
  // Set the current player as the logged in one, will persist until the app is closed
  public void setPlayer(String uname)
  {
    try
    {
      if (parser.checkUser(uname) == null)
      {
        player = parser.add(uname);
      } else
      {
        player = parser.checkUser(uname);
      }
    } catch (IOException e)
    {
      JOptionPane.showMessageDialog(view, "Could not write to file.");
    }
  }
  
  /*****************************************************************
   * Displaying panels
   *
   * Used for switching between windows, setting up what needs to be
   *****************************************************************/
  
  public void displayMenu()
  {
    menu.setName(player.getUsername());
    cardLayout.show(card, "menu");
  }
  
  // Show the game choice menu, called from the action listener (set up below) for when new game is selected
  public void displayChoiceMenu()
  {
    cardLayout.show(card, "gameChoice");
  }
  
  // Takes place after the game choice has been made, sets up a new game, called from startListener (in ActionListeners)
  public void newGame(int puzzle, int numLet)
  {
    String p = ""; //Initialised empty incase file is not found
    BufferedReader r = null;
    try
    {
      r = new BufferedReader(new FileReader("phrases.txt"));
    } catch (FileNotFoundException e)
    {
      JOptionPane.showMessageDialog(view, "Phrases file missing.");
    }
    for(int x = 0; x < 15; x++)
    {
        try
        {
          p = r.readLine();
        } catch (IOException e)
        {
          JOptionPane.showMessageDialog(view, "Failed IO operation");
        }
        if (x == puzzle)
          break;
    }
    // Create new game, passing the puzzle, choice of letters or numeric and the panel
    Game g = new Game(p, numLet,game);
    JOptionPane.showMessageDialog(view, "Phrase: " + p); // Only shown for sprint 1 demo
    cardLayout.show(card, "game");
  }
  
  // Called when load game is selected from the menu by the Action Listener
  // TODO add saved games to parser file
  public void loadGame()
  {
    //Game g = new Game(); //get info from file
    cardLayout.show(card, "game");
  }
  
  
  
  /**************************************************************
   * ACTION LISTENERS
   *
   * Passed to the panels when the action listeners are created,
   * store variables and call above functions
   **************************************************************/
  
  public class LoginListener implements ActionListener
  {
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      setPlayer(loginPanel.getText());
      displayMenu();
    }
  }
  
  public class NewListener implements ActionListener
  {
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      displayChoiceMenu();
    }
  }
  
  public class StartListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      int puzzle = gameChoice.getPuzzle();
      int type = gameChoice.getType();
      newGame(puzzle, type);
    }
  }
  
  public class LoadGameListener implements ActionListener
  {
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      
      loadGame();
    }
  }
  
  public class ExitListener implements ActionListener
  {
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      System.exit(0);
    }
  }
  
  
}
