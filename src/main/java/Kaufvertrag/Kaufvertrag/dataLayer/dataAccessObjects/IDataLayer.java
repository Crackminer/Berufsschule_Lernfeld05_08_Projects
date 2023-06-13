package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.Kaufvertrag.businessObjects.IWare;

public interface IDataLayer
{
  IDao<IVertragspartner, String> getDaoVertragspartner();
  IDao<IWare, Long> getDaoWare();
}
