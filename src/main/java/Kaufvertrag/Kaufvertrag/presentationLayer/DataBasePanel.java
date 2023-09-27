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
    createButton.addActionListener((ActionEvent evt) ->
      {
        switch ((TableData) selectedTable.getSelectedItem())
        {
          case CONTRACT ->
          {
            IKaufvertrag updatedObject = new DialogKaufvertrag().getObject();
            if (updatedObject == null) break;
            if (persistence.equals("sqlite"))
              new KaufvertragDaoSqlite().create(updatedObject);
            else
              new KaufvertragDaoXml().create(updatedObject);
          }
          case PERSON ->
          {
            IVertragspartner updatedObject = new DialogVertragspartner().getObject();
            if (updatedObject == null) break;
            if (persistence.equals("sqlite"))
              new VertragspartnerDaoSqlite().create(updatedObject);
            else
              new VertragspartnerDaoXml().create(updatedObject);
          }
          case ADDRESS ->
          {
            IAdresse updatedObject = new DialogAdresse().getObject();
            if (updatedObject == null) break;
            if (persistence.equals("sqlite"))
              new AdresseDaoSqlite().create(updatedObject);
            else
              new AdresseDaoXml().create(updatedObject);
          }
          case WARE ->
          {
            IWare updatedObject = new DialogWare().getObject();
            if (updatedObject == null) break;
            if (persistence.equals("sqlite"))
              new WareDaoSqlite().create(updatedObject);
            else
              new WareDaoXml().create(updatedObject);
          }
          default ->
          {
          }
        }
        setTable((TableData) selectedTable.getSelectedItem());
      }
    );
    add(createButton, constraints);

    constraints.gridy = 4;
    updateButton = new JButton("Update");
    updateButton.addActionListener((ActionEvent evt) ->
      {
        if (persistenceTable.getSelectedRow() != -1)
        {
          Object[] objectArray = getSelectedObjectArray();
          switch ((TableData) selectedTable.getSelectedItem())
          {
            case CONTRACT ->
            {
              IKaufvertrag updatedObject = new UpdateDialogKaufvertrag(objectArray).getUpdatedObject();
              if (updatedObject == null) break;
              if (persistence.equals("sqlite"))
                new KaufvertragDaoSqlite().update(updatedObject);
              else
                new KaufvertragDaoXml().update(updatedObject);
            }
            case PERSON ->
            {
              IVertragspartner updatedObject = new UpdateDialogVertragspartner(objectArray).getUpdatedObject();
              if (updatedObject == null) break;
              if (persistence.equals("sqlite"))
                new VertragspartnerDaoSqlite().update(updatedObject);
              else
                new VertragspartnerDaoXml().update(updatedObject);
            }
            case ADDRESS ->
            {
              IAdresse updatedObject = new UpdateDialogAdresse(objectArray).getUpdatedObject();
              if (updatedObject == null) break;
              if (persistence.equals("sqlite"))
                new AdresseDaoSqlite().update(updatedObject);
              else
                new AdresseDaoXml().update(updatedObject);
            }
            case WARE ->
            {
              IWare updatedObject = new UpdateDialogWare(objectArray).getUpdatedObject();
              if (updatedObject == null) break;
              if (persistence.equals("sqlite"))
                new WareDaoSqlite().update(updatedObject);
              else
                new WareDaoXml().update(updatedObject);
            }
            default ->
            {
            }
          }
          setTable((TableData) selectedTable.getSelectedItem());
        }
      }
    );
    add(updateButton, constraints);

    constraints.gridy = 5;
    deleteButton = new JButton("Delete");
    deleteButton.addActionListener((ActionEvent evt) ->
      {
        if (persistenceTable.getSelectedRow() != -1)
        {
          Object id = persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 0);
          switch ((TableData) selectedTable.getSelectedItem())
          {
            case CONTRACT ->
            {
              if (persistence.equals("sqlite"))
                new KaufvertragDaoSqlite().delete((Long) id);
              else
                new KaufvertragDaoXml().delete((Long) id);
            }
            case PERSON ->
            {
              if (persistence.equals("sqlite"))
                new VertragspartnerDaoSqlite().delete((String) id);
              else
                new VertragspartnerDaoXml().delete((String) id);
            }
            case ADDRESS ->
            {
              if (persistence.equals("sqlite"))
                new AdresseDaoSqlite().delete((Long) id);
              else
                new AdresseDaoXml().delete((Long) id);
            }
            case WARE ->
            {
              if (persistence.equals("sqlite"))
                new WareDaoSqlite().delete((Long) id);
              else
                new WareDaoXml().delete((Long) id);
            }
            default ->
            {
            }
          }
          setTable((TableData) selectedTable.getSelectedItem());
        }
      }
    );
    add(deleteButton, constraints);

    readCurrentTableData();
  }

  private Object[] getSelectedObjectArray()
  {
    Object[] objectArray = null;
    switch ((TableData) selectedTable.getSelectedItem())
    {
      case CONTRACT ->
      {
        Long id = (Long)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 0);
        String ausweisVerkaufer = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 1);
        String ausweisKaufer = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 2);
        Long idWare = (Long)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 3);
        String zahlmethode = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 4);
        objectArray = new Object[] {id, ausweisVerkaufer, ausweisKaufer, idWare, zahlmethode};
      }
      case PERSON ->
      {
        String ausweis = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 0);
        String vorname = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 1);
        String nachname = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 2);
        Long idAdresse = (Long)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 3);
        objectArray = new Object[] {ausweis, vorname, nachname, idAdresse};
      }
      case ADDRESS ->
      {
        Long id = (Long)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 0);
        String strasse = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 1);
        String hausnr = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 2);
        String plz = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 3);
        String ort = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 4);
        objectArray = new Object[] {id, strasse, hausnr, plz, ort};
      }
      case WARE ->
      {
        Long id = (Long)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 0);
        String bezeichnung = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 1);
        String beschreibung = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 2);
        Double preis = (Double)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 3);
        String besonderheiten = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 4);
        String maengel = (String)persistenceTable.getValueAt(persistenceTable.getSelectedRow(), 5);
        objectArray = new Object[] {id, bezeichnung, beschreibung, preis, besonderheiten, maengel};
      }
      default ->
      {
      }
    }
    return objectArray;
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
    createTables();
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
    FlatLaf.updateUI();
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