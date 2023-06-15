package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerSqlite implements IDataLayer
{
  private VertragspartnerDaoSqlite daoVertragspartner;
  private WareDaoSqlite daoWare;

  public DataLayerSqlite()
  {
    daoVertragspartner = new VertragspartnerDaoSqlite();
    daoWare = new WareDaoSqlite();
  }

  @Override
  public IDao<IVertragspartner, String> getDaoVertragspartner()
  {
    return daoVertragspartner;
  }

  @Override
  public IDao<IWare, Long> getDaoWare()
  {
    return daoWare;
  }
}
