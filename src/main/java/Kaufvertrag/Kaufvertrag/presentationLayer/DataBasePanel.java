package Kaufvertrag.Kaufvertrag.presentationLayer;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.AdresseDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.KaufvertragDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.AdresseDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.KaufvertragDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.VertragspartnerDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.WareDaoXml;
import com.formdev.flatlaf.FlatLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

enum TableData
{
  NONE,
  CONTRACT,
  PERSON,
  ADDRESS,
  WARE,
}

public class DataBasePanel extends JPanel
{
  private final String persistence;
  private JTable persistenceTable;
  private JTable contractTable;
  private JTable partnerTable;
  private JTable addressTable;
  private JTable wareTable;
  private JScrollPane pane;
  private JLabel persistenceLabel;
  private JComboBox<TableData> selectedTable;
  private TableData currentData = TableData.NONE;
  private JButton createButton;
  private JButton updateButton;
  private JButton deleteButton;
  private GridBagConstraints paneConstraints;
  public DataBasePanel(String persistence)
  {
    this.persistence = persistence;
    RootFrame.instance.setBack(PersistencePanel.instance);
    initComponents();
  }

  private void initComponents()
  {
    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1f;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    persistenceLabel = new JLabel(persistence.toUpperCase() + " Persistence");
    persistenceLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    add(persistenceLabel, constraints);

    constraints.gridy = 1;

    selectedTable = new JComboBox<>();
    for (TableData data : TableData.values())
    {
      if (data != TableData.NONE)
      {
        selectedTable.addItem(data);
      }
    }
    selectedTable.addActionListener((ActionEvent e) ->
      {
        if (selectedTable.getSelectedItem() != null)
        {
          setTable((TableData) selectedTable.getSelectedItem());
        }
      }
    );

    add(selectedTable, constraints);

    newTable(null, null);

    createTables();

    setTable(TableData.CONTRACT);
    constraints.gridy = 2;
    constraints.weighty = 1f;
    constraints.gridwidth = 1;
    constraints.gridheight = 4;
    add(pane, constraints);
    paneConstraints = constraints;

    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 3;
    createButton = new JButton("Create");
    add(createButton, constraints);

    constraints.gridy = 4;
    updateButton = new JButton("Update");
    add(updateButton, constraints);

    constraints.gridy = 5;
    deleteButton = new JButton("Delete");
    add(deleteButton, constraints);

    readCurrentTableData();
  }

  private void createTables()
  {
    String[] tableheaders = new String[] {
      "Buyer",
      "Seller",
      "Ware",
      "Paymentmethod"
    };
    ArrayList<Object[]> objectArrayList = new ArrayList<Object[]>();
    List<IKaufvertrag> contracts = persistence.equals("sqlite") ? new KaufvertragDaoSqlite().readAll() : new KaufvertragDaoXml().readAll();
    if (contracts == null)
    {
      newTable(null, tableheaders);
      return;
    }
    for (IKaufvertrag kaufvertrag : contracts)
    {
      objectArrayList.add(new Object[] {kaufvertrag.getKaeufer().getAusweisNr(), kaufvertrag.getVerkaeufer().getAusweisNr(), kaufvertrag.getWare().getId(), kaufvertrag.getZahlungsModalitaeten()});
    }
    Object[][] objectArrayArray = new Object[objectArrayList.size()][4];
    for (int i = 0; i < objectArrayList.size(); i++)
    {
      objectArrayArray[i] = objectArrayList.get(i);
    }
    contractTable = new JTable(objectArrayArray, tableheaders);

    tableheaders = new String[] {
      "Identification Number",
      "First Name",
      "Last Name",
      "Address"
    };
    objectArrayList = new ArrayList<Object[]>();
    List<IVertragspartner> partners = persistence.equals("sqlite") ? new VertragspartnerDaoSqlite().readAll() : new VertragspartnerDaoXml().readAll();
    if (partners == null)
    {
      newTable(null, tableheaders);
      return;
    }
    for (IVertragspartner partner : partners)
    {
      objectArrayList.add(new Object[] {partner.getAusweisNr(), partner.getVorname(), partner.getNachname(), partner.getAdresse().getID()});
    }
    objectArrayArray = new Object[objectArrayList.size()][4];
    for (int i = 0; i < objectArrayList.size(); i++)
    {
      objectArrayArray[i] = objectArrayList.get(i);
    }
    partnerTable = new JTable(objectArrayArray, tableheaders);

    tableheaders = new String[] {
      "ID",
      "Street",
      "House Number",
      "Postal Coda",
      "City"
    };
    objectArrayList = new ArrayList<Object[]>();
    List<IAdresse> addresses = persistence.equals("sqlite") ? new AdresseDaoSqlite().readAll() : new AdresseDaoXml().readAll();
    if (addresses == null)
    {
      newTable(null, tableheaders);
      return;
    }
    for (IAdresse address : addresses)
    {
      objectArrayList.add(new Object[] {address.getID(), address.getStrasse(), address.getHausNr(), address.getPlz(), address.getOrt()});
    }
    objectArrayArray = new Object[objectArrayList.size()][5];
    for (int i = 0; i < objectArrayList.size(); i++)
    {
      objectArrayArray[i] = objectArrayList.get(i);
    }
    addressTable = new JTable(objectArrayArray, tableheaders);

    tableheaders = new String[] {
      "ID",
      "Name",
      "Description",
      "Price",
      "Benefits",
      "Deficits"
    };
    objectArrayList = new ArrayList<Object[]>();
    List<IWare> wares = persistence.equals("sqlite") ? new WareDaoSqlite().readAll() : new WareDaoXml().readAll();
    if (wares == null)
    {
      newTable(null, tableheaders);
      return;
    }
    for (IWare ware : wares)
    {
      objectArrayList.add(new Object[] {ware.getId(), ware.getBezeichnung(), ware.getBeschreibung(), ware.getPreis(), ware.getBesonderheiten().toString(), ware.getMaengel().toString()});
    }
    objectArrayArray = new Object[objectArrayList.size()][6];
    for (int i = 0; i < objectArrayList.size(); i++)
    {
      objectArrayArray[i] = objectArrayList.get(i);
    }
    wareTable = new JTable(objectArrayArray, tableheaders);
  }

  private void setTable(TableData data)
  {
    this.remove(pane);
    switch (data)
    {
      case CONTRACT ->
      {
        persistenceTable = contractTable;
      }
      case PERSON ->
      {
        persistenceTable = partnerTable;
      }
      case ADDRESS ->
      {
        persistenceTable = addressTable;
      }
      case WARE ->
      {
        persistenceTable = wareTable;
      }
      default ->
      {
      }
    }
    pane = new JScrollPane(persistenceTable);
    this.add(pane, paneConstraints);
    currentData = data;
    repaint();
  }

  private void readCurrentTableData()
  {
    setTable(currentData);
    repaint();
  }

  private void newTable(Object[][] content, String[] header)
  {
    persistenceTable = header == null ? new JTable() : new JTable(content, header);
    persistenceTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    persistenceTable.setFillsViewportHeight(true);

    pane = new JScrollPane(persistenceTable);
    repaint();
    FlatLaf.updateUI();
  }
}