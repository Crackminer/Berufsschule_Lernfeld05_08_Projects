package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class XMLManager
{
  public static Document getDocument(String filepath)
  {
    try
    {

      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      // to be compliant, completely disable DOCTYPE declaration:
      docFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      // or completely disable external entities declarations:
      docFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      docFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      // or prohibit the use of all protocols by external entities:
      docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      // or disable entity expansion but keep in mind that this doesn't prevent fetching external entities
      // and this solution is not correct for OpenJDK < 13 due to a bug: https://bugs.openjdk.java.net/browse/JDK-8206132
      docFactory.setExpandEntityReferences(false);
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      File file = new File(filepath);
      if (file.exists())
      {
        return docBuilder.parse(file);
      }
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("adresse");
      doc.appendChild(rootElement);
      return doc;
    }
    catch (SAXException | IOException | ParserConfigurationException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#getDocument().");
    }
    return null;
  }

  public static void writeToXML(Document doc, OutputStream output)
  {
    try
    {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      // to be compliant, prohibit the use of all protocols by external entities:
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);

      transformer.transform(source, result);
    }
    catch (TransformerException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#writeToXML(Document doc, OutputStream output).");
    }
  }
}
