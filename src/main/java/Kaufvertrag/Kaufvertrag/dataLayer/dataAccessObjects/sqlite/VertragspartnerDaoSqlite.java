package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String>
{
  @Override
  public IVertragspartner create()
  {
    Vertragspartner objectToInsert = new Vertragspartner("","");
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into vertragspartner (vorname, nachname, ausweisNr) values (?, ? , ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getVorname());
      statement.setString(2, objectToInsert.getNachname());
      statement.setString(3, objectToInsert.getAusweisNr());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoSqlite#create(IVertragspartner objectToInsert).");
    }
    return objectToInsert;
  }

  @Override
  public void create(IVertragspartner objectToInsert)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into vertragspartner (vorname, nachname, ausweisNr) values (?, ? , ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getVorname());
      statement.setString(2, objectToInsert.getNachname());
      statement.setString(3, objectToInsert.getAusweisNr());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoSqlite#create(IVertragspartner objectToInsert).");
    }
  }

  @Override
  public IVertragspartner read(String id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT vorname, nachname, ausweisNr from vertragspartner WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);
      ResultSet result = statement.executeQuery();
      String vorname = result.getString("vorname");
      String nachname = result.getString("nachname");
      String ausweisNr = result.getString("ausweisNr");
      Vertragspartner vertragspartner = new Vertragspartner(vorname, nachname);
      vertragspartner.setAusweisNr(ausweisNr);
      return vertragspartner;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoSqlite#read(String id).");
    }
    return null;
  }

  @Override
  public List<IVertragspartner> readAll()
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT vorname, nachname, ausweisNr from vertragspartner";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IVertragspartner> list = new ArrayList<>();
      while (result.next())
      {
        Vertragspartner vertragspartner = new Vertragspartner("","");
        vertragspartner.setVorname(result.getString("vorname"));
        vertragspartner.setNachname(result.getString("nachname"));
        vertragspartner.setAusweisNr(result.getString("ausweisNr"));
        list.add(vertragspartner);
      }
      return list;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertargspartnerDaoSqlite#readAll()");
    }
    return null;
  }

  @Override
  public void update(IVertragspartner objectToUpdate)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "UPDATE vertragspartner SET vorname = ?, nachname = ?, ausweisNr = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getVorname());
      statement.setString(2, objectToUpdate.getNachname());
      statement.setString(3, objectToUpdate.getAusweisNr());
      statement.executeUpdate();
    } catch (Exception ex) {
      System.out.println("here was an unexpected Exception in VertragspartnerDaoSqlite#update(Vertargspartner objectToUpdate).");
    }
  }

  @Override
  public void delete(String id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "DELETE from vertragspartner WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);
      ResultSet result = statement.executeQuery();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertargspartnerDaoSqlite#delete(String id).");
    }
  }
}
