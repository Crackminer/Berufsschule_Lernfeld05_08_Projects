package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.AdresseDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.KaufvertragDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.AdresseDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.KaufvertragDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.VertragspartnerDaoXml;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.WareDaoXml;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication implements IApplication
{
  private final Scanner sc = new Scanner(System.in);

  public ConsoleApplication()
  {

  }

  @Override
  public void startApplication()
  {
    System.out.println("The program succesfully started.");
    DataLayerManager dataLayerManager = DataLayerManager.getInstance();
    IDataLayer dataLayer = dataLayerManager.getDataLayer();

    while(true)
    {
      System.out.println("Which Data Access Object do you want to manipulate?\nValid inputs are \"kaufvertrag\", \"ware\", \"vertragspartner\" or \"adresse\".\nIf you want to quit the program please input \"q\".");
      IDao<?, ?> dataAccessObject = null;
      while(dataAccessObject == null)
      {
        String inputDao = sc.next().trim().toLowerCase();
        switch (inputDao)
        {
          case "kaufvertrag" -> dataAccessObject = dataLayer.getDaoKaufvertrag();
          case "ware" -> dataAccessObject = dataLayer.getDaoWare();
          case "vertragspartner" -> dataAccessObject = dataLayer.getDaoVertragspartner();
          case "adresse" -> dataAccessObject = dataLayer.getDaoAdresse();
          case "q" ->
          {
            return;
          }
        }
      }

      System.out.println("What do you intend to do with your data access object?\nValid inputs are \"create\", \"read\", \"readall\", \"update\", \"delete\".\nIf you want to quit the program please input \"q\".");
      String inputMethod = sc.next().trim().toLowerCase();
      switch (inputMethod)
      {
        case "create" -> {
          Object object = dataAccessObject.create();
          System.out.println("you succesfully created the object:\n" + object);
        }
        case "read" -> {
          if (dataAccessObject.getClass().equals(VertragspartnerDaoSqlite.class) || dataAccessObject.getClass().equals(VertragspartnerDaoXml.class))
          {
            System.out.println(((IDao<?, String>)dataAccessObject).read(getString("Vertragspartner Ausweisnummer", getClass())));
          }
          else
          {
            System.out.println(((IDao<?, Long>)dataAccessObject).read(getID()));
          }
        }
        case "readall" -> {
          List objectList = dataAccessObject.readAll();
          for (Object object : objectList)
          {
            System.out.println(object);
          }
        }
        case "update" -> {
          ((IDao<Object, ?>)dataAccessObject).update(((IDao<Object, ?>)dataAccessObject).create());
        }
        case "delete" -> {
          if (dataAccessObject.getClass().equals(VertragspartnerDaoSqlite.class) || dataAccessObject.getClass().equals(VertragspartnerDaoXml.class))
          {
            ((IDao<?, String>)dataAccessObject).delete(getString("Vertragspartner Ausweisnummer", getClass()));
          }
          else
          {
            ((IDao<?, Long>)dataAccessObject).delete(getID());
          }
        }
        case "q" ->
        {
          return;
        }
      }
    }
  }

  @Override
  public String getPersistenceType()
  {
    String type = "";
    System.out.println("Which persistence-type do you want? Valid inputs are \"sqlite\" or \"xml\".");
    while (true)
    {
      type = sc.next().trim().toLowerCase();
      if ("sqlite".equals(type) || "xml".equals(type))
      {
        break;
      }
      System.out.println("Your input was wrong. Please make sure you use the correct options.");
    }
    return type;
  }

  @Override
  public Long getID()
  {
    System.out.println("Please input the ID of the object.");
    while (true)
    {
      String idString = sc.next().trim();
      try
      {
        //This currently does not check the id for validity (if id exists in context)
        return Long.parseLong(idString);
      }
      catch (NumberFormatException ex)
      {
        System.out.println("Your input was wrong. Please make sure you input only numbers here.");
      }
    }
  }

  @Override
  public Long getForeignID(String whatToGetTheIDFor, Class<?> classForTheID)
  {
    System.out.println("Please input the ID of " + whatToGetTheIDFor + " for the class " + classForTheID.getName() + ".");
    while (true)
    {
      String idString = sc.next().trim();
      try
      {
        //This currently does not check the id for validity (if id exists in context)
        return Long.parseLong(idString);
      }
      catch (NumberFormatException ex)
      {
        System.out.println("Your input was wrong. Please make sure you input only numbers here.");
      }
    }
  }

  @Override
  public String getString(String whatToGetTheStringFor, Class<?> classForTheString)
  {
    String string = "";
    System.out.println("Please input your String for the " + whatToGetTheStringFor + " for the class " + classForTheString.getName() + ".");
    string = sc.next();
    return string;
  }

  @Override
  public String getYesOrNo(String message)
  {
    String string = "";
    System.out.println(message);
    string = sc.next();
    return string;
  }

  @Override
  public int getInt(String whatToGetTheIntFor, Class<?> classForTheInt)
  {
    System.out.println("Please input your Integer for the " + whatToGetTheIntFor + " for the class " + classForTheInt.getName() + ".");
    while (true)
    {
      String integerString = sc.next().trim();
      try
      {
        return Integer.parseInt(integerString);
      }
      catch (NumberFormatException ex)
      {
        System.out.println("Your input was wrong. Please make sure you input only numbers here.");
      }
    }
  }

  @Override
  public double getDouble(String whatToGetTheDoubleFor, Class<?> classForTheDouble)
  {
    System.out.println("Please input your Integer for the " + whatToGetTheDoubleFor + " for the class " + classForTheDouble.getName() + ".");
    while (true)
    {
      String doubleString = sc.next().trim().replaceAll(",", ".");
      try
      {
        return Double.parseDouble(doubleString);
      }
      catch (NumberFormatException ex)
      {
        System.out.println("Your input was wrong. Please make sure you input only valid numbers here.");
      }
    }
  }
}
