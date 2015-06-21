package com.u2d.css4swing;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Mar 27, 2007
 * Time: 1:36:19 PM
 */
public class UnderstandSizing extends JPanel
{
   InnerPanel ip;
   public UnderstandSizing()
   {
      setOpaque(true);
      setBackground(Color.red);
      
      JLabel label = new JLabel("Testing");
      label.setFont(label.getFont().deriveFont(16f));
      add(label);
      
      ip = new InnerPanel();
      add(ip);
      label.setBorder(new MyCSSBorder(3, 3, 3, Color.black));

      JButton btn = new JButton("What's my preferred size?");
      add(btn);
      btn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane.showMessageDialog(UnderstandSizing.this,
                                          UnderstandSizing.this.getPreferredSize());
         }
      });
   }
   
   public JPanel controls() { return ip.controls(); }

   class InnerPanel extends JPanel
   {
      MyCSSBorder border;
      
      InnerPanel()
      {
         setOpaque(true);
         setBackground(Color.green);
         
         JLabel label = new JLabel("learning about component sizing..");
         label.setFont(label.getFont().deriveFont(16f));
         add(label);

         border = new MyCSSBorder(5, 5, 5, Color.blue);
         setBorder(border);
      }
      
      public JPanel controls()
      {
         JPanel p = new JPanel();
         JPanel marginSlider = slider("M", MyCSSBorder.MARGIN);
         JPanel borderSlider = slider("B", MyCSSBorder.BORDER);
         JPanel paddingSlider = slider("P", MyCSSBorder.PADDING);
         p.add(marginSlider);
         p.add(borderSlider);
         p.add(paddingSlider);
         return p;
      }
      private JPanel slider(String labelText, final int which)
      {
         JPanel p = new JPanel(new BorderLayout());
         p.add(new JLabel(labelText), BorderLayout.PAGE_START);

         JSlider slider = new JSlider(JSlider.VERTICAL, 0, 25, 5);
         slider.setMajorTickSpacing(5);
         slider.setMinorTickSpacing(1);
         slider.setPaintLabels(true);
         slider.setPaintTicks(true);
         slider.setPaintTrack(true);
         slider.setSnapToTicks(true);
         
         slider.addChangeListener(new ChangeListener()
         {
            public void stateChanged(ChangeEvent e)
            {
               JSlider slider = (JSlider) e.getSource();
               int width = slider.getValue();
               switch (which)
               {
                  case MyCSSBorder.MARGIN:
                  {
                     border.setMargin(width);
                     revalidate(); repaint();
                     break;
                  }
                  case MyCSSBorder.PADDING:
                  {
                     border.setPadding(width);
                     revalidate(); repaint();
                     break;
                  }
                  case MyCSSBorder.BORDER:
                  {
                     border.setBorder(width);
                     revalidate(); repaint();
                     break;
                  }
                  default:
                  {
                     System.out.println("hmm..which is "+which);
                  }
               }
            }
         });
         p.add(slider, BorderLayout.CENTER);
         return p;
      }
   }

   public static void main(String[] args)
   {
      JFrame f = new JFrame();
      JPanel pane = new JPanel();
      f.setContentPane(pane);
      pane.setLayout(new BorderLayout());
      UnderstandSizing us = new UnderstandSizing();
      pane.add(us, BorderLayout.CENTER);
      pane.add(us.controls(), BorderLayout.LINE_END);
      
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocation(100,100);
      f.pack();
      f.setVisible(true);
   }
}