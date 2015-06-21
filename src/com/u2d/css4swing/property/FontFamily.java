package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;

import java.util.*;
import java.util.List;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 4:36:54 PM
 */
public class FontFamily implements Declaration
{
   public static final String SANS_SERIF = "SansSerif";
   public static final String SERIF = "Serif";
   public static final String MONOSPACE = "Monospaced";
   
   private List<String> _values = new ArrayList<String>();
   private String computedFamilyName = SANS_SERIF;
   
   static Set<String> availableFamilies = new HashSet<String>();
   static
   {
      GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] names = env.getAvailableFontFamilyNames();
      availableFamilies.addAll(Arrays.asList(names));
   }
   
   public FontFamily() {}
   public FontFamily(List<String> values) { setValue(values); }

   public void setValue(List<String> values)
   {
      _values = values;
      updateComputedFamilyName();
   }

   private void updateComputedFamilyName()
   {
      for (String name : _values)
      {
         if (availableFamilies.contains(name))
         {
            computedFamilyName = name;
            return;
         }
      }
   }
   
   public String familyName() { return computedFamilyName; }


   public void applyTo(ComponentStyle style)
   {
      style.getFont().setFamily(this);
   }

   public boolean inherited() { return true; }
}
