package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
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

public class AdresseDaoXml implements IDao<IAdresse, Long>
{
  private static final String FILEPATH = "src/main/resources/xml/Adresse.xml";

  @Override
  public IAdresse create()
  {
    Adresse objectToInsert = new Adresse("", "", "", "");
    objectToInsert.setStrasse(Programm.getInputMethod().getString("Strasse", getClass()));
    objectToInsert.setHausNr(Programm.getInputMethod().getString("Hausnummer", getClass()));
    objectToInsert.setPlz(Programm.getInputMethod().getString("PLZ", getClass()));
    objectToInsert.setOrt(Programm.getInputMethod().getString("Ort", getClass()));

    objectToInsert.setID(Programm.getInputMethod().getID());
    return objectToInsert;
  }

  @Override
  public void create(IAdresse objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      Node root = null;
      if(doc == null)
      {
        return;
      }
      root = doc.getElementsByTagName("adresse").item(0);
      if (root == null)
      {
        root = doc.createElement("adresse");
        doc.appendChild(root);
      }
      Element nodeID = doc.createElement("id");
      root.appendChild(nodeID);
      nodeID.setAttribute("id", String.valueOf(objectToInsert.getID()));
      nodeID.setIdAttribute("id", true);

      Element strasse = doc.createElement("strasse");
      strasse.setTextContent(objectToInsert.getStrasse());
      nodeID.appendChild(strasse);

      Element hausnummer = doc.createElement("hausnummer");
      hausnummer.setTextContent(objectToInsert.getHausNr());
      nodeID.appendChild(hausnummer);

      Element plz = doc.createElement("postleitzahl");
      plz.setTextContent(objectToInsert.getPlz());
      nodeID.appendChild(plz);

      Element ort = doc.createElement("ort");
      ort.setTextContent(objectToInsert.getOrt());
      nodeID.appendChild(ort);

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#create(IAdresse objectToInsert).");
    }
  }

  @Override
  public IAdresse read(Long id)
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("adresse").item(0);
    if (root == null)
    {
      System.out.println("The Document was empty, so there is nothing to read. Returning now.");
      return null;
    }
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
    Adresse adresse = new Adresse(nodeID.getChildNodes().item(1).getTextContent(), nodeID.getChildNodes().item(3).getTextContent(), nodeID.getChildNodes().item(5).getTextContent(), nodeID.getChildNodes().item(7).getTextContent());
    adresse.setID(id);
    return adresse;
  }

  @Override
  public List<IAdresse> readAll()
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Node root = doc.getElementsByTagName("adresse").item(0);
    List<IAdresse> adressListe = new ArrayList<>();
    NodeList adressen = root.getChildNodes();
    for (int i = 0; i < adressen.getLength(); i++)
    {
      Node nodeID = adressen.item(i);
      if (nodeID.hasAttributes())
      {
        Adresse adresse = new Adresse(nodeID.getChildNodes().item(1).getTextContent(), nodeID.getChildNodes().item(3).getTextContent(), nodeID.getChildNodes().item(5).getTextContent(), nodeID.getChildNodes().item(7).getTextContent());
        adresse.setID(Long.parseLong(nodeID.getAttributes().getNamedItem("id").getTextContent()));
        adressListe.add(adresse);
      }
    }
    return adressListe;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("adresse").item(0);
      NodeList nodes = root.getChildNodes();
      Node nodeID = null;
      for(int i = 0; i < nodes.getLength(); i++)
      {
        if (nodes.item(i).hasAttributes())
        {
          if (nodes.item(i).getAttributes().getNamedItem("id") != null)
          {
            if (nodes.item(i).getAttributes().getNamedItem("id").getTextContent().equals(String.valueOf(objectToUpdate.getID())))
            {
              nodeID = nodes.item(i);
            }
          }
        }
      }

      Node strasse = nodeID.getChildNodes().item(1);
      strasse.setTextContent(objectToUpdate.getStrasse());

      Node hausnummer = nodeID.getChildNodes().item(3);
      hausnummer.setTextContent(objectToUpdate.getHausNr());

      Node plz = nodeID.getChildNodes().item(5);
      plz.setTextContent(objectToUpdate.getPlz());

      Node ort = nodeID.getChildNodes().item(7);
      ort.setTextContent(objectToUpdate.getOrt());

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#update(IAdresse objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Node root = doc.getElementsByTagName("adresse").item(0);
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
      System.out.println("There was an unexpected Exception in AdresseDaoXml#delete(Long id).");
    }
  }
}
