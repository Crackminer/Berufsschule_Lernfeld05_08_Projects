package Kaufvertrag.Kaufvertrag.presentationLayer.dialogues;

import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.Kaufvertrag.presentationLayer.DataBasePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateDialogWare
{
  private DataBasePanel dataBase;
  private JFrame frame;

  private Ware returnWare = null;

  private JLabel titleLabel = new JLabel("Create Ware");
  private JLabel idLabel = new JLabel("ID");
  private JLabel bezeichnungLabel = new JLabel("Name");
  private JLabel beschreibungLabel = new JLabel("Description");
  private JLabel preisLabel = new JLabel("Price");
  private JLabel besonderheitenLabel = new JLabel("Features");
  private JLabel maengelLabel = new JLabel("Deficits");

  private JTextField idTextField = new JTextField();
  private JTextField bezeichnungTextField = new JTextField();
  private JTextField beschreibungTextField = new JTextField();
  private JTextField preisTextField = new JTextField();
  private JTextField besonderheitenTextField = new JTextField();
  private JTextField maengelTextField= new JTextField();

  private JButton resetButton = new JButton("Reset");
  private JButton confirmButton = new JButton("Confirm");

  public CreateDialogWare(DataBasePanel dataBasePanel)
  {
    dataBase = dataBasePanel;
    initComponents();
  }

  private void initComponents()
  {
    frame = new JFrame();
    frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.75f), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.75f));
    frame.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.75f), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.75f)));
    frame.setLocationRelativeTo(null);
    frame.setLayout(new GridLayout());
    frame.addWindowListener(new WindowAdapter()
      {
        @Override
        public void windowClosed(WindowEvent evt)
        {
          if (returnWare == null)
          {
            dataBase.doCreate(null);
          }
        }
      }
    );

    JPanel pane = new JPanel();
    pane.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    DocumentListener listener = new DocumentListener()
    {
      @Override
      public void insertUpdate(DocumentEvent e)
      {
        checkAvailability();
      }

      @Override
      public void removeUpdate(DocumentEvent e)
      {
        checkAvailability();
      }

      @Override
      public void changedUpdate(DocumentEvent evt)
      {
        checkAvailability();
      }
    };

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridwidth = 2;
    pane.add(titleLabel, constraints);
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    pane.add(idLabel, constraints);
    constraints.gridy = 2;
    pane.add(bezeichnungLabel, constraints);
    constraints.gridy = 3;
    pane.add(beschreibungLabel, constraints);
    constraints.gridy = 4;
    pane.add(preisLabel, constraints);
    constraints.gridy = 5;
    pane.add(besonderheitenLabel, constraints);
    constraints.gridy = 6;
    pane.add(maengelLabel, constraints);
    constraints.gridy = 1;
    constraints.gridx = 1;
    pane.add(idTextField, constraints);
    constraints.gridy = 2;
    pane.add(bezeichnungTextField, constraints);
    constraints.gridy = 3;
    pane.add(beschreibungTextField, constraints);
    constraints.gridy = 4;
    pane.add(preisTextField, constraints);
    constraints.gridy = 5;
    pane.add(besonderheitenTextField, constraints);
    constraints.gridy = 5;
    pane.add(maengelTextField, constraints);
    constraints.gridy = 7;
    constraints.gridx = 0;
    pane.add(resetButton, constraints);
    constraints.gridx = 1;
    pane.add(confirmButton, constraints);

    idTextField.getDocument().addDocumentListener(listener);
    bezeichnungTextField.getDocument().addDocumentListener(listener);
    beschreibungTextField.getDocument().addDocumentListener(listener);
    preisTextField.getDocument().addDocumentListener(listener);
    besonderheitenTextField.getDocument().addDocumentListener(listener);
    maengelTextField.getDocument().addDocumentListener(listener);

    resetButton.addActionListener((ActionEvent evt) ->
      {
        idTextField.setText("");
        bezeichnungTextField.setText("");
        beschreibungTextField.setText("");
        preisTextField.setText("");
        besonderheitenTextField.setText("");
        maengelTextField.setText("");
      }
    );

    confirmButton.addActionListener((ActionEvent evt) ->
      {
        returnWare = new Ware(bezeichnungTextField.getText(), Double.parseDouble(preisTextField.getText()));
        returnWare.setBeschreibung(beschreibungTextField.getText());
        returnWare.setId(Long.parseLong(idTextField.getText()));
        dataBase.doCreate(returnWare);
        frame.dispose();
      }
    );
    checkAvailability();
    frame.add(pane);
    frame.setVisible(true);
  }

  private void checkAvailability()
  {
    resetButton.setEnabled(!idTextField.getText().isEmpty() || !bezeichnungTextField.getText().isEmpty() || !beschreibungTextField.getText().isEmpty() || !preisTextField.getText().isEmpty() || !besonderheitenTextField.getText().isEmpty() || !maengelTextField.getText().isEmpty());
    confirmButton.setEnabled(!idTextField.getText().isEmpty() && !bezeichnungTextField.getText().isEmpty() && !beschreibungTextField.getText().isEmpty() && !preisTextField.getText().isEmpty() && !besonderheitenTextField.getText().isEmpty() && !maengelTextField.getText().isEmpty());
  }
}
