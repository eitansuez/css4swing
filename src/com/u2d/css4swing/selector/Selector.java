package com.u2d.css4swing.selector;

import com.u2d.css4swing.Stylesheet;

import javax.swing.JComponent;

/**
 * @author Eitan Suez
 * Date: Aug 2007
 */
public interface Selector
{
   public static final String CLASS = "css-class";
   public static final String IDENT = "css-ident";

   public boolean appliesTo(JComponent component);
   public int specificity();
   public int fineSpecificity(JComponent component);
   
   public void setStylesheet(Stylesheet sheet);
}
