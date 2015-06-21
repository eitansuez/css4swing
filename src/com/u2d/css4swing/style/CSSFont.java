package com.u2d.css4swing.style;

import com.u2d.css4swing.property.FontFamily;
import com.u2d.css4swing.property.FontSize;
import com.u2d.css4swing.property.FontStyle;
import com.u2d.css4swing.property.FontWeight;
import java.awt.Font;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 1:28:00 PM
 */
public class CSSFont
{
   private FontFamily family = new FontFamily();
   private FontSize size = new FontSize();
   private FontStyle style = new FontStyle();
   private FontWeight weight = new FontWeight();
   
   public CSSFont() {}
   public CSSFont(FontFamily f, FontSize s, FontStyle st, FontWeight fw)
   {
      setFamily(f); setSize(s); setStyle(st); setWeight(fw);
   }
   
   public Font awtfont()
   {
      int awtStyle = style.getValue() | weight.getValue();
      return new Font(family.familyName(), awtStyle, size.pointSize());
   }

   public void setFamily(FontFamily family) { this.family = family; }
   public void setSize(FontSize size) { this.size = size; }
   public void setStyle(FontStyle style) { this.style = style; }
   public void setWeight(FontWeight weight) { this.weight = weight; }
}
