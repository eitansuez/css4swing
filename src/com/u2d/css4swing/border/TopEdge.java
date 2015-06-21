package com.u2d.css4swing.border;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:39:31 PM
 */
public class TopEdge extends Edge
{
   public TopEdge(CSSBorder border)
   {
      super(border);
   }

   protected void drawMargin(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      g2.fillRect(x, y, width, margin);
   }

   protected void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      int leftMargin = border.getLeftEdge().getMargin();
      int rightMargin = border.getRightEdge().getMargin();
      int thickness = style.getThickness();
      
      g2.fillRect(x + leftMargin,
                  y + margin, 
                  width - leftMargin - rightMargin,
                  thickness);
   }
}
