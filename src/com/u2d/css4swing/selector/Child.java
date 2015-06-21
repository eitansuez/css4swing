package com.u2d.css4swing.selector;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 27, 2007
 * Time: 12:36:12 PM
 */
public class Child extends BaseRelator
{

   public JComponent matchingComponent(JComponent target)
   {
      Container parent = target.getParent();
      if (parent == null || !(parent instanceof JComponent))
         return null;
      
      JComponent jParent = (JComponent) parent;
      if (_selector.appliesTo(jParent))
      {
         return jParent;
      }
      else
      {
         return null;
      }
   }

   public String toString() { return ">"; }
}
