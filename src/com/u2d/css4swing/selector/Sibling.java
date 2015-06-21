package com.u2d.css4swing.selector;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 27, 2007
 * Time: 12:35:56 PM
 */
public class Sibling extends BaseRelator
{
   public JComponent matchingComponent(JComponent target)
   {
      Container parent = target.getParent();
      if (parent == null || !(parent instanceof JComponent))
         return null;
      
      int index = indexOfComponent(target, parent);
      if (index <= 0) return null;
      
      JComponent jParent = (JComponent) parent;
      JComponent prevSibling = (JComponent) jParent.getComponent(index - 1);
      if (_selector.appliesTo(prevSibling))
      {
         return prevSibling;
      }
      return null;
   }
   
   private int indexOfComponent(Component comp, Container parent)
   {
      for (int i=0; i<parent.getComponents().length; i++)
      {
         if (parent.getComponent(i) == comp)
            return i;
      }
      return -1;
   }

   public String toString() { return "+"; }
}
