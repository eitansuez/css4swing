package com.u2d.css4swing;

import junit.framework.TestCase;

import javax.swing.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 2:11:34 PM
 */
public class BasicTests
      extends TestCase
{
   public void testRegExp()
   {
      String value = "one.two";
      String[] parts = value.split("\\.");
      assertEquals(parts.length, 2);
      assertEquals("one", parts[0]);
      assertEquals("two", parts[1]);
   }
   
   public void testRegExpMatch()
   {
      String valueText = "12pt";
      Pattern pattern = Pattern.compile("^(\\d+)pt$");
      Matcher matcher = pattern.matcher(valueText);

      if (matcher.matches())
      {
         String sizeText = matcher.group(1);
         assertEquals("12", sizeText);
      }
   }
   
   public void testQualification()
   {
      Class cls = JLabel.class;
      assertEquals("javax.swing.JLabel", cls.getName());
   }
   
   public void testQualificationUtils()
   {
      Class cls = JLabel.class;
      assertEquals("JLabel", Utils.unqualifiedName(cls));
   }
   
   public void testUnquoteString()
   {
      assertEquals("This is a test", Utils.unquote("\"This is a test\""));
   }
}
