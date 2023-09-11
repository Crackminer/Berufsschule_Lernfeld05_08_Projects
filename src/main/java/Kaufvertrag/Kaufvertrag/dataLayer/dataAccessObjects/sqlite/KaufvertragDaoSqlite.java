package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KaufvertragDaoSqlite implements IDao<IKaufvertrag, Long>
{
  @Override
  public IKaufvertrag create()
  {
    IKaufvertrag objectToInsert = new Kaufvertrag(null, null, null);
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into kaufvertrag (id, kaufer, verkaufer, ware, zahlungsmittel) values(?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, 0);
      statement.setString(2, objectToInsert.getKaeufer().getAusweisNr());
      statement.setString(3, objectToInsert.getVerkaeufer().getAusweisNr());
      statement.setLong(4, objectToInsert.getWare().getId());
      statement.setString(5, objectToInsert.getZahlungsModalitaeten());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#create(IKaufvertrag objectToInsert).");
    }
    return objectToInsert;
  }

  @Override
  public void create(IKaufvertrag objectToInsert)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "INSERT into kaufvertrag (id, kaufer, verkaufer, ware, zahlungsmittel) values(?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, 0);
      statement.setString(2, objectToInsert.getKaeufer().getAusweisNr());
      statement.setString(3, objectToInsert.getVerkaeufer().getAusweisNr());
      statement.setLong(4, objectToInsert.getWare().getId());
      statement.setString(5, objectToInsert.getZahlungsModalitaeten());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#create(IKaufvertrag objectToInsert).");
    }
  }

  @Override
  public IKaufvertrag read(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT kaufer, verkaufer, ware, zahlungsmittel from kaufvertrag WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      IVertragspartner kaufer = new VertragspartnerDaoSqlite().read(result.getString("kaufer"));
      IVertragspartner verkaufer = new VertragspartnerDaoSqlite().read(result.getString("verkaufer"));
      IWare ware = new WareDaoSqlite().read(result.getLong("ware"));
      String zahlungsmittel = result.getString("zahlungsmittel");
      Kaufvertrag kaufvertrag = new Kaufvertrag(kaufer, verkaufer, ware);
      kaufvertrag.setZahlungsModalitaeten(zahlungsmittel);
      return kaufvertrag;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#read(Long id).");
    }
    return null;
  }

  @Override
  public List<IKaufvertrag> readAll()
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "SELECT kaufer, verkaufer, ware, zahlungsmittel from kaufvertrag";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IKaufvertrag> list = new ArrayList<>();
      while (result.next())
      {
        IVertragspartner kaufer = new VertragspartnerDaoSqlite().read(result.getString("kaufer"));
        IVertragspartner verkaufer = new VertragspartnerDaoSqlite().read(result.getString("verkaufer"));
        IWare ware = new WareDaoSqlite().read(result.getLong("ware"));
        String zahlungsmittel = result.getString("zahlungsmittel");
        Kaufvertrag kaufvertrag = new Kaufvertrag(kaufer, verkaufer, ware);
        kaufvertrag.setZahlungsModalitaeten(zahlungsmittel);
        list.add(kaufvertrag);
      }
      return list;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#readAll().");
    }
    return null;
  }

  @Override
  public void update(IKaufvertrag objectToUpdate)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "UPDATE kaufvertrag SET kaufer = ?, verkaufer = ?, ware = ?, zahlungsmittel = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getKaeufer().getAusweisNr());
      statement.setString(2, objectToUpdate.getVerkaeufer().getAusweisNr());
      statement.setLong(3, objectToUpdate.getWare().getId());
      statement.setString(4, objectToUpdate.getZahlungsModalitaeten());
      statement.setLong(5, 0);
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#update(IKaufvertrag objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Connection connection = ConnectionManager.getNewConnection();
      String query = "DELETE from kaufvertrag WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoSqlite#delete(Long id).");
    }
  }
}
