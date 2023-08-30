package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfirmationFrame
{
  public static void confirm()
  {
    JFrame jFrame = new JFrame();

    JDialog jd = new JDialog(jFrame);

    jd.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridy = 0;
    constraints.gridx = 0;
    jd.setBounds(400, 300, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35f), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25f));
    jd.setLocationRelativeTo(null);

    JLabel jLabel = new JLabel("Your contract has been saved and you can now go back to the main page.");

    JButton jButton = new JButton("Ok");
    jButton.addActionListener((ActionEvent e) ->
      {
        jd.setVisible(false);
        System.exit(0);
      }
    );

    jd.add(jLabel, constraints);
    constraints.gridy = 1;
    jd.add(jButton, constraints);
    jd.setVisible(true);
  }
}
