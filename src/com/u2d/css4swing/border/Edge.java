package com.u2d.css4swing.border;

import com.u2d.css4swing.LineStyle;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:38:48 PM
 */
public abstract class Edge
{
   protected final BorderStyle style = new BorderStyle(0, LineStyle.SOLID, Color.black);
   protected int margin = 0, padding = 3;
   protected Dimension borderRadius = new Dimension(0, 0);
   protected CSSBorder border;

   public Edge(CSSBorder border)
   {
      this.border = border;
   }

   public BorderStyle getBorderStyle() { return style; }
   
   public int getMargin() { return margin; }
   public void setMargin(int margin) { this.margin = margin; }
   
   public int getPadding() { return padding; }
   public void setPadding(int padding) { this.padding = padding; }
   
   public Dimension getBorderRadius() { return borderRadius; }
   public void setBorderRadius(Dimension dim) { borderRadius = dim; }
   

   protected void paintEdge(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      paintBorder(g2, c, x, y, width, height);
   }
   
   protected void paintBorder(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      if (getBorderStyle().getThickness() == 0) return;
      BasicStroke stroke = new BasicStroke(style.getThickness(),
                                  BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 
                                  1f, style.getLineStyle().dashArray(), 0f);
      g2.setStroke(stroke);
      g2.setColor(style.getColor());
      drawBorder(g2, c, x, y, width, height);
   }
   
   protected abstract void drawBorder(Graphics2D g2, Component c, int x, int y, int width, int height);


   protected void drawBorderIntrinsic(Graphics2D g2, Component c, int x, int y, int width, int height)
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
