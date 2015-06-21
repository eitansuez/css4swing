package com.u2d.css4swing.border;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 4:10:41 PM
 */
public class CSSBorder extends AbstractBorder
{
//   private final Edge topEdge = new TopEdge(this);
//   private final Edge rightEdge = new RightEdge(this);
//   private final Edge bottomEdge = new BottomEdge(this);
//   private final Edge leftEdge = new LeftEdge(this);
   private final Edge topEdge = new TopEdgeStroke(this);
   private final Edge rightEdge = new RightEdgeStroke(this);
   private final Edge bottomEdge = new BottomEdgeStroke(this);
   private final Edge leftEdge = new LeftEdgeStroke(this);
   
   /* have yet to implement these border styles:
     BevelBorder LOWERED - inset
     BevelBorder RAISED - outset
     EtchedBorder LOWERED - groove 
     EtchedBorder RAISED - ridge
     double - can implement yourself.
    */
   public CSSBorder() { }

   public Edge getTopEdge() { return topEdge; }
   public Edge getRightEdge() { return rightEdge; }
   public Edge getBottomEdge() { return bottomEdge; }
   public Edge getLeftEdge() { return leftEdge; }

   public void paintBorder(Component c, Graphics g, 
                           int x, int y, int width, int height)
   {
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      Color originalColor = g2.getColor();
      Composite originalComposite = g2.getComposite();
      Stroke originalStroke = g2.getStroke();
      
      topEdge.paintEdge(g2, c, x, y, width, height);
      rightEdge.paintEdge(g2, c, x, y, width, height);
      bottomEdge.paintEdge(g2, c, x, y, width, height);
      leftEdge.paintEdge(g2, c, x, y, width, height);
      
      g2.setStroke(originalStroke);
      g2.setComposite(originalComposite);
      g2.setColor(originalColor);
   }
   
   public Insets getBorderInsets(Component c)
   {
      return new Insets(topEdge.padding + topEdge.getBorderStyle().getThickness() + topEdge.margin, 
                        leftEdge.padding + leftEdge.getBorderStyle().getThickness() + leftEdge.margin, 
                        bottomEdge.padding + bottomEdge.getBorderStyle().getThickness() + bottomEdge.margin, 
                        rightEdge.padding + rightEdge.getBorderStyle().getThickness() + rightEdge.margin);
   }
   public Insets getBorderInsets(Component c, Insets insets)
   {
      insets.top = topEdge.padding + topEdge.getBorderStyle().getThickness() + topEdge.margin;
      insets.left = leftEdge.padding + leftEdge.getBorderStyle().getThickness() + leftEdge.margin;
      insets.bottom = bottomEdge.padding + bottomEdge.getBorderStyle().getThickness() + bottomEdge.margin;
      insets.right = rightEdge.padding + rightEdge.getBorderStyle().getThickness() + rightEdge.margin;
      return insets;
   }
   
   public boolean isBorderOpaque() { return false; }
   
}