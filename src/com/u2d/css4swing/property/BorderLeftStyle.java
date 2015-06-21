package com.u2d.css4swing.property;

import com.u2d.css4swing.LineStyleDeclaration;
import com.u2d.css4swing.LineStyle;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 5:25:43 PM
 */
public class BorderLeftStyle extends LineStyleDeclaration
{
   public BorderLeftStyle()    { }
   public BorderLeftStyle(LineStyle style) { setValue(style); }

   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getLeftEdge().getBorderStyle().setLineStyle(_linestyle);
   }

   public boolean inherited() { return false; }
}
