package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
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


public class WareDaoXml implements IDao<IWare, Long>
{
  private static final String FILEPATH = "src/main/resources/xml/Ware.xml";

  @Override
  public IWare create()
  {
    Ware objectToInsert = new Ware("", 0.0);
    objectToInsert.setBezeichnung(Programm.getInputMethod().getString("Bezeichnung", getClass()));
    objectToInsert.setBeschreibung(Programm.getInputMethod().getString("Beschreibung", getClass()));
    objectToInsert.setPreis(Programm.getInputMethod().getDouble("Preis", getClass()));

    objectToInsert.setId(Programm.getInputMethod().getID());
    return objectToInsert;
  }

  @Override
  public void create(IWare objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("ware").item(0);
      Element nodeID = doc.createElement("id");
      root.appendChild(nodeID);
      nodeID.setAttribute("id", String.valueOf(objectToInsert.getId()));
      nodeID.setIdAttribute("id", true);

      Element bezeichnung = doc.createElement("bezeichnung");
      bezeichnung.setTextContent(objectToInsert.getBezeichnung());
      nodeID.appendChild(bezeichnung);

      Element beschreibung = doc.createElement("beschreibung");
      beschreibung.setTextContent(objectToInsert.getBeschreibung());
      nodeID.appendChild(beschreibung);

      Element preis = doc.createElement("preis");
      preis.setTextContent(String.valueOf(objectToInsert.getPreis()));
      nodeID.appendChild(preis);

      Element besonderheiten = doc.createElement("besonderheiten");
      for (String besonderheitString : objectToInsert.getBesonderheiten())
      {
        Element besonderheit = doc.createElement("besonderheit");
        besonderheit.setTextContent(besonderheitString);
        besonderheiten.appendChild(besonderheit);
      }
      nodeID.appendChild(besonderheiten);

      Element maengel = doc.createElement("maengel");
      for (String mangelString : objectToInsert.getMaengel())
      {
        Element mangel = doc.createElement("mangel");
        mangel.setTextContent(mangelString);
        maengel.appendChild(mangel);
      }
      nodeID.appendChild(maengel);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException e)
    {
      System.out.println("There was an unexpected exception in WareDaoXml#create(IWare objectToInsert).");
    }

  }

  @Override
  public IWare read(Long id)
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("ware").item(0);
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
    Ware ware = new Ware(nodeID.getChildNodes().item(1).getTextContent(), Double.parseDouble(nodeID.getChildNodes().item(5).getTextContent()));
    ware.setBeschreibung(nodeID.getChildNodes().item(3).getTextContent());
    ware.setId(id);
    return ware;
  }

  @Override
  public List<IWare> readAll()
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("ware").item(0);
    List<IWare> wareListe = new ArrayList<>();
    NodeList waren = root.getChildNodes();
    for (int i = 0; i < waren.getLength(); i++)
    {
      Node nodeID = waren.item(i);
      if (nodeID.hasAttributes())
      {
        Ware ware = new Ware(nodeID.getChildNodes().item(1).getTextContent(), Double.parseDouble(nodeID.getChildNodes().item(5).getTextContent()));
        ware.setBeschreibung(nodeID.getChildNodes().item(3).getTextContent());
        ware.setId(Long.parseLong(waren.item(i).getAttributes().getNamedItem("id").getTextContent()));
        wareListe.add(ware);
      }
    }
    return wareListe;
  }

  @Override
  public void update(IWare objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("ware").item(0);
      NodeList nodes = root.getChildNodes();
      Node nodeID = null;
      for(int i = 0; i < nodes.getLength(); i++)
      {
        if (nodes.item(i).hasAttributes())
        {
          if (nodes.item(i).getAttributes().getNamedItem("id") != null)
          {
            if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(String.valueOf(objectToUpdate.getId())))
            {
              nodeID = nodes.item(i);
            }
          }
        }
      }

      Node bezeichnung = nodeID.getChildNodes().item(1);
      bezeichnung.setTextContent(objectToUpdate.getBezeichnung());

      Node beschreibung = nodeID.getChildNodes().item(3);
      beschreibung.setTextContent(objectToUpdate.getBeschreibung());

      Node preis = nodeID.getChildNodes().item(5);
      preis.setTextContent(String.valueOf(objectToUpdate.getPreis()));

      Node besonderheiten = nodeID.getChildNodes().item(7);
      while (besonderheiten.getChildNodes().getLength() != 0)
      {
        besonderheiten.removeChild(besonderheiten.getChildNodes().item(0));
      }

      for (String besonderheitString : objectToUpdate.getBesonderheiten())
      {
        Element besonderheit = doc.createElement("besonderheit");
        besonderheit.setTextContent(besonderheitString);
        besonderheiten.appendChild(besonderheit);
      }

      Node maengel = nodeID.getChildNodes().item(9);
      while (maengel.getChildNodes().getLength() != 0)
      {
        maengel.removeChild(maengel.getChildNodes().item(0));
      }
      for (String mangelString : objectToUpdate.getMaengel())
      {
        Element mangel = doc.createElement("mangel");
        mangel.setTextContent(mangelString);
        maengel.appendChild(mangel);
      }

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException e)
    {
      System.out.println("There was an unexpected exception in WareDaoXml#update(IWare objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("ware").item(0);
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
    catch (IOException e)
    {
      System.out.println("There was an unexpected exception in WareDaoXml#delete(Long id).");
    }
  }
}
