package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class AdresseDaoXml implements IDao<IAdresse, Long>
{
  /*public AdresseDaoXml()
  {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("adresse");
    doc.appendChild(rootElement);
  }*/

  @Override
  public IAdresse create()
  {
    return null;
  }

  @Override
  public IAdresse create(IAdresse objectToInsert)
  {
    return null;
  }

  @Override
  public IAdresse read(Long id)
  {
    return null;
  }

  @Override
  public List<IAdresse> readAll()
  {
    return null;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {

  }

  @Override
  public void delete(Long id)
  {

  }
}
