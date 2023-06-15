package Kaufvertrag.Kaufvertrag;

import Kaufvertrag.Kaufvertrag.presentationLayer.RootFrame;

import java.awt.*;

public class Programm
{
    public static void main(String[] args)
    {
        if(args.length >= 1 && args[0].equals("-Console"))
        {
            //Do Console Application
            System.out.println("I was started via Console!");
        }
        else
        {
            EventQueue.invokeLater(() ->
            {
                RootFrame root = new RootFrame();
                root.setVisible(true);
            });
        }
    }
}
