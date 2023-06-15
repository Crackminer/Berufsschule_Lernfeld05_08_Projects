package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;

public class RootFrame extends JFrame
{
  public RootFrame()
  {
    initComponents();
  }

  private void initComponents()
  {
    GridBagConstraints gridBagConstraints;

    jPanel1 = new JPanel();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Kaufvertragsmanager");
    setName("Kaufvertragsmanager");
    setSize(new Dimension(800, 450));
    setPreferredSize(new Dimension(800, 450));
    getContentPane().add(jPanel1);

    jPanel1.setLayout(new GridLayout());
    //jpanel1.add(classFormular); //TODO: ersetze mit klasse für start-seite

    pack();
    setLocationRelativeTo(null);
  }

  //private ClassFormular classFormular; //TODO: ersetze mit klasse für start-seite
  private JPanel jPanel1;
}
