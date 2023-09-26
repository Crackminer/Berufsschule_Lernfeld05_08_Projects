package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
      /*JDialog dialog = new JDialog(root);
      dialog.setLayout(new GridBagLayout());

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.gridy = 0;
      constraints.gridx = 0;
      dialog.setBounds(400, 300, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35f), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25f));
      dialog.setLocationRelativeTo(null);

      JLabel jLabel = new JLabel("Sadly we cant say that the GUI is working. We are sorry for this inconvenience.");

      JButton jButton = new JButton("Acknowledge");
      jButton.addActionListener((ActionEvent e) ->
        {
          dialog.setVisible(false);
          System.exit(0);
        }
      );
      dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

      dialog.add(jLabel, constraints);
      constraints.gridy = 1;
      dialog.add(jButton, constraints);
      dialog.setVisible(true);*/
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
  public Long getForeignID(String whatToGetTheIDFor, Class<?> classForTheID)
  {
    return null;
  }

  @Override
  public String getString(String whatToGetTheStringFor, Class<?> classForTheString)
  {
    return null;
  }

  @Override
  public String getYesOrNo(String message)
  {
    return null;
  }

  @Override
  public int getInt(String whatToGetTheIntFor, Class<?> classForTheInt)
  {
    return 0;
  }

  @Override
  public double getDouble(String whatToGetTheDoubleFor, Class<?> classForTheDouble)
  {
    return 0;
  }
}
