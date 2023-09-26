package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
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

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String>
{
  private static final String FILEPATH = "src/main/resources/xml/Vertragspartner.xml";

  @Override
  public IVertragspartner create()
  {
    IVertragspartner objectToInsert = new Vertragspartner("", "");
    objectToInsert.setNachname(Programm.getInputMethod().getString("Nachname", getClass()));
    objectToInsert.setVorname(Programm.getInputMethod().getString("Vorname", getClass()));
    objectToInsert.setAusweisNr(Programm.getInputMethod().getString("Ausweisnummer", getClass()));

    IAdresse adresse = null;
    if ("y".equalsIgnoreCase(Programm.getInputMethod().getYesOrNo("Do you want your own Adresse for the Vertragspartner or do you want to supply an Id of an existing Adresse? Y for own, N for existing.").trim()))
    {
      adresse = new Adresse("", "", "", "");
      adresse.setStrasse(Programm.getInputMethod().getString("Adresse Strasse", getClass()));
      adresse.setHausNr(Programm.getInputMethod().getString("Adresse Hausnummer", getClass()));
      adresse.setPlz(Programm.getInputMethod().getString("Adresse PLZ", getClass()));
      adresse.setOrt(Programm.getInputMethod().getString("Adresse Ort", getClass()));

      ((Adresse)adresse).setID(Programm.getInputMethod().getForeignID("Adresse", getClass()));

      new AdresseDaoXml().create(adresse);
    }
    else
    {
      objectToInsert.setAdresse(new AdresseDaoXml().read(Programm.getInputMethod().getForeignID("Adresse", getClass())));
    }
    return objectToInsert;
  }

  @Override
  public void create(IVertragspartner objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("vertragspartner").item(0);
      Element nodeID = doc.createElement("id");
      root.appendChild(nodeID);
      nodeID.setAttribute("id", objectToInsert.getAusweisNr());
      nodeID.setIdAttribute("id", true);

      Element vorname = doc.createElement("vorname");
      vorname.setTextContent(objectToInsert.getVorname());
      nodeID.appendChild(vorname);

      Element nachname = doc.createElement("nachname");
      nachname.setTextContent(objectToInsert.getNachname());
      nodeID.appendChild(nachname);

      Element addressId = doc.createElement("adresse");
      addressId.setTextContent(String.valueOf(objectToInsert.getAdresse().getID()));
      nodeID.appendChild(addressId);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create(IVertragspartner objectToInsert).");
    }
  }

  @Override
  public IVertragspartner read(String id)
  {
    AdresseDaoXml adresseDaoXml = new AdresseDaoXml();
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("vertragspartner").item(0);
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
    Vertragspartner vertragspartner = new Vertragspartner(nodeID.getChildNodes().item(1).getTextContent(), nodeID.getChildNodes().item(3).getTextContent());
    vertragspartner.setAusweisNr(id);
    vertragspartner.setAdresse(adresseDaoXml.read(Long.parseLong(nodeID.getChildNodes().item(5).getTextContent())));
    return vertragspartner;
  }

  @Override
  public List<IVertragspartner> readAll()
  {
    AdresseDaoXml adresseDaoXml = new AdresseDaoXml();
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("vertragspartner").item(0);
    List<IVertragspartner> vertragspartnerListe = new ArrayList<>();
    NodeList vertragspartnerL = root.getChildNodes();
    for (int i = 0; i < vertragspartnerL.getLength(); i++) {
      Node nodeID = vertragspartnerL.item(i);
      if (nodeID.hasAttributes())
      {
        Vertragspartner vertragspartner = new Vertragspartner(nodeID.getChildNodes().item(1).getTextContent(), nodeID.getChildNodes().item(3).getTextContent());
        vertragspartner.setAusweisNr(nodeID.getAttributes().getNamedItem("id").getTextContent());
        vertragspartner.setAdresse(adresseDaoXml.read(Long.parseLong(nodeID.getChildNodes().item(5).getTextContent())));
        vertragspartnerListe.add(vertragspartner);
      }
    }
    return vertragspartnerListe;
  }

  @Override
  public void update(IVertragspartner objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("vertragspartner").item(0);
      NodeList nodes = root.getChildNodes();
      Node nodeID = null;
      for(int i = 0; i < nodes.getLength(); i++)
      {
        if (nodes.item(i).hasAttributes())
        {
          if (nodes.item(i).getAttributes().getNamedItem("id") != null)
          {
            if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(objectToUpdate.getAusweisNr()))
            {
              nodeID = nodes.item(i);
            }
          }
        }
      }

      Node vorname = nodeID.getChildNodes().item(1);
      vorname.setTextContent(objectToUpdate.getVorname());

      Node nachname = nodeID.getChildNodes().item(3);
      nachname.setTextContent(objectToUpdate.getNachname());

      Node addressId = nodeID.getChildNodes().item(5);
      addressId.setTextContent(String.valueOf(objectToUpdate.getAdresse().getID()));

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#update(IVertragspartner objectToUpdate).");
    }
  }

  @Override
  public void delete(String id)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("vertragspartner").item(0);
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
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#delete(String id).");
    }
  }
}
