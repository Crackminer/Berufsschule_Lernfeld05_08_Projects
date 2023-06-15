package Kaufvertrag.Kaufvertrag;

public class ConsoleApplication implements IApplication
{
  @Override
  public void startApplication()
  {
    System.out.println("I got started as a Console Application :)");
  }

  @Override
  public String getPersistenceType()
  {
    return null;
  }
}
