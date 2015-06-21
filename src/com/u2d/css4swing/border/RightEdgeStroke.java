package com.u2d.css4swing.border;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Arc2D;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:25 PM
 */
public class RightEdgeStroke
      extends Edge
{
   public RightEdgeStroke(CSSBorder border)
   {
      super(border);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int topMargin = border.getTopEdge().getMargin();
      int bottomMargin = border.getBottomEdge().getMargin();
      Dimension topRightCornerRadius = border.getTopEdge().getBorderRadius();
      Dimension bottomRightCornerRadius = border.getRightEdge().getBorderRadius();

      x += width - margin - 1;
      y += topMargin + topRightCornerRadius.height;
      height = height - topMargin - bottomMargin - 
            topRightCornerRadius.height - bottomRightCornerRadius.height - 1;
      g2.drawLine(x, y, x, y + height);

      g2.drawArc(x-2*bottomRightCornerRadius.width, 
                 y+height-bottomRightCornerRadius.height, 
                 2 * (bottomRightCornerRadius.width), 
                 2 * (bottomRightCornerRadius.height), 
                 270, 90);
   }
}
