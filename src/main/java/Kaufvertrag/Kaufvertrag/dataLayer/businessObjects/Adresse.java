package Kaufvertrag.Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;

public class Adresse implements IAdresse
{
  private long id;
  private String strasse;
  private String hausNr;
  private String plz;
  private String ort;

  public Adresse(String strasse, String hausNr, String plz, String ort)
  {
    this.strasse = strasse;
    this.hausNr = hausNr;
    this.plz = plz;
    this.ort = ort;
  }

  public void setID(long id)
  {
    this.id = id;
  }

  @Override
  public long getID()
  {
    return id;
  }

  public String getStrasse()
  {
    return strasse;
  }

  public void setStrasse(String strasse)
  {
    this.strasse = strasse;
  }


  public String getHausNr()
  {
    return hausNr;
  }

  public void setHausNr(String hausNr)
  {
    this.hausNr = hausNr;
  }

  public String getPlz()
  {
    return plz;
  }

  public void setPlz(String plz)
  {
    this.plz = plz;
  }


  public String getOrt()
  {
    return ort;
  }

  public void setOrt(String ort)
  {
    this.ort = ort;
  }

  @Override
  public String toString()
  {
    return
      "Adresse:\n"
        + "\tStraße:\n"
        + "\t\t" + strasse + "\n"
        + "\tHausnr:\n"
        + "\t\t" + hausNr + "\n"
        + "\tPLZ:\n"
        + "\t\t" + plz + "\n"
        + "\tOrt:\n"
        + "\t\t" + ort + "\n";
  }
}
