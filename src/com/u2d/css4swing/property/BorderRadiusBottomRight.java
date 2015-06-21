package com.u2d.css4swing.property;

import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jun 1, 2007
 * Time: 1:08:34 PM
 */
public class BorderRadiusBottomRight
      extends BorderRadius
{
   public BorderRadiusBottomRight()
   {
      super();
   }
   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getRightEdge().setBorderRadius(asDim());
      style.getPainter().setBottomRightBorderRadius(asDim());
   }
}
