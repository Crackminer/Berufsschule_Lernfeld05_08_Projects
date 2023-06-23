package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
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
    while((type = root.getPersistenceType()) == null)
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
}
