package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.Kaufvertrag.Programm;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml.DataLayerXml;

public class DataLayerManager
{
  private static DataLayerManager instance;
  private String persistenceType;
  private IDataLayer dataLayer;

  private DataLayerManager()
  {
  }

  public static DataLayerManager getInstance()
  {
    if (instance == null)
    {
      instance = new DataLayerManager();
    }
    String persistenceType = instance.readPersistenceType();
    if ("sqlite".equals(persistenceType))
    {
      instance.dataLayer = new DataLayerSqlite();
    } else if ("xml".equals(persistenceType))
    {
      instance.dataLayer = new DataLayerXml();
    }
    return instance;
  }

  public IDataLayer getDataLayer()
  {
    return this.dataLayer;
  }

  private String readPersistenceType()
  {
    return persistenceType == null ? persistenceType = Programm.getPersistenceType() : persistenceType;
  }
}
