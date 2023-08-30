package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.XMLManager.getDocument;
import static Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.XMLManager.writeToXML;

import java.util.List;

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
      verkaufer.setNodeValue(objectToInsert.getVerkaeufer());   //need to find id
      nodeID.appendChild(verkaufer);

      Element kaufer = doc.createElement("kaufer");
      kaufer.setNodeValue(objectToInsert.getKaeufer());   //need to find id
      nodeID.appendChild(kaufer);

      Element ware = doc.createElement("ware");
      ware.setNodeValue(objectToInsert.getWare());   //need to find id
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
    return null;
  }

  @Override
  public List<IKaufvertrag> readAll()
  {
    return null;
  }

  @Override
  public void update(IKaufvertrag objectToUpdate)
  {

  }

  @Override
  public void delete(Long id)
  {

  }
}
