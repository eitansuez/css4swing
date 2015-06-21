package com.u2d.css4swing.property;

import com.u2d.css4swing.ColorDeclaration;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 1:21:56 PM
 */
public class BackgroundColor extends ColorDeclaration
{
   public BackgroundColor() {}
   
   public void applyTo(ComponentStyle style)
   {
      style.setBackground(_paint);
   }

   public boolean inherited() { return false; }
}
