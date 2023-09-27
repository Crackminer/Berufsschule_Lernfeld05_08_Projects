package Kaufvertrag.Kaufvertrag.presentationLayer.dialogues;

import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.VertragspartnerDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.WareDaoXml;
import Kaufvertrag.Kaufvertrag.presentationLayer.DataBasePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateDialogKaufvertrag
{
  private DataBasePanel dataBase;
  private String persistence;
  private JFrame frame;

  private Kaufvertrag returnKaufvertrag = null;

  private JLabel titleLabel = new JLabel("Create Contract");
  private JLabel kauferLabel = new JLabel("Buyer");
  private JLabel verkauferLabel = new JLabel("Seller");
  private JLabel wareLabel = new JLabel("Ware");
  private JLabel zahlmethodeLabel = new JLabel("Payment Method");

  private JTextField kauferTextField = new JTextField();
  private JTextField verkauferTextField = new JTextField();
  private JTextField wareTextField = new JTextField();
  private JTextField zahlmethodeTextField = new JTextField();

  private JButton resetButton = new JButton("Reset");
  private JButton confirmButton = new JButton("Confirm");

  public CreateDialogKaufvertrag(DataBasePanel dataBasePanel, String persistence)
  {
    this.persistence = persistence;
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
          if (returnKaufvertrag == null)
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
    pane.add(kauferLabel, constraints);
    constraints.gridy = 2;
    pane.add(verkauferLabel, constraints);
    constraints.gridy = 3;
    pane.add(wareLabel, constraints);
    constraints.gridy = 4;
    pane.add(zahlmethodeLabel, constraints);
    constraints.gridy = 1;
    constraints.gridx = 1;
    pane.add(kauferTextField, constraints);
    constraints.gridy = 2;
    pane.add(verkauferTextField, constraints);
    constraints.gridy = 3;
    pane.add(wareTextField, constraints);
    constraints.gridy = 4;
    pane.add(zahlmethodeTextField, constraints);
    constraints.gridy = 5;
    constraints.gridx = 0;
    pane.add(resetButton, constraints);
    constraints.gridx = 1;
    pane.add(confirmButton, constraints);

    kauferTextField.getDocument().addDocumentListener(listener);
    verkauferTextField.getDocument().addDocumentListener(listener);
    wareTextField.getDocument().addDocumentListener(listener);
    zahlmethodeTextField.getDocument().addDocumentListener(listener);

    resetButton.addActionListener((ActionEvent evt) ->
      {
        kauferTextField.setText("");
        verkauferTextField.setText("");
        wareTextField.setText("");
        zahlmethodeTextField.setText("");
      }
    );

    confirmButton.addActionListener((ActionEvent evt) ->
      {
        if (persistence.equals("sqlite"))
        {
          returnKaufvertrag = new Kaufvertrag(new VertragspartnerDaoSqlite().read(kauferTextField.getText()), new VertragspartnerDaoSqlite().read(verkauferTextField.getText()), new WareDaoSqlite().read(Long.parseLong(wareTextField.getText())));
        }
        else
        {
          returnKaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(kauferTextField.getText()), new VertragspartnerDaoXml().read(verkauferTextField.getText()), new WareDaoXml().read(Long.parseLong(wareTextField.getText())));
        }
        returnKaufvertrag.setZahlungsModalitaeten(zahlmethodeTextField.getText());
        dataBase.doCreate(returnKaufvertrag);
        frame.dispose();
      }
    );
    checkAvailability();
    frame.add(pane);
    frame.setVisible(true);
  }

  private void checkAvailability()
  {
    resetButton.setEnabled(!kauferTextField.getText().isEmpty() || !verkauferTextField.getText().isEmpty() || !wareTextField.getText().isEmpty() || !zahlmethodeTextField.getText().isEmpty());
    confirmButton.setEnabled(!kauferTextField.getText().isEmpty() && !verkauferTextField.getText().isEmpty() && !wareTextField.getText().isEmpty() && !zahlmethodeTextField.getText().isEmpty());
  }
}
