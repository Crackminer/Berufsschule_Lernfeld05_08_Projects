package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.AdresseDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.KaufvertragDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;

public class DataLayerXml implements IDataLayer
{
  private final AdresseDaoXml daoAdresse;
  private final VertragspartnerDaoXml daoVertragspartner;
  private final WareDaoXml daoWare;
  private final KaufvertragDaoXml daoKaufvertrag;

  public DataLayerXml()
  {
    daoAdresse = new AdresseDaoXml();
    daoVertragspartner = new VertragspartnerDaoXml();
    daoWare = new WareDaoXml();
    daoKaufvertrag = new KaufvertragDaoXml();
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
