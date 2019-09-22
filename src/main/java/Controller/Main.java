package Controller;

import Model.Cryptogram;
import Model.NumericCrypto;
import Model.Player;
import View.GamePanel;
import View.MainWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

       //  public Game(String puzzle, int choice, GamePanel gamePanel) {

        try { //Set default theme to nimbus if installed
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to the system look and feel.
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch (Exception all){}
        }
        // Call driver which sets up everything
    Driver driver = new Driver();
    }
}
