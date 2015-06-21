package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.Measure;
import com.u2d.css4swing.style.ComponentStyle;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jun 1, 2007
 * Time: 11:01:38 AM
 * 
 * Todo:  break this down into four properties, one for each corner
 */
public class BorderRadius implements Declaration
{
   protected Measure horizontal, vertical;
   
   public BorderRadius(Measure horizontal, Measure vertical)
   {
      setValue(horizontal, vertical);
   }
   public BorderRadius(Measure value)
   {
      setValue(value);
   }
   public BorderRadius()
   {
      setValue(new Measure(0, "px"));
   }
   
   public void setValue(BorderRadius radius)
   {
      setValue(radius.horizontalValue(), radius.verticalValue());
   }
   
   public Measure horizontalValue() { return horizontal; }
   public Measure verticalValue() { return vertical; }
   
   
   public void setValue(Measure horiz, Measure vert)
   {
      horizontal = horiz;
      vertical = vert;
   }
   public void setValue(Measure value)
   {
      horizontal = value;
      vertical = new Measure(value);
   }
   
   public Dimension asDim()
   {
      return new Dimension(horizontal.pixels(), vertical.pixels());
   }
   
   public void applyTo(ComponentStyle style)
   {
      Dimension dim = asDim();
      style.getBorder().getTopEdge().setBorderRadius(dim);
      style.getBorder().getRightEdge().setBorderRadius(dim);
      style.getBorder().getBottomEdge().setBorderRadius(dim);
      style.getBorder().getLeftEdge().setBorderRadius(dim);
      
      style.getPainter().setTopLeftBorderRadius(dim);
      style.getPainter().setTopRightBorderRadius(dim);
      style.getPainter().setBottomLeftBorderRadius(dim);
      style.getPainter().setBottomRightBorderRadius(dim);
   }

   public boolean inherited() { return false; }
}
