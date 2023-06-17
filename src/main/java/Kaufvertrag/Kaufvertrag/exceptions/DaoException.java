package Kaufvertrag.Kaufvertrag.exceptions;

/**
 * @author Patrick Schewe
 */
public class DaoException extends Exception
{
  private final String message;
  public DaoException(String message)
  {
    this.message = message;
  }

  @Override
  public String getMessage()
  {
    return this.message;
  }
}
