package com.u2d.css4swing.demo;

import com.l2fprod.common.swing.JOutlookBar;
import com.u2d.css4swing.border.BorderStyle;
import com.u2d.css4swing.border.CSSBorder;
import com.u2d.css4swing.property.FontFamily;
import com.u2d.css4swing.selector.Selector;
import com.u2d.css4swing.style.ComponentStyle;
import com.u2d.css4swing.LineStyle;
import com.u2d.css4swing.FieldCaption;
import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.StyleMenuBar;
import static junit.framework.Assert.*;
import junit.framework.Assert;
import org.jdesktop.swingx.JXPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 11:48:24 AM
 */
@SuppressWarnings("serial")
public class Simple extends JXPanel
{

   public Simple()
   {
      ComponentStyle.setIdent(this, "main-pnl");
      setLayout(new BorderLayout());

      JXPanel child = new JXPanel();
      child.setLayout(new FlowLayout(FlowLayout.LEFT));
      ComponentStyle.setIdent(child, "child-pnl");

      add(child, BorderLayout.CENTER);
   }

   public static void main(String[] args)
   {
      CSSEngine.initialize("simple.css");

      final Simple st = new Simple();
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            JFrame f = new JFrame("Simple");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setJMenuBar(new StyleMenuBar(f));
            f.getJMenuBar().getMenu(0).add(exitItem());
            f.setContentPane(st);
            f.setBounds(100,100,500,500);
            f.setVisible(true);
         }
      });
   }

   public static JMenuItem exitItem()
   {
      JMenuItem item = new JMenuItem("Exit");
      item.setMnemonic('x');
      item.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) { System.exit(0); }
      });
      return item;
   }

}