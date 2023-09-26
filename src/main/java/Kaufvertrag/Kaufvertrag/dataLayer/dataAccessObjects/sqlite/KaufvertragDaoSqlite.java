package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
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
    //verkaufer
    IVertragspartner verkaufer = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Verkaufer or do you want to supply an Id of an existing Verkaufer? Y for own, N for existing.").trim()))
    {
      verkaufer = new Vertragspartner("", "");
      verkaufer.setNachname(Programm.getInputMethod().getString("Verkaufer Nachname", getClass()));
      verkaufer.setVorname(Programm.getInputMethod().getString("Verkaufer Vorname", getClass()));
      verkaufer.setAusweisNr(Programm.getInputMethod().getString("Verkaufer Ausweisnummer", getClass()));

      IAdresse adresse = null;
      if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Verkaufer or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
      {
        adresse = new Adresse("", "", "", "");
        adresse.setStrasse(Programm.getInputMethod().getString("Verkaufer Adresse Strasse", getClass()));
        adresse.setHausNr(Programm.getInputMethod().getString("Verkaufer Adresse Hausnummer", getClass()));
        adresse.setPlz(Programm.getInputMethod().getString("Verkaufer Adresse PLZ", getClass()));
        adresse.setOrt(Programm.getInputMethod().getString("Verkaufer Adresse Ort", getClass()));

        ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

        new AdresseDaoSqlite().create(adresse);
      }
      else
      {
        verkaufer.setAdresse(new AdresseDaoSqlite().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
      }
      new VertragspartnerDaoSqlite().create(verkaufer);
    }
    else
    {
      verkaufer = new VertragspartnerDaoSqlite().read(Programm.getInputMethod().getForeignID("Verkaufer", getClass()).toString());
    }
    objectToInsert.setVerkaeufer(verkaufer);
    //kaufer
    IVertragspartner kaufer = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Kaufer or do you want to supply an Id of an existing Kaufer? Y for own, N for existing.").trim()))
    {
      kaufer = new Vertragspartner("", "");
      kaufer.setNachname(Programm.getInputMethod().getString("Kaufer Nachname", getClass()));
      kaufer.setVorname(Programm.getInputMethod().getString("Kaufer Vorname", getClass()));
      kaufer.setAusweisNr(Programm.getInputMethod().getString("Kaufer Ausweisnummer", getClass()));

      IAdresse adresse = null;
      if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Kaufer or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
      {
        adresse = new Adresse("", "", "", "");
        adresse.setStrasse(Programm.getInputMethod().getString("Kaufer Adresse Strasse", getClass()));
        adresse.setHausNr(Programm.getInputMethod().getString("Kaufer Adresse Hausnummer", getClass()));
        adresse.setPlz(Programm.getInputMethod().getString("Kaufer Adresse PLZ", getClass()));
        adresse.setOrt(Programm.getInputMethod().getString("Kaufer Adresse Ort", getClass()));

        ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

        new AdresseDaoSqlite().create(adresse);
      }
      else
      {
        kaufer.setAdresse(new AdresseDaoSqlite().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
      }
      new VertragspartnerDaoSqlite().create(kaufer);
    }
    else
    {
      kaufer = new VertragspartnerDaoSqlite().read(Programm.getInputMethod().getForeignID("Kaufer", getClass()).toString());
    }
    objectToInsert.setKaeufer(kaufer);
    //ware
    IWare ware = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Ware or do you want to supply an Id of an existing Ware? Y for own, N for existing.").trim()))
    {
      ware = new Ware("", 0.0);
      ware.setBezeichnung(Programm.getInputMethod().getString("Ware Bezeichnung", getClass()));
      ware.setBeschreibung(Programm.getInputMethod().getString("Ware Beschreibung", getClass()));
      ware.setPreis(Programm.getInputMethod().getDouble("Ware Preis", getClass()));

      ((Ware)ware).setId(Programm.getInputMethod().getForeignID("Ware", getClass()));

      new WareDaoSqlite().create(ware);
    }
    else
    {
      ware = new WareDaoSqlite().read(Programm.getInputMethod().getForeignID("Ware", getClass()));
    }
    objectToInsert.setWare(ware);
    objectToInsert.setZahlungsModalitaeten(Programm.getInputMethod().getString("Zahlungsmittel", getClass()));
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
      statement.setLong(1, Programm.getInputMethod().getID());
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
      statement.setLong(5, Programm.getInputMethod().getID());
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
