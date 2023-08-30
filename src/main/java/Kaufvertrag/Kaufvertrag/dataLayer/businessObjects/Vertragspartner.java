package Kaufvertrag.Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;

public class Vertragspartner implements IVertragspartner
{
  private String ausweisNr;
  private String vorname;
  private String nachname;
  private IAdresse adresse;

  public Vertragspartner(String vorname, String nachname)
  {
    this.vorname = vorname;
    this.nachname = nachname;
    this.ausweisNr = "";
    this.adresse = null;
  }


  @Override
  public String getAusweisNr()
  {
    return this.ausweisNr;
  }

  @Override
  public void setAusweisNr(String ausweisNr)
  {
    this.ausweisNr = ausweisNr;
  }

  @Override
  public String getVorname()
  {
    return this.vorname;
  }

  @Override
  public void setVorname(String vorname)
  {
    this.vorname = vorname;
  }

  @Override
  public String getNachname()
  {
    return this.nachname;
  }

  @Override
  public void setNachname(String nachname)
  {
    this.nachname = nachname;
  }

  @Override
  public IAdresse getAdresse()
  {
    return this.adresse;
  }

  @Override
  public void setAdresse(IAdresse adresse)
  {
    this.adresse = adresse;
  }

  @Override
  public String toString()
  {
    return "Vertragspartner:\n"
      + "\tAusweisNr:\n"
      + "\t\t" + ausweisNr + "\n"
      + "\tVorname:\n"
      + "\t\t" + vorname + "\n"
      + "\tNachname:\n"
      + "\t\t" + nachname + "\n"
      + "\tAdresse:\n"
      + "\t\t" + adresse + "\n";
  }
}
