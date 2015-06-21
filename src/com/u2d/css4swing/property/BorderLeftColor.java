package com.u2d.css4swing.property;

import com.u2d.css4swing.ColorDeclaration;
import com.u2d.css4swing.style.ComponentStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:31:56 PM
 */
public class BorderLeftColor extends ColorDeclaration
{
   public BorderLeftColor() { }
   public BorderLeftColor(Color c) { setValue(c); }

   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getLeftEdge().getBorderStyle().setColor((Color) _paint);
   }

   public boolean inherited() { return false; }
}
