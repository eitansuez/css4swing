package com.u2d.css4swing;

import com.u2d.cssparser.CSSLexer;
import com.u2d.cssparser.CSSParser;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 30, 2007
 * Time: 11:49:39 AM
 */
public class CSSEngine implements AWTEventListener
{
   private Stylesheet _baseStylesheet;
   private Stylesheet _stylesheet = new Stylesheet();
   private String _resourceRef;
   
   private static CSSEngine _instance = null;
   public static final String DEFAULT_STYLESHEET_NAME = "styles.css";
   
   public static void initialize(InputStream is)
   {
      if (_instance == null)
      {
         _instance = new CSSEngine(is);
      }
   }
   public static void initialize(String resourceRef)
   {
      if (_instance == null)
      {
         _instance = new CSSEngine(resourceRef);
      }
   }
   public static void initialize()
   {
      if (_instance == null)
      {
         _instance = new CSSEngine();
      }
   }
   
   private CSSEngine()
   {
      this(DEFAULT_STYLESHEET_NAME);
   }
   private CSSEngine(String resourceRef)
   {
      _resourceRef = resourceRef;
      init(stream4resource(_resourceRef));
   }
   private CSSEngine(InputStream is)
   {
      init(is);
   }
   
   private InputStream stream4resource(String resourceRef)
   {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      return loader.getResourceAsStream(resourceRef);
   }
   
   private void init(InputStream is)
   {
      setupHook();
      
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      _baseStylesheet = parseStylesheet(loader.getResourceAsStream("defaults.css"));
      
      if (is != null)
      {
         _stylesheet = parseStylesheet(is);
      }
   }
   private Stylesheet parseStylesheet(InputStream is)
   {
      try
      {
         ANTLRInputStream input = new ANTLRInputStream(is);
         CSSLexer lexer = new CSSLexer(input);
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         CSSParser parser = new CSSParser(tokens);
         Stylesheet stylesheet = parser.stylesheet();
         stylesheet.postParse();
         return stylesheet;
      }
      catch (IOException ex)
      {
         System.err.println("IO Exception: "+ex.getMessage());
         ex.printStackTrace();
      }
      catch (org.antlr.runtime.RecognitionException ex2)
      {
         System.err.println("Recognition Exception: "+ex2.getMessage());
         ex2.printStackTrace();
      }
      return null;
   }
   
   public void restyle(JComponent rootComponent)
   {
      restyle(stream4resource(DEFAULT_STYLESHEET_NAME), rootComponent);
   }
   public void restyle(InputStream is, JComponent rootComponent)
   {
      _stylesheet = parseStylesheet(is);
      _stylesheet = _baseStylesheet.merge(_stylesheet);
      style(rootComponent, _stylesheet);
   }
   
   private void style(Container rootComponent, Stylesheet stylesheet)
   {
      Set<JComponent> components = allComponents(rootComponent);
      for (JComponent c : components)
      {
         stylesheet.applyTo(c);
      }
   }
   
   private Set<JComponent> allComponents(Container rootComponent)
   {
      Set<JComponent> set = new HashSet<JComponent>();
      if (rootComponent instanceof JComponent)
      {
         set.add((JComponent) rootComponent);
      }
      for (int i = 0; i<rootComponent.getComponentCount(); i++)
      {
         Component c = rootComponent.getComponent(i);
         if (c instanceof JComponent)
         {
            JComponent jc = (JComponent) c;
            set.addAll(allComponents(jc));
         }
      }
      return set;
   }
   
   private void setupHook()
   {
      Toolkit defaultTk = Toolkit.getDefaultToolkit();
      defaultTk.addAWTEventListener(this, AWTEvent.COMPONENT_EVENT_MASK);
      defaultTk.addAWTEventListener(this, AWTEvent.CONTAINER_EVENT_MASK);
   }

   public void eventDispatched(AWTEvent event)
   {
      if (  (event instanceof ComponentEvent) && 
            (event.getID() == ComponentEvent.COMPONENT_SHOWN) &&
            ((ComponentEvent) event).getComponent() instanceof RootPaneContainer
         )
      {
         RootPaneContainer rootContainer = (RootPaneContainer) ((ComponentEvent) event).getComponent();
         
         style(rootContainer.getRootPane(), _stylesheet);
         
         if (rootContainer instanceof Window)
         {
            ((Window) rootContainer).pack();
         }
         else if (rootContainer instanceof JInternalFrame)
         {
            ((JInternalFrame) rootContainer).pack();
         }
      }
      // this one catches any lazily constructed/added views after its root pane container
      //   has been constructed and shown..
      else if (  (event instanceof ContainerEvent) && 
            (event.getID() == ContainerEvent.COMPONENT_ADDED)
         )
      {
         Container parent = ((ContainerEvent) event).getContainer();
         Component child = ((ContainerEvent) event).getChild();
         if (parent.isVisible() && child instanceof Container)
         {
            style((Container) child, _stylesheet);
         }
         // this one catches cell renderer components on lists, tables, and trees..
         else if (parent instanceof CellRendererPane)
         {
            style(parent, _stylesheet);
         }
      }
      else if ( (event instanceof ContainerEvent) &&
            (event.getID() == ContainerEvent.COMPONENT_REMOVED) )
      {
         Component component = ((ContainerEvent) event).getChild();
         _stylesheet.cleanupStylesFor(component);
      }
      
      
   }
   
   public static CSSEngine getInstance() { return _instance; }
   public Stylesheet stylesheet() { return _stylesheet; }
   public String getSourceRef() { return _resourceRef; }
   
}
