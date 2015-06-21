package com.u2d.css4swing;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 31, 2007
 * Time: 4:38:27 PM
 */
public enum LineStyle
{
   DASHED(new float[] {5f, 5f}), SOLID(null), DOTTED(new float[] {1f, 5f});
   
   private float[] dashArray;
   LineStyle(float[] dashArray)
   {
      this.dashArray = dashArray;
   }
   public float[] dashArray() { return this.dashArray; }
}
