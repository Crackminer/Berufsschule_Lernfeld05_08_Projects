package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerSqlite implements IDataLayer
{
  private final AdresseDaoSqlite daoAdresse;
  private final VertragspartnerDaoSqlite daoVertragspartner;
  private final WareDaoSqlite daoWare;
  private final KaufvertragDaoSqlite daoKaufvertrag;

  public DataLayerSqlite()
  {
    daoAdresse = new AdresseDaoSqlite();
    daoVertragspartner = new VertragspartnerDaoSqlite();
    daoWare = new WareDaoSqlite();
    daoKaufvertrag = new KaufvertragDaoSqlite();
  }

  @Override
  public IDao<IAdresse, Long> getDaoAdresse()
  {
    return daoAdresse;
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

  @Override
  public IDao<IKaufvertrag, Long> getDaoKaufvertrag()
  {
    return daoKaufvertrag;
  }
}
