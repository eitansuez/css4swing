package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.SizingDeclaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 10:46:38 AM
 */
public class MarginLeft extends SizingDeclaration
{
   public MarginLeft() {}
   public MarginLeft(Measure measure) { setValue(measure); }
   
   public void applyTo(ComponentStyle style)
   {
      int value = _measure.value();   // *
      style.getBorder().getLeftEdge().setMargin(value);
      style.getPainter().setLeftMargin(value);
   }
   
   public boolean inherited() { return false; }
}
