package Kaufvertrag.Kaufvertrag;

public interface IApplication
{
  void startApplication();

  String getPersistenceType();

  Long getID();

  String getString(String whatToGetTheStringFor, Class classForTheString);

  int getInt(String whatToGetTheIntFor, Class classForTheInt);

  double getDouble(String whatToGetTheDoubleFor, Class classForTheDouble);
}
