package com.u2d.css4swing.property;

import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jun 1, 2007
 * Time: 1:08:34 PM
 */
public class BorderRadiusBottomLeft
      extends BorderRadius
{
   public BorderRadiusBottomLeft()
   {
      super();
   }
   public void applyTo(ComponentStyle style)
   {
      style.getBorder().getBottomEdge().setBorderRadius(asDim());
      style.getPainter().setBottomLeftBorderRadius(asDim());
   }
}
