package com.u2d.css4swing.border;

import com.u2d.css4swing.LineStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:38:13 PM
 */
public class BorderStyle
{
   private Color color;
   private LineStyle lineStyle;
   private int thickness;
   
   public BorderStyle(int thickness, LineStyle lineStyle, Color color)
   {
      this.color = color;
      this.lineStyle = lineStyle;
      this.thickness = thickness;
   }
   
   public Color getColor() { return color; }
   public void setColor(Color color)
   {
      this.color = color;
   }
   public LineStyle getLineStyle() { return lineStyle; }
   public void setLineStyle(LineStyle lineStyle)
   {
      this.lineStyle = lineStyle;
   }
   public int getThickness() { return thickness; }
   public void setThickness(int thickness)
   {
      this.thickness = thickness;
   }
}
