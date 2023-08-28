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
    this.add(menueLabel, gridBagConstraints);

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
    gridBagConstraints.gridy = 1;
    this.add(persistanceComboBox, gridBagConstraints);

    confirmButton = new JButton("Confirm");
    confirmButton.addActionListener((ActionEvent e) ->
      {
        ((RootFrame)this.getParent().getParent().getParent().getParent().getParent()).setPanel(((String)persistanceComboBox.getSelectedItem()).equals("xml") ? RootFrame.xmlPanel : RootFrame.sqlitePanel);
      }
    );
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(4, 7, 5, 8);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    this.add(confirmButton, gridBagConstraints);
  }

  private JLabel menueLabel;
  private JComboBox<String> persistanceComboBox;
  private JButton confirmButton;
  private JButton exitButton;
}
