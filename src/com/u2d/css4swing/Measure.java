package com.u2d.css4swing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 10:50:46 AM
 */
public class Measure
{
   private int _value;
   private String _units;
   
   public Measure(Measure prototype)
   {
      this(prototype.value(), prototype.units());
   }
   public Measure(String text)
   {
      Pattern pattern = Pattern.compile("^(\\d+)(px|pt|ex|em)$");
      Matcher matcher = pattern.matcher(text);
      
      if (matcher.matches())
      {
         String sizeText = matcher.group(1);
         _value = Integer.parseInt(sizeText);
         _units = matcher.group(2);
      }
      else
      {
         throw new RuntimeException("Invalid measure: "+text);
      }
   }
   public Measure(int value, String units)
   {
      _value = value;
      _units = units;
   }
   
   public int value() { return _value; }
   public String units() { return _units; }
   
   public int pixels()
   {
      return value(); // for now..
   }
   
}
