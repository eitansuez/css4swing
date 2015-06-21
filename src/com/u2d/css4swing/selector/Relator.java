package com.u2d.css4swing.selector;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 27, 2007
 * Time: 12:35:28 PM
 */
public interface Relator
{
   public void setSelector(Selector selector);
   public boolean matches(JComponent target);
   public JComponent matchingComponent(JComponent target);
}
