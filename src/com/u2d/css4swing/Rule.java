package com.u2d.css4swing;

import com.u2d.css4swing.selector.Selector;

import javax.swing.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 2:39:46 PM
 */
public class Rule implements Comparable
{
   private Selector _selector;
   private List<Declaration> _declarations = new ArrayList<Declaration>();
   private Set<Selector> _additionalSelectors = new HashSet<Selector>();
   
   public Rule(Selector selector)
   {
      _selector = selector;
   }
   
   // this is more an artifact of implementation.  once stylesheet is read, rules with more than
   //  one selector will be split into multiple single-selector rules.
   public void addSelector(Selector selector)
   {
      _additionalSelectors.add(selector);
   }
   public Set<Selector> getAdditionalSelectors() { return _additionalSelectors; }
   public void copyDeclarationsTo(Rule rule)
   {
      for (Declaration d : _declarations)
      {
         rule.addDeclaration(d);
      }
   }
   
   public void setStylesheet(Stylesheet sheet)
   {
      _selector.setStylesheet(sheet);
   }
   
   public Selector selector() { return _selector; };

   public void addDeclaration(Declaration decl)
   {
      _declarations.add(decl);
   }
   public List<Declaration> declarations() { return _declarations; }
   
   public boolean appliesTo(JComponent component)
   {
      return _selector.appliesTo(component);
   }


   public int compareTo(Object o)
   {
      return (specificity() - ((Rule) o).specificity());
   }
   public int specificity() { return _selector.specificity(); }
   public int fineSpecificity(JComponent comp) { return _selector.fineSpecificity(comp); }
   
   public String toString()
   {
      return _selector.toString() + " ("+_declarations.size()+" declarations)";
   }


   public boolean equals(Object obj)
   {
      if (obj == null) return false;
      if (!(obj instanceof Rule)) return false;
      Rule rule = (Rule) obj;
      return _selector.equals(rule.selector()) && _declarations.equals(rule.declarations());
   }

   public int hashCode()
   {
      return _selector.hashCode() * 31 + _declarations.size();
   }
   
   
   // added to support stylesheet.merge function
   List<Declaration> getDeclarations() { return _declarations; }
   
   
   public Rule copy()
   {
      Rule rule = new Rule(_selector);
      copyDeclarationsTo(rule);
      return rule;
   }
   
}
