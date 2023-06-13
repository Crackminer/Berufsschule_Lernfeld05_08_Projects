package Kaufvertrag.Kaufvertrag.businessObjects;

/**
 * @author Patrick Schewe
 */
public interface IKaufvertrag
{
  IVertragspartner getVerkaeufer();
  void setVerkaeufer(IVertragspartner vertragsPartner);
  IVertragspartner getKaeufer();
  void setKaeufer(IVertragspartner kaeufer);
  IWare getWare();
  void setWare(IWare ware);
  String getZahlungsModalitaeten();
  void setZahlungsModalitaeten(String zahlungsModalitaeten);
}
