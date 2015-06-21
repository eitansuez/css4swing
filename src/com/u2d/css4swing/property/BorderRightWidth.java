package com.u2d.css4swing.property;

import com.u2d.css4swing.SizingDeclaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 5:07:30 PM
 */
public class BorderRightWidth extends SizingDeclaration
{
   public BorderRightWidth() { }
   public BorderRightWidth(Measure value) { setValue(value); }

   public void applyTo(ComponentStyle style)
   {
      int value = _measure.value();  // will later address the issue of units..
      style.getBorder().getRightEdge().getBorderStyle().setThickness(value);
   }

   public boolean inherited() { return false; }
}
