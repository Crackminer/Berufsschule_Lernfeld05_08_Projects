package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;

public interface IDataLayer
{
  IDao<IAdresse, Long> getDaoAdresse();
  IDao<IVertragspartner, String> getDaoVertragspartner();
  IDao<IWare, Long> getDaoWare();
  IDao<IKaufvertrag, Long> getDaoKaufvertrag();
}
