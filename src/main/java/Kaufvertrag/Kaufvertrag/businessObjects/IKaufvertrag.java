package Kaufvertrag.Kaufvertrag.businessObjects;

/**
 * @author Patrick Schewe
 */
public interface IKaufvertrag
{
  IVertragsPartner getVerkaeufer();
  void setVerkaeufer(IVertragsPartner vertragsPartner);
  IVertragsPartner getKaeufer();
  void setKaeufer(IVertragsPartner kaeufer);
  IWare getWare();
  void setWare(IWare ware);
  String getZahlungsModalitaeten();
  void setZahlungsModalitaeten(String zahlungsModalitaeten);
}
