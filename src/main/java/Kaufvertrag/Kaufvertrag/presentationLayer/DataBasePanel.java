package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

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
    persistenceTable = new JTable();
    persistenceTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    persistenceTable.setFillsViewportHeight(true);

    pane = new JScrollPane(persistenceTable);

    TableColumn idColumn = new TableColumn();
    idColumn.setIdentifier("ID");
    idColumn.setHeaderValue("ID");
    idColumn.setWidth(200);

    TableColumn buyerColumn = new TableColumn();
    buyerColumn.setIdentifier("BUYER");
    buyerColumn.setHeaderValue("Buyer");
    buyerColumn.setWidth(200);

    TableColumn sellerColumn = new TableColumn();
    sellerColumn.setIdentifier("SELLER");
    sellerColumn.setHeaderValue("Seller");
    sellerColumn.setWidth(200);

    TableColumn waresColumn = new TableColumn();
    waresColumn.setIdentifier("WARES");
    waresColumn.setHeaderValue("Wares");
    waresColumn.setWidth(200);

    persistenceTable.addColumn(idColumn);
    persistenceTable.addColumn(buyerColumn);
    persistenceTable.addColumn(sellerColumn);
    persistenceTable.addColumn(waresColumn);

    this.add(pane);
  }

  private JTable persistenceTable;
  private JScrollPane pane;
}
