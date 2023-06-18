package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;

public class RootFrame extends JFrame
{
  private String persistenceType;
  public RootFrame()
  {
    persistenceType = null;
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
    add(jPanel1);

    jPanel1.setLayout(new GridLayout());
    persistencePanel = new PersistencePanel();
    jPanel1.add(persistencePanel);

    pack();
    setLocationRelativeTo(null);
  }

  public void setPersistenceType(String type)
  {
    this.persistenceType = type;
  }

  public String getPersistenceType()
  {
    return this.persistenceType;
  }

  private PersistencePanel persistencePanel;
  private JPanel jPanel1;
}
