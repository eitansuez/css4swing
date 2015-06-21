package com.u2d.css4swing.style;

import com.u2d.css4swing.property.BackgroundImage;
import com.u2d.css4swing.Stylesheet;
import com.u2d.css4swing.CSSEngine;
import org.jdesktop.swingx.painter.AbstractPainter;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jul 24, 2007
 * Time: 1:28:11 PM
 */
public class CSSPainter
      extends AbstractPainter<JComponent>
{
   private float _alpha = 1.0f;
   private Dimension topLeft, topRight, bottomLeft, bottomRight;
   private int topMargin, rightMargin, bottomMargin, leftMargin;
   private BackgroundImage bgImg;

   // for convenience:
   Stylesheet stylesheet;

   public CSSPainter()
   {
      super();
      setCacheable(false);
      setAntialiasing(true);
      stylesheet = CSSEngine.getInstance().stylesheet();
   }

   public boolean isBackgroundPaintingNeeded()
   {
      return _alpha < 1.0f || (topLeft != null) || (topRight != null)
            || (bottomLeft != null) || (bottomRight != null) || (bgImg != null);
   }

   protected void doPaint(Graphics2D g2, JComponent comp, int width, int height)
   {
      Composite composite = g2.getComposite();
      Paint paint = g2.getPaint();
      g2.translate(leftMargin, topMargin);

      if (composite instanceof AlphaComposite)
      {
         _alpha *= ((AlphaComposite) composite).getAlpha();
      }
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _alpha));

      ComponentStyle style = stylesheet.styleFor(comp);
      Paint bgPaint = style.getBackground();
      if (bgPaint == null) bgPaint = comp.getBackground();
      g2.setPaint(bgPaint);

      width = comp.getWidth() - leftMargin - rightMargin;
      height = comp.getHeight() - topMargin - bottomMargin;

      Rectangle2D rect = new Rectangle2D.Double(0, 0, width-1, height-1);
      Area area = new Area(rect);

      if(topLeft != null)
      {
         Rectangle2D boundingRect = new Rectangle2D.Double(0, 0, topLeft.width, topLeft.height);
         Area boundingArea = new Area(boundingRect);
         Arc2D pie = new Arc2D.Double(0, 0, 2*topLeft.width, 2*topLeft.height, 90, 90, Arc2D.PIE);
         boundingArea.subtract(new Area(pie));
         area.subtract(boundingArea);
      }

      if(topRight != null)
      {
         Rectangle2D boundingRect = new Rectangle2D.Double(width - topRight.width, 0,
                                                           topRight.width, topRight.height);
         Area boundingArea = new Area(boundingRect);
         Arc2D pie = new Arc2D.Double(width-2*topRight.width, 0,
                                      2*topRight.width, 2*topRight.height, 0, 90, Arc2D.PIE);
         boundingArea.subtract(new Area(pie));
         area.subtract(boundingArea);
      }

      if(bottomLeft != null)
      {
         Rectangle2D boundingRect = new Rectangle2D.Double(0, height-bottomLeft.height,
                                                           bottomLeft.width, bottomLeft.height);
         Area boundingArea = new Area(boundingRect);
         Arc2D pie = new Arc2D.Double(0, height - 2*bottomLeft.height,
                                      2*bottomLeft.width, 2*bottomLeft.height, 180, 90, Arc2D.PIE);
         boundingArea.subtract(new Area(pie));
         area.subtract(boundingArea);
      }

      if(bottomRight != null)
      {
         Rectangle2D boundingRect = new Rectangle2D.Double(width-bottomRight.width,
                                                           height-bottomRight.height,
                                                           bottomRight.width, bottomRight.height);
         Area boundingArea = new Area(boundingRect);
         Arc2D pie = new Arc2D.Double(width - 2*bottomRight.width, height - 2*bottomRight.height,
                                      2*bottomRight.width, 2*bottomRight.height, 270, 90, Arc2D.PIE);
         boundingArea.subtract(new Area(pie));
         area.subtract(boundingArea);
      }

      g2.fill(area);
      if (bgImg != null && bgImg.isSet())
      {
         bgImg.draw(g2, comp);
      }

      g2.translate(-leftMargin, -topMargin);
      g2.setPaint(paint);
      g2.setComposite(composite);
   }

   public BackgroundImage getBgImg() { return bgImg; }
   public void setBgImg(BackgroundImage img) { bgImg = img; }
   
   public void setOpacity(float alpha) { _alpha = alpha; }
   public float getOpacity() { return _alpha; }

   public void setTopLeftBorderRadius(Dimension dim) { topLeft = dim; }
   public void setTopRightBorderRadius(Dimension dim) { topRight = dim; }
   public void setBottomLeftBorderRadius(Dimension dim) { bottomLeft = dim; }
   public void setBottomRightBorderRadius(Dimension dim) { bottomRight = dim; }
   
   public void setTopMargin(int value) { topMargin = value; }
   public void setRightMargin(int value) { rightMargin = value; }
   public void setBottomMargin(int value) { bottomMargin = value; }
   public void setLeftMargin(int value) { leftMargin = value; }

}
