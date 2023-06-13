package Kaufvertrag.Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragsPartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;

public class Kaufvertrag implements IKaufvertrag
{
  private IVertragsPartner verkaeufer;
  private IVertragsPartner kaeufer;
  private IWare ware;
  private String zahlungsModalitaeten;

  public Kaufvertrag(IVertragsPartner kaeufer, IVertragsPartner verkaeufer, IWare ware)
  {
    this.kaeufer = kaeufer;
    this.verkaeufer = verkaeufer;
    this.ware = ware;
    this.zahlungsModalitaeten = "";
  }

  @Override
  public IVertragsPartner getVerkaeufer()
  {
    return verkaeufer;
  }

  @Override
  public void setVerkaeufer(IVertragsPartner vertragsPartner)
  {
    this.verkaeufer = verkaeufer;
  }

  @Override
  public IVertragsPartner getKaeufer()
  {
    return kaeufer;
  }

  @Override
  public void setKaeufer(IVertragsPartner kaeufer)
  {
    this.kaeufer = kaeufer;
  }

  @Override
  public IWare getWare()
  {
    return ware;
  }

  @Override
  public void setWare(IWare ware)
  {
    this.ware = ware;
  }

  @Override
  public String getZahlungsModalitaeten()
  {
    return zahlungsModalitaeten;
  }

  @Override
  public void setZahlungsModalitaeten(String zahlungsModalitaeten)
  {
    this.zahlungsModalitaeten = zahlungsModalitaeten;
  }

  @Override
  public String toString()
  {
    return  "Kaufvertrag:\n"
          + "\tKäufer:\n"
          + "\t\t" + kaeufer.toString() + "\n"
          + "\tVerkäufer:\n"
          + "\t\t" + verkaeufer.toString() + "\n"
          + "\tWare:\n"
          + "\t\t" + ware.toString() + "\n"
          + "\tZahlungsmodalität:\n"
          + "\t\t" + zahlungsModalitaeten + "\n";
  }
}
