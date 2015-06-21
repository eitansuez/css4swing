package com.u2d.css4swing.style;

import com.u2d.css4swing.border.CSSBorder;
import com.u2d.css4swing.property.TextAlign;
import com.u2d.css4swing.selector.Selector;
import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.Utils;
import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 1:27:54 PM
 */
public class ComponentStyle
{
   private Paint foreground;
   private Paint background;
   private int cursorType;
   private CSSBorder border;
   private final CSSFont cssfont = new CSSFont();
   private TextAlign.Alignment textAlign;
   private CSSPainter painter;
   
   public ComponentStyle() { }

   private boolean requiresBackgroundPainting()
   {
      return borderSpecified() || (backgroundSpecified() && !(background instanceof Color)) ||
            getPainter().getBgImg() != null;
   }

   public void applyTo(JComponent c)
   {
      if (ignore) return;
      
      if (Utils.isBackgroundPaintable(c) && requiresBackgroundPainting())
      {
         Utils.setBackgroundPainter(c, getPainter());
         c.setOpaque(false);
      }
      else if ( backgroundSpecified() )
      {
         c.setBackground((Color) background);
      }

      if ( foregroundSpecified() )
      {
         c.setForeground((Color) foreground);
      }

      if ( c instanceof JButton )
      {
         // for compatibility with substance look and feel
         JButton b = (JButton) c;
         if (!b.isContentAreaFilled())
         {
            Boolean bprop = (Boolean) b.getClientProperty("substancelaf.buttonpaintnever");
            if (bprop == null || !bprop)
            {
               b.putClientProperty("substancelaf.buttonpaintnever", Boolean.TRUE);
            }
         }
      }
      
      c.setFont(cssfont.awtfont());
      c.setCursor(Cursor.getPredefinedCursor(cursorType));
      if (borderSpecified() && !(c instanceof JViewport))
      {
         c.setBorder(border);
      }
      if (textAlign != null)
      {
         TextAlign.applyTo(c, textAlign);
      }
      
      c.revalidate(); c.repaint();
   }

   private boolean ignore = false;
   public void ignore(boolean value) { ignore = value; }

   public CSSBorder getBorder()
   {
      if (border == null)
      {
         border = new CSSBorder();
      }
      return border;
   }

   public CSSPainter getPainter()
   {
      if (painter == null)
      {
         painter = new CSSPainter();
      }
      return painter;
   }

   public CSSFont getFont() { return cssfont; }

   public void setForeground(Paint foreground) { this.foreground = foreground; }
   public void setBackground(Paint background) { this.background = background; }
   public Paint getBackground() { return this.background; }

   public boolean backgroundSpecified() { return this.background != null; }
   public boolean foregroundSpecified() { return this.foreground != null; }
   public boolean borderSpecified() { return this.border != null; }
   
   public void setCursorType(int cursorType) { this.cursorType = cursorType; }
   public void setTextAlign(TextAlign.Alignment align) { this.textAlign = align; }
   
   // static methods that you wish you could mix into JComponent..
   public static void addClass(JComponent component, String cssClassName)
   {
      Set<String> cssClasses = (Set<String>) component.getClientProperty(Selector.CLASS);
      if (cssClasses == null)
      {
         cssClasses = new HashSet<String>();
      }
      cssClasses.add(cssClassName);
      component.putClientProperty(Selector.CLASS, cssClasses);
      updateStyleFor(component);
   }
   public static void removeClass(JComponent component, String cssClassName)
   {
      Set<String> cssClasses = (Set<String>) component.getClientProperty(Selector.CLASS);
      if (cssClasses == null) return;
      cssClasses.remove(cssClassName);
      component.putClientProperty(Selector.CLASS,  cssClasses);
      updateStyleFor(component);
   }
   public static boolean hasClass(JComponent component, String cssClassName)
   {
      Set<String> cls = (Set<String>) component.getClientProperty(Selector.CLASS);
      if (cls == null) return false;
      return cls.contains(cssClassName);
   }
   public static void setIdent(JComponent component, String cssIdent)
   {
      component.putClientProperty(Selector.IDENT, cssIdent);
   }
   public static boolean isIdent(JComponent component, String cssIdent)
   {
      return cssIdent.equals(component.getClientProperty(Selector.IDENT));
   }
   
   private static void updateStyleFor(JComponent component)
   {
      CSSEngine engine = CSSEngine.getInstance();
      if (engine != null) // i.e. engine already initialized
      {
         engine.stylesheet().applyTo(component);
      }
   }
   
}
