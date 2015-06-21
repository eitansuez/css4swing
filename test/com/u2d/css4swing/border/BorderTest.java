package com.u2d.css4swing.border;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 4:52:03 PM
 */
public class BorderTest extends JPanel
{
   public BorderTest()
   {
      setLayout(new FlowLayout());
      JPanel panel = new JPanel();

//      BorderStyle blackDashed = new BorderStyle(1, LineStyle.SOLID, Color.blue);
//      BorderStyle redSolid = new BorderStyle(1, LineStyle.SOLID, Color.red);

//      Edge topEdge = new TopEdge(blackDashed, 0, 0);
//      Edge rightEdge = new RightEdge(blackDashed, 0, 0);
//      Edge bottomEdge = new BottomEdge(blackDashed, 0, 0);
//      Edge leftEdge = new LeftEdge(blackDashed, 0, 0);
//      
//      panel.setBorder(new CSSBorder(topEdge, rightEdge, bottomEdge, leftEdge, 
//                                    true, true, true, true));
      
      JLabel label = new JLabel("Hello World");
      
//      topEdge = new TopEdge(redSolid, 10, 0);
//      rightEdge = new RightEdge(redSolid, 10, 0);
//      bottomEdge = new BottomEdge(redSolid, 10, 0);
//      leftEdge = new LeftEdge(redSolid, 10, 0);
//      
//      label.setBorder(new CSSBorder(topEdge, rightEdge, bottomEdge, leftEdge, 
//                                    true, true, true, true));
      
      panel.add(label);
      
      add(panel, BorderLayout.CENTER);
   }
   
   public static void main(String[] args)
   {
      JFrame f = new JFrame("Border Test");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      f.setContentPane(new BorderTest());

      f.setBounds(100,100,300,300);
      f.setVisible(true);
   }
}
