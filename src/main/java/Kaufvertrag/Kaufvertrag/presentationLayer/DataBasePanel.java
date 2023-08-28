package Kaufvertrag.Kaufvertrag.presentationLayer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class DataBasePanel extends JPanel
{
  private String persistence;

  public DataBasePanel(String persistence)
  {
    this.persistence = persistence;
    //RootFrame.instance.setBack(PersistencePanel.instance);
    initComponents();
  }

  private void initComponents()
  {
    persistenceTable = new JTable();
    persistenceTableHeader = new JTableHeader();

    TableColumn idColumn = new TableColumn();
    idColumn.setIdentifier("ID");
    idColumn.setHeaderValue("ID");
    idColumn.setWidth(200);

    persistenceTable.addColumn(idColumn);
    persistenceTable.add(new JButton("Hello"));

    persistenceTableHeader.setTable(persistenceTable);

    this.add(persistenceTableHeader);
    this.add(persistenceTable);
  }

  private JTable persistenceTable;
  private JTableHeader persistenceTableHeader;
}
