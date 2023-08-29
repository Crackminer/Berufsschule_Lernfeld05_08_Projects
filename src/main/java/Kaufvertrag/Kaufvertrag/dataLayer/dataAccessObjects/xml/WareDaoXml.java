package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Ware;
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
        nodeID.setIdAttribute("",true);

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
        for(int i = 0; i < 1; i++){
          Element besonderheit = doc.createElement("besonderheit");
          besonderheit.setNodeValue("");
          besonderheiten.appendChild(besonderheit);
        }
        nodeID.appendChild(besonderheiten);

        Element maengel = doc.createElement("maengel");
        for(int i = 0; i < 1; i++){
          Element mangel = doc.createElement("mangel");
          mangel.setNodeValue("");
          maengel.appendChild(mangel);
          }
        nodeID.appendChild(maengel);

        root.appendChild(nodeID);
        Ware ware = new Ware();
        writeToXML(doc, new FileOutputStream(FILEPATH));
        return ware;
        }
    catch(IOException e){
      System.out.println("There was an unexpected exception in WareDaoXml#create().");
      }
    return null;
  }

  @Override
  public void create(IWare objectToInsert)
  {

  }

  @Override
  public IWare read(Long id)
  {
    return null;
  }

  @Override
  public List<IWare> readAll()
  {
    return null;
  }

  @Override
  public void update(IWare objectToUpdate)
  {

  }

  @Override
  public void delete(Long id)
  {

  }
}
