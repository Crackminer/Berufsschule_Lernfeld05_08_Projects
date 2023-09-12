package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WareDaoSqlite implements IDao<IWare, Long>
{
  @Override
  public IWare create()
  {
    Ware objectToInsert = new Ware("", 0.0);
    objectToInsert = new Ware("", 0.0);
    objectToInsert.setBezeichnung(Programm.getInputMethod().getString("Bezeichnung", getClass()));
    objectToInsert.setBeschreibung(Programm.getInputMethod().getString("Beschreibung", getClass()));
    objectToInsert.setPreis(Programm.getInputMethod().getDouble("Preis", getClass()));

    objectToInsert.setId(Programm.getInputMethod().getID());
    /*try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into ware (id, bezeichnung, beschreibung, preis, besonderheiten, maengel) values(?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, objectToInsert.getId());
      statement.setString(2, objectToInsert.getBezeichnung());
      statement.setString(3, objectToInsert.getBeschreibung());
      statement.setDouble(4, objectToInsert.getPreis());
      statement.setString(5, Arrays.toString(objectToInsert.getBesonderheiten().toArray()));
      statement.setString(6, Arrays.toString(objectToInsert.getMaengel().toArray()));
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#create(IWare objectToInsert).");
    }*/
    return objectToInsert;
  }

  @Override
  public void create(IWare objectToInsert)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into ware (id, bezeichnung, beschreibung, preis, besonderheiten, maengel) values(?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, objectToInsert.getId());
      statement.setString(2, objectToInsert.getBezeichnung());
      statement.setString(3, objectToInsert.getBeschreibung());
      statement.setDouble(4, objectToInsert.getPreis());
      statement.setString(5, Arrays.toString(objectToInsert.getBesonderheiten().toArray()));
      statement.setString(6, Arrays.toString(objectToInsert.getMaengel().toArray()));
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#create(IWare objectToInsert).");
    }
  }

  @Override
  public IWare read(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT bezeichnung, beschreibung, preis, besonderheiten, maengel from ware WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      String bezeichnung = result.getString("bezeichnung");
      String beschreibung = result.getString("beschreibung");
      double preis = result.getDouble("preis");
      String besonderheiten = result.getString("besonderheiten");
      String maengel = result.getString("maengel");
      Ware ware = new Ware(bezeichnung, preis);
      ware.setBeschreibung(beschreibung);
      ware.setId(id);
      return ware;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#read(Long id).");
    }
    return null;
  }

  @Override
  public List<IWare> readAll()
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT bezeichnung, beschreibung, preis, besonderheiten, maengel from ware";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IWare> list = new ArrayList<>();
      while (result.next())
      {
        String bezeichnung = result.getString("bezeichnung");
        String beschreibung = result.getString("beschreibung");
        double preis = result.getDouble("preis");
        String besonderheiten = result.getString("besonderheiten");
        String maengel = result.getString("maengel");
        Ware ware = new Ware(bezeichnung, preis);
        ware.setBeschreibung(beschreibung);
        ware.setId(result.getLong("id"));
        list.add(ware);
      }
      return list;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#readAll().");
    }
    return null;
  }

  @Override
  public void update(IWare objectToUpdate)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "UPDATE ware SET bezeichnung = ?, beschreibung = ?, preis = ?, besonderheiten = ?, maengel = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getBezeichnung());
      statement.setString(2, objectToUpdate.getBeschreibung());
      statement.setDouble(3, objectToUpdate.getPreis());
      statement.setString(4, Arrays.toString(objectToUpdate.getBesonderheiten().toArray()));
      statement.setString(5, Arrays.toString(objectToUpdate.getMaengel().toArray()));
      statement.setLong(6, objectToUpdate.getId());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#update(IAdresse objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "DELETE from ware WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in WareDaoSqlite#delete(Long id).");
    }
  }
}
