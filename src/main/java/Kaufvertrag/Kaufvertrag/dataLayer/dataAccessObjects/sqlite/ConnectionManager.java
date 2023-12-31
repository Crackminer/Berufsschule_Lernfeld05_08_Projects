package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionManager
{
  private static final String CLASSNAME = "org.sqlite.JDBC";
  private static final String CONNECTIONSTRING;

  static
  {
    try
    {
      CONNECTIONSTRING = "jdbc:sqlite:" + new File(".").getCanonicalPath() + "/src/main/resources/databases/Kaufvertrag";
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private static Connection existingConnection;
  private static boolean classLoaded = false;

  public static Connection getNewConnection()
  {
    try
    {
      if (!classLoaded)
      {
        Class.forName(CLASSNAME);
        classLoaded = true;
      }
      if (existingConnection != null && !existingConnection.isClosed())
      {
        existingConnection.close();
      }
      existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in ConnectionManager#getNewConnection() .");
    }
    return existingConnection;
  }

  public static Connection getExistingConnection()
  {
    return existingConnection;
  }

  public static void close(ResultSet resultSet, Statement statement, Connection connection)
  {
    try
    {
      if (resultSet != null && !resultSet.isClosed())
      {
        resultSet.close();
      }
      if (statement != null && !statement.isClosed())
      {
        statement.close();
      }
      if (connection != null && !connection.isClosed())
      {
        connection.close();
      }
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in ConnectionManager#close() .");
    }
    classLoaded = false;
  }
}
