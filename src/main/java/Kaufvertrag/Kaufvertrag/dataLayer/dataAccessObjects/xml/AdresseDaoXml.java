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
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Adresse.xml";

  @Override
  public IAdresse create()
  {
    Adresse objectToInsert = new Adresse("", "", "", "");
    objectToInsert.setStrasse(Programm.getInputMethod().getString("Strasse", getClass()));
    objectToInsert.setHausNr(Programm.getInputMethod().getString("Hausnummer", getClass()));
    objectToInsert.setPlz(Programm.getInputMethod().getString("PLZ", getClass()));
    objectToInsert.setOrt(Programm.getInputMethod().getString("Ort", getClass()));

    objectToInsert.setID(Programm.getInputMethod().getID());
    /*try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("adresse");
      Element nodeID = doc.createElement("id");
      nodeID.setIdAttribute(String.valueOf(objectToInsert.getID()), true);

      Element strasse = doc.createElement("strasse");
      strasse.setNodeValue(objectToInsert.getStrasse());
      nodeID.appendChild(strasse);

      Element hausnummer = doc.createElement("hausnummer");
      hausnummer.setNodeValue(objectToInsert.getHausNr());
      nodeID.appendChild(hausnummer);

      Element plz = doc.createElement("postleitzahl");
      plz.setNodeValue(objectToInsert.getPlz());
      nodeID.appendChild(plz);

      Element ort = doc.createElement("ort");
      ort.setNodeValue(objectToInsert.getOrt());
      nodeID.appendChild(ort);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return objectToInsert;
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#create().");
    }
    return null;*/
    return objectToInsert;
  }

  @Override
  public void create(IAdresse objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("adresse");
      Element nodeID = doc.createElement("id");
      nodeID.setIdAttribute(String.valueOf(objectToInsert.getID()), true);

      Element strasse = doc.createElement("strasse");
      strasse.setNodeValue(objectToInsert.getStrasse());
      nodeID.appendChild(strasse);

      Element hausnummer = doc.createElement("hausnummer");
      hausnummer.setNodeValue(objectToInsert.getHausNr());
      nodeID.appendChild(hausnummer);

      Element plz = doc.createElement("postleitzahl");
      plz.setNodeValue(objectToInsert.getPlz());
      nodeID.appendChild(plz);

      Element ort = doc.createElement("ort");
      ort.setNodeValue(objectToInsert.getOrt());
      nodeID.appendChild(ort);

      root.appendChild(nodeID);
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
    Element root = doc.getElementById("adresse");
    Element nodeID = root.getOwnerDocument().getElementById(id.toString());
    Adresse adresse = new Adresse(nodeID.getElementsByTagName("strasse").item(0).getNodeValue(), nodeID.getElementsByTagName("hausnummer").item(0).getNodeValue(), nodeID.getElementsByTagName("postleitzahl").item(0).getNodeValue(), nodeID.getElementsByTagName("ort").item(0).getNodeValue());
    adresse.setID(Long.parseLong(nodeID.getAttribute("id")));
    return adresse;
  }

  @Override
  public List<IAdresse> readAll()
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("adresse");
    List<IAdresse> adressListe = new ArrayList<>();
    NodeList adressen = root.getElementsByTagName("id");
    for (int i = 0; i < adressen.getLength(); i++)
    {
      NodeList children = adressen.item(i).getChildNodes();
      Adresse adresse = new Adresse(children.item(0).getNodeValue(), children.item(1).getNodeValue(), children.item(2).getNodeValue(), children.item(3).getNodeValue());
      adresse.setID(Long.parseLong(adressen.item(i).getAttributes().getNamedItem("id").getNodeValue()));
      adressListe.add(adresse);
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
      Element root = doc.getElementById("adresse");
      Element nodeID = root.getOwnerDocument().getElementById(String.valueOf(objectToUpdate.getID()));

      Node strasse = nodeID.getElementsByTagName("strasse").item(0);
      strasse.setNodeValue(objectToUpdate.getStrasse());

      Node hausnummer = nodeID.getElementsByTagName("hausnummer").item(0);
      hausnummer.setNodeValue(objectToUpdate.getHausNr());

      Node plz = nodeID.getElementsByTagName("postleitzahl").item(0);
      plz.setNodeValue(objectToUpdate.getPlz());

      Node ort = nodeID.getElementsByTagName("ort").item(0);
      ort.setNodeValue(objectToUpdate.getOrt());

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
      Element root = doc.getElementById("adresse");
      Element nodeID = root.getOwnerDocument().getElementById(id.toString());
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#delete(Long id).");
    }
  }
}
