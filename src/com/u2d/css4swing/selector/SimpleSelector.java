package com.u2d.css4swing.selector;

import com.u2d.css4swing.style.ComponentStyle;
import com.u2d.css4swing.Stylesheet;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 12:15:54 PM
 */
public class SimpleSelector implements Selector
{
   private String elementName = "";
   private String cssClassName = "";
   private String cssIdent = "";
   private String namespace = "";

   public SimpleSelector() {}

   public SimpleSelector(String elementName, String cssClassName)
   {
      this.elementName = elementName;
      setCssClassName(cssClassName);
   }
   public SimpleSelector(String elementName) { this.elementName = elementName; }

   public void setElementName(String elemName) { elementName = elemName; }
   public void setCssClassName(String cssName) { cssClassName = cssName; }
   public void setIdent(String ident) { cssIdent = ident; }
  
   public void setNamespace(String ns) { namespace = ns; }

   public boolean appliesTo(JComponent component)
   {
      if (identSpecified())
      {
         return matchesIdent(component);
      }
      if (elementNameSpecified())
      {
         if (cssClassNameSpecified())
         {
            return matchesElementName(component) && matchesCssClassName(component);
         }
         return matchesElementName(component);
      }
      else if (cssClassNameSpecified())
      {
         return matchesCssClassName(component);
      }
      throw new RuntimeException("Invalid selector? " + this.toString());
   }

   private boolean elementNameSpecified() { return !isBlank(elementName); }
	private boolean matchesElementName(JComponent component)
	{
		return classForElementName().isAssignableFrom(component.getClass());      
	}

	private boolean cssClassNameSpecified() { return !isBlank(cssClassName); }
   private boolean matchesCssClassName(JComponent component)
	{
      return ComponentStyle.hasClass(component, cssClassName);
	}
	private boolean identSpecified() { return !isBlank(cssIdent); }
	private boolean matchesIdent(JComponent component)
	{
		return ComponentStyle.isIdent(component, cssIdent);
	}
   
   private boolean isBlank(String text)
   {
      return (text == null) || (text.trim().length() == 0);
   }


   public int specificity()
	{
		int value = 0;
		if (identSpecified()) value += 100;
		if (cssClassNameSpecified()) value += 10;
		if (elementNameSpecified()) value += 1;
		return value;
	}
   
   public int fineSpecificity(JComponent component)
   {
      int typeNearness = 9 - typeDistance(component);
      return specificity() * 10 + typeNearness;
   }
   private int typeDistance(JComponent component)
   {
      if (!elementNameSpecified()) return 9;  // greatest distance..
      if (!matchesElementName(component)) return 9;

      Class selectorClass = classForElementName();
      
      int distance = 0;
      Class classUnderEval = component.getClass();
      while (! selectorClass.equals(classUnderEval))
      {
         distance++;
         classUnderEval = classUnderEval.getSuperclass();
      }
      return distance;
   }



   private Stylesheet stylesheet;
	public void setStylesheet(Stylesheet sheet) { stylesheet = sheet; }

	private Class klass = null;
	private Class classForElementName()
	{
      if (klass != null) return klass;
      
      String fqName = stylesheet.getNamespace(this.namespace)+ "." + this.elementName;
		try
		{
         ClassLoader loader = Thread.currentThread().getContextClassLoader();
			klass = loader.loadClass(fqName);
		}
		catch (ClassNotFoundException e1) {}

		if (klass == null)
		{
			throw new RuntimeException("Invalid selector element name: "+elementName+
					"; must match a valid ui component class name;  e.g. JLabel (if outside "+
			" the javax.swing package, must fully qualify class name)");
		}
		return klass;
	}


	public String toString()
	{
		StringBuffer text = new StringBuffer();
		if (!isBlank(elementName)) text.append(elementName);
		if (!isBlank(cssClassName)) text.append(".").append(cssClassName);
		if (!isBlank(cssIdent)) text.append("#").append(cssIdent);
		return text.toString();
	}


	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof SimpleSelector)) return false;
		SimpleSelector s = (SimpleSelector) obj;
		return elementName.equals(s.elementName) && 
		cssClassName.equals(s.cssClassName) &&
		cssIdent.equals(s.cssIdent);
	}

	public int hashCode()
	{
		return toString().hashCode();
	}
}
