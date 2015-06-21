package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 3:08:53 PM
 */
public class FontWeight implements Declaration
{
   private int _value = java.awt.Font.PLAIN;
   
   public FontWeight() {}
   public FontWeight(int value)
   {
      setValue(value);
   }
   
   /**
    * @param value corresponds to java.awt.Font field constants BOLD, NORMAL, etc..
    */
   public void setValue(int value)
   {
      _value = value;
   }
   
   public int getValue() { return _value; }


   public void applyTo(ComponentStyle style)
   {
      style.getFont().setWeight(this);
   }

   public boolean inherited() { return true; }
}
