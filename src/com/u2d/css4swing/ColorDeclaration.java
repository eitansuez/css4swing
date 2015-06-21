package com.u2d.css4swing;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 10:56:57 AM
 */
public abstract class ColorDeclaration implements Declaration
{
   protected java.awt.Paint _paint;

   public void setValue(java.awt.Paint paint) { _paint = paint; }
}
