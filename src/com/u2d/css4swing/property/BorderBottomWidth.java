package com.u2d.css4swing.property;

import com.u2d.css4swing.SizingDeclaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 5:05:58 PM
 */
public class BorderBottomWidth extends SizingDeclaration
{
   public BorderBottomWidth() { }
   public BorderBottomWidth(Measure value) { setValue(value); }

   public void applyTo(ComponentStyle style)
   {
      int value = _measure.value();  // will later address the issue of units..
      style.getBorder().getBottomEdge().getBorderStyle().setThickness(value);
   }

   public boolean inherited() { return false; }
}
