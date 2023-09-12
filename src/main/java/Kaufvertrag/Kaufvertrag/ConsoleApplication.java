package Kaufvertrag.Kaufvertrag;

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
    System.out.println("I got started as a Console Application :)");

    //This method will need all the dialogue between console and user and mitigate from user input to create methods, update methods, read methods and delete methods of the current persistence type
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
    System.out.println("message");
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
