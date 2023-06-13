package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionManager
{
  private static final String CLASSNAME = "";
  private static final String CONNECTIONSTRING = "";
  private static Connection existingConnection;
  private static boolean classLoaded;

  public Connection getNewConnection()
  {
    return null;
  }

  public Connection getExistingConnection()
  {
    return null;
  }

  public void close(ResultSet resultSet, Statement statement, Connection connection)
  {

  }
}
