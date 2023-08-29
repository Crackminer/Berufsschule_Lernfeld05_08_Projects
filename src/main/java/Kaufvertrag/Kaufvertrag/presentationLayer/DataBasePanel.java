package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DataBasePanel extends JPanel
{
  private String persistence;

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
    persistenceLabel = new JLabel(persistence.toUpperCase() + " Persistence");
    persistenceLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    add(persistenceLabel, constraints);

    constraints.gridy = 1;

    selectedTable = new JComboBox<>();
    for (TableData data : TableData.values())
    {
      if (data != TableData.NONE)
        selectedTable.addItem(data);
    }
    selectedTable.addActionListener((ActionEvent e) ->
      {
        if(selectedTable.getSelectedItem() != null)
          setTable((TableData)selectedTable.getSelectedItem());
      }
    );

    add(selectedTable, constraints);

    persistenceTable = new JTable();
    persistenceTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    persistenceTable.setFillsViewportHeight(true);

    pane = new JScrollPane(persistenceTable);

    initTableColumns();

    setTable(TableData.CONTRACT);
    constraints.gridy = 2;
    add(pane, constraints);
  }

  private void initTableColumns()
  {
    idContractColumn = new TableColumn();
    idContractColumn.setIdentifier("IDCONTRACT");
    idContractColumn.setHeaderValue("ID");
    idContractColumn.setWidth(200);

    buyerColumn = new TableColumn();
    buyerColumn.setIdentifier("BUYER");
    buyerColumn.setHeaderValue("Buyer");
    buyerColumn.setWidth(200);

    sellerColumn = new TableColumn();
    sellerColumn.setIdentifier("SELLER");
    sellerColumn.setHeaderValue("Seller");
    sellerColumn.setWidth(200);

    waresColumn = new TableColumn();
    waresColumn.setIdentifier("WARES");
    waresColumn.setHeaderValue("Wares");
    waresColumn.setWidth(200);

    paymentColumn = new TableColumn();
    paymentColumn.setIdentifier("PAYMENT");
    paymentColumn.setHeaderValue("Payment Method");
    paymentColumn.setWidth(200);


    idAdressColumn = new TableColumn();
    idAdressColumn.setIdentifier("IDADRESS");
    idAdressColumn.setHeaderValue("ID");
    idAdressColumn.setWidth(200);

    streetColumn = new TableColumn();
    streetColumn.setIdentifier("STREET");
    streetColumn.setHeaderValue("Street");
    streetColumn.setWidth(200);

    houseNumberColumn = new TableColumn();
    houseNumberColumn.setIdentifier("HOUSENUMBER");
    houseNumberColumn.setHeaderValue("Housenumber");
    houseNumberColumn.setWidth(200);

    postCodeColumn = new TableColumn();
    postCodeColumn.setIdentifier("POSTALCODE");
    postCodeColumn.setHeaderValue("Postal Code");
    postCodeColumn.setWidth(200);

    cityColumn = new TableColumn();
    cityColumn.setIdentifier("CITY");
    cityColumn.setHeaderValue("City");
    cityColumn.setWidth(200);


    idPersonColumn = new TableColumn();
    idPersonColumn.setIdentifier("IDPERSON");
    idPersonColumn.setHeaderValue("Personal ID");   //Ausweisnummer statt eigens generierter ID!
    idPersonColumn.setWidth(200);

    nameColumn = new TableColumn();
    nameColumn.setIdentifier("NAME");
    nameColumn.setHeaderValue("Name");
    nameColumn.setWidth(200);

    surnameColumn = new TableColumn();
    surnameColumn.setIdentifier("SURNAME");
    surnameColumn.setHeaderValue("Surname");
    surnameColumn.setWidth(200);

    adressColumn = new TableColumn();
    adressColumn.setIdentifier("ADRESS");
    adressColumn.setHeaderValue("Adress");
    adressColumn.setWidth(200);


    idWareColumn = new TableColumn();
    idWareColumn.setIdentifier("IDWARE");
    idWareColumn.setHeaderValue("ID");
    idWareColumn.setWidth(200);

    productNameColumn = new TableColumn();
    productNameColumn.setIdentifier("PRODUCTNAME");
    productNameColumn.setHeaderValue("Name");
    productNameColumn.setWidth(200);

    descriptionColumn = new TableColumn();
    descriptionColumn.setIdentifier("DESCRIPTION");
    descriptionColumn.setHeaderValue("Description");
    descriptionColumn.setWidth(200);

    priceColumn = new TableColumn();
    priceColumn.setIdentifier("PRICE");
    priceColumn.setHeaderValue("price");
    priceColumn.setWidth(200);

    featuresColumn = new TableColumn();
    featuresColumn.setIdentifier("FEATURES");
    featuresColumn.setHeaderValue("Features");
    featuresColumn.setWidth(200);

    defectsColumn = new TableColumn();
    defectsColumn.setIdentifier("DEFECTS");
    defectsColumn.setHeaderValue("defects");
    defectsColumn.setWidth(200);
  }

  private void setTable(TableData data)
  {
    switch (currentData)
    {
      case CONTRACT ->
      {
        persistenceTable.removeColumn(idContractColumn);
        persistenceTable.removeColumn(buyerColumn);
        persistenceTable.removeColumn(sellerColumn);
        persistenceTable.removeColumn(waresColumn);
        persistenceTable.removeColumn(paymentColumn);
      }
      case PERSON ->
      {
        persistenceTable.removeColumn(idPersonColumn);
        persistenceTable.removeColumn(nameColumn);
        persistenceTable.removeColumn(surnameColumn);
        persistenceTable.removeColumn(adressColumn);
      }
      case ADDRESS ->
      {
        persistenceTable.removeColumn(idAdressColumn);
        persistenceTable.removeColumn(streetColumn);
        persistenceTable.removeColumn(houseNumberColumn);
        persistenceTable.removeColumn(postCodeColumn);
        persistenceTable.removeColumn(cityColumn);
      }
      case WARE ->
      {
        persistenceTable.removeColumn(idWareColumn);
        persistenceTable.removeColumn(productNameColumn);
        persistenceTable.removeColumn(descriptionColumn);
        persistenceTable.removeColumn(priceColumn);
        persistenceTable.removeColumn(featuresColumn);
        persistenceTable.removeColumn(defectsColumn);
      }
      default ->
      {
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
}

enum TableData
{
  NONE,
  CONTRACT,
  PERSON,
  ADDRESS,
  WARE,
}