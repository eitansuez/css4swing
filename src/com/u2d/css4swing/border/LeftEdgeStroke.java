package com.u2d.css4swing.border;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Arc2D;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:18 PM
 */
public class LeftEdgeStroke
      extends Edge
{
   public LeftEdgeStroke(CSSBorder border)
   {
      super(border);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int topMargin = border.getTopEdge().getMargin();
      int bottomMargin = border.getBottomEdge().getMargin();
      Dimension bottomLeftCornerRadius = border.getBottomEdge().getBorderRadius();
      Dimension topLeftCornerRadius = border.getLeftEdge().getBorderRadius();
      
      x += margin;
      y += topMargin + topLeftCornerRadius.height;
      height = height - topMargin - bottomMargin - 
            topLeftCornerRadius.height - bottomLeftCornerRadius.height - 1;
      g2.drawLine(x, y, x, y + height);

      g2.drawArc(x, 
                 y-topLeftCornerRadius.height, 
                 2 * (topLeftCornerRadius.width), 
                 2 * (topLeftCornerRadius.height),
                 90, 90);
   }
}
