package View;

import javax.swing.*;


public class CryptoTextField  extends JTextField {
  private String encrypted;
  private String userChoice;

  public CryptoTextField(int columns) {
    super(columns);
  }

  public String getEncrypted() {
    return encrypted;
  }

  public void setEncrypted(String encrypted) {
    this.encrypted = encrypted;
    setText(encrypted);
  }

  public String getUserChoice() {
    return userChoice;

  }

  public void setUserChoice(String userChoice) {
    this.userChoice = userChoice;
    setText(userChoice);
  }
}
