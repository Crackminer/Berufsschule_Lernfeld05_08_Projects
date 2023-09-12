package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;

public class WindowApplication implements IApplication
{
  private RootFrame root;

  public WindowApplication()
  {

  }

  @Override
  public void startApplication()
  {
    EventQueue.invokeLater(() -> {
      FlatDarkLaf.setup();
      root = new RootFrame();
      root.setVisible(true);
    });
  }

  @Override
  public String getPersistenceType()
  {
    String type = null;
    while ((type = root.getPersistenceType()) == null)
    {
      try
      {
        wait(1000);
      }
      catch (InterruptedException ex)
      {
        System.out.println("Unexpected Exception for Waiting on user input via button from gui application.");
      }
    }
    System.out.println("I got the type of " + type + " !");
    return type;
  }

  @Override
  public Long getID()
  {
    return null;
  }

  @Override
  public Long getForeignID(String whatToGetTheIDFor, Class classForTheID)
  {
    return null;
  }

  @Override
  public String getString(String whatToGetTheStringFor, Class classForTheString)
  {
    return null;
  }

  @Override
  public String getYesOrNo(String message)
  {
    return null;
  }

  @Override
  public int getInt(String whatToGetTheIntFor, Class classForTheInt)
  {
    return 0;
  }

  @Override
  public double getDouble(String whatToGetTheDoubleFor, Class classForTheDouble)
  {
    return 0;
  }
}
