package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;

import java.awt.*;

public class WindowApplication implements IApplication
{
  @Override
  public void startApplication()
  {
    EventQueue.invokeLater(() -> {
      RootFrame root = new RootFrame();
      root.setVisible(true);
    });
  }

  @Override
  public String getPersistenceType()
  {
    return null;
  }
}
