package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    persistanceComboBox = new JComboBox<String>();
    persistanceComboBox.addItem("xml");
    persistanceComboBox.addItem("sqlite");
    persistanceComboBox.addActionListener((ActionEvent e) ->
      {
        ((RootFrame)this.getParent().getParent().getParent().getParent().getParent()).setPersistenceType((String)persistanceComboBox.getSelectedItem());
      }
    );

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    this.add(persistanceComboBox, gridBagConstraints);
  }

  private JComboBox<String> persistanceComboBox;
}
