package com.u2d.css4swing;

import com.u2d.css4swing.style.ComponentStyle;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import static com.u2d.css4swing.Utils.*;
import com.u2d.css4swing.selector.Selector;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 2:52:21 PM
 */
public class Stylesheet
{
   private static final String DEFAULT_NS = "javax.swing";
	private Set<Rule> _rules = new HashSet<Rule>();
   private Map<String, String> _namespaces = new HashMap<String, String>();

   public Stylesheet()
   {
      _namespaces.put("", DEFAULT_NS);
   }
   
   public Stylesheet(Collection<Rule> rules)
   {
      this();
      for (Iterator itr = rules.iterator(); itr.hasNext(); )
      {
         Rule rule = (Rule) itr.next();
         addRule(rule);
      }
   }

   public void addNamespace(String key, String value)
   {
      if (_namespaces.containsKey(key))
      {
         throw new IllegalArgumentException("Namespace \"" + key + "\" already defined");
      }
      _namespaces.put(key, value);
   }
   public String getNamespace(String key) { return _namespaces.get(key); }
   

   public Rule addRule(Selector selector)
   {
      Rule rule = new Rule(selector);
      rule.setStylesheet(this);
      _rules.add(rule);
      return rule;
   }

   public void postParse()
   {
      flattenRules();
      //System.out.println("num rules: "+numRules());
   }
   private void flattenRules()
   {
      List<Rule> additionalRules = new ArrayList<Rule>();
      for (Iterator itr = _rules.iterator(); itr.hasNext(); )
      {
         Rule rule = (Rule) itr.next();
         Set<Selector> selectors = rule.getAdditionalSelectors();
         for (Selector s : selectors)
         {
            Rule newrule = new Rule(s);
            newrule.setStylesheet(this);
            rule.copyDeclarationsTo(newrule);
            additionalRules.add(newrule);
         }
      }
      for (Rule rule : additionalRules)
      {
         _rules.add(rule);
      }
   }
   
   public int numRules() { return _rules.size(); }
   
   private Map<Component, ComponentStyle> _styles = new WeakHashMap<Component, ComponentStyle>();
   public synchronized ComponentStyle styleFor(JComponent component)
   {
      if (!_styles.containsKey(component))
      {
         ComponentStyle style = new ComponentStyle();
         _styles.put(component, style);
      }
      return _styles.get(component);
   }
   public void cleanupStylesFor(Component component)
   {
      _styles.remove(component);
   }

   /**
    * @param container target container object
    * @return number of componentstyle objects removed from the style map cache
    */
   public int cleanupStylesForRecursive(Container container)
   {
      int count = 0;
      Object value = _styles.remove(container);
      if (value != null)
      {
         count++;
      }
      for (int i=0; i<container.getComponentCount(); i++)
      {
         Component childComponent = container.getComponent(i);
         if (childComponent instanceof Container)
         {
            count += cleanupStylesForRecursive((Container) childComponent);
         }
         else
         {
            _styles.remove(childComponent);
            count++;
         }
      }
      return count;
   }
      
   public void applyTo(final JComponent component)
   {
      List hierarchy = componentHierarchyFor(component);  // exclude component
      ComponentStyle componentStyle = styleFor(component);
      
      for (int i=0; i<hierarchy.size(); i++)
      {
         JComponent container = (JComponent) hierarchy.get(i);
         buildStyleFor(componentStyle, container, true);
      }
      buildStyleFor(componentStyle, component, false);
      
      // and finally..
      componentStyle.applyTo(component);
   }
   
   private void buildStyleFor(ComponentStyle style, JComponent component, boolean isContainer)
   {
      List<Rule> matchingRules = matchingRulesFor(component);

      // build a componentstyle object
      for (int i=0; i<matchingRules.size(); i++)
      {
         Rule rule = matchingRules.get(i);
         List<Declaration> decls = rule.declarations();
         if (isContainer)
         {
            decls = filter(rule.declarations(), new FilterPredicate()
            {
               public boolean keep(Object item)
               {
                  return ((Declaration) item).inherited();
               }
            });
         }

         for (Declaration decl : decls)
         {
            decl.applyTo(style);
         }
      }
      
   }

   private List componentHierarchyFor(JComponent component)
   {
      LinkedList list = new LinkedList();
      Component parent = component.getParent();
      while (parent != null && (parent instanceof JComponent))
      {
         list.addFirst(parent);
         parent = parent.getParent();
      }
      return list;
   }

   private List<Rule> matchingRulesFor(final JComponent component)
   {
      // 1. find matching rules
      List<Rule> matchingRules = filter(_rules, new FilterPredicate()
      {
         public boolean keep(Object item)
         {
            return ((Rule) item).appliesTo(component);
         }
      });

      // 2. sort them by specificity..
      Collections.sort(matchingRules, new Comparator()
      {
         public int compare(Object o1, Object o2)
         {
            Rule r1 = (Rule) o1;  Rule r2 = (Rule) o2;
            return r1.fineSpecificity(component) - r2.fineSpecificity(component);
         }
      });
      return matchingRules;
   }
   
   public Stylesheet merge(Stylesheet stylesheet)
   {
      Map<Selector, Rule> ruleMap = new HashMap<Selector, Rule>();
      for (Iterator itr = _rules.iterator(); itr.hasNext(); )
      {
         Rule rule = (Rule) itr.next();
         ruleMap.put(rule.selector(), rule.copy());
      }
      for (Iterator itr = stylesheet.getRules().iterator(); itr.hasNext(); )
      {
         Rule rule = (Rule) itr.next();
         Rule mapRule = ruleMap.get(rule.selector());
         if (mapRule == null)
         {
            ruleMap.put(rule.selector(), rule.copy());
         }
         else
         {
            // merge declarations..
            for (int i=0; i<rule.getDeclarations().size(); i++)
            {
               Declaration decl = rule.getDeclarations().get(i);
               mapRule.addDeclaration(decl);
            }
         }
      }
      
      return new Stylesheet(ruleMap.values());
   }
   
   // added to support stylesheet.merge function
   Set<Rule> getRules() { return _rules; }
   void addRule(Rule rule) { _rules.add(rule); }
   
}
