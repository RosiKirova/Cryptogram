package Controller;

import Model.Cryptogram;
import Model.LetterCrypto;
import Model.NumericCrypto;
import View.CryptoTextField;
import View.GamePanel;
import Model.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game {
  // Sets up variables that will be used during and some returned from the game
  private String usersGuess;
  private Cryptogram cryptogram;
  private int correctGuesses;
  private int totalGuesses;
  private GamePanel gamePanel;
  private ArrayList<Pair> guess = new ArrayList<>();
  int position;
  int choice;

  /*****************
   * Constructors
   *****************/

  // Construct a new game
  public Game(String puzzle, int choice, GamePanel gamePanel) {
    this.choice=choice;
    this.gamePanel = gamePanel;
    // Create a new cryptogram based on the choice
    if (choice == 0) {
      this.cryptogram = new LetterCrypto(puzzle);
      gamePanel.genTextField(cryptogram.getEncrypted(),false);
    } else {
      this.cryptogram = new NumericCrypto(puzzle);
      gamePanel.genTextField(cryptogram.getEncrypted(),true);
    }
    // Initialise variables to 0
    this.correctGuesses = 0;
    this.totalGuesses = 0;

    // Set up game panel so it can control it

    gamePanel.addUndoListener(new UndoListener());
    gamePanel.addFreqListener(new FreqListener());

    StringBuilder builder = new StringBuilder();
    // Add listeners to each text field for the guesses
    for (int i = 0; i < puzzle.length(); i++) {
      gamePanel.addEnterListener( new FocusTextListener(), new MakeGuessListener(), i);
      if(choice==0) {
        builder.append("0");
      }else{
        builder.append("00");
      }
    }
    this.usersGuess = builder.toString();
    // Set up character array that holds the guesses of the size of the phrase
  }

  // Constructor for when a game has been loaded
  public Game(Cryptogram c, int total, int correct, GamePanel gamePanel) {
    this.cryptogram = c;
    this.totalGuesses = total;
    this.correctGuesses = correct;
  }

  /********************
   * Utility functions
   ********************/

  public ArrayList<Integer> findLetterPositions(char selected, String string) {
    ArrayList<Integer> positions = new ArrayList<>();
    for (int i = 0; i < string.length(); i++) {
      if (string.charAt(i) == selected) {
        positions.add(i);
      }
    }
    return positions;
  }

  public ArrayList<Integer> findLetterPositions(String selected, String string){
    ArrayList<Integer> positions = new ArrayList<>();
    for(int i=0;i<string.length();i+=2){
      String buffer=String.valueOf(string.charAt(i))+String.valueOf(string.charAt(i+1));
      if(buffer.equals(selected)){
        positions.add(i);

      }
    }
    return positions;
  }

  public void makeGuess(char newChar, char original) {
    StringBuilder builder = new StringBuilder(usersGuess);
    ArrayList<Integer> positions = findLetterPositions(original, cryptogram.getEncrypted());
    for (Integer i : positions) {
      builder.setCharAt(i, newChar);
    }
    usersGuess = builder.toString();
  }

  public void makeGuess(char newChar, String original){
    StringBuilder builder = new StringBuilder(usersGuess);
    ArrayList<Integer> positions = findLetterPositions(original, cryptogram.getEncrypted());
    for(Integer i:positions){
      builder.setCharAt(i,newChar);
    }
    for(int i=positions.size()-1;i>=0;i--){
      builder.setCharAt(positions.get(i)+1,' ');
    }
    usersGuess=builder.toString();
  }

  public void changeGuess(char newChar, char original) {
    StringBuilder builder = new StringBuilder(usersGuess);
    ArrayList<Integer> positions = findLetterPositions(original, usersGuess);
    for (Integer i : positions) {
      builder.setCharAt(i, newChar);
    }
    usersGuess = builder.toString();
  }

  public void changeGuess(char newChar, String original){
    StringBuilder builder=new StringBuilder(usersGuess);
    ArrayList<Integer> positions=findLetterPositions(original,usersGuess);
    for (Integer i : positions) {
      builder.setCharAt(i, newChar);
      builder.setCharAt(i+1,newChar);
    }
    usersGuess = builder.toString();
  }

//  public void changeGuess(char newChar, char original) {
//    StringBuilder builder = new StringBuilder(usersGuess);
//    ArrayList<Integer> positions = findLetterPositions(original, usersGuess);
//    for (Integer i : positions) {
//      builder.setCharAt(i, newChar);
//    }
//    usersGuess = builder.toString();
//  }

//  public void changeGuess(String newChar, String original){
//    StringBuilder builder = new StringBuilder(usersGuess);
//    ArrayList<Integer> positions = findLetterPositions(original, usersGuess);
//    for (Integer i : positions) {
//      builder.setCharAt(i, newChar.charAt(0));
//
//    }
//    usersGuess = builder.toString();
//  }

  public void save() {
    // TODO return current status to driver to be saved
  }

  // Checks if cryptogram was successfully completed
  public boolean checkVictory(boolean numeric) {
    if (!numeric) {
      StringBuilder builder = new StringBuilder(usersGuess);
      for (int i = 0; i < usersGuess.length(); i++) {
        if (usersGuess.charAt(i) == '0') {
          builder.setCharAt(i, ' ');
        } else {
          builder.setCharAt(i, usersGuess.charAt(i));
        }
      }
      return builder.toString().equals(cryptogram.getPhrase());
    }else{
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < usersGuess.length(); i+=2) {
        if (usersGuess.charAt(i) == '0' && usersGuess.charAt(i+1)=='0') {
          builder.append(" ");
        } else {
          builder.append(usersGuess.charAt(i));
        }
      }
      return builder.toString().equals(cryptogram.getPhrase());
    }
  }

  public void getHint() {
  }

  public String giveUp() {
    return cryptogram.getPhrase();
  }

  public String getUsersGuess() {
    return usersGuess;
  }

  public void setUsersGuess(String usersGuess) {
    this.usersGuess = usersGuess;
  }

  public Cryptogram getCryptogram() {
    return cryptogram;
  }

  public void setCryptogram(Cryptogram cryptogram) {
    this.cryptogram = cryptogram;
  }

  public int getCorrectGuesses() {
    return correctGuesses;
  }

  public void setCorrectGuesses(int correctGuesses) {
    this.correctGuesses = correctGuesses;
  }

  public int getTotalGuesses() {
    return totalGuesses;
  }

  public void setTotalGuesses(int totalGuesses) {
    this.totalGuesses = totalGuesses;
  }


  /******************
   * Event Listeners
   *****************/

  public class MakeGuessListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

      JTextField field = (JTextField) e.getSource();
      JFrame window = (JFrame) SwingUtilities.getWindowAncestor(field);
      char original=' ';
      String originalNum="";
      if(choice==0) {
        original = cryptogram.getEncrypted().charAt(position);
      }else {
        originalNum = String.valueOf(cryptogram.getEncrypted().charAt(position * 2)) + String.valueOf(cryptogram.getEncrypted().charAt(position * 2 + 1));
      }
      if (usersGuess.contains(field.getText())) {
        JOptionPane.showMessageDialog(window, "Error, you have already used this letter. Please undo it first");
        if (usersGuess.charAt(position) == '0') {
          if(choice==0) {
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(position)));
          }else{
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(position * 2))+String.valueOf(cryptogram.getEncrypted().charAt(position * 2+1)));
          }
        } else {
          field.setText(String.valueOf(usersGuess.charAt(position)));
        }
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
      } else {

        boolean used;
        if(choice==0){
          used=usersGuess.charAt(position) == '0';
        }else{
          used=usersGuess.charAt(position*2) == '0';
        }

        if (used) {
          if(choice==0) {
            makeGuess(field.getText().charAt(0), original);
          }else{
            makeGuess(field.getText().charAt(0), originalNum);
          }
        } else {
          String[] options = {"yes", "no"};
          int answer = JOptionPane.showOptionDialog(window, "Do you want to overwrite your previous mapping?",
                  "Click a button",
                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                  null,
                  options, options[0]);
          if (answer == 0) {
            if(choice==0) {
              makeGuess(field.getText().charAt(0), original);
            }else{
              makeGuess(field.getText().charAt(0), originalNum);
            }
          } else {
            field.setText(String.valueOf(usersGuess.charAt(position)));
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
          }
        }
      }
      if(choice==0) {
        gamePanel.updateTextField(cryptogram.getEncrypted(), usersGuess,false);
      }else{
        gamePanel.updateTextField(cryptogram.getEncrypted(), usersGuess,true);
      }
      if (choice==0) {
        if (checkVictory(false)) {
          JOptionPane.showMessageDialog(window, "Congratulations, you've completed the crypto!");
        }
      }else{
        if (checkVictory(true)) {
          JOptionPane.showMessageDialog(window, "Congratulations, you've completed the crypto!");
        }
      }
    }

  }


  public class FocusTextListener implements FocusListener {

    JTextField field;
    ArrayList<Integer> positions;
    ArrayList<JTextField> fields;

    @Override
    public void focusGained(FocusEvent e) {
      field = (JTextField) e.getSource();
      field.selectAll();
      Container container = field.getParent();
      Component[] components = container.getComponents();
      fields = new ArrayList<>();
      for (int i = 0; i < components.length; i++) {
        if (components[i].getClass() == (CryptoTextField.class)) {
            fields.add((CryptoTextField) components[i]);
        }
      }
      for (int i = 0; i < fields.size(); i++) {
        if (fields.get(i).equals(field)) {
          position = i;
          break;
        }
      }

      if(choice==0) {
        positions = findLetterPositions(cryptogram.getEncrypted().charAt(position), cryptogram.getEncrypted());
      }else{
        String buffer=String.valueOf(cryptogram.getEncrypted().charAt(position*2))+String.valueOf(cryptogram.getEncrypted().charAt(position*2+1));
        positions = findLetterPositions(buffer, cryptogram.getEncrypted());
      }
      for (int i = 0; i < positions.size(); i++) {
        if(choice==0) {
          fields.get(positions.get(i)).setBackground(Color.green);
        }else{
          fields.get(positions.get(i)/2).setBackground(Color.green);
        }
      }
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (field.getText().length() > 0) {
        if(choice==0) {
          if (cryptogram.getEncrypted().charAt(position) == field.getText().charAt(0)) {
            for (int i = 0; i < positions.size(); i++) {
              fields.get(positions.get(i)).setBackground(Color.white);
            }
          } else {
            for (int i = 0; i < positions.size(); i++) {
              fields.get(positions.get(i)).setBackground(Color.orange);
            }
          }
        }else{
          String num=String.valueOf(cryptogram.getEncrypted().charAt(position*2))+String.valueOf(cryptogram.getEncrypted().charAt(position*2+1));
          String checking=field.getText()+" ";
          if (num.equals(checking.substring(0,2))) {
            for (int i = 0; i < positions.size(); i++) {
              fields.get(positions.get(i)/2).setBackground(Color.white);
            }
          } else {
            for (int i = 0; i < positions.size(); i++) {
              fields.get(positions.get(i)/2).setBackground(Color.orange);
            }
          }
        }
      }
      else {
        if (usersGuess.charAt(position) == '0') {

          if(choice==0) {
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(position)));
          }else{
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(position*2))+String.valueOf(cryptogram.getEncrypted().charAt(position*2+1)));
          }
        } else {
          if(choice==0) {
            field.setText(String.valueOf(usersGuess.charAt(position)));
          }else{
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(position*2))+String.valueOf(cryptogram.getEncrypted().charAt(position*2+1)));
          }
        }
      }
    }
  }


  public class UndoListener implements ActionListener{
  
    @Override
    public void actionPerformed(ActionEvent e)
    {
     try
      {
        //getting to root
        JButton button=(JButton) e.getSource();
        JPanel panel=(JPanel)button.getParent();
        Container container=panel.getParent();
        Component[] components = container.getComponents();

        //getting to CryptoTextField JPanel
        JPanel textPanel=(JPanel)components[1];
        Component[] textPanelComponents= textPanel.getComponents();

        //getting all CryptoTextField
        ArrayList<CryptoTextField> fields = new ArrayList<>();
        for (int i = 0; i < textPanelComponents.length; i++) {
          if (textPanelComponents[i].getClass() == (CryptoTextField.class)) {
            fields.add((CryptoTextField) textPanelComponents[i]);
          }
        }

        //getting letter from the last focused JTextField and it's positions in userGuess
        JTextField field=new JTextField();

        ArrayList<Integer> positions;
        if(choice==0) {
          positions = findLetterPositions(fields.get(position).getText().charAt(0), usersGuess);
        }else{
          String checking=fields.get(position).getText()+" ";
          positions = findLetterPositions(checking.substring(0,2), usersGuess);
        }

        //set new values to TextFields on right positions from encrypted string
        for(int i=0;i<positions.size();i++) {

          if(choice==0) {
            field=fields.get(positions.get(i));
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(positions.get(i))));
          }else{
            field=fields.get(positions.get(i)/2);
            field.setText(String.valueOf(cryptogram.getEncrypted().charAt(positions.get(i)))+String.valueOf(cryptogram.getEncrypted().charAt(positions.get(i)+1)));
          }
        }
        //change userGuess
        //makeGuess('0', usersGuess.charAt(position));
        if(choice==0) {
          changeGuess('0', usersGuess.charAt(position));
        }else{
          String checker=String.valueOf(usersGuess.charAt(position*2))+String.valueOf(usersGuess.charAt(position*2+1));
          changeGuess('0',checker);
        }
        //request for a focus of the represented char textField, update textField, loose focus from textField
        field.requestFocus();
        if(choice==0) {
          gamePanel.updateTextField(cryptogram.getEncrypted(), usersGuess,false);
        }else{
          gamePanel.updateTextField(cryptogram.getEncrypted(), usersGuess,true);
        }
        button.requestFocus();




        //TODO artificially set a JTextField on position (position) to gain and loose focus.

      }catch (IndexOutOfBoundsException iob){
        JOptionPane.showMessageDialog(gamePanel, "No moves to undo.");
      }
    }
  }

  public class FreqListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
       JOptionPane.showMessageDialog(gamePanel, cryptogram.getFreq());
    }
  }


}

