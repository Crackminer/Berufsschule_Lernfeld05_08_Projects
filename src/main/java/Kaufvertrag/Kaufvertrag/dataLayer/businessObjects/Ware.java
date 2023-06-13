package Kaufvertrag.Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IWare;

import java.util.List;

/*
* Chris Thomas @Author
* */


public class Ware implements IWare
{
    private long id;
    private String bezeichnung;
    private String beschreibung;
    private double preis;
    private List<String> besonderheiten;
    private List<String> maengel;

    @Override
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public String getBezeichnung()
    {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung)
    {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String getBeschreibung()
    {
        return beschreibung;
    }

    @Override
    public void setBeschreibung(String beschreibung)
    {
        this.beschreibung = beschreibung;
    }

    @Override
    public double getPreis()
    {
        return preis;
    }

    @Override
    public void setPreis(double preis)
    {
        this.preis = preis;
    }

    @Override
    public List<String> getBesonderheiten()
    {
        return besonderheiten;
    }

    @Override
    public List<String> getMaengel()
    {
        return maengel;
    }

    @Override
    public String toString()
    {
        StringBuilder s =
          new StringBuilder("Ware:\n"
            + "\tID:"
            + "\t\t" + id + "\n"
            + "\tBezeichnung:"
            + "\t\t" + bezeichnung + "\n"
            + "\tBeschreibung:"
            + "\t\t" + beschreibung + "\n"
            + "\tPreis:"
            + "\t\t" + preis + "\n"
            + "\tBesonderheiten:\n");
        for (String besonderheit : besonderheiten)
        {
            s.append("\t\t").append(besonderheit).append("\n");
        }
        s.append("\tMängel:\n");
        for (String mangel : maengel)
        {
            s.append("\t\t").append(mangel).append("\n");
        }
        return s.toString();
    }
}
