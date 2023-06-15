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

    jpanel1 = new JPanel();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Kaufvertragsmanager");
    setName("Kaufvertragsmanager");
    setSize(new Dimension(800, 450));
    setPreferredSize(new Dimension(800, 450));
    getContentPane().add(jpanel1);

    jpanel1.setLayout(new GridLayout());
    //jpanel1.add(classformular); //TODO: ersetze mit klasse für start-seite

    pack();
    setLocationRelativeTo(null);
  }

  //private Classformular classformular; //TODO: ersetze mit klasse für start-seite
  private JPanel jpanel1;
}
