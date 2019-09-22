package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;


public class GamePanel extends JPanel
{

  /**************************************************************************
   * Panel that displays the game itself
   *
   * Controlled by the Game class
   *
   * Sets up components and variables that holds their values
   * Creates methods that allows the game class to construct event listeners
   **************************************************************************/
  // Create panels to layout the components
  private JPanel mainCard = new JPanel(new BorderLayout(0, 40));
  private JPanel buttons = new JPanel();
  private JPanel textBoxes = new JPanel();

  // Create components
  private JButton hint = new JButton("Hint");
  private JButton giveUp = new JButton("Give up");
  private JButton saveGame = new JButton("Save game");
  private JButton undo = new JButton("Undo");
  private JButton getFreq = new JButton("Get Frequencies");

  //private JTextField fields[];
  private CryptoTextField fields[];
  // Declare variables
  private int phraseCount;

  public GamePanel()
  {
    // Add buttons to the button panel
    this.buttons.add(undo);
    this.buttons.add(hint);
    this.buttons.add(saveGame);
    this.buttons.add(giveUp);
    this.buttons.add(getFreq);
    
    this.buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.textBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Add it to the main panel with layout options
    mainCard.add(buttons, BorderLayout.NORTH);
    mainCard.add(textBoxes, BorderLayout.CENTER);

    add(mainCard);

  }

  // Sets up the text fields based on the number of characters in the phrase
  public void genTextField(String phrase, boolean numeric)
  {

    this.phraseCount = phrase.length();
    fields=new CryptoTextField[phraseCount];

    if(!numeric) {
      for (int textFieldCount = 0; textFieldCount < phraseCount; textFieldCount++) {

        CryptoTextField field = new CryptoTextField(1);
        field.setEncrypted(String.valueOf(phrase.charAt(textFieldCount)));
        if (field.getText().equals(" ")) {
          field.setEnabled(false);
        }
        fields[textFieldCount] = (field);
        textBoxes.add(field);
      }
    }else{
      int textFieldCount=0;
      for (int i = 0; i < phraseCount; i+=2) {
        CryptoTextField field = new CryptoTextField(2);
        String buffer = String.valueOf(phrase.charAt(i)) + phrase.charAt(i + 1);
        field.setEncrypted(buffer);
        if (field.getText().equals("  ")) {
          field.setEnabled(false);
        }
        fields[textFieldCount] = (field);
        textFieldCount++;
        textBoxes.add(field);
      }
    }
  }


  public void updateTextField(String encrypted, String userGuess,boolean numeric){
    if(!numeric) {
      for (int textFieldCount = 0; textFieldCount < phraseCount; textFieldCount++) {

        if (userGuess.charAt(textFieldCount) == '0') {
          fields[textFieldCount].setEncrypted(String.valueOf(encrypted.charAt(textFieldCount)));
          fields[textFieldCount].setBackground(Color.white);
        } else {
          fields[textFieldCount].setUserChoice(String.valueOf(userGuess.charAt(textFieldCount)));
          fields[textFieldCount].setBackground(Color.orange);
        }

      }
    }else {
      int i = 0;
      for (int textFieldCount = 0; textFieldCount < phraseCount; textFieldCount+=2) {

        if (userGuess.charAt(textFieldCount) == '0' && userGuess.charAt(textFieldCount + 1) == '0') {
          fields[i].setEncrypted(String.valueOf(encrypted.charAt(textFieldCount))+ (encrypted.charAt(textFieldCount+1)));
          fields[i].setBackground(Color.white);
          i++;
        } else {
          fields[i].setUserChoice(String.valueOf(userGuess.charAt(textFieldCount)));
          fields[i].setBackground(Color.orange);
          i++;
        }
      }
    }

  }

  /*******************************************
   * Event Listeners
   *
   * Allows Game class to add event listeners
   *******************************************/
  public void addEnterListener(FocusListener focusListener, ActionListener enterListener, int i)
  {
    fields[i].addActionListener(enterListener);
    fields[i].addFocusListener(focusListener);
  }
  
  public void addUndoListener(ActionListener undoListener)
  {
    undo.addActionListener(undoListener);
  }

  public void addFreqListener(ActionListener freqListener) {getFreq.addActionListener(freqListener);}

}
