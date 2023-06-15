package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;

import java.awt.*;

public class Programm
{
    public static void main(String[] args)
    {
        for(String s : args)
        {
            if (s.equals("-Console"))
            {
                startConsoleApplication();
                return;
            }
        }

        EventQueue.invokeLater(() ->
        {
            RootFrame root = new RootFrame();
            root.setVisible(true);
        });
    }

    public static void startConsoleApplication()
    {
        System.out.println("I was started as a Console application :)");
    }
}
