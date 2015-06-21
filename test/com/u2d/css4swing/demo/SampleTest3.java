package com.u2d.css4swing.demo;

import org.jdesktop.swingx.JXPanel;
import javax.swing.*;
import java.awt.*;

import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.StyleMenuBar;

/**
 * This class is designed to help manually test complex selectors.
 */
public class SampleTest3 extends JXPanel
{
   public SampleTest3()
   {
      setLayout(new BorderLayout());
      JLabel label = new JLabel("Hello World");
      add(label, BorderLayout.CENTER);
      
      setPreferredSize(new Dimension(400,400));
   }
   
   public static void main(String[] args)
   {
      CSSEngine.initialize("sample3.css");

      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setJMenuBar(new StyleMenuBar(f));
            f.getJMenuBar().getMenu(0).add(SampleTest.exitItem());
            f.setContentPane(new SampleTest3());
            f.setBounds(100,100,500,500);
            f.setVisible(true);
         }
      });
   }
}