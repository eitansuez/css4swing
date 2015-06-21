package com.u2d.css4swing.demo;

import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.StyleMenuBar;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: May 22, 2007
 * Time: 1:54:16 PM
 */
public class Main extends JApplet
{

   public void init()
   {
      CSSEngine.initialize();
      
      setJMenuBar(new StyleMenuBar(this));
      setContentPane(new DataEntry().getMainPnl());
   }
   

   public static void main(String[] args)
   {
      CSSEngine.initialize();
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            JFrame f = new JFrame();
            f.setJMenuBar(new StyleMenuBar(f));
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new DataEntry().getMainPnl());
            f.pack();
            f.setLocation(100,100);
            f.setVisible(true);
         }
      });
   }
}
