package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;
import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 3:58:08 PM
 */
public class TextAlign implements Declaration
{
   public enum Alignment { LEFT, CENTER, RIGHT; }
   static int[] ALIGNMENTS = new int[] {SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT};
   
   private Alignment _value = Alignment.LEFT;
   
   public TextAlign()
   {
   }
   public TextAlign(Alignment value)
   {
      setValue(value);
   }
   
   public void setValue(Alignment value)
   {
      _value = value;
   }

   public void applyTo(ComponentStyle style)
   {
      style.setTextAlign(_value);
   }
   
   public static void applyTo(JComponent c, Alignment value)
   {
      if (c instanceof JLabel)
      {
         ((JLabel) c).setHorizontalAlignment(ALIGNMENTS[value.ordinal()]);
      }
      else if (c instanceof AbstractButton)
      {
         ((AbstractButton) c).setHorizontalAlignment(ALIGNMENTS[value.ordinal()]);
      }
   }

   public boolean inherited() { return true; }

}
