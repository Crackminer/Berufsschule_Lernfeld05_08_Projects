package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersistencePanel extends JPanel
{
  public PersistencePanel()
  {
    initComponents();
  }

  /**
   * //TODO: panel mit drop-down-menue für sql oder xml
   * //TODO: panel mit button zum bestätigen und button zum beenden
   */
  private void initComponents()
  {
    GridBagConstraints gridBagConstraints;
    this.setLayout(new GridBagLayout());

    xmlButton = new JButton("xml");
    xmlButton.addActionListener((ActionEvent e) ->
    {
             //JButton.  JPanel   .  JPanel   .JLayeredPane. JRootPane.  JFrame
      ((RootFrame)this.getParent().getParent().getParent().getParent().getParent()).setPersistenceType("xml");
    });

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    this.add(xmlButton, gridBagConstraints);

    sqliteButton = new JButton("sqlite");
    sqliteButton.addActionListener((ActionEvent e) ->
    {
             //JButton.  JPanel   .  JPanel   .JLayeredPane. JRootPane.  JFrame
      ((RootFrame)this.getParent().getParent().getParent().getParent().getParent()).setPersistenceType("sqlite");
    });

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    this.add(sqliteButton, gridBagConstraints);
  }

  private JButton xmlButton;
  private JButton sqliteButton;
}
