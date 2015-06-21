package com.u2d.css4swing.border;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Feb 21, 2008
 * Time: 1:01:10 PM
 *
 * Not used yet.  This is how I'd like componentui to behave to container painting can show through
 * in margin area of child component.
 */
public abstract class CSSComponentUI
      extends ComponentUI
{
   /*
   anything that extends ComponentUI should be made to extend CSSComponentUI
    */
   public void update(Graphics g, JComponent c)
   {
      if (c.getBorder() == null || !(c.getBorder() instanceof CSSBorder))
      {
         super.update(g, c);
         return;
      }

      if (c.isOpaque())
      {
         g.setColor(c.getBackground());
         CSSBorder border = (CSSBorder) c.getBorder();
         
         int left = border.getLeftEdge().margin;
         int right = border.getRightEdge().margin;
         int top = border.getTopEdge().margin;
         int bottom = border.getBottomEdge().margin;

         g.fillRect(left + 1, top + 1, 
                    c.getWidth() - (left + right), 
                    c.getHeight() - (top + bottom));
      }

      paint(g, c);
   }
}
