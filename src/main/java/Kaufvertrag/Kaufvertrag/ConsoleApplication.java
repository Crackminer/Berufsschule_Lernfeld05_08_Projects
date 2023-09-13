package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.VertragspartnerDaoXml;

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
      String inputDao = sc.next().trim().toLowerCase();
      switch (inputDao)
      {
        case "kaufvertrag" -> doKaufvertrag(dataLayer.getDaoKaufvertrag());
        case "ware" -> doWare(dataLayer.getDaoWare());
        case "vertragspartner" -> doVertragspartner(dataLayer.getDaoVertragspartner());
        case "adresse" -> doAdresse(dataLayer.getDaoAdresse());
        case "q" ->
        {
          return;
        }
      }
    }
  }

  private void doKaufvertrag(IDao<IKaufvertrag, Long> dataAccessObject)
  {
    System.out.println("What do you intend to do with your data access object?\nValid inputs are \"create\", \"read\", \"readall\", \"update\", \"delete\".\nIf you want to quit the program please input \"q\".");
    String inputMethod = sc.next().trim().toLowerCase();
    switch (inputMethod)
    {
      case "create" -> {
        IKaufvertrag object = dataAccessObject.create();
        System.out.println("you succesfully created the object:\n" + object);
        dataAccessObject.create(object);
      }
      case "read" -> {
        System.out.println(dataAccessObject.read(getID()));
      }
      case "readall" -> {
        List<IKaufvertrag> objectList = dataAccessObject.readAll();
        for (IKaufvertrag object : objectList)
        {
          System.out.println(object);
        }
      }
      case "update" -> {
        dataAccessObject.update(dataAccessObject.create());
      }
      case "delete" -> {
        dataAccessObject.delete(getID());
      }
      case "q" ->
      {
        return;
      }
    }
  }

  private void doVertragspartner(IDao<IVertragspartner, String> dataAccessObject)
  {
    System.out.println("What do you intend to do with your data access object?\nValid inputs are \"create\", \"read\", \"readall\", \"update\", \"delete\".\nIf you want to quit the program please input \"q\".");
    String inputMethod = sc.next().trim().toLowerCase();
    switch (inputMethod)
    {
      case "create" -> {
        IVertragspartner object = dataAccessObject.create();
        System.out.println("you succesfully created the object:\n" + object);
        dataAccessObject.create(object);
      }
      case "read" -> {
        System.out.println(dataAccessObject.read(getString("Verkaufspartner AusweisNr", getClass())));
      }
      case "readall" -> {
        List<IVertragspartner> objectList = dataAccessObject.readAll();
        for (IVertragspartner object : objectList)
        {
          System.out.println(object);
        }
      }
      case "update" -> {
        dataAccessObject.update(dataAccessObject.create());
      }
      case "delete" -> {
        dataAccessObject.delete(getString("Verkaufspartner AusweisNr", getClass()));
      }
      case "q" ->
      {
        return;
      }
    }
  }

  private void doAdresse(IDao<IAdresse, Long> dataAccessObject)
  {
    System.out.println("What do you intend to do with your data access object?\nValid inputs are \"create\", \"read\", \"readall\", \"update\", \"delete\".\nIf you want to quit the program please input \"q\".");
    String inputMethod = sc.next().trim().toLowerCase();
    switch (inputMethod)
    {
      case "create" -> {
        IAdresse object = dataAccessObject.create();
        System.out.println("you succesfully created the object:\n" + object);
        dataAccessObject.create(object);
      }
      case "read" -> {
        System.out.println(dataAccessObject.read(getID()));
      }
      case "readall" -> {
        List<IAdresse> objectList = dataAccessObject.readAll();
        System.out.println(objectList);
        for (IAdresse object : objectList)
        {
          System.out.println(object);
        }
      }
      case "update" -> {
        dataAccessObject.update(dataAccessObject.create());
      }
      case "delete" -> {
        dataAccessObject.delete(getID());
      }
      case "q" ->
      {
        return;
      }
    }
  }

  private void doWare(IDao<IWare, Long> dataAccessObject)
  {
    System.out.println("What do you intend to do with your data access object?\nValid inputs are \"create\", \"read\", \"readall\", \"update\", \"delete\".\nIf you want to quit the program please input \"q\".");
    String inputMethod = sc.next().trim().toLowerCase();
    switch (inputMethod)
    {
      case "create" -> {
        IWare object = dataAccessObject.create();
        System.out.println("you succesfully created the object:\n" + object);
        dataAccessObject.create(object);
      }
      case "read" -> {
        System.out.println(dataAccessObject.read(getID()));
      }
      case "readall" -> {
        List<IWare> objectList = dataAccessObject.readAll();
        for (IWare object : objectList)
        {
          System.out.println(object);
        }
      }
      case "update" -> {
        dataAccessObject.update(dataAccessObject.create());
      }
      case "delete" -> {
        dataAccessObject.delete(getID());
      }
      case "q" ->
      {
        return;
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
