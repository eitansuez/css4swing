package com.u2d.css4swing.property;

import com.u2d.css4swing.SizingDeclaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 3:58:08 PM
 */
public class FontSize extends SizingDeclaration
{
   public FontSize()
   {
      _measure = new Measure(11, "pt");
   }
   public FontSize(Measure measure)
   {
      setValue(measure);
   }
   
   public int pointSize()
   {
      return _measure.value();  // for now assume measure specified in pts
   }

   public void applyTo(ComponentStyle style)
   {
      style.getFont().setSize(this);
   }

   public boolean inherited() { return true; }
}
