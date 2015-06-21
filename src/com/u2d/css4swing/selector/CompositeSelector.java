package com.u2d.css4swing.selector;

import com.u2d.css4swing.Stylesheet;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 27, 2007
 * Time: 12:33:01 PM
 */
public class CompositeSelector implements Selector
{
   private Selector _branch;
   private Relator _relator;
   private SimpleSelector _leaf;
   
   public CompositeSelector(Selector branch, Relator relator, SimpleSelector leaf)
   {
      _branch = branch;
      _relator = relator;
      _relator.setSelector(_branch);
      _leaf = leaf;
   }

   public boolean appliesTo(JComponent component)
   {
      if (!_leaf.appliesTo(component))
         return false;
      
      return _relator.matches(component);
   }

   public int specificity()
   {
      return _branch.specificity() + _leaf.specificity();
   }

   public int fineSpecificity(JComponent component)
   {
      return _branch.fineSpecificity(_relator.matchingComponent(component)) + 
            _leaf.fineSpecificity(component);
   }

   public void setStylesheet(Stylesheet sheet)
   {
      _branch.setStylesheet(sheet);
      _leaf.setStylesheet(sheet);
   }

   
   
   public String toString()
   {
      StringBuffer text = new StringBuffer();
      text.append(_branch).append(_relator).append(_leaf);
      return text.toString();
   }


   public boolean equals(Object obj)
   {
      if (obj == null) return false;
      if (!(obj instanceof CompositeSelector)) return false;
      CompositeSelector cs = (CompositeSelector) obj;
      return toString().equals(cs.toString());
   }

   public int hashCode() { return toString().hashCode(); }

}
