package com.u2d.css4swing.property;

import com.u2d.css4swing.Declaration;
import com.u2d.css4swing.style.ComponentStyle;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Aug 30, 2007
 * Time: 4:40:57 PM
 */
public class BackgroundImage implements Declaration
{
   private Image _image;
   private PlacementMethod _placementMethod = PlacementMethod.STRETCHED;
   
   enum PlacementMethod { TILED, CENTERED, STRETCHED }
   
   public BackgroundImage() {}
   
   public void setValue(Image image)
   {
      _image = image;
   }
   public boolean isSet() { return _image != null; }
   public void setPlacementMethod(String method)
   {
      _placementMethod = PlacementMethod.valueOf(method.toUpperCase());
   }

   public void applyTo(ComponentStyle style)
   {
      style.getPainter().setBgImg(this);
   }
   
   public boolean inherited() { return false; }
   
   public void draw(Graphics2D g2, Component comp)
   {
      if (!isSet()) return;

      Dimension compSize = comp.getSize();
      Dimension imgSize = new Dimension(_image.getWidth(null), _image.getHeight(null));
      
      // stretched/tiled/centered
      if (_placementMethod == PlacementMethod.STRETCHED)
      {
         g2.drawImage(_image, 0, 0, compSize.width, compSize.height, 0, 0, imgSize.width, imgSize.height, null);
      }
      else if (_placementMethod == PlacementMethod.TILED)
      {
         int adjust = (compSize.width % imgSize.width) == 0 ? 0 : 1;
         int numCols = (int) (compSize.width / imgSize.width) + adjust;
         int numRows = (int) (compSize.height / imgSize.height) + adjust;
         for (int c=0; c<numCols; c++)
         {
            int x = c * imgSize.width;
            for (int r=0; r<numRows; r++)
            {
               int y = r * imgSize.height;
               g2.drawImage(_image, x, y, null);
            }
         }
      }
      else if (_placementMethod == PlacementMethod.CENTERED)
      {
         int x = (int) (compSize.width - imgSize.width) / 2;
         int y = (int) (compSize.height - imgSize.height) / 2;
         g2.drawImage(_image, x, y, null);
      }
   }
}

