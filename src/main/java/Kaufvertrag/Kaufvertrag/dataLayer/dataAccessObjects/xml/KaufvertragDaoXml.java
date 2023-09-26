package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.AdresseDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.XMLManager.getDocument;
import static Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.XMLManager.writeToXML;

public class KaufvertragDaoXml implements IDao<IKaufvertrag, Long>
{
  private static final String FILEPATH = "src/main/resources/xml/Kaufvertrag.xml";

  @Override
  public IKaufvertrag create()
  {
    IKaufvertrag objectToInsert = new Kaufvertrag(null, null, null);
    //verkaufer
    IVertragspartner verkaufer = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Verkaufer or do you want to supply an Id of an existing Verkaufer? Y for own, N for existing.").trim()))
    {
      verkaufer = new Vertragspartner("", "");
      verkaufer.setNachname(Programm.getInputMethod().getString("Verkaufer Nachname", getClass()));
      verkaufer.setVorname(Programm.getInputMethod().getString("Verkaufer Vorname", getClass()));
      verkaufer.setAusweisNr(Programm.getInputMethod().getString("Verkaufer Ausweisnummer", getClass()));

      IAdresse adresse = null;
      if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Verkaufer or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
      {
        adresse = new Adresse("", "", "", "");
        adresse.setStrasse(Programm.getInputMethod().getString("Verkaufer Adresse Strasse", getClass()));
        adresse.setHausNr(Programm.getInputMethod().getString("Verkaufer Adresse Hausnummer", getClass()));
        adresse.setPlz(Programm.getInputMethod().getString("Verkaufer Adresse PLZ", getClass()));
        adresse.setOrt(Programm.getInputMethod().getString("Verkaufer Adresse Ort", getClass()));

        ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

        new AdresseDaoXml().create(adresse);
      }
      else
      {
        verkaufer.setAdresse(new AdresseDaoXml().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
      }
      new VertragspartnerDaoXml().create(verkaufer);
    }
    else
    {
      verkaufer = new VertragspartnerDaoXml().read(Programm.getInputMethod().getForeignID("Verkaufer", getClass()).toString());
    }
    objectToInsert.setVerkaeufer(verkaufer);
    //kaufer
    IVertragspartner kaufer = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Kaufer or do you want to supply an Id of an existing Kaufer? Y for own, N for existing.").trim()))
    {
      kaufer = new Vertragspartner("", "");
      kaufer.setNachname(Programm.getInputMethod().getString("Kaufer Nachname", getClass()));
      kaufer.setVorname(Programm.getInputMethod().getString("Kaufer Vorname", getClass()));
      kaufer.setAusweisNr(Programm.getInputMethod().getString("Kaufer Ausweisnummer", getClass()));

      IAdresse adresse = null;
      if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Kaufer or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
      {
        adresse = new Adresse("", "", "", "");
        adresse.setStrasse(Programm.getInputMethod().getString("Kaufer Adresse Strasse", getClass()));
        adresse.setHausNr(Programm.getInputMethod().getString("Kaufer Adresse Hausnummer", getClass()));
        adresse.setPlz(Programm.getInputMethod().getString("Kaufer Adresse PLZ", getClass()));
        adresse.setOrt(Programm.getInputMethod().getString("Kaufer Adresse Ort", getClass()));

        ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

        new AdresseDaoXml().create(adresse);
      }
      else
      {
        kaufer.setAdresse(new AdresseDaoXml().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
      }
      new VertragspartnerDaoXml().create(kaufer);
    }
    else
    {
      kaufer = new VertragspartnerDaoXml().read(Programm.getInputMethod().getForeignID("Kaufer", getClass()).toString());
    }
    objectToInsert.setKaeufer(kaufer);
    //ware
    IWare ware = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Ware or do you want to supply an Id of an existing Ware? Y for own, N for existing.").trim()))
    {
      ware = new Ware("", 0.0);
      ware.setBezeichnung(Programm.getInputMethod().getString("Ware Bezeichnung", getClass()));
      ware.setBeschreibung(Programm.getInputMethod().getString("Ware Beschreibung", getClass()));
      ware.setPreis(Programm.getInputMethod().getDouble("Ware Preis", getClass()));

      ((Ware)ware).setId(Programm.getInputMethod().getForeignID("Ware", getClass()));

      new WareDaoXml().create(ware);
    }
    else
    {
      ware = new WareDaoXml().read(Programm.getInputMethod().getForeignID("Ware", getClass()));
    }
    objectToInsert.setWare(ware);
    objectToInsert.setZahlungsModalitaeten(Programm.getInputMethod().getString("Zahlungsmittel", getClass()));
    return objectToInsert;
  }

  @Override
  public void create(IKaufvertrag objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("kaufvertrag");
      Element nodeID = doc.createElement("id");
      root.appendChild(nodeID);
      nodeID.setAttribute("id", String.valueOf(Programm.getInputMethod().getID()));
      nodeID.setIdAttribute("id", true);

      Element verkaufer = doc.createElement("verkaufer");
      verkaufer.setTextContent(objectToInsert.getVerkaeufer().getAusweisNr());
      nodeID.appendChild(verkaufer);

      Element kaufer = doc.createElement("kaufer");
      kaufer.setTextContent(objectToInsert.getKaeufer().getAusweisNr());
      nodeID.appendChild(kaufer);

      Element ware = doc.createElement("ware");
      ware.setTextContent(String.valueOf(objectToInsert.getWare().getId()));
      nodeID.appendChild(ware);

      Element bezahlmethode = doc.createElement("bezahlmethode");
      bezahlmethode.setTextContent(objectToInsert.getZahlungsModalitaeten());
      nodeID.appendChild(bezahlmethode);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoXml#create(IKaufvertrag objectToInsert).");
    }
  }

  @Override
  public IKaufvertrag read(Long id)
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("adresse");
    NodeList nodes = root.getChildNodes();
    Node nodeID = null;
    for(int i = 0; i < nodes.getLength(); i++)
    {
      if (nodes.item(i).hasAttributes())
      {
        if (nodes.item(i).getAttributes().getNamedItem("id") != null)
        {
          if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(id.toString()))
          {
            nodeID = nodes.item(i);
          }
        }
      }
    }
    Kaufvertrag kaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(nodeID.getChildNodes().item(1).getTextContent()), new VertragspartnerDaoXml().read(nodeID.getChildNodes().item(3).getTextContent()), new WareDaoXml().read(Long.parseLong(nodeID.getChildNodes().item(5).getTextContent())));
    kaufvertrag.setZahlungsModalitaeten(nodeID.getChildNodes().item(7).getTextContent());
    return kaufvertrag;
  }

  @Override
  public List<IKaufvertrag> readAll()
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("adresse");
    List<IKaufvertrag> kaufvertragListe = new ArrayList<>();
    NodeList kaufvertraege = root.getElementsByTagName("id");
    for (int i = 0; i < kaufvertraege.getLength(); i++)
    {
      Node nodeID = kaufvertraege.item(i);
      if (nodeID.hasAttributes())
      {
        Kaufvertrag kaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(nodeID.getChildNodes().item(1).getTextContent()), new VertragspartnerDaoXml().read(nodeID.getChildNodes().item(3).getTextContent()), new WareDaoXml().read(Long.parseLong(nodeID.getChildNodes().item(5).getTextContent())));
        kaufvertrag.setZahlungsModalitaeten(nodeID.getChildNodes().item(7).getTextContent());
        kaufvertragListe.add(kaufvertrag);
      }
    }
    return kaufvertragListe;
  }

  @Override
  public void update(IKaufvertrag objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("kaufvertrag");
      NodeList nodes = root.getChildNodes();
      Node nodeID = null;
      for(int i = 0; i < nodes.getLength(); i++)
      {
        if (nodes.item(i).hasAttributes())
        {
          if (nodes.item(i).getAttributes().getNamedItem("id") != null)
          {
            if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(String.valueOf(Programm.getInputMethod().getID())))
            {
              nodeID = nodes.item(i);
            }
          }
        }
      }

      Node verkaufer = nodeID.getChildNodes().item(1);
      verkaufer.setTextContent(objectToUpdate.getVerkaeufer().getAusweisNr());

      Node kaufer = nodeID.getChildNodes().item(3);
      kaufer.setTextContent(objectToUpdate.getKaeufer().getAusweisNr());

      Node ware = nodeID.getChildNodes().item(5);
      ware.setTextContent(String.valueOf(objectToUpdate.getWare().getId()));

      Node bezahlmethode = nodeID.getChildNodes().item(7);
      bezahlmethode.setTextContent(objectToUpdate.getZahlungsModalitaeten());

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoXml#update(IKaufvertrag objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("kaufvertrag");
      NodeList nodes = root.getChildNodes();
      Node nodeID = null;
      for(int i = 0; i < nodes.getLength(); i++)
      {
        if (nodes.item(i).hasAttributes())
        {
          if (nodes.item(i).getAttributes().getNamedItem("id") != null)
          {
            if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(id.toString()))
            {
              nodeID = nodes.item(i);
            }
          }
        }
      }
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoXml#delete(Long id).");
    }
  }
}
