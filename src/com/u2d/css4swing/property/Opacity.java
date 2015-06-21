package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jul 24, 2007
 * Time: 1:09:55 PM
 */
public class Opacity implements Declaration
{
   private float _value;
   
   public Opacity() {}
   public Opacity(float value)
   {
      setValue(value);
   }

   public void setValue(float value) { _value = value; }

   public void applyTo(ComponentStyle style)
   {
      style.getPainter().setOpacity(_value);
   }
   
   public boolean inherited() { return false; }
}
