package com.u2d.css4swing.property;

import com.u2d.css4swing.ColorDeclaration;
import com.u2d.css4swing.style.ComponentStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:52:07 PM
 */
public class BorderBottomColor extends ColorDeclaration
{
   public BorderBottomColor()    { }
   public BorderBottomColor(Color c) { setValue(c); }

   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getBottomEdge().getBorderStyle().setColor((Color) _paint);
   }


   public boolean inherited() { return false; }
}
