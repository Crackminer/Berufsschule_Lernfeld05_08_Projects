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
  }

  @Override
  public String getPersistenceType()
  {
    String type = "";
    System.out.println("Which persistence-type do you want? Valid inputs are \"sqlite\" or \"xml\".");
    while(true)
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
}
