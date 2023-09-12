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
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Vertragspartner.xml";

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
    /*try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute(objectToInsert.getAusweisNr(), true);

      Element vorname = doc.createElement("vorname");
      vorname.setNodeValue(objectToInsert.getVorname());
      nodeID.appendChild(vorname);

      Element nachname = doc.createElement("nachname");
      nachname.setNodeValue(objectToInsert.getNachname());
      nodeID.appendChild(nachname);

      Element addressId = doc.createElement("adresse");
      addressId.setNodeValue(String.valueOf(objectToInsert.getAdresse().getID()));
      nodeID.appendChild(addressId);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return objectToInsert;
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create().");
    }
    return null;*/
    return objectToInsert;
  }

  @Override
  public void create(IVertragspartner objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = doc.createElement("id");
      nodeID.setIdAttribute(objectToInsert.getAusweisNr(), true);

      Element vorname = doc.createElement("vorname");
      vorname.setNodeValue(objectToInsert.getVorname());
      nodeID.appendChild(vorname);

      Element nachname = doc.createElement("nachname");
      nachname.setNodeValue(objectToInsert.getNachname());
      nodeID.appendChild(nachname);

      Element addressId = doc.createElement("adresse");
      addressId.setNodeValue(String.valueOf(objectToInsert.getAdresse().getID()));
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
    Element root = doc.getElementById("vertragspartner");
    Element nodeID = root.getOwnerDocument().getElementById(id);
    Vertragspartner vertragspartner = new Vertragspartner(nodeID.getElementsByTagName("vorname").item(0).getNodeValue(), nodeID.getElementsByTagName("nachname").item(0).getNodeValue());
    vertragspartner.setAusweisNr(id);
    vertragspartner.setAdresse(adresseDaoXml.read(Long.parseLong(nodeID.getElementsByTagName("adresse").item(0).getNodeValue())));
    return vertragspartner;
  }

  @Override
  public List<IVertragspartner> readAll()
  {
    AdresseDaoXml adresseDaoXml = new AdresseDaoXml();
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("vertragspartner");
    List<IVertragspartner> vertragspartnerListe = new ArrayList<>();
    NodeList vertragspartnerL = root.getElementsByTagName("id");
    for (int i = 0; i < vertragspartnerL.getLength(); i++) {
      NodeList children = vertragspartnerL.item(i).getChildNodes();
      Vertragspartner vertragspartner = new Vertragspartner(children.item(0).getNodeValue(), children.item(1).getNodeValue());
      vertragspartner.setAusweisNr(vertragspartnerL.item(i).getAttributes().getNamedItem("id").getNodeValue());
      vertragspartner.setAdresse(adresseDaoXml.read(Long.parseLong(children.item(2).getNodeValue())));
      vertragspartnerListe.add(vertragspartner);
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
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = root.getOwnerDocument().getElementById(objectToUpdate.getAusweisNr());

      Node vorname = nodeID.getElementsByTagName("vorname").item(0);
      vorname.setNodeValue(objectToUpdate.getVorname());

      Node nachname = nodeID.getElementsByTagName("nachname").item(0);
      nachname.setNodeValue(objectToUpdate.getNachname());

      Node addressId = nodeID.getElementsByTagName("adresse").item(0);
      addressId.setNodeValue(String.valueOf(objectToUpdate.getAdresse().getID()));

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
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = root.getOwnerDocument().getElementById(id);
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#delete(String id).");
    }
  }
}
