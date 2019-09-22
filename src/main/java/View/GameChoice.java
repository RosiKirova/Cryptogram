package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameChoice extends JPanel
{
  /*********************************************************************
   * UI for the game choice window
   *
   * Controlled by the driver class
   *
   * Sets up components and provides methods which returns their values
   * Created method which allows driver to add needed action listeners
   ********************************************************************/
  
  private String[] puzzlesSet = { "Puzzle 1", "Puzzle 2", "Puzzle 3", "Puzzle 4", "Puzzle 5", "Puzzle 6", "Puzzle 7", "Puzzle 8",
                                  "Puzzle 9", "Puzzle 10", "Puzzle 11", "Puzzle 12", "Puzzle 13", "Puzzle 14", "Puzzle 15" };
  private JComboBox puzzleChoice = new JComboBox(puzzlesSet);
  private String[] choiceSet = {"letters", "numbers"};
  private JComboBox puzzleType = new JComboBox(choiceSet);
  private JButton startGame = new JButton("Start");

    public GameChoice(){
        add(puzzleChoice);
        add(puzzleType);
        add(startGame);
    }
    
    // Called from driver, sets a new event listener to start button
    public void addStartListener(ActionListener startListener) { startGame.addActionListener(startListener);}
  
  /***********
   * Getters
   **********/
  
  public int getPuzzle(){
      return puzzleChoice.getSelectedIndex();
      
    }

    public int getType(){
      return puzzleType.getSelectedIndex();
    }
}
