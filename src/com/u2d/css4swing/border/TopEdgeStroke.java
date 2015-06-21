package com.u2d.css4swing.border;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Arc2D;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:31 PM
 */
public class TopEdgeStroke
      extends Edge
{
   public TopEdgeStroke(CSSBorder border)
   {
      super(border);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int leftMargin = border.getLeftEdge().getMargin();
      int rightMargin = border.getRightEdge().getMargin();
      Dimension topLeftCornerRadius = border.getLeftEdge().getBorderRadius();
      Dimension topRightCornerRadius = border.getTopEdge().getBorderRadius();
      
      x += leftMargin + topLeftCornerRadius.width;
      width = width - leftMargin - rightMargin - 
            topLeftCornerRadius.width - topRightCornerRadius.width - 1;
      y += margin;
      g2.drawLine(x, y, x + width, y);

      g2.drawArc(x + width-topRightCornerRadius.width,
                                  y,
                                  2 * (topRightCornerRadius.width),
                                  2 * (topRightCornerRadius.height),
                                  0, 90);
   }

}
