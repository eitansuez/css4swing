package com.u2d.css4swing;

import com.u2d.css4swing.style.ComponentStyle;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 23, 2007
 * Time: 2:45:30 PM
 */
public interface Declaration
{
   public void applyTo(ComponentStyle style);
   public boolean inherited();
}
