package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 4:13:59 PM
 */
public class Cursor implements Declaration
{
   private int _type = java.awt.Cursor.DEFAULT_CURSOR;
   
   public Cursor() { }

   public Cursor(int type)
   {
      setValue(type);
   }
   
   /**
    * @param type the cursor type:  abides by integer constant definitions in java.awt.Cursor
    */
   public void setValue(int type) { _type = type; }


   public void applyTo(ComponentStyle style)
   {
      style.setCursorType(_type);
   }

   public boolean inherited() { return true; }
}
