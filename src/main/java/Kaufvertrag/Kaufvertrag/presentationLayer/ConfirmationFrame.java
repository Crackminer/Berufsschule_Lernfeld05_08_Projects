package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;

public class ConfirmationFrame extends JFrame {
    public static void confirm() {
        JFrame confirmFrame = new JFrame();
        int result = JOptionPane.showConfirmDialog(confirmFrame, "Your contract has been saved and you can now go back to the main page.");
        if (result == 0) {
            System.out.println("Ok");
            System.exit(0);
        } else if (result == 1) {
            System.out.println("You pressed NO");
        } else {
            System.out.println("You pressed Cancel");
        }
    }
}
