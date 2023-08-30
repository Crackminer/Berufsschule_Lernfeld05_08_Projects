package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

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
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Ware.xml";

  @Override
  public IWare create()
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;

      Element root = doc.getElementById("ware");
      Element nodeID = doc.createElement("id");
      nodeID.setIdAttribute("", true);

      Element bezeichnung = doc.createElement("bezeichnung");
      bezeichnung.setNodeValue("");
      nodeID.appendChild(bezeichnung);

      Element beschreibung = doc.createElement("beschreibung");
      beschreibung.setNodeValue("");
      nodeID.appendChild(beschreibung);

      Element preis = doc.createElement("preis");
      preis.setNodeValue("");
      nodeID.appendChild(preis);

      Element besonderheiten = doc.createElement("besonderheiten");
      // i < getBesonderheiten().size()
      for (int i = 0; i < 1; i++)
      {
        Element besonderheit = doc.createElement("besonderheit");
        besonderheit.setNodeValue("");
        besonderheiten.appendChild(besonderheit);
      }
      nodeID.appendChild(besonderheiten);

      Element maengel = doc.createElement("maengel");
      for (int i = 0; i < 1; i++)
      {
        Element mangel = doc.createElement("mangel");
        mangel.setNodeValue("");
        maengel.appendChild(mangel);
      }
      nodeID.appendChild(maengel);

      root.appendChild(nodeID);
      Ware ware = new Ware(bezeichnung.getNodeValue(), Double.parseDouble(preis.getNodeValue()));
      ware.setBeschreibung(beschreibung.getNodeValue());
      ware.setId(Long.parseLong(nodeID.getAttribute("id")));
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return ware;
    }
    catch (IOException e)
    {
      System.out.println("There was an unexpected exception in WareDaoXml#create().");
    }
    return null;
  }

  @Override
  public void create(IWare objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("ware");
      Element nodeID = doc.createElement("id");
      nodeID.setIdAttribute(String.valueOf(objectToInsert.getId()), true);

      Element bezeichnung = doc.createElement("bezeichnung");
      bezeichnung.setNodeValue("");
      nodeID.appendChild(bezeichnung);

      Element beschreibung = doc.createElement("beschreibung");
      beschreibung.setNodeValue("");
      nodeID.appendChild(beschreibung);

      Element preis = doc.createElement("preis");
      preis.setNodeValue("");
      nodeID.appendChild(preis);

      Element besonderheiten = doc.createElement("besonderheiten");
      for (String besonderheitString : objectToInsert.getBesonderheiten())
      {
        Element besonderheit = doc.createElement("besonderheit");
        besonderheit.setNodeValue(besonderheitString);
        besonderheiten.appendChild(besonderheit);
      }
      nodeID.appendChild(besonderheiten);

      Element maengel = doc.createElement("maengel");
      for (String mangelString : objectToInsert.getMaengel())
      {
        Element mangel = doc.createElement("mangel");
        mangel.setNodeValue(mangelString);
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
    Element root = doc.getElementById("ware");
    Element nodeID = root.getOwnerDocument().getElementById(id.toString());
    Ware ware = new Ware(nodeID.getElementsByTagName("bezeichnung").item(0).getNodeValue(), Double.parseDouble(nodeID.getElementsByTagName("preis").item(0).getNodeValue()));
    ware.setBeschreibung(nodeID.getElementsByTagName("beschreibung").item(0).getNodeValue());
    ware.setId(id);
    return ware;
  }

  @Override
  public List<IWare> readAll()
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("ware");
    List<IWare> wareListe = new ArrayList<>();
    NodeList waren = root.getElementsByTagName("id");
    for (int i = 0; i < waren.getLength(); i++)
    {
      NodeList children = waren.item(i).getChildNodes();
      Ware ware = new Ware(children.item(0).getNodeValue(), Double.parseDouble(children.item(2).getNodeValue()));
      ware.setBeschreibung(children.item(1).getNodeValue());
      ware.setId(Long.parseLong(waren.item(i).getAttributes().getNamedItem("id").getNodeValue()));
      wareListe.add(ware);
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
      Element root = doc.getElementById("ware");
      Element nodeID = root.getOwnerDocument().getElementById("");

      Node bezeichnung = nodeID.getElementsByTagName("bezeichnung").item(0);
      bezeichnung.setNodeValue(objectToUpdate.getBezeichnung());

      Node beschreibung = nodeID.getElementsByTagName("beschreibung").item(0);
      beschreibung.setNodeValue(objectToUpdate.getBeschreibung());

      Node preis = nodeID.getElementsByTagName("preis").item(0);
      preis.setNodeValue(String.valueOf(objectToUpdate.getPreis()));

      Node besonderheiten = nodeID.getElementsByTagName("besonderheiten").item(0);
      while (besonderheiten.getChildNodes().getLength() != 0)
      {
        besonderheiten.removeChild(besonderheiten.getChildNodes().item(0));
      }

      for (String besonderheitString : objectToUpdate.getBesonderheiten())
      {
        Element besonderheit = doc.createElement("besonderheit");
        besonderheit.setNodeValue(besonderheitString);
        besonderheiten.appendChild(besonderheit);
      }

      Node maengel = nodeID.getElementsByTagName("maengel").item(0);
      while (maengel.getChildNodes().getLength() != 0)
      {
        maengel.removeChild(maengel.getChildNodes().item(0));
      }
      for (String mangelString : objectToUpdate.getMaengel())
      {
        Element mangel = doc.createElement("mangel");
        mangel.setNodeValue(mangelString);
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
      Element root = doc.getElementById("ware");
      Element nodeID = root.getOwnerDocument().getElementById(id.toString());
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException e)
    {
      System.out.println("There was an unexpected exception in WareDaoXml#delete(Long id).");
    }
  }
}
