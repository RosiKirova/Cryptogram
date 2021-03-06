package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel
{
  /***************************************************************
   * Displays the login screen
   *
   * Controlled by the driver
   *
   * Creates components
   * Creates method which returns the usersname
   * Creates methods which allows action listeners to be set up
   **************************************************************/
  
  private JLabel loginLabel = new JLabel("Username:");
  private JTextField promptField = new JTextField(10);
  private JButton loginBtn = new JButton("Login");
  
  public LoginPanel()
  {
    add(loginLabel);
    add(promptField);
    add(loginBtn);
  }
  
  public String getText()
  {
    return promptField.getText();
  }
  
  public void addActionListener(ActionListener loginListener)
  
  {
    loginBtn.addActionListener(loginListener);
    promptField.addActionListener(loginListener);
  }
  
}
