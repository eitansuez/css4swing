package com.u2d.css4swing;

import com.u2d.css4swing.LineStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 11:56:29 AM
 */
public abstract class LineStyleDeclaration implements Declaration
{
   protected LineStyle _linestyle;
   
   public void setValue(LineStyle style)
   {
      _linestyle = style;
   }
}
