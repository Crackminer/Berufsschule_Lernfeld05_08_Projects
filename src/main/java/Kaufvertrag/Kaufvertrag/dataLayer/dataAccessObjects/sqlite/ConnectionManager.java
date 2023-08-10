package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.*;

public class ConnectionManager
{
  private static final String CLASSNAME = "org.sqlite.JDBC";
  private static final String CONNECTIONSTRING = "jdbc:sqlite:database/Kaufvertrag";
  private static Connection existingConnection;
  private static boolean classLoaded = false;

  public Connection getNewConnection()
  {
    try
    {
      if (classLoaded)
      {
        Class.forName(CLASSNAME);
        classLoaded = true;
      }
      existingConnection.close();
      existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in ConnectionManager#getNewConnection() .");
    }
    return existingConnection;
  }

  public Connection getExistingConnection()
  {
    return existingConnection;
  }

  public void close(ResultSet resultSet, Statement statement, Connection connection)
  {
    try
    {
      resultSet.close();
      statement.close();
      connection.close();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in ConnectionManager#close() .");
    }
    classLoaded = false;
  }
}
