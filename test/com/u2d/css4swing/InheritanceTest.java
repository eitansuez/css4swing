package com.u2d.css4swing;

import com.u2d.css4swing.style.ComponentStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 3, 2007
 * Time: 11:43:45 AM
 */
public class InheritanceTest implements Runnable
{
   public static void main(String[] args)
   {
      CSSEngine.initialize(InheritanceTest.class.getResourceAsStream("/inheritance.css"));
      SwingUtilities.invokeLater(new InheritanceTest());
   }

   public InheritanceTest() {}

   public void run()
   {
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel cp = (JPanel) f.getContentPane();
      cp.setLayout(new FlowLayout());
      
      cp.add(new JLabel("Here is a label"));
      JButton btn = new JButton("Here is a button");
      ComponentStyle.addClass(btn, "required");
      cp.add(btn);
      
      f.pack();
      f.setVisible(true);
   }

}
