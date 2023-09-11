package Kaufvertrag.Kaufvertrag.businessObjects;

public interface IAdresse
{
  long getID();

  String getStrasse();

  void setStrasse(String strasse);

  String getHausNr();

  void setHausNr(String hausNr);

  String getPlz();

  void setPlz(String plz);

  String getOrt();

  void setOrt(String ort);

  @Override
  String toString();
}
