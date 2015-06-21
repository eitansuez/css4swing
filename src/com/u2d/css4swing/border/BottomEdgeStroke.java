package com.u2d.css4swing.border;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Arc2D;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:38 PM
 */
public class BottomEdgeStroke
      extends Edge
{
   public BottomEdgeStroke(CSSBorder border)
   {
      super(border);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int leftMargin = border.getLeftEdge().getMargin();
      int rightMargin = border.getRightEdge().getMargin();
      Dimension bottomRightCornerRadius = border.getRightEdge().getBorderRadius();
      Dimension bottomLeftCornerRadius = border.getBottomEdge().getBorderRadius();

      x += leftMargin + bottomLeftCornerRadius.width;
      width = width - leftMargin - rightMargin -  
            bottomLeftCornerRadius.width - bottomRightCornerRadius.width - 1;
      y += height - margin - 1;
      g2.drawLine(x, y, x + width, y);
      
      g2.drawArc(x-bottomLeftCornerRadius.width, 
                 y-2*bottomLeftCornerRadius.height, 
                 2 * ( bottomLeftCornerRadius.width), 
                 2 * ( bottomLeftCornerRadius.height), 
                 180, 90);
   }

}
