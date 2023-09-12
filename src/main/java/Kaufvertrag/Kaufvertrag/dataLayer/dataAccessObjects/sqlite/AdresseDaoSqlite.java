package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdresseDaoSqlite implements IDao<IAdresse, Long>
{
  @Override
  public IAdresse create()
  {
    Adresse objectToInsert = new Adresse("", "", "", "");
    objectToInsert = new Adresse("", "", "", "");
    objectToInsert.setStrasse(Programm.getInputMethod().getString("Strasse", getClass()));
    objectToInsert.setHausNr(Programm.getInputMethod().getString("Hausnummer", getClass()));
    objectToInsert.setPlz(Programm.getInputMethod().getString("PLZ", getClass()));
    objectToInsert.setOrt(Programm.getInputMethod().getString("Ort", getClass()));

    objectToInsert.setID(Programm.getInputMethod().getID());
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into adresse (id, strasse, hausnummer, postleitzahl, ort) values(?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, objectToInsert.getID());
      statement.setString(2, objectToInsert.getStrasse());
      statement.setString(3, objectToInsert.getHausNr());
      statement.setString(4, objectToInsert.getPlz());
      statement.setString(5, objectToInsert.getOrt());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#create(IAdresse objectToInsert).");
    }
    return objectToInsert;
  }

  @Override
  public void create(IAdresse objectToInsert)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into adresse (id, strasse, hausnummer, postleitzahl, ort) values(?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, objectToInsert.getID());
      statement.setString(2, objectToInsert.getStrasse());
      statement.setString(3, objectToInsert.getHausNr());
      statement.setString(4, objectToInsert.getPlz());
      statement.setString(5, objectToInsert.getOrt());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#create(IAdresse objectToInsert).");
    }
  }

  @Override
  public IAdresse read(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT strasse, hausnummer, postleitzahl, ort from adresse WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      String strasse = result.getString("strasse");
      String hausnummer = result.getString("hausnummer");
      String plz = result.getString("postleitzahl");
      String ort = result.getString("ort");
      Adresse adresse = new Adresse(strasse, hausnummer, plz, ort);
      adresse.setID(id);
      return adresse;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#read(Long id).");
    }
    return null;
  }

  @Override
  public List<IAdresse> readAll()
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT strasse, hausnummer, postleitzahl, ort from adresse";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IAdresse> list = new ArrayList<>();
      while (result.next())
      {
        String strasse = result.getString("strasse");
        String hausnummer = result.getString("hausnummer");
        String plz = result.getString("postleitzahl");
        String ort = result.getString("ort");
        Adresse addresse = new Adresse(strasse, hausnummer, plz, ort);
        addresse.setID(result.getLong("id"));
        list.add(addresse);
      }
      return list;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#readAll().");
    }
    return null;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "UPDATE adresse SET strasse = ?, hausnummer = ?, postleitzahl = ?, ort = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getStrasse());
      statement.setString(2, objectToUpdate.getHausNr());
      statement.setString(3, objectToUpdate.getPlz());
      statement.setString(4, objectToUpdate.getOrt());
      statement.setLong(5, objectToUpdate.getID());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#update(IAdresse objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "DELETE from adresse WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#delete(Long id).");
    }
  }
}
