package com.u2d.css4swing.property;

import com.u2d.css4swing.ColorDeclaration;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 12:55:40 PM
 */
public class ForegroundColor extends ColorDeclaration
{
   public ForegroundColor() { }

   public void applyTo(ComponentStyle style)
   {
      style.setForeground(_paint);
   }

   public boolean inherited() { return true; }
}
