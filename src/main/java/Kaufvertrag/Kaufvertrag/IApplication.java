package Kaufvertrag.Kaufvertrag;

public interface IApplication
{
  void startApplication();

  String getPersistenceType();

  Long getID();

  Long getForeignID(String whatToGetTheIDFor, Class classForTheID);

  String getString(String whatToGetTheStringFor, Class classForTheString);

  String getYesOrNo(String message);

  int getInt(String whatToGetTheIntFor, Class classForTheInt);

  double getDouble(String whatToGetTheDoubleFor, Class classForTheDouble);
}
