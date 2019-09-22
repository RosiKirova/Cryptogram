package View;

import javax.swing.*;

public class MainWindow extends JFrame
{
  
  /**********************************************************************************
   * Sets up the main window which will contain the layout which displays each panel
   **********************************************************************************/
  
  public MainWindow()
  {
    super("CryptoGames");
    setSize(900,400);
    setResizable(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
}
