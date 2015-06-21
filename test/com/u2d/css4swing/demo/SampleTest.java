package com.u2d.css4swing.demo;

import com.l2fprod.common.swing.JOutlookBar;
import com.u2d.css4swing.border.BorderStyle;
import com.u2d.css4swing.border.CSSBorder;
import com.u2d.css4swing.property.FontFamily;
import com.u2d.css4swing.selector.Selector;
import com.u2d.css4swing.style.ComponentStyle;
import com.u2d.css4swing.LineStyle;
import com.u2d.css4swing.FieldCaption;
import com.u2d.css4swing.CSSEngine;
import com.u2d.css4swing.StyleMenuBar;
import static junit.framework.Assert.*;
import junit.framework.Assert;
import org.jdesktop.swingx.JXPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 11:48:24 AM
 */
@SuppressWarnings("serial")
public class SampleTest extends JXPanel
{
	
   protected void verifyLabel_c_Required()
   {
      JLabel target = _label;
      assertTrue("class=required", ComponentStyle.hasClass(target, "required"));
      assertEquals("id=", null, target.getClientProperty(Selector.IDENT));
      
      assertEquals("orange foreground", Color.decode("0xffA500"), target.getForeground());
      assertEquals("#000088 background", Color.decode("0x000088"), target.getBackground());
      
      Font font = target.getFont();
      assertEquals("bold font", true, font.isBold());
      assertEquals("not italic font", false, font.isItalic());
      assertEquals("20pt font", 20, font.getSize());
      assertEquals("sans-serif font", FontFamily.SANS_SERIF, font.getFamily());
      
      Cursor cursor = target.getCursor();
      assertNotNull(cursor);
      assertEquals("pointer cursor", Cursor.HAND_CURSOR, cursor.getType());
      
      Border border = target.getBorder();
      assertEquals(true, border instanceof CSSBorder);
      CSSBorder cb = (CSSBorder) border;
      BorderStyle borderStyle = cb.getBottomEdge().getBorderStyle();
      assertEquals(Color.YELLOW, borderStyle.getColor());
      assertEquals(2, borderStyle.getThickness());
      Assert.assertEquals(LineStyle.DASHED, borderStyle.getLineStyle());
      
      assertEquals("padding-top: 5", 5, cb.getTopEdge().getPadding());
      assertEquals("padding-right: 20", 20, cb.getRightEdge().getPadding());
      assertEquals("padding-bottom: 5", 5, cb.getBottomEdge().getPadding());
      assertEquals("padding-top: 20", 20, cb.getLeftEdge().getPadding());
      
      assertEquals("text-align: left", SwingConstants.RIGHT, target.getHorizontalAlignment());
   }
   
   protected void verifyLabel_id_theone()
   {
      JLabel target = _lbl2;
      assertEquals("class=", null, target.getClientProperty(Selector.CLASS));
      assertEquals("id=theone", "theone", target.getClientProperty(Selector.IDENT));
      
      assertEquals("white foreground", Color.decode("0xffffff"), target.getForeground());
      assertEquals("blue background", Color.decode("0x0000ff"), target.getBackground());
      
      Font font = target.getFont();
      assertEquals("bold font", true, font.isBold());
      assertEquals("not italic font", false, font.isItalic());
      assertEquals("16pt font", 16, font.getSize());
      assertEquals("sans-serif font", FontFamily.SANS_SERIF, font.getFamily());
      
      Cursor cursor = target.getCursor();
      assertNotNull(cursor);
      assertEquals("pointer cursor", Cursor.HAND_CURSOR, cursor.getType());
      
      Border border = target.getBorder();
      assertEquals(true, border instanceof CSSBorder);
      CSSBorder cb = (CSSBorder) border;
      BorderStyle borderStyle = cb.getBottomEdge().getBorderStyle();
      assertEquals(Color.YELLOW, borderStyle.getColor());
      assertEquals(2, borderStyle.getThickness());
      assertEquals(LineStyle.DASHED, borderStyle.getLineStyle());
      
      assertEquals("padding-top: 5", 5, cb.getTopEdge().getPadding());
      assertEquals("padding-right: 5", 5, cb.getRightEdge().getPadding());
      assertEquals("padding-bottom: 5", 5, cb.getBottomEdge().getPadding());
      assertEquals("padding-top: 5", 5, cb.getLeftEdge().getPadding());
      
      assertEquals("text-align: left", SwingConstants.RIGHT, target.getHorizontalAlignment());      
   }

   protected void verifyCaption()
   {
      JLabel target = _caption;
      verifyDefaultLabel(target);
   }

   protected void verifyButton()
   {
      JButton target = _button2;
      assertEquals("class=", null, target.getClientProperty(Selector.CLASS));
      assertEquals("id=", null, target.getClientProperty(Selector.IDENT));
      
      assertEquals("default foreground", 
	    UIManager.getDefaults().getColor("Button.foreground"), target.getForeground());
      assertEquals("default background", 
	    UIManager.getDefaults().getColor("Button.background"), target.getBackground());

      Cursor cursor = target.getCursor();
      assertNotNull(cursor);
      assertEquals("default cursor", Cursor.DEFAULT_CURSOR, cursor.getType());

      Font font = target.getFont();
      assertEquals("16pt font", 16, font.getSize());
   }
   
   protected void verifyButton_c_required()
   {
      JButton target = _button;
      assertEquals("class=required", Collections.singleton("required"), target.getClientProperty(Selector.CLASS));
      assertEquals("id=", null, target.getClientProperty(Selector.IDENT));
      
      assertEquals("green foreground", Color.decode("0x008000"), target.getForeground());
      assertEquals("#abc background", Color.decode("0xaabbcc"), target.getBackground());
   
      Cursor cursor = target.getCursor();
      assertNotNull(cursor);
      assertEquals("default cursor", Cursor.WAIT_CURSOR, cursor.getType());
   
      
      Font expectedFont = new Font(FontFamily.SANS_SERIF, Font.BOLD, 24);
      String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      for (String s : availableFontFamilyNames) {
         if ("Comic Sans MS".equals(s)) {
            expectedFont = new Font("Comic Sans MS", Font.BOLD, 24);
         }
      }
   
      Font font = target.getFont();
      assertEquals("expected font", expectedFont, font);
   }

   protected void verifyBar()
   {
      JOutlookBar target = _bar;
      assertEquals("class=left-sidebar", Collections.singleton("left-sidebar"), target.getClientProperty(Selector.CLASS));
      assertEquals("id=", null, target.getClientProperty(Selector.IDENT));
      
      assertEquals("default background", Color.WHITE, target.getBackground());
      assertEquals("olive foreground", Color.decode("0x808000"), target.getForeground());

      Border border = target.getBorder();
      assertEquals(true, border instanceof CSSBorder);
      CSSBorder cb = (CSSBorder) border;

      assertEquals("margin-top: 0", 0, cb.getTopEdge().getMargin());
      assertEquals("margin-right: 0", 0, cb.getRightEdge().getMargin());
      assertEquals("margin-bottom: 0", 0, cb.getBottomEdge().getMargin());
      assertEquals("margin-top: 0", 0, cb.getLeftEdge().getMargin());
      
      Color expectedBorderColor = Color.decode("0x008000"); // green
      assertEquals(expectedBorderColor, cb.getTopEdge().getBorderStyle().getColor());
      assertEquals(expectedBorderColor, cb.getRightEdge().getBorderStyle().getColor());
      assertEquals(expectedBorderColor, cb.getBottomEdge().getBorderStyle().getColor());
      assertEquals(expectedBorderColor, cb.getLeftEdge().getBorderStyle().getColor());
      
      int expectedBorderSize = 2;
      assertEquals(expectedBorderSize, cb.getTopEdge().getBorderStyle().getThickness());
      assertEquals(expectedBorderSize, cb.getRightEdge().getBorderStyle().getThickness());
      assertEquals(expectedBorderSize, cb.getBottomEdge().getBorderStyle().getThickness());
      assertEquals(expectedBorderSize, cb.getLeftEdge().getBorderStyle().getThickness());
      
      LineStyle expectedBorderStyle = LineStyle.SOLID;
      assertEquals(expectedBorderStyle, cb.getTopEdge().getBorderStyle().getLineStyle());
      assertEquals(expectedBorderStyle, cb.getRightEdge().getBorderStyle().getLineStyle());
      assertEquals(expectedBorderStyle, cb.getBottomEdge().getBorderStyle().getLineStyle());
      assertEquals(expectedBorderStyle, cb.getLeftEdge().getBorderStyle().getLineStyle());
   }

   protected void verifyDefaultLabel(JLabel target) throws NumberFormatException
   {
      assertEquals("class=", null, target.getClientProperty(Selector.CLASS));
      assertEquals("id=", null, target.getClientProperty(Selector.IDENT));

      assertEquals("orange foreground", Color.decode("0xffA500"), target.getForeground());
      assertEquals("#000088 background", Color.decode("0x000088"), target.getBackground());

      Font font = target.getFont();
      assertEquals("bold font", true, font.isBold());
      assertEquals("not italic font", false, font.isItalic());
      assertEquals("20pt font", 20, font.getSize());
      assertEquals("sans-serif font", FontFamily.SANS_SERIF, font.getFamily());

      Cursor cursor = target.getCursor();
      assertNotNull(cursor);
      assertEquals("pointer cursor", Cursor.HAND_CURSOR, cursor.getType());

      Border border = target.getBorder();
      assertEquals(true, border instanceof CSSBorder);
      CSSBorder cb = (CSSBorder) border;
      BorderStyle borderStyle = cb.getBottomEdge().getBorderStyle();
      assertEquals(Color.YELLOW, borderStyle.getColor());
      assertEquals(2, borderStyle.getThickness());
      assertEquals(LineStyle.DASHED, borderStyle.getLineStyle());

      assertEquals("padding-top: 5", 5, cb.getTopEdge().getPadding());
      assertEquals("padding-right: 20", 20, cb.getRightEdge().getPadding());
      assertEquals("padding-bottom: 5", 5, cb.getBottomEdge().getPadding());
      assertEquals("padding-top: 20", 20, cb.getLeftEdge().getPadding());

      assertEquals("text-align: left", SwingConstants.RIGHT, target.getHorizontalAlignment());
   }

   protected void verifyBarLabels()
   {
      for (int i = 0; i < _bar.getTabCount(); i++)
      {
         JLabel target = (JLabel) _bar.getComponentAt(i);
         verifyDefaultLabel(target);
      }
   }

   public SampleTest()
   {
      ComponentStyle.setIdent(this, "main-pnl");
      setLayout(new BorderLayout());
      
      JPanel body = new JPanel(new FlowLayout(FlowLayout.LEFT));
      ComponentStyle.setIdent(body, "body-pnl");
      
      _label = new JLabel("Eitan is testing..");
      ComponentStyle.addClass(_label, "required");
      body.add(_label);
      
      _lbl2 = new JLabel("Ident-Designated");
      ComponentStyle.setIdent(_lbl2, "theone");
      body.add(_lbl2);
      
      _caption = new FieldCaption("A label subclass..");
      body.add(_caption);
      
      _button = new JButton("Button 1 (required)..");
      ComponentStyle.addClass(_button, "required");
      body.add(_button);

      _button2 = new JButton("Button 2 (not required)..");
      body.add(_button2);

      _bar = new JOutlookBar();
      ComponentStyle.addClass(_bar, "left-sidebar");
      _bar.addTab("Hello", new JLabel("Hi"));
      _bar.addTab("Bye", new JLabel("Bye"));
      add(_bar, BorderLayout.LINE_START);
      
      add(body, BorderLayout.CENTER);
   }
   
   private JOutlookBar _bar;
   private JLabel _label, _lbl2;
   private FieldCaption _caption;
   private JButton _button, _button2;
   
   public static void main(String[] args)
   {
      CSSEngine.initialize("sample.css");

      final SampleTest st = new SampleTest();
//      st.verify();
      
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            JFrame f = new JFrame("Sample Test");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setJMenuBar(new StyleMenuBar(f));
            f.getJMenuBar().getMenu(0).add(exitItem());
            f.setContentPane(st);
            f.setBounds(100,100,500,500);
            f.setVisible(true);
         }
      });
   }
   
   public static JMenuItem exitItem()
   {
      JMenuItem item = new JMenuItem("Exit");
      item.setMnemonic('x');
      item.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) { System.exit(0); }
      });
      return item;
   }

   protected void verify()
   {
      verifyCascadeBar();
      verifyButton_c_required();
      verifyButton();
      verifyCaption();
      verifyLabel_id_theone();
      verifyLabel_c_Required();
   }

   private void verifyCascadeBar()
   {
      verifyBar();
      verifyBarLabels();
   }

}
