package Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.util.List;

public class KaufvertragDaoSqlite implements IDao<IKaufvertrag, Long>
{
  @Override
  public IKaufvertrag create()
  {
    return null;
  }

  @Override
  public void create(IKaufvertrag objectToInsert)
  {

  }

  @Override
  public IKaufvertrag read(Long id)
  {
    return null;
  }

  @Override
  public List<IKaufvertrag> readAll()
  {
    return null;
  }

  @Override
  public void update(IKaufvertrag objectToUpdate)
  {

  }

  @Override
  public void delete(Long id)
  {

  }
}
