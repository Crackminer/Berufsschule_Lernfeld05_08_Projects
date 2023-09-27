package Kaufvertrag.Kaufvertrag.presentationLayer.dialogues;

import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.presentationLayer.DataBasePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateDialogAdresse
{
  private DataBasePanel dataBase;
  private JFrame frame;

  private Adresse returnAdresse = null;

  private JLabel titleLabel = new JLabel("Create Adresse");
  private JLabel idLabel = new JLabel("ID");
  private JLabel strassenLabel = new JLabel("Street");
  private JLabel hausnummerLabel = new JLabel("House number");
  private JLabel plzLabel = new JLabel("Postal Code");
  private JLabel ortLabel = new JLabel("City");

  private JTextField idTextField = new JTextField();
  private JTextField strassenTextField = new JTextField();
  private JTextField hausnummerTextField = new JTextField();
  private JTextField plzTextField = new JTextField();
  private JTextField ortTextField = new JTextField();

  private JButton resetButton = new JButton("Reset");
  private JButton confirmButton = new JButton("Confirm");

  public UpdateDialogAdresse(Object[] objectArray, DataBasePanel dataBasePanel)
  {
    dataBase = dataBasePanel;
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
          if (returnAdresse == null)
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
    pane.add(strassenLabel, constraints);
    constraints.gridy = 3;
    pane.add(hausnummerLabel, constraints);
    constraints.gridy = 4;
    pane.add(plzLabel, constraints);
    constraints.gridy = 5;
    pane.add(ortLabel, constraints);
    constraints.gridy = 1;
    constraints.gridx = 1;
    pane.add(idTextField, constraints);
    constraints.gridy = 2;
    pane.add(strassenTextField, constraints);
    constraints.gridy = 3;
    pane.add(hausnummerTextField, constraints);
    constraints.gridy = 4;
    pane.add(plzTextField, constraints);
    constraints.gridy = 5;
    pane.add(ortTextField, constraints);
    constraints.gridy = 6;
    constraints.gridx = 0;
    pane.add(resetButton, constraints);
    constraints.gridx = 1;
    pane.add(confirmButton, constraints);

    idTextField.getDocument().addDocumentListener(listener);
    strassenTextField.getDocument().addDocumentListener(listener);
    hausnummerTextField.getDocument().addDocumentListener(listener);
    plzTextField.getDocument().addDocumentListener(listener);
    ortTextField.getDocument().addDocumentListener(listener);

    idTextField.setText(String.valueOf((Long)objectArray[0]));
    strassenTextField.setText((String)objectArray[1]);
    hausnummerTextField.setText((String)objectArray[2]);
    plzTextField.setText((String)objectArray[3]);
    ortTextField.setText((String)objectArray[4]);

    resetButton.addActionListener((ActionEvent evt) ->
      {
        idTextField.setText("");
        strassenTextField.setText("");
        hausnummerTextField.setText("");
        plzTextField.setText("");
        ortTextField.setText("");
      }
    );

    confirmButton.addActionListener((ActionEvent evt) ->
      {
        returnAdresse = new Adresse(strassenTextField.getText(), hausnummerTextField.getText(), plzTextField.getText(), ortTextField.getText());
        returnAdresse.setID(Long.parseLong(idTextField.getText()));
        dataBase.doUpdate(returnAdresse);
        frame.dispose();
      }
    );
    checkAvailability();
    frame.add(pane);
    frame.setVisible(true);
  }

  private void checkAvailability()
  {
    resetButton.setEnabled(!idTextField.getText().isEmpty() || !strassenTextField.getText().isEmpty() || !hausnummerTextField.getText().isEmpty() || !plzTextField.getText().isEmpty() || !ortTextField.getText().isEmpty());
    confirmButton.setEnabled(!idTextField.getText().isEmpty() && !strassenTextField.getText().isEmpty() && !hausnummerTextField.getText().isEmpty() && !plzTextField.getText().isEmpty() && !ortTextField.getText().isEmpty());
  }
}
