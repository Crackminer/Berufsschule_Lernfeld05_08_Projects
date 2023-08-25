package Kaufvertrag.Kaufvertrag.presentationLayer;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    darkLightModePanel = new JPanel();
    darkLightModePanel.setLayout(new GridLayout());
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    ButtonGroup group = new ButtonGroup();
    JRadioButton lightMode = new JRadioButton("Light");
    JRadioButton darkMode = new JRadioButton("Dark");

    darkMode.setSelected(true);

    lightMode.addActionListener((ActionEvent e) ->
    {
      if (lightMode.isSelected())
      {
        EventQueue.invokeLater(() -> {
          try
          {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.updateUI();
          }
          catch (UnsupportedLookAndFeelException ex)
          {
            throw new RuntimeException(ex);
          }
        });
      }
    });

    darkMode.addActionListener((ActionEvent e) ->
    {
      if (darkMode.isSelected())
      {
        EventQueue.invokeLater(() -> {
          try
          {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            FlatDarkLaf.updateUI();
          }
          catch (UnsupportedLookAndFeelException ex)
          {
            throw new RuntimeException(ex);
          }
        });
      }
    });
    group.add(darkMode);
    group.add(lightMode);

    buttonPanel.add(darkMode);
    buttonPanel.add(lightMode);
    darkLightModePanel.add(buttonPanel);


    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Kaufvertragsmanager");
    setName("Kaufvertragsmanager");
    setSize(new Dimension(800, 450));
    setPreferredSize(new Dimension(800, 450));
    add(jPanel1);

    jPanel1.setLayout(new GridBagLayout());
    persistencePanel = new PersistencePanel();
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1f;
    jPanel1.add(darkLightModePanel, constraints);
    constraints.gridy = 1;
    constraints.weighty = 1f;
    jPanel1.add(persistencePanel, constraints);

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

  private JPanel darkLightModePanel;
}
