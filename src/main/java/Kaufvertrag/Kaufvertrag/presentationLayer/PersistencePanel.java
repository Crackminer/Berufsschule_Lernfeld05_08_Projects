package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class PersistencePanel extends JPanel
{
  public PersistencePanel()
  {
    initComponents();
  }

  private void initComponents()
  {
    GridBagConstraints gridBagConstraints;
    this.setLayout(new GridBagLayout());

    menueLabel = new JLabel("Menue");
    menueLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1f;
    this.add(menueLabel, gridBagConstraints);

    persistanceComboBox = new JComboBox<String>();
    persistanceComboBox.addItem("xml");
    persistanceComboBox.addItem("sqlite");
    persistanceComboBox.addActionListener((ActionEvent e) ->
      {
        RootFrame.instance.setPersistenceType((String)persistanceComboBox.getSelectedItem());
      }
    );

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1f;
    this.add(persistanceComboBox, gridBagConstraints);

    confirmButton = new JButton("Confirm");
    confirmButton.addActionListener((ActionEvent e) ->
      {
        RootFrame.instance.setPanel(Objects.equals((String) persistanceComboBox.getSelectedItem(), "xml") ? RootFrame.xmlPanel : RootFrame.sqlitePanel);
        RootFrame.instance.setBack(this);
      }
    );
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1f;
    this.add(confirmButton, gridBagConstraints);
  }

  private JLabel menueLabel;
  private JComboBox<String> persistanceComboBox;
  private JButton confirmButton;
}
