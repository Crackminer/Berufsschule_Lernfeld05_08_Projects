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
      docFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      docFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      docFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
      docFactory.setExpandEntityReferences(false);
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      File file = new File(filepath);
      if (!file.createNewFile())
      {
        return docBuilder.parse(file);
      }
      Document doc = docBuilder.newDocument();
      String[] split = filepath.trim().toLowerCase().split("/");
      Element rootElement = doc.createElement(split[split.length -1].replace(".xml", ""));
      doc.appendChild(rootElement);
      return doc;
    }
    catch (SAXException | IOException | ParserConfigurationException ex)
    {
      ex.printStackTrace();
      System.out.println("There was an unexpected Exception in AdresseDaoXml#getDocument().");
    }
    return null;
  }

  public static void writeToXML(Document doc, OutputStream output)
  {
    try
    {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

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
