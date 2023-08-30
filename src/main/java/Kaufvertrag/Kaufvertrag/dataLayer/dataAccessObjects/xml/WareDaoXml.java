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


public class WareDaoXml implements IDao<IWare, Long> {
    private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Ware.xml";

    @Override
    public IWare create() {

        try {
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
            for (int i = 0; i < 1; i++) {
                Element besonderheit = doc.createElement("besonderheit");
                besonderheit.setNodeValue("");
                besonderheiten.appendChild(besonderheit);
            }
            nodeID.appendChild(besonderheiten);

            Element maengel = doc.createElement("maengel");
            for (int i = 0; i < 1; i++) {
                Element mangel = doc.createElement("mangel");
                mangel.setNodeValue("");
                maengel.appendChild(mangel);
            }
            nodeID.appendChild(maengel);

            root.appendChild(nodeID);
            Ware ware = new Ware(bezeichnung.getNodeValue(), Double.parseDouble(preis.getNodeValue()));
            writeToXML(doc, new FileOutputStream(FILEPATH));
            return ware;
        } catch (IOException e) {
            System.out.println("There was an unexpected exception in WareDaoXml#create().");
        }
        return null;
    }

    @Override
    public void create(IWare objectToInsert) {
        try {
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
            for (int i = 0; i < objectToInsert.getBesonderheiten().size(); i++) {
                Element besonderheit = doc.createElement("besonderheit");
                besonderheit.setNodeValue("");
                besonderheiten.appendChild(besonderheit);
            }
            nodeID.appendChild(besonderheiten);

            Element maengel = doc.createElement("maengel");
            for (int i = 0; i < objectToInsert.getMaengel().size(); i++) {
                Element mangel = doc.createElement("mangel");
                mangel.setNodeValue("");
                maengel.appendChild(mangel);
            }
            nodeID.appendChild(maengel);

            root.appendChild(nodeID);
            writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException e) {
            System.out.println("There was an unexpected exception in WareDaoXml#create(IWare objectToInsert).");
        }

    }

    @Override
    public IWare read(Long id) {
        Document doc = getDocument(FILEPATH);
        assert doc != null;
        Element root = doc.getElementById("ware");
        Element nodeID = root.getOwnerDocument().getElementById(id.toString());
        return new Ware(nodeID.getElementsByTagName("bezeichnung").item(0).getNodeValue(), Double.valueOf(nodeID.getElementsByTagName("preis").item(0).getNodeValue()));
    }

    @Override
    public List<IWare> readAll() {
        Document doc = getDocument(FILEPATH);
        assert doc != null;
        Element root = doc.getElementById("ware");
        List<IWare> wareListe = new ArrayList<>();
        NodeList waren = root.getElementsByTagName("id");
        for (int i = 0; i < waren.getLength(); i++) {
            NodeList children = waren.item(i).getChildNodes();
            wareListe.add(new Ware(children.item(0).getNodeValue(), Double.valueOf(children.item(2).getNodeValue())));
        }
        return wareListe;
    }

    @Override
    public void update(IWare objectToUpdate) {
        try {
            Document doc = getDocument(FILEPATH);
            assert doc != null;
            Element root = doc.getElementById("ware");
            Element nodeID = root.getOwnerDocument().getElementById("");

            Node bezeichnung = nodeID.getElementsByTagName("bezeichnung").item(0);
            bezeichnung.setNodeValue(objectToUpdate.getBezeichnung());

            Node beschreibung = nodeID.getElementsByTagName("beschreibung").item(0);
            beschreibung.setNodeValue(objectToUpdate.getBeschreibung());

            // ahhhhhhhhh, was tun?!?!?!?
            Node preis = nodeID.getElementsByTagName("preis").item(0);
            preis.setNodeValue(String.valueOf(objectToUpdate.getPreis()));

            Node besonderheiten = nodeID.getElementsByTagName("besonderheiten").item(0);
            for (int i = 0; i < objectToUpdate.getBesonderheiten().size(); i++) {
                Node besonderheit = besonderheiten.getChildNodes().item(i);
                besonderheit.setNodeValue(objectToUpdate.getBesonderheiten().get(i));
            }

            Node maengel = nodeID.getElementsByTagName("maengel").item(0);
            for (int i = 0; i < objectToUpdate.getMaengel().size(); i++) {
                Node mangel = maengel.getChildNodes().item(i);
                mangel.setNodeValue(objectToUpdate.getMaengel().get(i));
            }

            writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException e) {
            System.out.println("There was an unexpected exception in WareDaoXml#update(IWare objectToUpdate).");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Document doc = getDocument(FILEPATH);
            assert doc != null;
            Element root = doc.getElementById("ware");
            Element nodeID = root.getOwnerDocument().getElementById(id.toString());
            root.removeChild(nodeID);
            writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException e) {
            System.out.println("There was an unexpected exception in WareDaoXml#delete(Long id).");
        }
    }
}
