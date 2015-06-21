package com.u2d.css4swing.selector;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 27, 2007
 * Time: 2:17:37 PM
 */
public abstract class BaseRelator implements Relator
{
   protected Selector _selector;
   public void setSelector(Selector selector) { _selector = selector; }
   
   public boolean matches(JComponent target)
   {
      return (matchingComponent(target) != null);
   }

   public abstract JComponent matchingComponent(JComponent target);
}
