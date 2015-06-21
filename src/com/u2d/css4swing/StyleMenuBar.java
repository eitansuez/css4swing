package com.u2d.css4swing;

import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: May 22, 2007
 * Time: 10:37:56 AM
 */
public class StyleMenuBar extends JMenuBar
{
   RootPaneContainer _owner;
   
   public StyleMenuBar(RootPaneContainer owner)
   {
      super();
      _owner = owner;
      JMenu fileMenu = new JMenu("File");
      fileMenu.setMnemonic('f');
      
      JMenuItem mi = new JMenuItem("Edit Style Sheet");
      mi.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JDialog dlg = new StyleDialog();
            if (_owner instanceof JFrame)
            {
               JFrame frame = (JFrame) _owner;
               Point dlgloc = new Point(frame.getLocation().x + frame.getSize().width + 2, 
                                          frame.getLocation().y);
               dlg.setLocation(dlgloc);
            }
            dlg.pack();
            dlg.setVisible(true);
         }
      });
      fileMenu.add(mi);
      add(fileMenu);
      
   }
   
   class StyleDialog extends JDialog
   {
      JTextArea ta;
      
      public StyleDialog()
      {
         super();
         setTitle("Stylesheet");
         JXPanel contentPane = new JXPanel(new BorderLayout());
         setContentPane(contentPane);
         ta = new JTextArea(20, 50);
         ta.setText(initialText());
         ta.setCaretPosition(0);
         configureTextAreaFocusTraversal();
         
         JScrollPane sp = new JScrollPane(ta);
         contentPane.add(sp, BorderLayout.CENTER);
         
         
         JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.TRAILING));
         JButton applyBtn = new JButton("Apply");
         applyBtn.setMnemonic('a');
         applyBtn.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               new Thread()
               {
                  public void run()
                  {
                     String text = ta.getText();
                     InputStream is = new ByteArrayInputStream(text.getBytes());
                     CSSEngine.getInstance().restyle(is, _owner.getRootPane());
                     
                     // put focus back on text area for additional changes..
                     SwingUtilities.invokeLater(new Runnable()
                     {
                        public void run() { ta.requestFocus(); }
                     });
                        
                  }
               }.start();
            }
         });
         btnPnl.add(applyBtn);
         contentPane.add(btnPnl, BorderLayout.PAGE_END);
         
      }

      private void configureTextAreaFocusTraversal()
      {
         java.util.Set<KeyStroke> forwardTab = new java.util.HashSet<KeyStroke>();
         forwardTab.add(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_TAB, 0));
         ta.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardTab);
      
         java.util.Set<KeyStroke> backTab = new java.util.HashSet<KeyStroke>();
         backTab.add(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_TAB, java.awt.event.InputEvent.SHIFT_MASK));
         ta.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backTab);
      }

      private String initialText()
      {
         try
         {
            String stylesheetRef = CSSEngine.getInstance().getSourceRef();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(stylesheetRef);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuffer text = new StringBuffer();
            String line;
            while ( (line = br.readLine()) != null)
            {
               text.append(line).append("\n");
            }
            stream.close();
            return text.toString();
         }
         catch (IOException ex)
         {
            System.err.println("Failed to read styles.css..");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
         }
         return "";
      }
   }
}
