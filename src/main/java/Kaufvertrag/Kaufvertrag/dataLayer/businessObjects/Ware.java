package Kaufvertrag.Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IWare;

import java.util.List;

/*
* Chris Thomas @Author
* */


public class Ware implements IWare
{
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getBezeichnung() {
        return null;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {

    }

    @Override
    public String getBeschreibung() {
        return null;
    }

    @Override
    public void setBeschreibung() {

    }

    @Override
    public double getPreis() {
        return 0;
    }

    @Override
    public void setPreis(double preis) {

    }

    @Override
    public List<String> getBesonderheiten() {
        return null;
    }

    @Override
    public List<String> getMaengel() {
        return null;
    }
}
