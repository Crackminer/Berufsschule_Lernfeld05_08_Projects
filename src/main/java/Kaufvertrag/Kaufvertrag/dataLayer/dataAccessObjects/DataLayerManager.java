package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects;

public class DataLayerManager
{
  private static DataLayerManager instance;
  private String persistenceType;   //Witzlos, wenn ich das nirgends setze?

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
    if("sqlite".equals(persistenceType))
    {

    }
    return instance;
  }

  public IDataLayer getDataLayer()
  {
    return null;
  }

  private String readPersistenceType()
  {
    return persistenceType;
  }
}
