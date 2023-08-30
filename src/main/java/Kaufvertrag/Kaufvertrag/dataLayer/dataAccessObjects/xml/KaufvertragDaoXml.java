package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
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

public class KaufvertragDaoXml implements IDao<IKaufvertrag, Long>
{
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Kaufvertrag.xml";

  @Override
  public IKaufvertrag create()
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      assert doc != null;
      Element root = doc.getElementById("kaufvertrag");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element verkaufer = doc.createElement("verkaufer");
      verkaufer.setNodeValue("");
      nodeID.appendChild(verkaufer);

      Element kaufer = doc.createElement("kaufer");
      kaufer.setNodeValue("");
      nodeID.appendChild(kaufer);

      Element ware = doc.createElement("ware");
      ware.setNodeValue("");
      nodeID.appendChild(ware);

      Element bezahlmethode = doc.createElement("bezahlmethode");
      bezahlmethode.setNodeValue("");
      nodeID.appendChild(bezahlmethode);

      root.appendChild(nodeID);
      Kaufvertrag kaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(verkaufer.getNodeValue()), new VertragspartnerDaoXml().read(kaufer.getNodeValue()), new WareDaoXml().read(Long.parseLong(ware.getNodeValue())));
      kaufvertrag.setZahlungsModalitaeten(bezahlmethode.getNodeValue());
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return kaufvertrag;
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoXml#create().");
    }
    return null;
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
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element verkaufer = doc.createElement("verkaufer");
      verkaufer.setNodeValue(objectToInsert.getVerkaeufer().getAusweisNr());
      nodeID.appendChild(verkaufer);

      Element kaufer = doc.createElement("kaufer");
      kaufer.setNodeValue(objectToInsert.getKaeufer().getAusweisNr());
      nodeID.appendChild(kaufer);

      Element ware = doc.createElement("ware");
      ware.setNodeValue(String.valueOf(objectToInsert.getWare().getId()));
      nodeID.appendChild(ware);

      Element bezahlmethode = doc.createElement("bezahlmethode");
      bezahlmethode.setNodeValue(objectToInsert.getZahlungsModalitaeten());
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
    Element nodeID = root.getOwnerDocument().getElementById(id.toString());
    Kaufvertrag kaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(nodeID.getElementsByTagName("verkaufer").item(0).getNodeValue()), new VertragspartnerDaoXml().read(nodeID.getElementsByTagName("kaufer").item(0).getNodeValue()), new WareDaoXml().read(Long.parseLong(nodeID.getElementsByTagName("ware").item(0).getNodeValue())));
    kaufvertrag.setZahlungsModalitaeten(nodeID.getElementsByTagName("bezahlmethode").item(0).getNodeValue());
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
      NodeList children = kaufvertraege.item(i).getChildNodes();
      Kaufvertrag kaufvertrag = new Kaufvertrag(new VertragspartnerDaoXml().read(children.item(0).getNodeValue()), new VertragspartnerDaoXml().read(children.item(1).getNodeValue()), new WareDaoXml().read(Long.parseLong(children.item(2).getNodeValue())));
      kaufvertrag.setZahlungsModalitaeten(children.item(3).getNodeValue());
      kaufvertragListe.add(kaufvertrag);
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
      //get the id here pls.
      Element nodeID = root.getOwnerDocument().getElementById("");

      Node verkaufer = nodeID.getElementsByTagName("verkaufer").item(0);
      verkaufer.setNodeValue(objectToUpdate.getVerkaeufer().getAusweisNr());

      Node kaufer = nodeID.getElementsByTagName("kaufer").item(0);
      kaufer.setNodeValue(objectToUpdate.getKaeufer().getAusweisNr());

      Node ware = nodeID.getElementsByTagName("ware").item(0);
      ware.setNodeValue(String.valueOf(objectToUpdate.getWare().getId()));

      Node bezahlmethode = nodeID.getElementsByTagName("bezahlmethode").item(0);
      bezahlmethode.setNodeValue(objectToUpdate.getZahlungsModalitaeten());

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
      Element nodeID = root.getOwnerDocument().getElementById(id.toString());
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in KaufvertragDaoXml#delete(Long id).");
    }
  }
}
