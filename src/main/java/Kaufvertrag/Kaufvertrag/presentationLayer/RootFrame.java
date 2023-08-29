package Kaufvertrag.Kaufvertrag.presentationLayer;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RootFrame extends JFrame
{
  private String persistenceType;
  public static RootFrame instance;

  public RootFrame()
  {
    persistenceType = null;
    initComponents();
    instance = this;
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
    commandPanel = new JPanel();
    commandPanel.setLayout(new GridLayout());

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
    constraints.gridwidth = 2;
    constraints.weightx = 1f;
    constraints.anchor = GridBagConstraints.WEST;
    jPanel1.add(darkLightModePanel, constraints);



    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.SOUTHWEST;
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener((ActionEvent e) ->
      {
        ConfirmationFrame.confirm();
        //System.exit(0);
      }
    );
    backButton = new JButton("Back");
    backButton.setVisible(false);
    commandPanel.add(backButton);
    commandPanel.add(exitButton);
    jPanel1.add(backButton, constraints);
    constraints.gridx = 1;
    constraints.anchor = GridBagConstraints.SOUTHEAST;
    jPanel1.add(exitButton, constraints);


    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weighty = 1f;
    constraints.anchor = GridBagConstraints.CENTER;
    currentContentPanel = new JPanel();
    currentContentPanel.setSize(this.getSize());
    currentContentPanel.add(persistencePanel);
    jPanel1.add(currentContentPanel, constraints);

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

  public void setBack(JPanel panel)
  {
    backButton.removeActionListener(currentActionListener);
    currentActionListener = (ActionEvent e) ->
    {
      setPanel(panel);
    };
    backButton.addActionListener(currentActionListener);
  }

  public void setPanel(JPanel panel)
  {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1f;
    constraints.weighty = 1f;
    currentContentPanel.removeAll();
    currentContentPanel.add(panel, constraints);
    backButton.setVisible(currentContentPanel.getComponent(0) != persistencePanel);
    FlatLaf.updateUI();
  }

  private JPanel currentContentPanel;

  private PersistencePanel persistencePanel;
  private JPanel jPanel1;

  private JPanel darkLightModePanel;

  private JPanel commandPanel;

  public JButton backButton;

  private ActionListener currentActionListener;
}
