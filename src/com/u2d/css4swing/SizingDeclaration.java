package com.u2d.css4swing;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 28, 2007
 * Time: 10:50:10 AM
 */
public abstract class SizingDeclaration implements Declaration 
{
   protected Measure _measure;
   
   public void setValue(Measure measure)
   {
      _measure = measure;
   }
}
