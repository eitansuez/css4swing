package com.u2d.css4swing.border;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:18 PM
 */
public class LeftEdge extends Edge
{
   public LeftEdge(CSSBorder border)
   {
      super(border);
   }
   protected void drawMargin(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      g2.fillRect(x, y, margin, height);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int topMargin = border.getTopEdge().getMargin();
      int bottomMargin = border.getBottomEdge().getMargin();
      int thickness = style.getThickness();
      
      g2.fillRect(x + margin,
                  y + topMargin,
                  thickness,
                  height - topMargin - bottomMargin);
   }
}
