package com.u2d.css4swing.demo;

import com.u2d.css4swing.style.ComponentStyle;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: May 22, 2007
 * Time: 1:40:37 PM
 */
public class DataEntry
{
   private JTextField textField1;
   private JTextField textField2;
   private JTextField textField3;
   private JComboBox comboBox1;
   private JTextField textField4;
   private JButton submitButton;
   private JPanel mainPnl;
   private JLabel heading;

   public DataEntry()
   {
      ComponentStyle.addClass(heading, "heading");
      ComponentStyle.addClass(mainPnl, "mainPnl");
   }
   
   public JPanel getMainPnl() { return mainPnl; }
   
}
