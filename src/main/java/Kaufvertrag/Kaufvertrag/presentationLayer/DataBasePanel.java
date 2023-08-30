package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

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
  private JScrollPane pane;
  private TableColumn idContractColumn;
  private TableColumn buyerColumn;
  private TableColumn sellerColumn;
  private TableColumn waresColumn;
  private TableColumn paymentColumn;
  private TableColumn idPersonColumn;
  private TableColumn nameColumn;
  private TableColumn surnameColumn;
  private TableColumn adressColumn;
  private TableColumn idAdressColumn;
  private TableColumn streetColumn;
  private TableColumn houseNumberColumn;
  private TableColumn postCodeColumn;
  private TableColumn cityColumn;
  private TableColumn idWareColumn;
  private TableColumn productNameColumn;
  private TableColumn descriptionColumn;
  private TableColumn priceColumn;
  private TableColumn featuresColumn;
  private TableColumn defectsColumn;
  private JLabel persistenceLabel;
  private JComboBox<TableData> selectedTable;
  private TableData currentData = TableData.NONE;
  private JButton createButton;
  private JButton updateButton;
  private JButton deleteButton;
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

    newTable();

    initTableColumns();

    setTable(TableData.CONTRACT);
    constraints.gridy = 2;
    constraints.weighty = 1f;
    constraints.gridwidth = 1;
    constraints.gridheight = 4;
    add(pane, constraints);

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

  private void initTableColumns()
  {
    getContractColumns();

    getAddressColumns();

    getPersonColumns();

    getWareColumns();
  }

  private void setTable(TableData data)
  {
    int helper = 0;
    while (persistenceTable.getColumnCount() > 0)
    {
      if (persistenceTable.getColumnModel() != null)
      {
        persistenceTable.removeColumn(persistenceTable.getColumnModel().getColumn(0));
      }
      if (++helper == 100)  //Fail save if this doesnt work anymore, so we dont get stuck in an infinite loop
      {
        break;
      }
    }

    switch (data)
    {
      case CONTRACT ->
      {
        persistenceTable.addColumn(idContractColumn);
        persistenceTable.addColumn(buyerColumn);
        persistenceTable.addColumn(sellerColumn);
        persistenceTable.addColumn(waresColumn);
        persistenceTable.addColumn(paymentColumn);
      }
      case PERSON ->
      {
        persistenceTable.addColumn(idPersonColumn);
        persistenceTable.addColumn(nameColumn);
        persistenceTable.addColumn(surnameColumn);
        persistenceTable.addColumn(adressColumn);
      }
      case ADDRESS ->
      {
        persistenceTable.addColumn(idAdressColumn);
        persistenceTable.addColumn(streetColumn);
        persistenceTable.addColumn(houseNumberColumn);
        persistenceTable.addColumn(postCodeColumn);
        persistenceTable.addColumn(cityColumn);
      }
      case WARE ->
      {
        persistenceTable.addColumn(idWareColumn);
        persistenceTable.addColumn(productNameColumn);
        persistenceTable.addColumn(descriptionColumn);
        persistenceTable.addColumn(priceColumn);
        persistenceTable.addColumn(featuresColumn);
        persistenceTable.addColumn(defectsColumn);
      }
      default ->
      {
      }
    }
    currentData = data;
  }

  private void readCurrentTableData()
  {
    switch (currentData)
    {
      case CONTRACT ->
      {
        getContractColumns();
      }
      case PERSON ->
      {
        getPersonColumns();
      }
      case ADDRESS ->
      {
        getAddressColumns();
      }
      case WARE ->
      {
        getWareColumns();
      }
      default ->
      {
      }
    }
    repaint();
  }

  //TODO: get the data of the persistence type stored data into the table in here, likely with a tablecelleditor (4th argument in new tablecolumn, standard is null, like i am currently displaying)
  private void getContractColumns()
  {
    idContractColumn = new TableColumn(0, 75, null, null);
    idContractColumn.setIdentifier("IDCONTRACT");
    idContractColumn.setHeaderValue("ID");
    idContractColumn.setWidth(200);

    buyerColumn = new TableColumn(0, 75, null, null);
    buyerColumn.setIdentifier("BUYER");
    buyerColumn.setHeaderValue("Buyer");
    buyerColumn.setWidth(200);

    sellerColumn = new TableColumn(0, 75, null, null);
    sellerColumn.setIdentifier("SELLER");
    sellerColumn.setHeaderValue("Seller");
    sellerColumn.setWidth(200);

    waresColumn = new TableColumn(0, 75, null, null);
    waresColumn.setIdentifier("WARES");
    waresColumn.setHeaderValue("Wares");
    waresColumn.setWidth(200);

    paymentColumn = new TableColumn(0, 75, null, null);
    paymentColumn.setIdentifier("PAYMENT");
    paymentColumn.setHeaderValue("Payment Method");
    paymentColumn.setWidth(200);
  }

  private void getAddressColumns()
  {
    idAdressColumn = new TableColumn(0, 75, null, null);
    idAdressColumn.setIdentifier("IDADRESS");
    idAdressColumn.setHeaderValue("ID");
    idAdressColumn.setWidth(200);

    streetColumn = new TableColumn(0, 75, null, null);
    streetColumn.setIdentifier("STREET");
    streetColumn.setHeaderValue("Street");
    streetColumn.setWidth(200);

    houseNumberColumn = new TableColumn(0, 75, null, null);
    houseNumberColumn.setIdentifier("HOUSENUMBER");
    houseNumberColumn.setHeaderValue("Housenumber");
    houseNumberColumn.setWidth(200);

    postCodeColumn = new TableColumn(0, 75, null, null);
    postCodeColumn.setIdentifier("POSTALCODE");
    postCodeColumn.setHeaderValue("Postal Code");
    postCodeColumn.setWidth(200);

    cityColumn = new TableColumn(0, 75, null, null);
    cityColumn.setIdentifier("CITY");
    cityColumn.setHeaderValue("City");
    cityColumn.setWidth(200);
  }

  private void getPersonColumns()
  {
    idPersonColumn = new TableColumn(0, 75, null, null);
    idPersonColumn.setIdentifier("IDPERSON");
    idPersonColumn.setHeaderValue("Personal ID");   //Ausweisnummer statt eigens generierter ID!
    idPersonColumn.setWidth(200);

    nameColumn = new TableColumn(0, 75, null, null);
    nameColumn.setIdentifier("NAME");
    nameColumn.setHeaderValue("Name");
    nameColumn.setWidth(200);

    surnameColumn = new TableColumn(0, 75, null, null);
    surnameColumn.setIdentifier("SURNAME");
    surnameColumn.setHeaderValue("Surname");
    surnameColumn.setWidth(200);

    adressColumn = new TableColumn(0, 75, null, null);
    adressColumn.setIdentifier("ADRESS");
    adressColumn.setHeaderValue("Adress");
    adressColumn.setWidth(200);
  }

  private void getWareColumns()
  {
    idWareColumn = new TableColumn(0, 75, null, null);
    idWareColumn.setIdentifier("IDWARE");
    idWareColumn.setHeaderValue("ID");
    idWareColumn.setWidth(200);

    productNameColumn = new TableColumn(0, 75, null, null);
    productNameColumn.setIdentifier("PRODUCTNAME");
    productNameColumn.setHeaderValue("Name");
    productNameColumn.setWidth(200);

    descriptionColumn = new TableColumn(0, 75, null, null);
    descriptionColumn.setIdentifier("DESCRIPTION");
    descriptionColumn.setHeaderValue("Description");
    descriptionColumn.setWidth(200);

    priceColumn = new TableColumn(0, 75, null, null);
    priceColumn.setIdentifier("PRICE");
    priceColumn.setHeaderValue("price");
    priceColumn.setWidth(200);

    featuresColumn = new TableColumn(0, 75, null, null);
    featuresColumn.setIdentifier("FEATURES");
    featuresColumn.setHeaderValue("Features");
    featuresColumn.setWidth(200);

    defectsColumn = new TableColumn(0, 75, null, null);
    defectsColumn.setIdentifier("DEFECTS");
    defectsColumn.setHeaderValue("defects");
    defectsColumn.setWidth(200);
  }

  private void newTable()
  {
    persistenceTable = new JTable();
    persistenceTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    persistenceTable.setFillsViewportHeight(true);

    pane = new JScrollPane(persistenceTable);
    repaint();
  }
}