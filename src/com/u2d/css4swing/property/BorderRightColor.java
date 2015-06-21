package com.u2d.css4swing.property;

import com.u2d.css4swing.ColorDeclaration;
import com.u2d.css4swing.style.ComponentStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:51:06 PM
 */
public class BorderRightColor extends ColorDeclaration
{
   public BorderRightColor()    { }
   public BorderRightColor(Color c) { setValue(c); }

   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getRightEdge().getBorderStyle().setColor((Color) _paint);
   }

   public boolean inherited() { return false; }
}
