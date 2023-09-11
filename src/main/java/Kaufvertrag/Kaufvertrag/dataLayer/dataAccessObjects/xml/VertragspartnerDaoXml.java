package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

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

public class VertragspartnerDaoXml implements IDao<Vertragspartner, String>
{
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Vertragspartner.xml";

  @Override
  public Vertragspartner create()
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element vorname = doc.createElement("vorname");
      vorname.setNodeValue("");
      nodeID.appendChild(vorname);

      Element nachname = doc.createElement("nachname");
      nachname.setNodeValue("");
      nodeID.appendChild(nachname);

      Element ausweisNr = doc.createElement("ausweisNr");
      ausweisNr.setNodeValue("");
      nodeID.appendChild(ausweisNr);

      root.appendChild(nodeID);
      Vertragspartner vertragspartner = new Vertragspartner(vorname.getNodeValue(), nachname.getNodeValue());
      vertragspartner.setAusweisNr(ausweisNr.getNodeValue());
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return vertragspartner;
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create().");
    }
    return null;
  }

  @Override
  public void create(Vertragspartner objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("vertragspartner");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element vorname = doc.createElement("vorname");
      vorname.setNodeValue(objectToInsert.getVorname());
      nodeID.appendChild(vorname);

      Element nachname = doc.createElement("nachname");
      nachname.setNodeValue(objectToInsert.getNachname());
      nodeID.appendChild(nachname);

      Element ausweisNr = doc.createElement("ausweisNr");
      ausweisNr.setNodeValue(objectToInsert.getAusweisNr());
      nodeID.appendChild(ausweisNr);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create(IVertragspartner objectToInsert).");
    }
  }

  @Override
  public Vertragspartner read(String id)
  {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("vertragspartner");
    Element nodeID = root.getOwnerDocument().getElementById(id);
    Vertragspartner vertragspartner = new Vertragspartner(nodeID.getElementsByTagName("vorname").item(0).getNodeValue(), nodeID.getElementsByTagName("nachname").item(0).getNodeValue());
    vertragspartner.setAusweisNr(nodeID.getElementsByTagName("ausweisNr").item(0).getNodeValue());
    return vertragspartner;
  }

  @Override
  public List<Vertragspartner> readAll() {
    Document doc = getDocument(FILEPATH);
    assert doc != null;
    Element root = doc.getElementById("vertragspartner");
    List<Vertragspartner> vertragspartnerListe = new ArrayList<>();
    NodeList vertragspartnerL = root.getElementsByTagName("id");
    for (int i = 0; i < vertragspartnerL.getLength(); i++) {
      NodeList children = vertragspartnerL.item(i).getChildNodes();
      Vertragspartner vertragspartner = new Vertragspartner(children.item(0).getNodeValue(), children.item(1).getNodeValue());
      vertragspartner.setAusweisNr(children.item(2).getNodeValue());
      vertragspartnerListe.add(vertragspartner);
    }
    return vertragspartnerListe;
  }

  @Override
  public void update(Vertragspartner objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("vertragspartner");
      //get the id here pls.
      Element nodeID = root.getOwnerDocument().getElementById("");

      Node vorname = nodeID.getElementsByTagName("vorname").item(0);
      vorname.setNodeValue(objectToUpdate.getVorname());

      Node nachname = nodeID.getElementsByTagName("nachname").item(0);
      nachname.setNodeValue(objectToUpdate.getNachname());

      Node ausweisNr = nodeID.getElementsByTagName("ausweisNr").item(0);
      ausweisNr.setNodeValue(objectToUpdate.getAusweisNr());

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
      System.out.println("There was an unexpected Exception in VertagspartnerDaoXml#delete(String id).");
    }
  }
}
