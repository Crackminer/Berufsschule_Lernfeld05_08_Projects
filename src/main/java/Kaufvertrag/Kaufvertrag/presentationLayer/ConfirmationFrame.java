package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmationFrame extends JFrame {
    public static void confirm() {
        JFrame jFrame = new JFrame();

        JDialog jd = new JDialog(jFrame);

        jd.setLayout(new FlowLayout());

        jd.setBounds(400, 300, 450, 150);

        JLabel jLabel = new JLabel("Your contract has been saved and you can now go back to the main page.");

        JButton jButton = new JButton("Ok");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jd.setVisible(false);
                System.exit(0);
            }
        });

        jd.add(jLabel);
        jd.add(jButton);
        jd.setVisible(true);
    }
}
