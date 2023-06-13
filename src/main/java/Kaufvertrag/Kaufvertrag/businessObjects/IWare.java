package Kaufvertrag.Kaufvertrag.businessObjects;

import java.util.List;

/*
* Chris Thomas @Author
* */

public interface IWare
{
    long getId();
    String getBezeichnung();
    void setBezeichnung(String bezeichnung);
    String getBeschreibung();
    void setBeschreibung(String beschreibung);
    double getPreis();
    void setPreis(double preis);
    List<String> getBesonderheiten();
    List<String> getMaengel();
}
