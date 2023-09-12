package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String>
{
  @Override
  public IVertragspartner create()
  {
    IVertragspartner objectToInsert = new Vertragspartner("", "");
    objectToInsert.setNachname(Programm.getInputMethod().getString("Nachname", getClass()));
    objectToInsert.setVorname(Programm.getInputMethod().getString("Vorname", getClass()));
    objectToInsert.setAusweisNr(Programm.getInputMethod().getString("Ausweisnummer", getClass()));

    IAdresse adresse = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Vertragspartner or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
    {
      adresse = new Adresse("", "", "", "");
      adresse.setStrasse(Programm.getInputMethod().getString("Adresse Strasse", getClass()));
      adresse.setHausNr(Programm.getInputMethod().getString("Adresse Hausnummer", getClass()));
      adresse.setPlz(Programm.getInputMethod().getString("Adresse PLZ", getClass()));
      adresse.setOrt(Programm.getInputMethod().getString("Adresse Ort", getClass()));

      ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

      new AdresseDaoSqlite().create(adresse);
    }
    else
    {
      objectToInsert.setAdresse(new AdresseDaoSqlite().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
    }
    /*try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into vertragspartner (vorname, nachname, ausweisNr, adresse) values (?, ? , ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getVorname());
      statement.setString(2, objectToInsert.getNachname());
      statement.setString(3, objectToInsert.getAusweisNr());
      statement.setLong(4, objectToInsert.getAdresse().getID());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoSqlite#create(IVertragspartner objectToInsert).");
    }*/
    return objectToInsert;
  }

  @Override
  public void create(IVertragspartner objectToInsert)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into vertragspartner (vorname, nachname, ausweisNr, adresse) values (?, ? , ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getVorname());
      statement.setString(2, objectToInsert.getNachname());
      statement.setString(3, objectToInsert.getAusweisNr());
      statement.setLong(4, objectToInsert.getAdresse().getID());
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
      String query = "SELECT vorname, nachname, ausweisNr, adresse from vertragspartner WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);
      ResultSet result = statement.executeQuery();
      String vorname = result.getString("vorname");
      String nachname = result.getString("nachname");
      String ausweisNr = result.getString("ausweisNr");
      Long addressId = result.getLong("addressId");
      Vertragspartner vertragspartner = new Vertragspartner(vorname, nachname);
      vertragspartner.setAusweisNr(ausweisNr);
      vertragspartner.setAdresse(new AdresseDaoSqlite().read(addressId));
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
      String query = "SELECT vorname, nachname, ausweisNr, adresse from vertragspartner";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IVertragspartner> list = new ArrayList<>();
      while (result.next())
      {
        Vertragspartner vertragspartner = new Vertragspartner("","");
        vertragspartner.setVorname(result.getString("vorname"));
        vertragspartner.setNachname(result.getString("nachname"));
        vertragspartner.setAusweisNr(result.getString("ausweisNr"));
        vertragspartner.setAdresse(new AdresseDaoSqlite().read(result.getLong("addressId")));
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
      String query = "UPDATE vertragspartner SET vorname = ?, nachname = ?, ausweisNr = ?, adresse = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getVorname());
      statement.setString(2, objectToUpdate.getNachname());
      statement.setString(3, objectToUpdate.getAusweisNr());
      statement.setLong(4, objectToUpdate.getAdresse().getID());
      statement.setLong(5, Programm.getInputMethod().getID());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
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
