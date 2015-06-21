package com.u2d.css4swing;

import org.jdesktop.swingx.painter.Painter;
import javax.swing.*;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 2:19:11 PM
 */
public class Utils
{
   public static String unqualifiedName(Class cls)
   {
      String clsName = cls.getName();
      return clsName.substring(clsName.lastIndexOf(".")+1);
   }
   
   public static String unquote(String quotedString)
   {
      if (quotedString == null) return quotedString;
      if (quotedString.startsWith("\"") && quotedString.endsWith("\""))
      {
         return quotedString.substring(1, quotedString.length() - 1);
      }
      return quotedString;
   }

   interface FilterPredicate
   {
      boolean keep(Object item);
   }
   public static List filter(Collection items, FilterPredicate rule)
   {
      List results = new ArrayList();
      for (Iterator itr = items.iterator(); itr.hasNext(); )
      {
         Object item = itr.next();
         if (rule.keep(item))
            results.add(item);
      }
      return results;
   }
   
   /* nice going swingx team.. */
   public static boolean isBackgroundPaintable(Component c)
   {
      try
      {
         c.getClass().getMethod("setBackgroundPainter", Painter.class);
         return true;
      }
      catch (NoSuchMethodException e)
      {
         return false;
      }
   }

   public static void setBackgroundPainter(JComponent c, Painter painter)
   {
      try
      {
         c.getClass().getMethod("setBackgroundPainter", Painter.class).invoke(c, painter);
      }
      catch (NoSuchMethodException e) { /* ignore */ }
      catch (IllegalAccessException e) { /* ignore */ }
      catch (InvocationTargetException e) { /* ignore */ }
   }

}
