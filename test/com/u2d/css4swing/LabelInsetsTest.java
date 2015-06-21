package com.u2d.css4swing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 27, 2007
 * Time: 5:00:52 PM
 */
public class LabelInsetsTest extends JPanel
{
   public LabelInsetsTest()
   {
      JLabel label = new JLabel("Testing 123..");
      label.setFont(label.getFont().deriveFont(16f));
      label.setBorder(new LineBorder(Color.black, 15));
      add(label);
   }
   public static void main(String[] args)
   {
      JFrame f = new JFrame();
      f.setContentPane(new LabelInsetsTest());
      
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocation(100,100);
      f.pack();
      f.setVisible(true);
   }
   
}
