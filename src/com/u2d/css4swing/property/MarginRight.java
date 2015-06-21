package com.u2d.css4swing.property;

import com.u2d.css4swing.SizingDeclaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 10:55:12 AM
 */
public class MarginRight extends SizingDeclaration
{
   public MarginRight() {}
   public MarginRight(Measure measure) { setValue(measure); }
   
   public void applyTo(ComponentStyle style)
   {
      int value = _measure.value();   // *
      style.getBorder().getRightEdge().setMargin(value);
      style.getPainter().setRightMargin(value);
   }
   
   public boolean inherited() { return false; }
}
