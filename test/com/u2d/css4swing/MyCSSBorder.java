package com.u2d.css4swing;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 27, 2007
 * Time: 5:04:20 PM
 */
public class MyCSSBorder
      extends AbstractBorder
{
   public static final int MARGIN = 1;
   public static final int PADDING = 2;
   public static final int BORDER = 3;

   int _padding, _border, _margin;
   Color _borderColor;
   
   MyCSSBorder(int margin, int border, int padding, Color borderColor)
   {
      _margin = margin;
      _border = border;
      _padding = padding;
      _borderColor = borderColor;
   }

   public void setMargin(int width) { _margin = width; }
   public void setBorder(int width) { _border = width; }
   public void setPadding(int width) { _padding = width; }

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
   {
      Graphics2D g2 = (Graphics2D) g;
      Color oldColor = g.getColor();
      Stroke oldStroke = g2.getStroke();

      drawMargin(g2, c, x, y, width, height);
      drawBorder(g2, x, y, width, height);
      // drawing the padding is unnecessary because its background
      // color is the same as the component's.
      
      g.setColor(oldColor);
      g2.setStroke(oldStroke);
   }

   private void drawMargin(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      if (_margin == 0) return;
      g2.setStroke(new BasicStroke(_margin));
      Color marginColor = c.getParent().getBackground();
      g2.setColor(marginColor);
      int marginrectwidth = width - _margin;
      int marginrectheight = height - _margin;
      int xpos = x + _margin/2;
      int ypos = y + _margin/2;
      g2.drawRect(xpos, ypos, marginrectwidth, marginrectheight);
   }

   private void drawBorder(Graphics2D g2, int x, int y, int width, int height)
   {
      if (_border == 0) return;
      g2.setStroke(new BasicStroke(_border));
      g2.setColor(_borderColor);
      int borderrectwidth = width - (2*_margin + _border);
      int borderrectheight = height - (2*_margin + _border);
      int xpos = x + _margin + _border/2 ;
      int ypos = y + _margin + _border/2 ;
      g2.drawRect(xpos, ypos, borderrectwidth, borderrectheight);
   }

   /*
   private void drawPadding(Graphics2D g2, Component c, int x, int y, int width, int height)
   {
      if (_padding == 0) return;
      g2.setStroke(new BasicStroke(_padding));
      Color paddingColor = c.getBackground();
      g2.setColor(paddingColor);
      int paddingwidth = width - (2*_margin + 2*_border + _padding);
      int paddingheight = height - (2*_margin + 2*_border + _padding);
      int xpos = x + _margin + _border + _padding/2;
      int ypos = y + _margin + _border + _padding/2;
      g2.drawRect(xpos, ypos, paddingwidth, paddingheight);
   }
   */

   public Insets getBorderInsets(Component c)
   {
      int thickness = _padding + _border + _margin;
      return new Insets(thickness, thickness, thickness, thickness);
   }

   public Insets getBorderInsets(Component c, Insets insets)
   {
      int thickness = _padding + _border + _margin;
      insets.top = insets.left = insets.bottom = insets.right = thickness;
      return insets;
   }

   public boolean isBorderOpaque() { return true; }
}