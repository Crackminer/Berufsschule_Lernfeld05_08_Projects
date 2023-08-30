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

    jd.setLayout(new FlowLayout());

    jd.setBounds(400, 300, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.27f), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1f));
    jd.setLocationRelativeTo(null);

    JLabel jLabel = new JLabel("Your contract has been saved and you can now go back to the main page.");

    JButton jButton = new JButton("Ok");
    jButton.addActionListener((ActionEvent e) ->
      {
        jd.setVisible(false);
        System.exit(0);
      }
    );

    jd.add(jLabel);
    jd.add(jButton);
    jd.setVisible(true);
  }
}
