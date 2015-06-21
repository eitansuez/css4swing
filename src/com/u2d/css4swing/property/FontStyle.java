package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 3:08:53 PM
 */
public class FontStyle implements Declaration
{
   private int _value = java.awt.Font.PLAIN;
   
   public FontStyle() {}
   public FontStyle(int value)
   {
      setValue(value);
   }
   
   /**
    * @param value corresponds to java.awt.Font field constants ITALIC, NORMAL, etc..
    */
   public void setValue(int value) { _value = value; }
   
   public int getValue() { return _value; }


   public void applyTo(ComponentStyle style)
   {
      style.getFont().setStyle(this);
   }

   public boolean inherited() { return true; }
}
