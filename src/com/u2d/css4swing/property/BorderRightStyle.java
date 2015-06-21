package com.u2d.css4swing.property;

import com.u2d.css4swing.LineStyleDeclaration;
import com.u2d.css4swing.LineStyle;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 5:34:59 PM
 */
public class BorderRightStyle extends LineStyleDeclaration
{
   public BorderRightStyle()    { }
   public BorderRightStyle(LineStyle style) { setValue(style); }

   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getRightEdge().getBorderStyle().setLineStyle(_linestyle);
   }

   public boolean inherited() { return false; }
}
