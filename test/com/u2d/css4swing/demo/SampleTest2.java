package com.u2d.css4swing.demo;

import com.u2d.css4swing.style.ComponentStyle;
import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.StyleMenuBar;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This class is designed to help manually test complex selectors.
 */
public class SampleTest2 extends JXPanel
{
   public SampleTest2()
   {
      setLayout(new BorderLayout());
      JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      JXPanel panel = panel();
      ComponentStyle.addClass(panel, "leftSide");
      sp.setLeftComponent(panel);

      panel = panel();
      ComponentStyle.addClass(panel, "rightSide");
      sp.setRightComponent(panel);
      add(sp, BorderLayout.CENTER);
      
      setPreferredSize(new Dimension(400,400));
   }
   
   private JXPanel panel()
   {
      JXPanel mainPanel = new JXPanel(new BorderLayout());

      JXPanel p = new JXPanel();
      for (int i=0; i<3; i++)
      {
         p.add(new JButton("Button #"+i));
      }
      mainPanel.add(p, BorderLayout.CENTER);
      
      JXPanel southPanel = new JXPanel(new FlowLayout());
      southPanel.add(new JLabel("A Label.."));
      mainPanel.add(southPanel, BorderLayout.PAGE_END);
      
      return mainPanel;
   }
   
   public static void main(String[] args)
   {
      CSSEngine.initialize("sample2.css");

      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setJMenuBar(new StyleMenuBar(f));
            f.getJMenuBar().getMenu(0).add(SampleTest.exitItem());
            f.setContentPane(new SampleTest2());
            f.setBounds(100,100,500,500);
            f.setVisible(true);
         }
      });
   }
}
