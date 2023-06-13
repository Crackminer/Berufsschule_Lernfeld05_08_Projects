package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects;

public class DataLayerManager
{
  private static DataLayerManager instance;
  private String persistenceType;

  private DataLayerManager()
  {
  }

  public static DataLayerManager getInstance()
  {
    if (instance == null)
    {
      instance = new DataLayerManager();
    }
    String persistanceType = instance.readPersistanceType();
    if(persistanceType.equals("sqlite"))
    {

    }
    return instance;
  }

  public IDataLayer getDataLayer()
  {
    return null;
  }

  private String readPersistanceType()
  {
    return "";
  }
}
