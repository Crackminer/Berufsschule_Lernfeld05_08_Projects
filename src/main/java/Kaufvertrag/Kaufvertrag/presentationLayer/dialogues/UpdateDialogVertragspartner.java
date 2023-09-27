package Kaufvertrag.Kaufvertrag.presentationLayer.dialogues;

import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.AdresseDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.AdresseDaoXml;
import Kaufvertrag.Kaufvertrag.presentationLayer.DataBasePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateDialogVertragspartner
{
  private DataBasePanel dataBase;
  private String persistence;
  private JFrame frame;

  private Vertragspartner returnVertragspartner = null;

  private JLabel titleLabel = new JLabel("Create Contractpartner");
  private JLabel idLabel = new JLabel("Registration ID");
  private JLabel firstnameLabel = new JLabel("First Name");
  private JLabel lastnameLabel = new JLabel("Last Name");
  private JLabel addressLabel = new JLabel("Address");

  private JTextField idTextField = new JTextField();
  private JTextField firstnameTextField = new JTextField();
  private JTextField lastnameTextField = new JTextField();
  private JTextField addressTextField = new JTextField();

  private JButton resetButton = new JButton("Reset");
  private JButton confirmButton = new JButton("Confirm");

  public UpdateDialogVertragspartner(Object[] objectArray, DataBasePanel dataBasePanel, String persistence)
  {
    dataBase = dataBasePanel;
    this.persistence = persistence;
    initComponents(objectArray);
  }

  private void initComponents(Object[] objectArray)
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
          if (returnVertragspartner == null)
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
    pane.add(firstnameLabel, constraints);
    constraints.gridy = 3;
    pane.add(lastnameLabel, constraints);
    constraints.gridy = 4;
    pane.add(addressLabel, constraints);
    constraints.gridy = 1;
    constraints.gridx = 1;
    pane.add(idTextField, constraints);
    constraints.gridy = 2;
    pane.add(firstnameTextField, constraints);
    constraints.gridy = 3;
    pane.add(lastnameTextField, constraints);
    constraints.gridy = 4;
    pane.add(addressTextField, constraints);
    constraints.gridy = 5;
    constraints.gridx = 0;
    pane.add(resetButton, constraints);
    constraints.gridx = 1;
    pane.add(confirmButton, constraints);

    idTextField.getDocument().addDocumentListener(listener);
    firstnameTextField.getDocument().addDocumentListener(listener);
    lastnameTextField.getDocument().addDocumentListener(listener);
    addressTextField.getDocument().addDocumentListener(listener);

    idTextField.setText(String.valueOf((Long)objectArray[0]));
    firstnameTextField.setText((String)objectArray[1]);
    lastnameTextField.setText((String)objectArray[2]);
    addressTextField.setText((String)objectArray[3]);

    resetButton.addActionListener((ActionEvent evt) ->
      {
        idTextField.setText("");
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        addressTextField.setText("");
      }
    );

    confirmButton.addActionListener((ActionEvent evt) ->
      {
        returnVertragspartner = new Vertragspartner(firstnameTextField.getText(), lastnameTextField.getText());
        returnVertragspartner.setAusweisNr(idTextField.getText());
        returnVertragspartner.setAdresse(persistence.equals("sqlite") ? new AdresseDaoSqlite().read(Long.parseLong(addressTextField.getText())) : new AdresseDaoXml().read(Long.parseLong(addressTextField.getText())));
        dataBase.doUpdate(returnVertragspartner);
        frame.dispose();
      }
    );
    checkAvailability();
    frame.add(pane);
    frame.setVisible(true);
  }

  private void checkAvailability()
  {
    resetButton.setEnabled(!idTextField.getText().isEmpty() || !firstnameTextField.getText().isEmpty() || !lastnameTextField.getText().isEmpty() || !addressTextField.getText().isEmpty());
    confirmButton.setEnabled(!idTextField.getText().isEmpty() && !firstnameTextField.getText().isEmpty() && !lastnameTextField.getText().isEmpty() && !addressTextField.getText().isEmpty());
  }
}
