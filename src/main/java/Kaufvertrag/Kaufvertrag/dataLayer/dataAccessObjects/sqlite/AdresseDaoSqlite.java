package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AdresseDaoSqlite implements IDao<IAdresse, Long>
{
  @Override
  public IAdresse create()
  {
    return null;
  }

  @Override
  public IAdresse create(IAdresse objectToInsert)
  {
    return null;
  }

  @Override
  public IAdresse read(Long id)
  {
    return null;
  }

  @Override
  public List<IAdresse> readAll()
  {
    return null;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String sql = "UPDATE adresse SET strasse = ?, hausnummer = ?, postleitzahl = ?, ort = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, objectToUpdate.getStrasse());
      statement.setString(2, objectToUpdate.getHausNr());
      statement.setString(3, objectToUpdate.getPlz());
      statement.setString(4, objectToUpdate.getOrt());
      statement.setInt(5, whatthefuckshalliputastheidhere?);
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#update() .");
    }
  }

  @Override
  public void delete(Long id)
  {

  }
}
